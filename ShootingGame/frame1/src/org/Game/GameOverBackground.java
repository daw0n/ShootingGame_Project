package org.Game;

import org.Framework.AppManager;
import org.Framework.R;
import org.Framework.SpriteAnimation;

import android.graphics.Canvas;

public class GameOverBackground extends SpriteAnimation {
   private int _width;
   private int _height;
   protected int spt_width;
   protected int spt_height;
   
   public GameOverBackground() {
      super(AppManager.getInstance().getBitmap(R.drawable.gameoverbackground));
      spt_height = m_bitmap.getHeight();
       spt_width = m_bitmap.getWidth();
   }
   public void Init()
   {
      _width = AppManager.getInstance().getGameView().getWidth();
      _height = AppManager.getInstance().getGameView().getHeight();
      this.InitSpriteData(320, 480, 1, 1);
   }
   
   public void Draw(Canvas canvas){
      super.Draw(canvas);
   }
   
   public void Update(){
      super.Update(System.currentTimeMillis());
   }
}