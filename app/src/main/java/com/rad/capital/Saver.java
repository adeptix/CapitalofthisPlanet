package com.rad.capital;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;


public class Saver {

    private SharedPreferences sp;
    private SharedPreferences.Editor ed;


    @SuppressLint("CommitPrefEdits")
    Saver(Context context) {
        sp = context.getSharedPreferences("save", Context.MODE_PRIVATE);
        ed = sp.edit();
    }


    public void saveInt(String key, int value){
        ed.putInt(key, value);
    }

    public int getInt(String key){
       return sp.getInt(key, -1);
    }

    public void apply(){
        ed.apply();
    }

    public void delete(String key){
        ed.remove(key);
    }


    public void saveArray(String key, ArrayList list){

        String joined = TextUtils.join(",", list);
        ed.putString(key, joined);
    }

    public ArrayList getArray(String key, boolean isNum){

        String saved = sp.getString(key , null);

           String[] mas = saved.split(",");

            if(isNum){
                ArrayList<Integer> ar = new ArrayList<>();

                for(String s : mas) ar.add(Integer.parseInt(s));

                return ar;
            }

        return new ArrayList<>(Arrays.asList(mas));
    }







}
