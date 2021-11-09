package org.Game;

import org.Framework.AppManager;
import org.Framework.R;

import android.os.Vibrator;

public class Missile_Enemy extends Missile {
	Missile_Enemy(int x,int y){
		super(AppManager.getInstance().getBitmap(R.drawable.missileenem));
		this.SetPosition(x, y);
		
	}
	
	public void Update(){
		// 미사일이 아래로 발사되는 효과를 준다
		m_y+=4;
		if(m_y > 400)
			state = STATE_OUT;
		
		m_BoundBox.set(m_x,m_y,m_x+30,m_y+30);
		
	}
	
}
