package org.Game;

import org.Framework.AppManager;
import org.Framework.GraphicObject;
import org.Framework.R;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class BackGround extends GraphicObject {

	static final float SCROLL_SPEED = 1.f;
	private float m_scroll = -2000 + 480;
	
	Bitmap m_layer2;

	static final float SCROLL_SPEED_2 = 0.5f;
	private float m_scroll_2 = -2000 + 480;
	
	public BackGround(int backtype) {
		super(null);
		if(backtype == 0)
			m_bitmap =AppManager.getInstance().getBitmap(R.drawable.mainback2);
		else if(backtype == 1)
			m_bitmap =AppManager.getInstance().getBitmap(R.drawable.mainback2);
		m_layer2 = AppManager.getInstance().getBitmap(R.drawable.background_2);
		SetPosition(0,(int)m_scroll);
	}
	void Update(long GameTime){
		m_scroll = m_scroll + SCROLL_SPEED;
		if(m_scroll >= 0)
			m_scroll = -2000 + 480;
		SetPosition(0,(int)m_scroll); 
		m_scroll_2 = m_scroll_2 + SCROLL_SPEED_2;
		if(m_scroll_2 >= 0)
			m_scroll_2 = -2000 + 480;
	}
	@Override
	public void Draw(Canvas canvas){
		canvas.drawBitmap(m_bitmap,m_x,m_y,null);
		canvas.drawBitmap(m_layer2,m_x,m_scroll_2,null);		
	}

}
