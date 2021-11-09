package org.Game;

import java.util.ArrayList;
import java.util.Random;

import org.Framework.AppManager;
import org.Framework.CollisionManager;
import org.Framework.GraphicObject;
import org.Framework.IStage;
import org.Framework.R;
import org.Framework.SoundManager;

import android.graphics.Canvas;

public class Stage1 implements IStage {
	
	private Player _player;
	
	ArrayList<Missile> _pmslist;
	ArrayList<Missile> _enemmslist;
	ArrayList<Item> _itemlist;
	ArrayList<Enemy> _enemlist;
	ArrayList<Effect_Explosion> _explist;
	
	long LastRegenEnemy;
	
	Random randItem = new Random();
	Random randEnem = new Random();
	
	private static Stage1 s_instance;

	public static Stage1 getInstance(){
		if(s_instance == null){
			s_instance = new Stage1();
		}
		return s_instance;
	}
	
	private Stage1(){}
	
	@Override
	public void Init() {
		// TODO Auto-generated method stub
		_player = GameState.getInstance().GetPlayer();
		_pmslist = GameState.getInstance()._pmslist;
		_enemlist = GameState.getInstance()._enemlist;
		_enemmslist = GameState.getInstance()._enemmslist;
		_itemlist = GameState.getInstance()._itemlist;
		_explist = GameState.getInstance()._explist;
				
		_enemlist.clear();
		LastRegenEnemy = System.currentTimeMillis();
        SoundManager.getInstance().playMedia(R.raw.gamestart);
	}

	@Override
	public void Destroy() {
		// TODO Auto-generated method stub
		
		for(int i=0; i<_enemlist.size(); i++){
			_explist.add(new Effect_Explosion(_enemlist.get(i).GetX(),_enemlist.get(i).GetY()));
		}		
		_enemlist.clear();
		_player = null;
		_pmslist = null;
		SoundManager.getInstance().releseMedia();
	}
	

	@Override
	public void CreateItem(int x, int y) {
		// TODO Auto-generated method stub
		int rand = randItem.nextInt(100);
		
		if(rand >= 80)
			_itemlist.add(new Item_AddLife(x,y));
		
		else if(rand >=65)
			_itemlist.add(new Item_AddPower(x,y));
		
		else 
			_itemlist.add(new Item_AddScore(x,y));
	    
	}

	@Override
	public void CheckCollision() {
		// TODO Auto-generated method stub
		for (int i = 0; i < _pmslist.size(); ++i){
	         for (int j = 0; j < _enemlist.size(); ++j){
	             if(CollisionManager.CheckBoxToBox(_pmslist.get(i).m_BoundBox, _enemlist.get(j).m_BoundBox)){
	                
	                _pmslist.remove(i);
	                
	                _enemlist.get(j).Damage(_player.GetPower());
	                if(_enemlist.get(j).GetHP() <= 0)
	                {
	                   _explist.add(new Effect_Explosion(_enemlist.get(j).GetX(),_enemlist.get(j).GetY()));
	                   CreateItem(_enemlist.get(j).GetX(),_enemlist.get(j).GetY());
	                   _enemlist.remove(j);
	                }
	                break;
	             }
	           }
	        }
	      
	      //플레이어와 적의 충돌 판정
	      for (int i = 0; i < _enemlist.size(); ++i) {
	          if(CollisionManager.CheckBoxToBox(_player.m_BoundBox,_enemlist.get(i).m_BoundBox)){
	             _explist.add(new Effect_Explosion(_enemlist.get(i).GetX(),_enemlist.get(i).GetY()));
	             _enemlist.remove(i);
	                        
	             _player.SubLife();
	             if(_player.getLife()<=0)
	               return;

	          }
	        }
	      
	      for (int i =0 ; i < _enemmslist.size();  i++) {
	          if(CollisionManager.CheckBoxToBox(_player.m_BoundBox,_enemmslist.get(i).m_BoundBox)){
	             _enemmslist.remove(i);   
	             
	             _player.SubLife();
	             if(_player.getLife()<=0)
	                return;

	          }
	        }
	      for (int i = 0; i < _itemlist.size(); i++)
	      {
	          if(CollisionManager.CheckBoxToBox(_player.m_BoundBox,_itemlist.get(i).m_BoundBox))
	          {
	             _itemlist.get(i).GetItem();
	             _itemlist.remove(i);
	             return;
	          }
	        }
	      
	   
	      for (int i = 0; i < _itemlist.size(); i++)
	      {
	          if(CollisionManager.CheckBoxToBox(_player.m_BoundBox,_itemlist.get(i).m_BoundBox))
	          {
	             _itemlist.get(i).GetItem();
	             _itemlist.remove(i);
	             break;
	          }
	        }

	}

