package org.Game;

import org.Framework.AppManager;
import org.Framework.IState;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class GameClearState implements IState {
	
   private GameClearBackground background;

   private static  GameClearState s_instance;
   private  GameClearState(){}
   
   public static  GameClearState getInstance() { 
      if(s_instance == null)
         s_instance = new GameClearState();      
      return s_instance;   
   }
   @Override
   public void Init() {
      // TODO Auto-generated method stub
	   background = new GameClearBackground();
	   background.Init();

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
	   background.Draw(canvas);
      Paint p = new Paint();
       p.setTextSize(20); p.setColor(Color.RED);
      canvas.drawText("YOU'RE SCORE", 100, 150, p);
      p.setTextSize(20); p.setColor(Color.WHITE);
      canvas.drawText("Score : "+String.valueOf(GameState.getInstance().score), 100, 190, p);

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