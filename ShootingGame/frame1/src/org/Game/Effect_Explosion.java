package org.Game;

import org.Framework.AppManager;
import org.Framework.R;
import org.Framework.SpriteAnimation;

import android.graphics.Rect;
import android.os.Vibrator;

public class Effect_Explosion extends SpriteAnimation {

	
	public Effect_Explosion(int x , int y) {
		super(AppManager.getInstance().getBitmap(R.drawable.explosion));
		this.InitSpriteData(104	, 66, 3, 6);
		
		m_x = x;
		m_y = y;
		
		mbReply = false;
		
		
	}
	
}
