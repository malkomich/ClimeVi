package com.github.malkomich.climeviewer.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.github.malkomich.climeviewer.R;

/**
 * Activity which controls the splash screen shown the first time the application is opened.
 */
public class SplashScreenActivity extends Activity {

    /** Splash millis duration **/
    private final static int SPLASH_DISPLAY_LENGTH = 2000;

    /* (non-Javadoc)
     * @see android.app.Activity#onCreate()
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_splash_creen);

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.up_half);
        LinearLayout animView = (LinearLayout) findViewById(R.id.splashContent);
        animView.setAnimation(anim);

        /* New Handler to start the main Activity
         * and close this Splash Screen after the specific wait.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

}
