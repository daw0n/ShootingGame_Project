package org.Game;

import org.Framework.AppManager;
import org.Framework.R;

public class Enemy_3 extends Enemy {

	public Enemy_3(){
		super(AppManager.getInstance().getBitmap(R.drawable.enemy_3));
		spt_height = m_bitmap.getHeight();
		spt_width = m_bitmap.getWidth()/2;
		this.InitSpriteData(65 , 65	, 1, 1);
		hp = 30;
		speed = 1.5f;
		
		movetype = Enemy.MOVE_PATTERN_3;
	}
	
	@Override
	public void Update(long GameTime){
		super.Update(GameTime);

		m_BoundBox.set(m_x,m_y,m_x+65,m_y+65);	
	}

}
