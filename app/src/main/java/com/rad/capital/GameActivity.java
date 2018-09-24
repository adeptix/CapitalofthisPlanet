package com.rad.capital;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class GameActivity extends Activity implements View.OnClickListener {


    TextView label, counter, title;

    ImageView image;

    LinearLayout buttonPanel;
    Button[] buttons = new Button[4];
    boolean isSave;

    Init init;

    Saver saver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        init = intent.getParcelableExtra("init");
        saver = new Saver(this);

        isSave = intent.getBooleanExtra("isSave", false);




        create();
        update();

    }




    private void create() {

        label = findViewById(R.id.label);
        counter = findViewById(R.id.counter);
        title = findViewById(R.id.title);

        image = findViewById(R.id.image);
        image.getLayoutParams().height = init.height;
        image.requestLayout();

        buttonPanel = findViewById(R.id.buttonPanel);


        int butWidth = (int)(0.8 * init.width);

        for (int i = 0; i < 4; i++) {
            buttons[i] = new Button(this);
            buttons[i].setOnClickListener(this);
            buttons[i].setWidth(butWidth);
            buttonPanel.addView(buttons[i]);
        }

    }

    @SuppressLint("SetTextI18n")
    private void update(){

        init.setItem();
        label.setText(""+init.getHp());
        counter.setText(Integer.toString(init.getG() + 1));
        title.setText(init.getNation());
        image.setImageResource(init.getImgs());

        ArrayList<String> ar = build();
        for (int i = 0; i < 4; i++) buttons[i].setText(ar.get(i));

          if(init.getG() > 0) {

             init.save(saver);
             saver.saveArray("but", ar);
             saver.apply();

          }

        init.inc();
    }

    private ArrayList<String> build(){

        if(isSave){
            isSave = false;
            return saver.getArray("but", false);
        }

        ArrayList<String> ar = new ArrayList<>(4);
        ar.add(init.getCapital());
        ar.add(init.getAnchor());

        String[] trash = init.getTrash();
        Random r = new Random();

        int x = 0;

        lb: while(x < 2) {
           String s = trash[r.nextInt(trash.length)];

           for(String str : ar)
               if(str.equals(s)) continue lb;

           ar.add(s);
           x++;
        }
        Collections.shuffle(ar);
        return ar;
    }


    @Override
    public void onClick(View v) {
        if( !((Button)v).getText().toString().equals(init.getCapital()) ){
            init.minusHP();
        }
        Intent intent = new Intent(this, ResultActivity.class);

        if(init.getHp() == -1) {
           //lose
            finish();
            startActivity(intent.putExtra("result", false));
            return;
        }

        if(init.getG() == init.getLength()){
            //win
            finish();
            startActivity(intent.putExtra("result", true));
            return;
        }

        update();
    }



    public void pause(View v){

    }

}
