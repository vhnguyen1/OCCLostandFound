package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Starts up when the user first opens the app, where a timer loads
 * a splash screen for about 3 seconds, in which it loads the main
 * menu right after
 *
 * @author Vincent Nguyen
 */
public class SplashScreenActivity extends AppCompatActivity {
    private Timer splashTimer;
    private TimerTask splashTimerTask;
    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        splashTimerTask = new TimerTask() {
            /**
             *
             */
            @Override
            public void run() {
                finish();
                startActivity(new Intent(SplashScreenActivity.this, MainMenuActivity.class));
            }
        };

        splashTimer = new Timer();
        splashTimer.schedule(splashTimerTask, 3000);
    }
}