package org.Game;

import org.Framework.GameView;
import org.Framework.SpriteAnimation;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.Log;

public class Player extends SpriteAnimation {

	public static int m_power_s = 700;
	Rect m_BoundBox = new Rect();
	
	int m_Life = 10;
	int m_power = 10;
	
	public Player(Bitmap bitmap) {
		super(bitmap);
		// 애니메이션 정보를 세팅해준다.
		Log.d("TH","init:"+bitmap.getWidth()/6);
		this.InitSpriteData(104, 104, 1, 1);
		// 초기 위치값을 설정해준다.
		this.SetPosition(120, 380);
		
	}
	
	
	public int GetPower(){
		return m_power;
	}
	public void SetPower(int _power){
		m_power = _power;
	}
	
	// 움직이는 상태값을 가지는 플래그
	private boolean bMove = false;
	// 방향값을 가지는 변수
	private int _dirX = 0;
	private int _dirY = 0;
	
	public int getLife(){
		return m_Life;
	}
	public void destroyPlayer(){
		
		m_Life--;
		GameView.m_vibrator.vibrate(100);
		
	}
	public void SubLife(){
		m_Life--;
		GameView.m_vibrator.vibrate(100);
	}
	public void AddLife(){
		m_Life++;
	}
	public void AddPower(){
		m_power += 5;
	}
	
	// onTouchEvent 에서 DOWN 메세지를 받았을때 호출할 메서드
	public void startMove(int dirX,int dirY){
		// 움직임을 활성화시켜준다
		bMove = true;
		// 방향값을 저장한다
		_dirX = dirX;
		_dirY = dirY;		
	}
	// onTouchEvent 에서 UP 메세지를 받았을때 호출할 메서드
	public void stopMove(){
		// 움직임을 비활성화시켜준다
		bMove =false;
		// 방향값을 초기화시킨다
		_dirX = 0;
		_dirY = 0;
	}
	long LastShoot = System.currentTimeMillis();
	
	void Attack(){
		if(System.currentTimeMillis() - LastShoot >= m_power_s){
			LastShoot = System.currentTimeMillis();
			// 미사일을 발사하는 로직
	         GameState.getInstance()._pmslist.add(new Missile_Player(m_x-10, m_y+20));
		}
	}
		
	// 프레임워크 Update에서 지속적으로 호출시킬메서드
	@Override
	public void Update(long GameTime){
		// 오버로드에 대한 처리
		 super.Update(GameTime);
		Attack();
		// 움직임이 활성화되있을경우
		if(bMove){
			this.m_x += _dirX;
			this.m_y += _dirY;
		}
		
		m_BoundBox.set(m_x,m_y,m_x+104,m_y+52);	
		
	}

}
