package com.jdkgroup.baseclass;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jdkgroup.constant.AppConstant;
import com.jdkgroup.interacter.disposablemanager.DisposableManager;
import com.jdkgroup.interviewdemo.R;
import com.jdkgroup.utils.AppUtils;

import org.json.JSONObject;
import org.parceler.Parcels;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class BaseActivity extends AppCompatActivity {

    private Unbinder unbinder;

    public ProgressBar progressToolbar;
    private Dialog progressDialog;
    private Intent intent;
    private HashMap<String, String> params;
    private Calendar calendar;

    private LinearLayoutManager layoutManager;
    protected int recyclerViewLinearLayout = 0, recyclerViewGridLayout = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

    @Override
    protected void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        DisposableManager.dispose();
        super.onDestroy();
    }

    protected void bindViews() {
        unbinder = ButterKnife.bind(this);
    }

    protected void setContentViewWithoutInject(int layoutResId) {
        super.setContentView(layoutResId);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            hideSoftKeyboard();
            onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    protected void toolBarSetFont(final Toolbar toolBar) {
        for (int i = 0; i < toolBar.getChildCount(); i++) {
            View view = toolBar.getChildAt(i);
            if (view instanceof TextView) {
                TextView tv = (TextView) view;
                Typeface titleFont = Typeface.createFromAsset(getApplicationContext().getAssets(), AppConstant.FONT_AILERON_SEMIBOLD);
                if (tv.getText().equals(toolBar.getTitle())) {
                    tv.setTypeface(titleFont);
                    break;
                }
            }
        }
    }

    protected void hideSoftKeyboard() {
        try {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
            imm.hideSoftInputFromWindow(getWindow().getDecorView().getApplicationWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //SCREEN SIZE
    protected int[] getScreenSize(Activity activity) {
        Point size = new Point();
        WindowManager w = activity.getWindowManager();

        w.getDefaultDisplay().getSize(size);
        return new int[]{size.x, size.y};
    }

    protected void setFullScreen(Activity activity) {
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    protected void showKeyboard(Activity activity, AppCompatEditText appCompatEditText) {
        Context context = activity;
        try {
            if (context != null) {
                InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(appCompatEditText, InputMethodManager.SHOW_IMPLICIT);
            }
        } catch (Exception e) {
            AppUtils.loge("Exception on  show " + e.toString());
        }
    }

    protected void requestEditTextFocus(Activity activity, AppCompatEditText view) {
        view.requestFocus();
        showKeyboard(activity, view);
    }

    public HashMap<String, String> getDefaultParameter() {
        params = new HashMap<>();
        return params;
    }

    public HashMap<String, String> getDefaultParamWithIdAndToken() {
        params = getDefaultParameter();
        return params;
    }

    protected Map<String, String> checkParams(Map<String, String> map) {
        Iterator<Map.Entry<String, String>> entryIterator = map.entrySet().iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<String, String> pairs = entryIterator.next();
            if (pairs.getValue() == null) {
                map.put(pairs.getKey(), "");
            }
        }
        return map;
    }

    public void showProgressDialog(final boolean show) {
        //Show Progress bar here
        if (show) {
            showProgressDialog();
        } else {
            hideProgressDialog();
        }
    }

    public void showProgressToolBar(final boolean show, final View view) {
        if (show) {
            progressToolbar.setVisibility(View.VISIBLE);
            if (view != null)
                view.setVisibility(View.GONE);

        } else {
            progressToolbar.setVisibility(View.GONE);
            if (view != null)
                view.setVisibility(View.VISIBLE);
        }
    }

    protected final void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new Dialog(this);
        } else {
            return;
        }
        View view = LayoutInflater.from(this).inflate(R.layout.progressbar_dialog, null, false);

        AppCompatImageView appIvProgressBar = view.findViewById(R.id.appIvProgressBar);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.progress_anim);
        animation.setDuration(1500);
        appIvProgressBar.startAnimation(animation);

        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.setContentView(view);
        Window window = progressDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(ContextCompat.getDrawable(this, android.R.color.transparent));
        }
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    /**
     * hide progress bar
     */
    protected final void hideProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public Activity getActivity() {
        return this;
    }

    @Override
    protected void attachBaseContext(final Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public boolean hasInternetWithoutMessage() {
        return hasInternet();
    }

    protected void showNoInternet() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getApplicationContext());
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_no_internet, null);

        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.setCancelable(false);

        dialogView.findViewById(R.id.txtOkay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }

    protected void showSnackBar(CoordinatorLayout coordinatorLayout, String message) {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    //TODO RECYCLERVIEW
    protected LinearLayoutManager setRecyclerView(RecyclerView recyclerView, int spanCount, int no) {
        switch (no) {
            case 0:
                layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setHasFixedSize(true);
                break;

            case 1:
                layoutManager = new GridLayoutManager(getActivity(), spanCount);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setHasFixedSize(true);
                break;
        }
        return layoutManager;
    }

    //TODO GSON
    protected String getToJson(List<?> alData) {
        return new Gson().toJson(alData);
    }

    protected String getToJsonClass(Object src) {
        return new Gson().toJson(src);
    }

    protected <T> T getFromJson(String str, Class<T> classType) {
        return new Gson().fromJson(str, classType);
    }

    protected <T> T fromJson(File file, Class<T> clazz) throws Exception {
        T data = new Gson().fromJson(new FileReader(file.getAbsoluteFile()), clazz);
        return data;
    }

    protected Gson switchGson(int param) {
        switch (param) {
            case 1:
                Gson gson = new GsonBuilder().create();
                return gson;

            case 2: //FIRST CHARACTER UPPER CAMEL
                gson = new GsonBuilder().
                        disableHtmlEscaping().
                        setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).
                        setPrettyPrinting().serializeNulls().
                        create();
                return gson;

            default:
                break;
        }
        return null;
    }

    //Parcel
    protected void launchIsClearParcelable(final Class<?> classType, final Bundle bundle, final int status) {
        intent = new Intent(this, classType);
        if (status == 0) {
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        intent.putExtra("bundle", bundle);
        startActivity(intent);
    }

    protected void launchParcel(final Class<?> classType, final Bundle data, final int status) {
        intent = new Intent(getActivity(), classType);
        if (status == 0) {
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        }
        intent.putExtras(data);
        startActivity(intent);
    }

    /* TODO LAUNCH ACTIVITY */
    /*
     * Bundle bundle = new Bundle();
     * bundle.putParcelable(bundleName, Parcels.wrap(alData));
     * */
    protected <T> T getParcelable(String bundleName) {
        return Parcels.unwrap(getActivity().getIntent().getParcelableExtra(bundleName));
    }

    protected void launch(Class<?> classType, final Bundle bundle, int addFlag) {
        switch (addFlag) {
            case 1: //NO BUNDLE AND NO CLEAR
                intent = new Intent(getActivity(), classType);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                break;

            case 2: //NO BUNDLE AND CLEAR ALL HISTORY
                intent = new Intent(getActivity(), classType);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;

            case 3: //BUNDLE AND NO CLEAR
                intent = new Intent(getActivity(), classType);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
                break;

            case 4: //BUNDLE AND CLEAR ALL HISTORY
                intent = new Intent(getActivity(), classType);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
                break;
        }
    }

    protected <T> List getUnionList(List<T> first, List<T> last) {
        if (isNotEmptyList(first) && isNotEmptyList(last)) {
            first.addAll(last);
            return first;
        } else if (isNotEmptyList(first) && !isNotEmptyList(last)) {
            return first;
        }
        return last;
    }

    protected boolean isNotEmptyList(List list) {
        return list != null && !list.isEmpty();
    }

    protected boolean hasLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    protected boolean hasM() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    /* TODO LAUNCH ACTIVITY/FRAGMENT ANIMATION*/
    protected void intentOpenBrowser(final String url) {
        if (hasInternet()) {
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        } else {
            AppUtils.showToast(getActivity(), getString(R.string.no_internet_message));
        }
    }

    protected boolean hasInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        @SuppressLint("MissingPermission") NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (!(networkInfo != null && networkInfo.isConnectedOrConnecting())) {
            AppUtils.showToast(getActivity(), getActivity().getString(R.string.no_internet_message));
            return false;
        }
        return true;
    }

    protected InputFilter decimalPointAfterBeforeAmount(final int maxDigitsBeforeDecimalPoint, final int maxDigitsAfterDecimalPoint) {
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                StringBuilder builder = new StringBuilder(dest);
                builder.replace(dstart, dend, source.subSequence(start, end).toString());
                if (!builder.toString().matches("(([1-9]{1})([0-9]{0," + (maxDigitsBeforeDecimalPoint - 1) + "})?)?(\\.[0-9]{0," + maxDigitsAfterDecimalPoint + "})?")) {
                    if (source.length() == 0)
                        return dest.subSequence(dstart, dend);
                    return "";
                }
                return null;
            }
        };

        return filter;
    }

    //TODO FILE SAVE
    protected Bitmap getImageFileFromSDCard(String filename) {
        Bitmap bitmap = null;
        File imageFile = new File(Environment.getExternalStorageDirectory() + File.separator + filename);
        try {
            FileInputStream fis = new FileInputStream(imageFile);
            bitmap = BitmapFactory.decodeStream(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    protected void getSaveImageSDCard(Bitmap bitmap, String filename) {
        File fileMakeDirectory = new File(Environment.getExternalStorageDirectory() + File.separator + AppConstant.FOLDER_NAME);
        fileMakeDirectory.mkdirs();

        File file = new File(fileMakeDirectory, filename);
        if (file.exists()) file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void appExist() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this, R.style.AlertDialog);
        alert.setTitle(R.string.app_name);
        alert.setMessage(R.string.dialog_message_app_exist);
        alert.setPositiveButton(R.string.dialog_ok, (dialog, id) -> BaseActivity.this.finish());
        alert.setNegativeButton(R.string.dialog_cancel, null);
        alert.show();
    }

    protected void getFileDelete(String fileName) {
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + AppConstant.FOLDER_NAME + File.separator + fileName);
        boolean deleted = file.delete();
        if (deleted == true)
            System.out.println("Tag" + "Delete Successfully");
        System.out.println("Tag" + "Delete no Successfully");
    }

    protected Map<String, String> getLetter() {
        Map<String, String> lettermap = new HashMap() {
            {
                for (char ch = 'A'; ch <= 'Z'; ++ch) {
                    put(String.valueOf(ch), String.valueOf(ch));
                }
            }
        };
        return lettermap;
    }

    protected String readFileFromAssets(final String fileName, final String extension) throws Exception {
        String str = null;
        try {
            InputStream is = getActivity().getAssets().open("json/" + fileName + "." + extension);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            str = new String(buffer, "UTF-8");
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return str;
    }

    private JSONObject mapConvertToJSON(final Map map) {
        JSONObject jsonobject = new JSONObject();
        try {
            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry pair = (Map.Entry) iterator.next();

                jsonobject.put(String.valueOf(pair.getKey()), String.valueOf(pair.getValue()));
            }
        } catch (Exception ex) {
        }

        return jsonobject;
    }

    public void DatePicker(AppCompatTextView appCompatTextView) {
        calendar = Calendar.getInstance();
        int mYear, mMonth, mDay;
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year, monthOfYear, dayOfMonth) -> timePicker(dayOfMonth, (monthOfYear + 1), year, appCompatTextView), mYear, mMonth, mDay);
        datePickerDialog.show();
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
    }

    protected void timePicker(int selectDayOfMonth, int selectMonthOfYear, int selectYear, AppCompatTextView appCompatTextView) {
        calendar = Calendar.getInstance();
        int mHour = calendar.get(Calendar.HOUR_OF_DAY);
        int mMinute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                (view, hourOfDay, minute) -> {
                    String ampm;
                    if (hourOfDay < 12 && hourOfDay >= 0) {
                        ampm = " AM";
                        if (hourOfDay == 0) {
                            hourOfDay = 12;
                        }
                    } else {
                        hourOfDay -= 12;
                        if (hourOfDay == 0) {
                            hourOfDay = 12;
                        }
                        ampm = " PM";
                    }
                    appCompatTextView.setText(new StringBuilder().append(selectDayOfMonth + "-" + selectMonthOfYear + "-" + selectYear + " " + pad(hourOfDay)).append(":").append(pad(minute)) + ampm);
                }, mHour, mMinute, false);
        timePickerDialog.updateTime(mHour, mHour);
        timePickerDialog.show();
    }

    private String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    protected boolean IsHasSDCard() {
        String status = Environment.getExternalStorageState();
        return status.equals(Environment.MEDIA_MOUNTED);
    }

    //For take screenshot with status bar return Bitmap
    protected Bitmap nbGetScreenShotWithStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        int width = getScreenSize(activity)[0];
        int height = getScreenSize(activity)[1];
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
        view.destroyDrawingCache();
        return bp;
    }

    //For take screenshot without status bar return Bitmap
    protected Bitmap nbGetScreenShotWithoutStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        int width = getScreenSize(activity)[0];
        int height = getScreenSize(activity)[1];
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height - statusBarHeight);
        view.destroyDrawingCache();
        return bp;
    }

    //For Get the screen dimensions
    private int[] getScreenSize() {
        Point size = new Point();
        WindowManager w = getActivity().getWindowManager();

        w.getDefaultDisplay().getSize(size);
        return new int[]{size.x, size.y};
    }

    //DISABLE SCREEN CAPTURE
    protected void disableScreenshotFunctionality() {
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
    }

    protected String getDateDifference(Date startDate, Date endDate) {
        try {
            long different = endDate.getTime() - startDate.getTime();
            if (different <= 0) {
                return "0";
            }
            long secondsInMilli = 1000;
            long minutesInMilli = secondsInMilli * 60;
            long elapsedMinutes = different / minutesInMilli;
            different = different % minutesInMilli;

            long elapsedSeconds = different / secondsInMilli;

            String mSec = "";
            if (elapsedSeconds <= 9)
                mSec = "0";
            return " 0" + elapsedMinutes + " : " + mSec + elapsedSeconds + "";
            // return elapsedMinutes + " min " + elapsedSeconds + " Sec";

        } catch (Exception e) {
            return "0";
        }
    }
}

