package com.mindtree.permissiondemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private Button mBtnReadContacts;
    private Button mBtnWriteContacts;
    private Button mBtnCamera;
    private Button mBtnLocation;
    private Button mBtnReadSDCard;
    private Button mBtnWriteSDCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnReadContacts = (Button) findViewById(R.id.btnReadContacts);
        mBtnWriteContacts = (Button) findViewById(R.id.btnWriteContacts);
        mBtnCamera = (Button) findViewById(R.id.btnCamera);
        mBtnLocation = (Button) findViewById(R.id.btnLocation);
        mBtnReadSDCard = (Button) findViewById(R.id.btnReadSDCard);
        mBtnWriteSDCard = (Button) findViewById(R.id.btnWriteSDCard);

        mBtnReadContacts.setOnClickListener(this);
        mBtnWriteContacts.setOnClickListener(this);
        mBtnCamera.setOnClickListener(this);
        mBtnLocation.setOnClickListener(this);
        mBtnReadSDCard.setOnClickListener(this);
        mBtnWriteSDCard.setOnClickListener(this);
    }

    @Override
    protected void permissionGranted(int permissionType) {
        String msg = "";

        switch (permissionType) {
            case PERMISSION_CONTACTS_READ:
                msg = "Contact read permission granted";
                break;

            case PERMISSION_CONTACTS_WRITE:
                msg = "Contact write permission granted";
                break;

            case PERMISSION_CAMERA:
                msg = "Camera permission granted";
                break;

            case PERMISSION_LOCATION:
                msg = "Location permission granted";
                break;

            case PERMISSION_READ_EXTERNAL_STORAGE:
                msg = "Read external storage permission granted";
                break;

            case PERMISSION_WRITE_EXTERNAL_STORAGE:
                msg = "Write external storage permission granted";
                break;
        }

        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void permissionDenied(int permissionType) {
        String msg = "";

        switch (permissionType) {
            case PERMISSION_CONTACTS_READ:
                msg = "Contact read permission denied";
                break;

            case PERMISSION_CONTACTS_WRITE:
                msg = "Contact write permission denied";
                break;

            case PERMISSION_CAMERA:
                msg = "Camera permission denied";
                break;

            case PERMISSION_LOCATION:
                msg = "Location permission denied";
                break;

            case PERMISSION_READ_EXTERNAL_STORAGE:
                msg = "Read external storage permission denied";
                break;

            case PERMISSION_WRITE_EXTERNAL_STORAGE:
                msg = "Write external storage permission denied";
                break;
        }

        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.btnReadContacts:
                if (checkPermission(PERMISSION_CONTACTS_READ)) {
                    Toast.makeText(this, "Read contact permission already granted.", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.btnWriteContacts:
                if (checkPermission(PERMISSION_CONTACTS_WRITE)) {
                    Toast.makeText(this, "Write contact permission already granted.", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.btnCamera:
                if (checkPermission(PERMISSION_CAMERA)) {
                    Toast.makeText(this, "Camera permission already granted.", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.btnLocation:
                if (checkLocationlPermission()) {
                    Toast.makeText(this, "Location permission already granted.", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.btnReadSDCard:
                if (checkPermission(PERMISSION_READ_EXTERNAL_STORAGE)) {
                    Toast.makeText(this, "Read SD Card permission already granted.", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.btnWriteSDCard:
                if (checkPermission(PERMISSION_WRITE_EXTERNAL_STORAGE)) {
                    Toast.makeText(this, "Write SD Card permission already granted.", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
