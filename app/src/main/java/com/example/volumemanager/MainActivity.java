package com.example.volumemanager;


import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtMedia, txtCalls, txtNotification;
    LinearLayout mediaLayout;
    LinearLayout notificationLayout;
    LinearLayout callsLayout;
    Button btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initView();
        setUpListner();

    }


    private void initView() {
        txtCalls = findViewById(R.id.txtCalls);
        txtNotification = findViewById(R.id.txtNotifications);
        txtMedia = findViewById(R.id.txtMedia);
        btnEdit = findViewById(R.id.btnEdit);
        mediaLayout = findViewById(R.id.mediaLayout);
        notificationLayout = findViewById(R.id.notificationLayout);
        callsLayout = findViewById(R.id.callsLayout);
    }


    private void setUpListner() {
        btnEdit.setOnClickListener(new WhenEditButtonClick());
    }

    private class WhenEditButtonClick implements View.OnClickListener {

        @Override
        public void onClick(View view) {


            VolumeEditorDialog volumeEditorDialog = new VolumeEditorDialog(MainActivity.this,Integer.parseInt(txtMedia.getText().toString()),Integer.parseInt(txtCalls.getText().toString()),Integer.parseInt(txtNotification.getText().toString()));
            volumeEditorDialog.setOnSaveButtonClick(new VolumeEditorDialogInterface());
            volumeEditorDialog.show();

        }

        private class VolumeEditorDialogInterface implements VolumeEditorDialog.VolumeDialogInterface {


            @Override
            public void seekMediaData(int mediaValue) {
                txtMedia.setText(String.valueOf(mediaValue));

                ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(mediaValue*8,80);
                mediaLayout.setLayoutParams(layoutParams);

                if (mediaValue <= 70 && mediaValue>30)mediaLayout.setBackgroundColor(getResources().getColor(R.color.black));
                else if (mediaValue>70)mediaLayout.setBackgroundColor(Color.RED);
                else mediaLayout.setBackgroundColor(Color.BLACK);
            }

            @Override
            public void seekCallData(int callValue) {
                txtCalls.setText(String.valueOf(callValue));
                ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(callValue*8,80);
                callsLayout.setLayoutParams(layoutParams);
            }

            @Override
            public void seekNotificationData(int notificationValue) {
                txtNotification.setText(String.valueOf(notificationValue));
                ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(notificationValue*8,80);
                notificationLayout.setLayoutParams(layoutParams);

            }


        }


    }


}