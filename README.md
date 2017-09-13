# Android Runtime Permission
A small utility to check runtime permissions on Android M and above versions.

The utility does the following

Check if the OS version is Android M or above.
If less than Android M, do not check for permission.
If Android M or above, check for permissions based on the constants defined in BaseActivity.
If the requested permission is not available, show dialog to user for him to either "Allow" or "Deny" it.
Call respective callback based on users action. These callbacks should be implemented in the child classes to take appropriate action.
Find below the steps to use the utility

Your Activity should Extend BaseActivity.
Call checkPermission method with appropriate permission type as declared in BaseActivity.
Override permissionGranted and permissionDenied and provide implementation specific to your app, when a particular permission is granted or denied.
