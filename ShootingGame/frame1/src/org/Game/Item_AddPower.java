package org.Game;

import org.Framework.AppManager;
import org.Framework.R;

public class Item_AddPower extends Item {

	public Item_AddPower(int x,int y) {
		super(AppManager.getInstance().getBitmap(R.drawable.item3));
		this.InitSpriteData(51	, 51, 3, 4);		
		
		m_x = x;
		m_y = y;		
	}

	@Override
	void GetItem(){
		GameState.getInstance().GetPlayer().AddPower();
	}
}

