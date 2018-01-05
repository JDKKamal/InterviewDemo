package com.jdkgroup.interviewdemo.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import java.util.List;

public class FragmentUtil {

    public final static String TAG_NAME_FRAGMENT = "ACTIVITY_FRAGMENT";

    public static Fragment getFragmentByTagName(FragmentManager fragmentManager, String fragmentTagName) {
        Fragment ret = null;

        // Get all Fragment list.
        List<Fragment> fragmentList = fragmentManager.getFragments();

        if (fragmentList != null) {
            int size = fragmentList.size();
            for (int i = 0; i < size; i++) {
                Fragment fragment = fragmentList.get(i);

                if (fragment != null) {
                    String fragmentTag = fragment.getTag();

                    // If Fragment tag name is equal then return it.
                    if (fragmentTag.equals(fragmentTagName)) {
                        ret = fragment;
                    }
                }
            }
        }

        return ret;
    }

    // Print fragment manager managed fragment in debug log.
    public static void printActivityFragmentList(FragmentManager fragmentManager) {
        // Get all Fragment list.
        List<Fragment> fragmentList = fragmentManager.getFragments();

        if (fragmentList != null) {
            int size = fragmentList.size();
            for (int i = 0; i < size; i++) {
                Fragment fragment = fragmentList.get(i);

                if (fragment != null) {
                    String fragmentTag = fragment.getTag();
                    Log.d(TAG_NAME_FRAGMENT, fragmentTag);
                }
            }

            Log.d(TAG_NAME_FRAGMENT, "***********************************");
        }
    }
}