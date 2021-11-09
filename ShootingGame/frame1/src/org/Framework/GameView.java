package org.Framework; 

import org.Game.IntroState;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class GameView extends SurfaceView implements SurfaceHolder.Callback{

	private GameViewThread _thread;
	
	private IState m_state;
	
	public static Vibrator m_vibrator;
	
    public GameView(Context context) {
        super(context);
        
    	// 키입력처리를 받기위해서
    	setFocusable(true);
    	m_vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    	SoundManager.getInstance().Init(context);
    	AppManager.getInstance().setGameView(this);
    	AppManager.getInstance().setResources(getResources());
    	AppManager.getInstance().setSize(getWidth(), getHeight());
  
    	ChangeGameState(IntroState.getInstance());
    	
        getHolder().addCallback(this);
        _thread = new GameViewThread(getHolder(),this);
        getHolder().setFixedSize(getWidth(),getHeight());
    }
    @Override
      public void onDraw(Canvas canvas) {
    	canvas.drawColor(Color.BLACK);
    	m_state.Render(canvas);    	
    }
	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		// 쓰레드를 실행상태로만든다.
		_thread.setRunning(true);
		// 쓰레드 실행
	    _thread.start();
	    m_state.Init();
	}
	
	public void Update() {
		m_state.Update();
	}
	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		boolean retry = true;
	    _thread.setRunning(false);
	    while (retry) {
	        try {
	        	// 쓰레드를 중지시킨다.
	            _thread.join();
	            retry = false;
	        } catch (InterruptedException e) {
	        	// thread 가 종료되도록 계속 시도한다.
	        }
	    }
	    SoundManager.getInstance().destroy();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		m_state.onKeyDown(keyCode, event);
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		m_state.onTouchEvent(event);
		return true;
		

	}
	
	public void ChangeGameState(IState _state){
		if(m_state!=null)
			m_state.Destroy();
		_state.Init();
		m_state = _state;
	}
	
	public GameViewThread getThread()
	{
		return _thread;
	}

}
