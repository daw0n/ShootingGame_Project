package org.Game;

import java.util.ArrayList;
import java.util.Random;

import org.Framework.AppManager;
import org.Framework.Collision;
import org.Framework.CollisionManager;
import org.Framework.GameView;
import org.Framework.GraphicObject;
import org.Framework.IStage;
import org.Framework.IState;
import org.Framework.R;
import org.Framework.SoundManager;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.MotionEvent;

@TargetApi(Build.VERSION_CODES.ECLAIR)
public class GameState implements IState {

	int move_touch_id = -1;
	int attack_touch_id = -1;
	
	private Player _player;
	private GraphicObject _keypad;
	private IStage m_stage;
	private BackGround _background;
	static Vibrator m_vibrator;
	
	ArrayList<Missile> _pmslist = new ArrayList<Missile>();
	ArrayList<Missile> _enemmslist = new ArrayList<Missile>();
	ArrayList<Item> _itemlist = new ArrayList<Item>();
	ArrayList<Enemy> _enemlist = new ArrayList<Enemy>();
	ArrayList<Effect_Explosion> _explist = new ArrayList<Effect_Explosion>();
	
	private float m_scroll = 0;
	private final static float SCROLL_SPEED = 0.5f;
	
	public int score = 0;
	public long startTime = System.currentTimeMillis();
	
	private static GameState s_instance;
	public Player GetPlayer(){
		return _player;
	}
	public IStage GetStage(){
		return m_stage;
	}

	public static GameState getInstance(){
		if(s_instance == null){
			s_instance = new GameState();
		}
		return s_instance;
	}
	
	private GameState(){
		
	}

	public void ChangeGameStage(IStage _stage){
		if(m_stage!=null)
			m_stage.Destroy();
		_stage.Init();
		m_stage = _stage;
	}
	
	@Override
	public void Destroy() {
		m_stage.Destroy();
		_pmslist.clear();
		
	}
	
