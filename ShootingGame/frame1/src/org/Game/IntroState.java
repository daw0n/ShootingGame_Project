package org.Game;

import org.Framework.AppManager;
import org.Framework.IState;
import org.Framework.R;
import org.Framework.SoundManager;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class IntroState implements IState {

	private static IntroState s_instance;
	private IntroState(){}
	private IntroBackGround _background;
	private int menuSign = -1;
	
	public static IntroState getInstance() { 
		if(s_instance == null)
			s_instance = new IntroState();		
		return s_instance;	
	}
	@Override
	public void Init() {
		// TODO Auto-generated method stub
		menuSign = -1;
		_background = new IntroBackGround();
		_background.Init();
		SoundManager.getInstance().playMedia(R.raw.main);
	}

	@Override
	public void Destroy() {
		// TODO Auto-generated method stub
		if(menuSign == 1)
			return;
		else
			SoundManager.getInstance().releseMedia();
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Render(Canvas canvas) {
		// TODO Auto-generated method stub
		_background.Draw(canvas);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		int _x,_y;
		_x = (int) event.getX();
		_y = (int) event.getY();
		int action = event.getAction();
		if(action == MotionEvent.ACTION_UP)
		{
			if(_background.getButtonRange(1).contains(_x,_y))
			{
				menuSign = 0;
				AppManager.getInstance().getGameView().ChangeGameState(GameState.getInstance());
			}
			else if(_background.getButtonRange(2).contains(_x,_y))
			{
				menuSign = 1;
				AppManager.getInstance().getGameView().ChangeGameState(TutorialState.getInstance());;
			}
			else if(_background.getButtonRange(3).contains(_x,_y))
			{
				menuSign = 2;
				AppManager.getInstance().getGameView().surfaceDestroyed(AppManager.getInstance().getGameView().getHolder());
				System.exit(0);
			}
		}
		
		return true;
	}

}
