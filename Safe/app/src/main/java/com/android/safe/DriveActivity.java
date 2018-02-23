package com.android.safe;

import android.app.Service;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class DriveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drive);

        RelativeLayout r = (RelativeLayout) findViewById(R.id.swipe_left);
        if (r != null) {
            r.setOnTouchListener(new OnSwipeTouchListener(DriveActivity.this) {
                /**
                 * if we find out that were swipe right then move to the friend activity
                 */
                @Override
                public void onSwipeLeft() {
                    Intent intent = new Intent(DriveActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }

        Button drive = (Button) findViewById(R.id.drive);
        if (drive != null) {
            drive.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startService(new Intent(DriveActivity.this, HUD.class));
                }
            });
        }
    }
}
