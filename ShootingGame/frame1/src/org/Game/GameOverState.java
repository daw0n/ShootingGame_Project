package org.Game;

import org.Framework.AppManager;
import org.Framework.IState;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class GameOverState implements IState {

   private static GameOverState s_instance;
   private GameOverState(){}
   private GameOverBackground _background;
   
   public static GameOverState getInstance() { 
      if(s_instance == null)
         s_instance = new GameOverState();      
      return s_instance;   
   }
   @Override
   public void Init() {
      // TODO Auto-generated method stub
      _background = new GameOverBackground();
      _background.Init();
   }

   @Override
   public void Destroy() {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void Update() {
      // TODO Auto-generated method stub
      _background.Update();
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