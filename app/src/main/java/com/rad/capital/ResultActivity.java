package com.rad.capital;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class ResultActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView tv = findViewById(R.id.result);

        boolean result = getIntent().getBooleanExtra("result", false);

        Saver saver = new Saver(this);
        saver.delete("g");
        saver.delete("hp");
        saver.apply();

        tv.setText(result ? "ПОБЕДА" : "ПОРАЖЕНИЕ");


    }


   /* public void Act (View v){

        switch(v.getId()){

            case R.id.restart :
                new Init();

                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        save.saveMas();
                    }
                };

                Thread thread = new Thread(runnable);
                thread.start();

                startActivity(new Intent(this, GameActivity.class));

                break;


            case R.id.menu :
                Intent intent = new Intent(this, StartActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;


        }

    }*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
