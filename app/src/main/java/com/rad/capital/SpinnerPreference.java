package com.rad.capital;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.preference.SwitchPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceViewHolder;
import android.support.v7.preference.SwitchPreferenceCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import android.widget.ImageView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;


public class SpinnerPreference extends Preference {

    private String[] entries;
    private String[] entryValues;
    private int[] entryImages;

    private final LayoutInflater layoutInflater;


    public SpinnerPreference(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpinnerPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayoutResource(R.layout.spinner);

        layoutInflater = LayoutInflater.from(getContext());

        entries = new String[]{"English", "Русский"};
        entryValues = new String[]{"en", "ru"};
        entryImages = new int[]{R.drawable.lg_flag1, R.drawable.lg_flag2};

    }


    private int select() {

        String value = getPersistedString(getContext().getResources().getString(R.string.lang_def));

        for (int i = 0; i < entryValues.length; i++) {
            if (value.equals(entryValues[i])) {
                return i;
            }
        }
       return 0;
    }


    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {

        ((TextView)(holder.itemView.findViewById(R.id.spin_t))).setText(getTitle());

        holder.itemView.setBackgroundColor(Color.parseColor("#0f7dec"));


        final CustomSpinner spinner = (CustomSpinner) holder.findViewById(R.id.spinner);

        View spinnerOverlay = holder.findViewById(R.id.spinner_overlay);
        spinnerOverlay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                spinner.performClick();
            }

        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner.performClick();
            }
        });


        ImageView img = (ImageView)holder.findViewById(R.id.sp_image);

        final ObjectAnimator rotateTo = ObjectAnimator.ofFloat(img, "rotation", 0f, 180f).setDuration(250);
        final ObjectAnimator rotateFrom = ObjectAnimator.ofFloat(img, "rotation", 180f, 0f).setDuration(250);

        spinner.setSpinnerEventsListener(new CustomSpinner.OnSpinnerEventsListener() {
            @Override
            public void onSpinnerOpened() {
                rotateTo.start();

            }

            @Override
            public void onSpinnerClosed() {
                rotateFrom.start();
            }
        });


        spinner.setAdapter(new SpinnerAdapter(){


            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = createDropDownView(position, parent);
                }

                bindDropDownView(position, convertView);
                return convertView;
            }

            @Override
            public void registerDataSetObserver(DataSetObserver observer) {

            }

            @Override
            public void unregisterDataSetObserver(DataSetObserver observer) {

            }

            @Override
            public int getCount() {
                return entries.length;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                return getDropDownView(position, convertView, parent);
            }

            @Override
            public int getItemViewType(int position) {
                return 0;
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

        });

        spinner.setSelection(select());

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                persistString(entryValues[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
    }

    private View createDropDownView(int position, ViewGroup parent){
        return layoutInflater.inflate(R.layout.spinner_dropdown_item, parent, false);
    }

    private void bindDropDownView(int position, View view){
        TextView textView = view.findViewById(R.id.textView1);
        textView.setText(entries[position]);

        ImageView imageView = view.findViewById(R.id.imageView1);
        imageView.setImageResource(entryImages[position]);
    }


}
