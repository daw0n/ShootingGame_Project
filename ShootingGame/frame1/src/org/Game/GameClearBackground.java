package org.Game;

import org.Framework.AppManager;
import org.Framework.GraphicObject;
import org.Framework.R;

import android.graphics.Canvas;
import android.graphics.Rect;

public class GameClearBackground extends GraphicObject {
	private int _width;
	private int _height;

	
	public GameClearBackground() {
		super(AppManager.getInstance().getBitmap(R.drawable.gameclearbackground));
		
	}
	public void Init()
	{
		_width = AppManager.getInstance().getGameView().getWidth();
		_height = AppManager.getInstance().getGameView().getHeight();
		
	}
	
	public void Draw(Canvas canvas){
		canvas.drawBitmap(m_bitmap, null,new Rect(m_x, m_y, m_x+_width, m_y+_height), null);
		
	}

}
