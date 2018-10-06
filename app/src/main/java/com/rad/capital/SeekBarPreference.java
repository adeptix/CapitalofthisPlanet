package com.rad.capital;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceViewHolder;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;


public class SeekBarPreference extends Preference {


    public SeekBarPreference(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SeekBarPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayoutResource(R.layout.seekbar);
    }


    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {

        ((TextView)(holder.itemView.findViewById(R.id.seek_t))).setText(getTitle());

         if (getKey().equals("set_music")){
              ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams();
              marginParams.setMargins(0, 150, 0, 0);
         }

         holder.itemView.setBackgroundColor(Color.parseColor("#9e0404"));


        final SeekBar seekBar = holder.itemView.findViewById(R.id.seekbar);
        seekBar.setProgress(getPersistedInt(75));

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               //change music volume
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                persistInt(seekBar.getProgress());
            }
        });



    }



}
