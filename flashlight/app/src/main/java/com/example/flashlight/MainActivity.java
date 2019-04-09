package com.example.flashlight;

import android.graphics.Camera;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private CameraManager mCameraManager;
    private String mCameraId;
    private Button button;
    private boolean islihgt =false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=(Button)this.findViewById(R.id.button);
        mCameraManager = (CameraManager)getSystemService(Context.CAMERA_SERVICE);
        try{
            mCameraId=mCameraManager.getCameraIdList()[0];

        }catch(CameraAccessException e){
            e.printStackTrace();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try

                {
                    if (islihgt) {
                        turnOffFlashLight();
                        islihgt = false;
                    } else {
                        turnOnFlashLight();
                        islihgt = true;

                    }
                } catch(
                        Exception e)

                {
                    e.printStackTrace();
                }

            }
        });



    }
    public void turnOnFlashLight(){
        try{
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                mCameraManager.setTorchMode(mCameraId,true);
                button.setText("关");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void turnOffFlashLight(){
        try{
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                mCameraManager.setTorchMode(mCameraId,false);
                button.setText("开");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
