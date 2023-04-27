package com.example.volumemanager;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

public class VolumeEditorDialog extends Dialog {


    public interface VolumeDialogInterface {

        void seekMediaData(int n);

        void seekCallData(int n);

        void seekNotificationData(int n);


    }

    private VolumeDialogInterface volumeDialogInterface;

    public void setOnSaveButtonClick(VolumeDialogInterface volumeDialogInterface) {
        this.volumeDialogInterface = volumeDialogInterface;
    }

    SeekBar seekMedia, seekCalls, seekNotifications;
    Button btnSave;
    ImageView imgClose;



    public VolumeEditorDialog(Context context) {
        super(context);
        setContentView(R.layout.edit_volume_dialog);

        initViews();

        setonClickListner();

    }
    public VolumeEditorDialog(Context context,int mediaVolume,int callsVolume,int notificationVolume) {
        super(context);
        setContentView(R.layout.edit_volume_dialog);

        initViews();

        setonClickListner();

        seekMedia.setProgress(mediaVolume);
        seekCalls.setProgress(callsVolume);
        seekNotifications.setProgress(notificationVolume);
    }

    private void initViews() {
        seekMedia = findViewById(R.id.seekMedia);
        btnSave = findViewById(R.id.btnSave);
        seekCalls = findViewById(R.id.seekCalls);
        seekNotifications = findViewById(R.id.seekNotifications);
        imgClose = findViewById(R.id.imgDialogClose);
    }


    private void setonClickListner() {

        seekMedia.setOnSeekBarChangeListener(new WhenChangesMadeInSeekbar());
        btnSave.setOnClickListener(new WhenButtonClicked());
        imgClose.setOnClickListener(new WhenButtonClicked());


    }

    private class WhenChangesMadeInSeekbar implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }

    }

    private class WhenButtonClicked implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.btnSave:

                    if (volumeDialogInterface != null) {
                        volumeDialogInterface.seekMediaData(seekMedia.getProgress());
                        volumeDialogInterface.seekNotificationData(seekNotifications.getProgress());
                        volumeDialogInterface.seekCallData(seekCalls.getProgress());
                    }

                    dismiss();

                    break;

                case R.id.imgDialogClose:
                    cancel();
                    break;
            }

        }
    }

}
