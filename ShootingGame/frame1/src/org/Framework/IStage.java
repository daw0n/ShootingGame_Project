package org.Framework;

import java.util.ArrayList;

import org.Game.Effect_Explosion;
import org.Game.Enemy;
import org.Game.Item;
import org.Game.Missile;

import android.graphics.Canvas;

public interface IStage {

	public void Init();
	public void Destroy();	
	public void CreateItem(int x , int y);
	public void CheckCollision();
	public void MakeEnemy();
	public void Render(Canvas canvas);
	public void Update(long GameTime);
}
