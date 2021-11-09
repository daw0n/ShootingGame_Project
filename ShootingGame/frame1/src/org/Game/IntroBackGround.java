package org.Game;

import org.Framework.AppManager;
import org.Framework.GraphicObject;
import org.Framework.R;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class IntroBackGround extends GraphicObject {
	private int _width;
	private int _height;
	
	private Bitmap img_button_1;
	private Bitmap img_button_2;
	private Bitmap img_button_3;

	private Rect button_1;
	private Rect button_2;
	private Rect button_3;
	
	private int std_left;
	private int std_top;
	private int std_width;
	private int std_height;

	
	public IntroBackGround() {
		super(AppManager.getInstance().getBitmap(R.drawable.intromain));
		img_button_1 = AppManager.getInstance().getBitmap(R.drawable.button_1);
		img_button_2 = AppManager.getInstance().getBitmap(R.drawable.button_2);
		img_button_3 = AppManager.getInstance().getBitmap(R.drawable.button_3);
		button_1 = new Rect();
		button_2 = new Rect();
		button_3 = new Rect();
	}
	public void Init()
	{
		_width = AppManager.getInstance().getGameView().getWidth();
		_height = AppManager.getInstance().getGameView().getHeight();
		
		std_width = (int)(img_button_1.getWidth()*0.75);
		std_height = (int)(img_button_1.getHeight()*0.75);
		std_left = (_width - std_width)/2;
		std_top = (_height - std_height)/2;
		
		button_1.set(std_left, std_top, std_left + std_width, std_top+std_height);
		button_2.set(std_left, button_1.bottom+5, std_left + std_width, button_1.bottom+std_height+5);
		button_3.set(std_left, button_2.bottom+5, std_left + std_width, button_2.bottom+std_height+5);
	}
	
	public void Draw(Canvas canvas){
		canvas.drawBitmap(m_bitmap, null,new Rect(m_x, m_y, m_x+_width, m_y+_height), null);
		canvas.drawBitmap(img_button_1, null, button_1, null);
		canvas.drawBitmap(img_button_2, null, button_2, null);
		canvas.drawBitmap(img_button_3, null, button_3, null);
		
	}
	public Rect getButtonRange(int idx)
	{
		if(idx == 1)
			return button_1;
		else if(idx == 2)
			return button_2;
		else if(idx == 3)
			return button_3;
		else
			return null;
	}

}
