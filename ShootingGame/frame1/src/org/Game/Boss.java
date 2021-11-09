package org.Game;

import java.util.Random;

import org.Framework.SpriteAnimation;

import android.graphics.Bitmap;
import android.graphics.Rect;

public class Boss extends SpriteAnimation {

	protected int spt_width;
	protected int spt_height;

	Rect m_BoundBox = new Rect();

	protected int hp;

	long LastShoot = System.currentTimeMillis();
	
	public Boss(Bitmap bitmap) {
		super(bitmap);
		// TODO Auto-generated constructor stub
	}
	public void Damage(int damage){
		hp -= damage; 
	}
	public int GetHP(){
		return hp;
	}
	public void Appear(int y)
	{
		if(m_y < y)
			m_y += 1;	
	}
	void Attack(){
		if(System.currentTimeMillis() - LastShoot >=1200 && hp > 0){
	         Random rand = new Random();
	         int _x = rand.nextInt(80);
	         int _y = 90;
	         LastShoot = System.currentTimeMillis();
	         // 미사일을 발사하는 로직
	            BossStage.getInstance()._enemmslist.add(new Missile_Enemy(_x    , _y + rand.nextInt(3)*20));
	            BossStage.getInstance()._enemmslist.add(new Missile_Enemy(_x+120, _y + rand.nextInt(3)*10));
	            BossStage.getInstance()._enemmslist.add(new Missile_Enemy(_x+240, _y + rand.nextInt(3)*30));        
	      }
		
	}
	
	public void Update(long GameTime){
		super.Update(GameTime);
		Appear(-15);
		Attack();
	};

}
