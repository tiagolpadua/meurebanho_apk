package com.timsoft.meurebanho;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import java.io.File;

public class MeuRebanhoApp extends Application {

    private static Context mContext;

    public static final String DEFAULT_IMAGE_FILE_EXTENSION = ".jpg";

    public static final String ACTION = "ACTION";

    public static final String ACTION_ADD = "ACTION_ADD";
    public static final String ACTION_EDIT = "ACTION_EDIT";


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext(){
        return mContext;
    }

    public static File getMediaStorageDir() {
        return new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), getContext().getResources().getString(R.string.app_full_name));
    }
}
