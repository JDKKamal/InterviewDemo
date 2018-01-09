package com.jdkgroup.customviews.permission;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.ColorRes;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mike Antipiev @nindzyago
 * @author Alex Bykov @NoNews
 * @version 1.1.2
 */
public class PermissionHelper {


    private static final int PERMISSION_REQUEST_CODE = 98;
    private Activity activity;
    private Fragment fragment;
    private String[] permissions;
    private Runnable successListener;
    private Runnable deniedListener;
    private Runnable neverAskAgainListener;
    private AlertDialog.Builder dialogBeforeRunBuilder;
    private int dialogBeforeAskPositiveButton;
    private int dialogBeforeAskPositiveButtonColor = DIALOG_WITHOUT_CUSTOM_COLOR;
    private final static int DIALOG_WITHOUT_CUSTOM_COLOR = 0;

    public PermissionHelper(Activity activity) {
        this.activity = activity;
    }

    public PermissionHelper(Fragment fragment) {
        this.fragment = fragment;
    }

    public PermissionHelper check(String permission) {
        this.permissions = new String[1];
        this.permissions[0] = permission;
        return this;
    }

    public PermissionHelper check(String... permissions) {
        this.permissions = permissions;
        return this;
    }

    public PermissionHelper onSuccess(Runnable listener) {
        this.successListener = listener;
        return this;
    }

    public PermissionHelper onDenied(Runnable listener) {
        this.deniedListener = listener;
        return this;
    }

    @Deprecated
    public PermissionHelper onFailure(Runnable listener) {
        this.deniedListener = listener;
        return this;
    }

    public PermissionHelper onNeverAskAgain(Runnable listener) {
        this.neverAskAgainListener = listener;
        return this;
    }


    public PermissionHelper withDialogBeforeRun(@StringRes int titleRes, @StringRes int messageRes, @StringRes int positiveButtonRes) {
        this.dialogBeforeAskPositiveButton = positiveButtonRes;
        dialogBeforeRunBuilder = getDialogBuilder(titleRes, messageRes);
        return this;
    }

    public PermissionHelper setDialogPositiveButtonColor(@ColorRes int colorRes) {
        this.dialogBeforeAskPositiveButtonColor = ContextCompat.getColor(getContext(), colorRes);
        return this;
    }

    private AlertDialog.Builder getDialogBuilder(@StringRes int titleRes, @StringRes int messageRes) {
        final Context context = getContext();
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        dialogBuilder.setTitle(context.getString(titleRes));
        dialogBuilder.setMessage(context.getString(messageRes));
        dialogBuilder.setCancelable(false);
        return dialogBuilder;
    }

    private Context getContext() {
        return activity == null ? fragment.getContext() : activity;
    }

    public void run() {
        if (isListenersCorrect()) {
            runSuccessOrAskPermissions();
        } else {
            throw new RuntimeException("permissionSuccessListener or permissionDeniedListener have null reference. You must realize onSuccess and onDenied methods");
        }
    }

    private void runSuccessOrAskPermissions() {
        if (isNeedToAskPermissions()) {
            checkPermissions();
        } else {
            successListener.run();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkPermissions() {
        final String[] permissionsForRequest = getPermissionsForRequest();
        if (permissionsForRequest.length > 0) {
            checkDialogAndAskPermissions(permissionsForRequest);
        } else {
            successListener.run();
        }
    }

    @SuppressLint("NewApi")
    private void checkDialogAndAskPermissions(final String[] permissionsForRequest) {
        if (dialogBeforeRunBuilder != null && isNotContainsNeverAskAgain(permissionsForRequest)) {
            showDialogBeforeRun(permissionsForRequest);
        } else {
            askPermissions(permissionsForRequest);
        }
    }

    private boolean isNotContainsNeverAskAgain(String[] permissionsForRequest) {
        for (String permissions : permissionsForRequest) {
            if (isNeverAskAgain(permissions)) {
                return false;
            }
        }
        return true;
    }

    private void showDialogBeforeRun(final String[] permissionsForRequest) {
        dialogBeforeRunBuilder.setPositiveButton(dialogBeforeAskPositiveButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                askPermissions(permissionsForRequest);
            }
        });
        final AlertDialog dialogBeforeRun = dialogBeforeRunBuilder.create();

        dialogBeforeRun.show();
        if (dialogBeforeAskPositiveButtonColor != DIALOG_WITHOUT_CUSTOM_COLOR) {
            dialogBeforeRun.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(dialogBeforeAskPositiveButtonColor);
        }
    }

    @SuppressLint("NewApi")
    private void askPermissions(String[] permissionsForRequest) {
        if (activity != null) {
            activity.requestPermissions(permissionsForRequest, PERMISSION_REQUEST_CODE);
        } else {
            fragment.requestPermissions(permissionsForRequest, PERMISSION_REQUEST_CODE);
        }
    }

    private boolean isListenersCorrect() {
        return successListener != null && deniedListener != null;
    }

    private boolean isNeedToAskPermissions() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    private String[] getPermissionsForRequest() {
        List<String> permissionsForRequest = new ArrayList<>();
        for (String permission : permissions) {
            if (isPermissionNotGranted(permission)) {
                permissionsForRequest.add(permission);
            }
        }
        return permissionsForRequest.toArray(new String[permissionsForRequest.size()]);
    }

    @SuppressLint("NewApi")
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            for (String permission : permissions) {
                if (isPermissionNotGranted(permission)) {
                    runDeniedOrNeverAskAgain(permission);
                    return;
                }
            }
        }
        successListener.run();
        unbind();
    }

    @SuppressLint("NewApi")
    private void runDeniedOrNeverAskAgain(String permission) {
        if (isNeverAskAgain(permission)) {
            runNeverAskAgain();
        } else {
            deniedListener.run();
        }
        unbind();
    }

    private void runNeverAskAgain() {
        if (neverAskAgainListener != null) {
            neverAskAgainListener.run();
        }
    }

    private boolean isPermissionNotGranted(String permission) {
        if (activity != null) {
            return ActivityCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED;
        } else {
            return ActivityCompat.checkSelfPermission(fragment.getContext(), permission) != PackageManager.PERMISSION_GRANTED;
        }
    }

    @SuppressLint("NewApi")
    private boolean isNeverAskAgain(String permission) {
        if (activity != null) {
            return !activity.shouldShowRequestPermissionRationale(permission);
        } else {
            return !fragment.shouldShowRequestPermissionRationale(permission);
        }
    }

    public void startApplicationSettingsActivity() {
        final Context context = getContext();
        final Intent intent = new Intent();
        final Uri uri = Uri.fromParts("package", context.getPackageName(), null);
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(uri);
        context.startActivity(intent);
    }

    @Deprecated
    public void unsubscribe() {
        unbind();
    }

    private void unbind() {
        deniedListener = null;
        successListener = null;
        if (dialogBeforeRunBuilder != null) {
            dialogBeforeRunBuilder = null;
            dialogBeforeAskPositiveButton = DIALOG_WITHOUT_CUSTOM_COLOR;
        }
        if (neverAskAgainListener != null) {
            neverAskAgainListener = null;
        }
    }
}
