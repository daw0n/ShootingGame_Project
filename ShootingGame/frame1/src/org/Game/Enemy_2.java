package org.Game;

import org.Framework.AppManager;
import org.Framework.R;

public class Enemy_2 extends Enemy {
	
	public Enemy_2(){
		super(AppManager.getInstance().getBitmap(R.drawable.enemy_2));
		spt_height = m_bitmap.getHeight();
		spt_width = m_bitmap.getWidth();
		this.InitSpriteData(65 , 65, 1, 1);
		hp = 20;
		speed = 2.5f;
		
		movetype = Enemy.MOVE_PATTERN_3;
	}
	
	@Override
	public void Update(long GameTime){
		super.Update(GameTime);

		m_BoundBox.set(m_x,m_y,m_x+65,m_y+65);	
	}

}