	public void CreateItem(int x , int y){		
		m_stage.CreateItem(x, y);
	
	}
	@Override
	public void Init() {
		_pmslist.clear();
		_enemmslist.clear();
		_itemlist.clear();
		_enemlist.clear();
		_explist.clear();
		m_scroll = 0;
        _player = new Player(AppManager.getInstance().getBitmap(R.drawable.bc));
        _keypad=new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.keypad));
        _background=new BackGround(0);
        score = 0;
        // 키패드 위치 
        _keypad.SetPosition(0,460-120);
        SoundManager.getInstance().addSound(0, R.raw.missile);
        startTime = System.currentTimeMillis();
        ChangeGameStage(Stage1.getInstance());
	}
	
	public void CheckCollision(){
		m_stage.CheckCollision();
		
	}

	public void MakeEnemy(){
		m_stage.MakeEnemy();

		m_scroll += SCROLL_SPEED;
	}
	
	
	@Override
	public void Render(Canvas canvas) {
		_background.Draw(canvas);
		m_stage.Render(canvas);
    	_player.Draw(canvas);
    	_keypad.Draw(canvas);  
    	
    	Paint p = new Paint();
    	p.setTextSize(20); p.setColor(Color.WHITE); 
    	canvas.drawText("LIFE :"+String.valueOf(_player.getLife()),0,20,p);
    	canvas.drawText("SCORE :"+String.valueOf(score),0,40,p);
    	canvas.drawText("POWER :"+String.valueOf(_player.GetPower()),0,60,p);
	}

	@Override
	public void Update() {
		long GameTime = System.currentTimeMillis();
		_background.Update(GameTime);
		_player.Update(GameTime);
		
		m_stage.Update(GameTime);
    	
    	if(_player.getLife() <= 0)
    	{
    		this.Destroy();
			AppManager.getInstance().getGameView().ChangeGameState(GameOverState.getInstance());
			
    	}
		
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// 키입력에따른 플레이어이동
		
		int x = _player.GetX();
		int y = _player.GetY();
		
		if(keyCode == KeyEvent.KEYCODE_DPAD_LEFT) // 왼쪽
			_player.SetPosition(x-20, y);
		if(keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)// 오른쪽
			_player.SetPosition(x+20, y);
		if(keyCode == KeyEvent.KEYCODE_DPAD_UP)  // 위
			_player.SetPosition(x, y-20);
		if(keyCode == KeyEvent.KEYCODE_DPAD_DOWN) // 아래
			_player.SetPosition(x, y+20);
	    if(keyCode == KeyEvent.KEYCODE_SPACE) // 스페이스를 눌렀을때
	      {
	         _pmslist.add(new Missile_Player(x+45,y-4));
	         SoundManager.getInstance().playSound(0);
	      }
	         
		
		
		return true;
	}


	
	@SuppressLint("NewApi")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		int action = event.getAction();
		int touch_id;
		
		
		int touch_mode;
		
		int _x,_y;
		_x = (int) event.getX();
		_y = (int) event.getY();
		
		Rect rt = new Rect();
		
		rt.set(5,385,45,425);
		if(rt.contains(_x,_y)){
	}
		rt.set(40,345,80,385);
		if(rt.contains(_x,_y)){
	}
		rt.set(80,385,120,425);
		if(rt.contains(_x,_y)){
	}
		rt.set(40,425,80,465);
		if(rt.contains(_x,_y)){
	}
		

		// 처음 눌렀을때
		switch(action & MotionEvent.ACTION_MASK){
		case MotionEvent.ACTION_DOWN:{
			touch_id = event.getPointerId(0); 
			_x = (int) event.getX();
			_y = (int) event.getY();
			if(move_touch_id == -1){
				if(Collision.CollisionCheckPointToBox(_x,_y, 5, 385, 45, 425)){
					_player.startMove(-4, 0);
					move_touch_id = touch_id;
				}
				// 위쪽방향
				if(Collision.CollisionCheckPointToBox(_x,_y, 40, 345, 80, 385)){
					_player.startMove(0, -4);
					move_touch_id = touch_id;
				}
				// 오른쪽방향
				if(Collision.CollisionCheckPointToBox(_x,_y, 80, 385, 120, 425)){
					_player.startMove(4, 0);
					move_touch_id = touch_id;
				}
				// 아래쪽방향
				if(Collision.CollisionCheckPointToBox(_x,_y, 40, 425, 80, 465)){
					_player.startMove(0, 4);
					move_touch_id = touch_id;
				}
			}
			
			if(Collision.CollisionCheckPointToBox(_x,_y, 270, 385, 290, 425)){
				_pmslist.add(new Missile_Player(_player.GetX()+10,_player.GetY()));
				
			}
			break;
		}
		// 누른상태로 이동했을때
		case MotionEvent.ACTION_MOVE:{
			break;
		}

		// 터치를 땟을때
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:{
			touch_id = event.getPointerId(0); 
			if(touch_id == move_touch_id){
				_player.stopMove();
				move_touch_id = -1;
			}
			
			break;
		}
		case MotionEvent.ACTION_POINTER_DOWN:{ 
			int pointer_index = (action & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT; 
			int pointer_id  = event.getPointerId(pointer_index);      
			if( pointer_id != -1 ){ 
				touch_id = pointer_index; 

				_x = (int) event.getX(touch_id);
				_y = (int) event.getY(touch_id);
				
				if(move_touch_id == -1){
					if(Collision.CollisionCheckPointToBox(_x,_y, 5, 385, 45, 425)){
						_player.startMove(-4, 0);
						move_touch_id = touch_id;
					}
					// 위쪽방향
					if(Collision.CollisionCheckPointToBox(_x,_y, 40, 345, 80, 385)){
						_player.startMove(0, -4);
						move_touch_id = touch_id;
					}
					// 오른쪽방향
					if(Collision.CollisionCheckPointToBox(_x,_y, 80, 385, 120, 425)){
						_player.startMove(4, 0);
						move_touch_id = touch_id;
					}
					// 아래쪽방향
					if(Collision.CollisionCheckPointToBox(_x,_y, 40, 425, 80, 465)){
						_player.startMove(0, 4);
						move_touch_id = touch_id;
					}
				}

				if(Collision.CollisionCheckPointToBox(_x,_y, 270, 385, 290, 425)){
					_pmslist.add(new Missile_Player(_player.GetX()+10,_player.GetY()));
				}
			} 
			break; 
		} 
			case MotionEvent.ACTION_POINTER_UP:{  
			int pointer_index = (action & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT; 
			int pointer_id  = event.getPointerId(pointer_index); 
			if( pointer_id != -1 ){ 
			touch_id = pointer_index; 
			if(touch_id == move_touch_id){
				_player.stopMove();
				move_touch_id = -1;
			}
			}     
			break;    
			} 
		};
		
		// Move 와 Up 의 이벤트를 받기위해 true 를 리턴해준다.
		return true;
	}

}
