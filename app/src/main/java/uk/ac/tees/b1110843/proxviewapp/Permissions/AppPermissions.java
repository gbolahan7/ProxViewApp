package uk.ac.tees.b1110843.proxviewapp.Permissions;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import uk.ac.tees.b1110843.proxviewapp.Constant.Constants;

public class AppPermissions {
    public boolean isStorageOk(Context context) {
        return ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    public void requestStoragePermission(Activity activity) {
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constants.STORAGE_REQUEST_CODE);
    }

    public boolean isLocationOn(Context context){
        return ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;

    }
}
