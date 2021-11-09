package org.Game;

import org.Framework.SpriteAnimation;

import android.graphics.Bitmap;
import android.graphics.Rect;

public class Item extends SpriteAnimation {

	Rect m_BoundBox = new Rect();
	
	public boolean bOut = false;
	
	public Item(Bitmap bitmap) {
		super(bitmap);
	}
	
	@Override
	public void Update(long GameTime){
		super.Update(GameTime);
		
		m_y += 2;
		if(m_y> 350)
			bOut = true;
		
		m_BoundBox.set(m_x, m_y, m_x +60, m_y+60);
	}
	
	void GetItem(){
		
	}
	
}
