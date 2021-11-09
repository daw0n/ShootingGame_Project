package org.Game;

import org.Framework.AppManager;
import org.Framework.R;

public class Enemy_1 extends Enemy {

	public Enemy_1(){
		super(AppManager.getInstance().getBitmap(R.drawable.enemy_1));
		spt_height = m_bitmap.getHeight();
	    spt_width = m_bitmap.getWidth();
	    this.InitSpriteData(65, 65, 1, 1);
		hp = 10;
		speed = 3.5f;
		
		movetype = Enemy.MOVE_PATTERN_3;
	}
	
	@Override
	public void Update(long GameTime){
		super.Update(GameTime);
		
		m_BoundBox.set(m_x,m_y,m_x+65,m_y+65);		
	}
	
}
