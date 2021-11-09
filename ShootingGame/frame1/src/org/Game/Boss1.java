package org.Game;

import android.graphics.Bitmap;

public class Boss1 extends Boss{

   public Boss1(int _hp, Bitmap bmp) {
      super(bmp);
      // TODO Auto-generated constructor stub
      spt_height = m_bitmap.getHeight();
      spt_width = m_bitmap.getWidth()/2;
      this.InitSpriteData(spt_height, spt_width, 3, 2);
      hp = _hp;
      
   }
   
   public void Update(long GameTime){
      super.Update(GameTime);   
      if(hp > 0)
         m_BoundBox.set(m_x,m_y,m_x+spt_width,m_y+spt_height);
      else
         m_BoundBox.set(0,0,0,0);
   }
   

}