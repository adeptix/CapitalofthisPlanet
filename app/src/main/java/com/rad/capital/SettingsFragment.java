package com.rad.capital;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;
import android.support.v7.widget.RecyclerView;
import android.util.LayoutDirection;
import android.util.Log;
import android.view.View;


public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener{


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.settings);

    }

     @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

         Log.i(StartActivity.TAG, "saving ...");

        switch (key){
            case "set_lang" :
                String lang = sharedPreferences.getString(key, null);
                Log.i(StartActivity.TAG, "lang -- " + lang);
                break;


        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        super.onPause();
    }

}
