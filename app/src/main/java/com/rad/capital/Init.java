package com.rad.capital;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

class Init implements Parcelable{

    private int x;

    private int g;
    private int hp;

    int height, width;

    private String [] nation, capital, anchor, ocean, africa, latin, canada, usa, europe, asia;
    int[] imgs;

    private Context context;

    ArrayList<Integer> world;

    Init(Context context) {
        this.context = context;

        arrayInit();

        int length = nation.length;
        world = new ArrayList<>(length);

        for (int x = 0; x < length; x++) {
            world.add(x);
        }



        sizeInit();

    }

    public void restart(){
        g = 0;
        hp = 3;

        Collections.shuffle(world);
    }

    private void sizeInit(){
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);

        height = dm.heightPixels / 3;
        width = (int)(1.6 * height);
    }

    private void arrayInit(){
        Resources res = context.getResources();

        nation = res.getStringArray(R.array.world);
        capital = res.getStringArray(R.array.capitals);
        anchor = res.getStringArray(R.array.anchor);

        ocean = res.getStringArray(R.array.trash_ocean);
        africa = res.getStringArray(R.array.trash_africa);
        latin = res.getStringArray(R.array.trash_latin);
        canada = res.getStringArray(R.array.trash_canada);
        usa = res.getStringArray(R.array.trash_usa);
        europe = res.getStringArray(R.array.trash_europe);
        asia = res.getStringArray(R.array.trash_asia);


        TypedArray ids = res.obtainTypedArray(R.array.imgs);
        int len = ids.length();
        imgs = new int[len];

        for (int i = 0; i < len; i++)
            imgs[i] = ids.getResourceId(i, 0);

        ids.recycle();

    }

    public void setItem(){
        x = world.get(g);
    }

    public int getG() {
        return g;
    }

    public int getHp() {
        return hp;
    }

    public String getNation() {
        return  nation[x];
    }

    public int getImgs() {
        return imgs[x];
    }

    public String getCapital() {
        return capital[x];
    }

    public String getAnchor() {
        return anchor[x];
    }

    public String[] getTrash() {
        if(x < 15) return ocean;
        if(x < 41) return africa;
        if(x < 57) return latin;
        if(x == 57) return canada;
        if(x == 58) return usa;
        if(x < 102) return europe;
        return asia;
    }

    public void inc(){
        g++;
    }

    public void minusHP(){
        hp--;
    }

    public int getLength(){
        return nation.length;
    }


    public void save(Saver saver){
        saver.saveInt("g", g);
        saver.saveInt("hp", hp);

        if (g == 1) saver.saveArray("world", world);

        saver.apply();
    }

    public void load(Saver saver){
        g = saver.getInt("g");
        hp = saver.getInt("hp");

        world = saver.getArray("world", true);
    }


    protected Init(Parcel in) {
        g = in.readInt();
        hp = in.readInt();
        height = in.readInt();
        width = in.readInt();
        world = in.readArrayList(null);
        nation = in.createStringArray();
        capital = in.createStringArray();
        anchor = in.createStringArray();
        ocean = in.createStringArray();
        africa = in.createStringArray();
        latin = in.createStringArray();
        canada = in.createStringArray();
        usa = in.createStringArray();
        europe = in.createStringArray();
        asia = in.createStringArray();
        imgs = in.createIntArray();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(g);
        dest.writeInt(hp);
        dest.writeInt(height);
        dest.writeInt(width);
        dest.writeList(world);
        dest.writeStringArray(nation);
        dest.writeStringArray(capital);
        dest.writeStringArray(anchor);
        dest.writeStringArray(ocean);
        dest.writeStringArray(africa);
        dest.writeStringArray(latin);
        dest.writeStringArray(canada);
        dest.writeStringArray(usa);
        dest.writeStringArray(europe);
        dest.writeStringArray(asia);
        dest.writeIntArray(imgs);
    }


    public static final Creator<Init> CREATOR = new Creator<Init>() {
        @Override
        public Init createFromParcel(Parcel in) {
            return new Init(in);
        }

        @Override
        public Init[] newArray(int size) {
            return new Init[size];
        }
    };


}
