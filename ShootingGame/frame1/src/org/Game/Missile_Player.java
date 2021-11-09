package org.Game;

import org.Framework.AppManager;
import org.Framework.GameView;
import org.Framework.R;

import android.os.Vibrator;

public class Missile_Player extends Missile {
	
	
	
	Missile_Player(int x,int y){
		super(AppManager.getInstance().getBitmap(R.drawable.missile_fire));
		this.SetPosition(x, y);
		
	}
	
	public void Update(){
		
		// 미사일이 위로 발사되는 효과를 준다.
		m_y-=6;
		if(m_y < 10)
			state = STATE_OUT;
		
		m_BoundBox.set(m_x,m_y,m_x+30,m_y+30);
		
	}
}
