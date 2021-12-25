package com.example.DeDeforest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import DeDeforest.R;

public class MainActivity extends AppCompatActivity {
private Button openMapBtn, openOverlayBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openMapBtn=findViewById(R.id.open_map_btn);
        openOverlayBtn=findViewById(R.id.open_overlay_btn);
        openOverlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent overlayIntent=new Intent();
                overlayIntent.setClass(getApplicationContext(),GroundOverlay1.class);
                startActivity(overlayIntent);

            }
        });

        openMapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getApplicationContext(),MapsActivity.class);
                startActivity(intent);
            }
        });

    }

}