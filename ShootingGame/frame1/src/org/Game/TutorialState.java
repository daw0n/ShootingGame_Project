package org.Game;

import org.Framework.AppManager;
import org.Framework.IState;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class TutorialState implements IState {

	private static TutorialState s_instance;
	private TutorialState(){}
	private TutorialBackGround _background;
	
	public static TutorialState getInstance() { 
		if(s_instance == null)
			s_instance = new TutorialState();		
		return s_instance;	
	}
	@Override
	public void Init() {
		// TODO Auto-generated method stub
		_background = new TutorialBackGround();
		_background.Init();
	}

	@Override
	public void Destroy() {
		// TODO Auto-generated method stub

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
		int action = event.getAction();
		if(action == MotionEvent.ACTION_UP)
			AppManager.getInstance().getGameView().ChangeGameState(IntroState.getInstance());
		return true;
	}

}