	@Override
	public void MakeEnemy() {
		// TODO Auto-generated method stub
		if(System.currentTimeMillis() - LastRegenEnemy >= 2000 ){
			LastRegenEnemy = System.currentTimeMillis();
			
			int enemtype = randEnem.nextInt(3);
			Enemy enem = null;
			if(enemtype == 0){
				// 1번타입 적군
				enem = new Enemy_1();				
			}
			else if(enemtype == 1){
				// 2번타입 적군
				enem = new Enemy_2();				
			}
			else if(enemtype == 2){
				// 3번타입 적군
				enem = new Enemy_3();			
			}
			
			enem.SetPosition(randEnem.nextInt(280), -60);
			enem.movetype = randEnem.nextInt(3);
			
			_enemlist.add(enem);
		}
	}

	@Override
	public void Render(Canvas canvas) {
		// TODO Auto-generated method stub

    	for (int i=0; i<_pmslist.size(); i++) {
    		_pmslist.get(i).Draw(canvas);
        }
    	for (int i=0; i<_enemmslist.size(); i++ ) {
    		_enemmslist.get(i).Draw(canvas);
        }
    	for (int i=0; i<_enemlist.size(); i++) {
    		_enemlist.get(i).Draw(canvas);
        }
    	for (int i=0; i<_explist.size(); i++ ) {
    		_explist.get(i).Draw(canvas);
        }
    	for (int i=0; i<_itemlist.size(); i++) {
    		_itemlist.get(i).Draw(canvas);
        }
	}

	@Override
	public void Update(long GameTime) {
		// TODO Auto-generated method stub
		if(System.currentTimeMillis() - GameState.getInstance().startTime  >= 50000)
		{
			this.Destroy();
			GameState.getInstance().ChangeGameStage(BossStage.getInstance());
			return;
		}
		
		MakeEnemy();
    	CheckCollision();
    	for (int i = _pmslist.size()-1; i >= 0; i--) {
    		Missile pms = _pmslist.get(i);
    		pms.Update();
    		if(pms.state == Missile.STATE_OUT){
    			_pmslist.remove(i);
    		}
        }
    	for (int i = _enemmslist.size()-1; i >= 0; i--) {
    		Missile enemms = _enemmslist.get(i);
    		enemms.Update();
    		if(enemms.state == Missile.STATE_OUT){
    			_enemmslist.remove(i);
    		}
        }
    	for (int i = _enemlist.size()-1; i >= 0; i--) {
    		Enemy enem = _enemlist.get(i);
    		enem.Update(GameTime);
    		if(enem.state == Enemy.STATE_OUT)
    			_enemlist.remove(i);
        }
    	for (int i = _explist.size()-1; i >= 0; i--) {
    		Effect_Explosion exp = _explist.get(i);
    		exp.Update(GameTime);
    		if(exp.getAnimationEnd())
    			_explist.remove(i);
        }
    	for (int i = _itemlist.size()-1; i >= 0; i--) {
    		Item item = _itemlist.get(i);
    		item.Update(GameTime);
    		if(item.bOut == true)
    			_itemlist.remove(i);
        }

	}

}
