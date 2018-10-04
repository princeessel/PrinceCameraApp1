package com.example.genius_otis.princecameraapp;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button captureButton;
    private ImageView pictureView;
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        captureButton = findViewById(R.id.captureButton);
        captureButton.setOnClickListener(this);
        pictureView = findViewById(R.id.screenView);
    }

    @Override
    public void onClick(View itemView) {
        switch (itemView.getId()){
            case R.id.captureButton:
                //here we process  action relevant to id
                processImage();
                break;
        }
    }

    private void processImage() {
        Intent imgIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (imgIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(imgIntent, REQUEST_IMAGE_CAPTURE);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle imgBundle = data.getExtras();
            Bitmap imgBitmap = (Bitmap) imgBundle.get("data");
            pictureView.setImageBitmap(imgBitmap);
        }
    }
}