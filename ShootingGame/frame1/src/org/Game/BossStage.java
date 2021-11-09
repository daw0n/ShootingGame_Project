package org.Game;

import java.util.ArrayList;

import org.Framework.AppManager;
import org.Framework.CollisionManager;
import org.Framework.IStage;
import org.Framework.R;
import org.Framework.SoundManager;

import android.graphics.Canvas;

public class BossStage implements IStage {

	private Player _player;
	
	ArrayList<Missile> _pmslist;
	ArrayList<Missile> _enemmslist;
	ArrayList<Item> _itemlist;
	Boss1 _boss;
	ArrayList<Effect_Explosion> _explist;
	
	private static BossStage s_instance;

	public static BossStage getInstance(){
		if(s_instance == null){
			s_instance = new BossStage();
		}
		return s_instance;
	}
	
	private BossStage(){}
	
	
	@Override
	public void Init() {
		// TODO Auto-generated method stub
		_player = GameState.getInstance().GetPlayer();
		_pmslist = GameState.getInstance()._pmslist;
		_enemmslist = GameState.getInstance()._enemmslist;
		_itemlist = GameState.getInstance()._itemlist;
		_explist = GameState.getInstance()._explist;
				
		_enemmslist.clear();
        SoundManager.getInstance().playMedia(R.raw.boss_1);
        _boss = new Boss1(200, AppManager.getInstance().getBitmap(R.drawable.bossman));
        _boss.SetPosition(50, -130);
	}

	@Override
	public void Destroy() {
		// TODO Auto-generated method stub
		_enemmslist.clear();
		_player = null;
		_pmslist=null;
		SoundManager.getInstance().releseMedia();
	}

	@Override
	public void CreateItem(int x, int y) {
		// TODO Auto-generated method stub
		_itemlist.add(new Item_AddScore(x+10,y+10));
		_itemlist.add(new Item_AddScore(x+40,y+30));
		_itemlist.add(new Item_AddScore(x+70,y+5));
		_itemlist.add(new Item_AddScore(x+100,y+50));
		_itemlist.add(new Item_AddScore(x+130,y+100));
		_itemlist.add(new Item_AddScore(x+160,y+70));
	}

	@Override
	public void CheckCollision() {
		// TODO Auto-generated method stub
		for (int i = 0; i < _pmslist.size(); ++i){
	         if(CollisionManager.CheckBoxToBox(_pmslist.get(i).m_BoundBox, _boss.m_BoundBox))
	         {
	            _pmslist.remove(i);
	            _boss.Damage(_player.GetPower());

	            if(_boss.GetHP() <= 0)
	            {
	               _explist.add(new Effect_Explosion(80,10));
	               CreateItem(80, 10);
	               _boss = new Boss1(0, AppManager.getInstance().getBitmap(R.drawable.defalut));
	            }
	         }
	         break;
	      }

	      if(CollisionManager.CheckBoxToBox(_player.m_BoundBox, _boss.m_BoundBox))
	      {                 
	         _player.SubLife();
	         if(_player.getLife()<=0)
	            return;
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

	}

	@Override
	public void MakeEnemy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Render(Canvas canvas) {
		// TODO Auto-generated method stub

		_boss.Draw(canvas);
    	for (int i=0; i<_pmslist.size(); i++) {
    		_pmslist.get(i).Draw(canvas);
        }
    	for (int i=0; i<_enemmslist.size(); i++ ) {
    		_enemmslist.get(i).Draw(canvas);
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
		if(_boss.GetHP() == 0 && _itemlist.size()==0)
	      {
	         this.Destroy();
	         AppManager.getInstance().getGameView().ChangeGameState(GameClearState.getInstance());
	         return;
	      }
		_boss.Update(GameTime);
		
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
