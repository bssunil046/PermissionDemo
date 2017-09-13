package com.mindtree.permissiondemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

/**
 * @author B.S.Sunil.
 *         <p/>
 *         This class implements the logic to check for permissions for Android M and above versions.
 *         The Activity's that need to check for permissions should extend this class.
 *         <p/>
 *         It does the following
 *         <p/>
 *         - Check if the OS version is Android M or above.
 *         - If less than Android M, do not check for permission.
 *         - If Android M or above, check for permissions based on the constants defined in this class.
 *         - If the requested permission is not available, show dilaog to user for him to either "Allow" or "Deny" it.
 *         - Call respective callback based on users action.  These callbacks should be implemented in the child classes to take appropriate action.
 */
public abstract class BaseActivity extends AppCompatActivity {
    public static final int PERMISSION_CALENDAR_READ = 101;
    public static final int PERMISSION_CALENDAR_WRITE = 102;
    public static final int PERMISSION_CAMERA = 103;
    public static final int PERMISSION_CONTACTS_READ = 104;
    public static final int PERMISSION_CONTACTS_WRITE = 105;
    public static final int PERMISSION_CONTACTS_GETACCOUNTS = 106;
    public static final int PERMISSION_LOCATION = 107;
    public static final int PERMISSION_LOCATION_FINE = 108;
    public static final int PERMISSION_LOCATION_COARSE = 109;
    public static final int PERMISSION_PHONE_READ_PHONE_STATE = 110;
    public static final int PERMISSION_PHONE_CALL_PHONE = 111;
    public static final int PERMISSION_READ_CALL_LOG = 112;
    public static final int PERMISSION_WRITE_CALL_LOG = 113;
    public static final int PERMISSION_ADD_VOICEMAIL = 114;
    public static final int PERMISSION_USE_SIP = 115;
    public static final int PERMISSION_PROCESS_OUTGOING_CALLS = 116;
    public static final int PERMISSION_BODY_SENSORS = 117;
    public static final int PERMISSION_SEND_SMS = 118;
    public static final int PERMISSION_RECEIVE_SMS = 119;
    public static final int PERMISSION_READ_SMS = 120;
    public static final int PERMISSION_RECEIVE_WAP_PUSH = 121;
    public static final int PERMISSION_RECEIVE_MMS = 122;
    public static final int PERMISSION_READ_EXTERNAL_STORAGE = 123;
    public static final int PERMISSION_WRITE_EXTERNAL_STORAGE = 124;

    private int mPermission = -1;

    public boolean checkPermission(int type) {
        String permission = null;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            // only for Marshmallow and newer versions
            switch (type) {
                case PERMISSION_CALENDAR_READ:
                    permission = Manifest.permission.READ_CALENDAR;
                    break;

                case PERMISSION_CALENDAR_WRITE:
                    permission = Manifest.permission.WRITE_CALENDAR;
                    break;

                case PERMISSION_CAMERA:
                    permission = Manifest.permission.CAMERA;
                    break;

                case PERMISSION_CONTACTS_READ:
                    permission = Manifest.permission.READ_CONTACTS;
                    break;

                case PERMISSION_CONTACTS_WRITE:
                    permission = Manifest.permission.WRITE_CONTACTS;
                    break;

                case PERMISSION_ADD_VOICEMAIL:
                    permission = Manifest.permission.ADD_VOICEMAIL;
                    break;

                case PERMISSION_BODY_SENSORS:
                    permission = Manifest.permission.BODY_SENSORS;
                    break;

                case PERMISSION_CONTACTS_GETACCOUNTS:
                    permission = Manifest.permission.GET_ACCOUNTS;
                    break;

                case PERMISSION_LOCATION_COARSE:
                    permission = Manifest.permission.ACCESS_COARSE_LOCATION;
                    break;

                case PERMISSION_LOCATION_FINE:
                    permission = Manifest.permission.ACCESS_FINE_LOCATION;
                    break;

                case PERMISSION_PHONE_CALL_PHONE:
                    permission = Manifest.permission.CALL_PHONE;
                    break;

                case PERMISSION_PHONE_READ_PHONE_STATE:
                    permission = Manifest.permission.READ_PHONE_STATE;
                    break;

                case PERMISSION_PROCESS_OUTGOING_CALLS:
                    permission = Manifest.permission.PROCESS_OUTGOING_CALLS;
                    break;

                case PERMISSION_READ_CALL_LOG:
                    permission = Manifest.permission.READ_CALL_LOG;
                    break;

                case PERMISSION_WRITE_CALL_LOG:
                    permission = Manifest.permission.WRITE_CALL_LOG;
                    break;

                case PERMISSION_USE_SIP:
                    permission = Manifest.permission.USE_SIP;
                    break;

                case PERMISSION_WRITE_EXTERNAL_STORAGE:
                    permission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
                    break;

                case PERMISSION_READ_EXTERNAL_STORAGE:
                    permission = Manifest.permission.READ_EXTERNAL_STORAGE;
                    break;

                case PERMISSION_SEND_SMS:
                    permission = Manifest.permission.SEND_SMS;
                    break;

                case PERMISSION_RECEIVE_SMS:
                    permission = Manifest.permission.RECEIVE_SMS;
                    break;

                case PERMISSION_RECEIVE_MMS:
                    permission = Manifest.permission.RECEIVE_MMS;
                    break;

                case PERMISSION_RECEIVE_WAP_PUSH:
                    permission = Manifest.permission.RECEIVE_WAP_PUSH;
                    break;

                case PERMISSION_READ_SMS:
                    permission = Manifest.permission.READ_SMS;
                    break;
            }

            mPermission = ContextCompat.checkSelfPermission(this, permission);

            if (mPermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{permission},
                        type);

                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }


    /**
     * This method is used to check for location services permission.
     * A separate method is written as we need to check two permissions: ACCESS_COARSE_LOCATION and ACCESS_FINE_LOCATION.
     * This method first checks if the OS version is M or above.
     * If < M it does not do anything, if M or above, it checks for permission and invokes dialog to ask user to grant permission.
     */
    public boolean checkLocationlPermission() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (!(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSION_LOCATION);

                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    /**
     * This is a callback method that is invoked when the user allow's or deny's permission
     *
     * @param requestCode  - code to identify which permission.
     * @param permissions  - set of permissions that user requested.
     * @param grantResults - Result telling if user allowed or denied permission.
     *                     If request is cancelled, this will be empty.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            permissionGranted(requestCode);
        } else {
            permissionDenied(requestCode);
        }
    }

    /**
     * This method is invoked when the user allows a permission.
     * Here you can implement the action to be taken on allowing the permission.
     * It is recommended to override this in the child class and take action there.
     *
     * @param permissionType - The permission type which the user allowed.
     */
    protected void permissionGranted(int permissionType) {
    }

    /**
     * This method is invoked when the user denies a permission.
     * Here you can implement the action to be taken on denying the permission like showing a message to the user.
     * It is recommended to override this in the child class and take action there.
     *
     * @param permissionType
     */
    protected void permissionDenied(int permissionType) {
    }
}
