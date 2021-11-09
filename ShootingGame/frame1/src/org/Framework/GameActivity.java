package org.Framework;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Window;




public class GameActivity extends Activity {
    /** Called when the activity is first created. */
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀제거
    	AppManager.getInstance().setActivity(this);
        setContentView(new GameView(this));
       
    
    }
    
}