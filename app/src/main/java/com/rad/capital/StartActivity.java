package com.rad.capital;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class StartActivity extends Activity {

    Init init;
    Intent intent;
    Saver saver;

    AlertDialog.Builder cont;


    public static String TAG = "ADEPT";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        init = new Init(this);
        intent = new Intent(this, GameActivity.class);
        intent.putExtra("init", init);

        saver = new Saver(this);


         cont = new AlertDialog.Builder(this);
          cont.setMessage(getString(R.string.saved));

         cont.setPositiveButton(getString(R.string.save0), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {

                init.load(saver);
                intent.putExtra("isSave", true);
                startActivity(intent);

            }
        });

        cont.setNegativeButton(getString(R.string.save1), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {

                saver.delete("g");
                saver.apply();
                init.restart();
                startActivity(intent);

            }
        });



    }





    public void Start(View v) {

        switch (v.getId()) {

            case R.id.game:
                long time = System.currentTimeMillis();
                intent.removeExtra("isSave");

                if(saver.getInt("g") != -1) cont.show();
                else{
                  init.restart();
                  startActivity(intent);
                }
                Log.i(TAG, "timeGame -- " + (System.currentTimeMillis() - time));
            break;


            case R.id.set:
                long time2 = System.currentTimeMillis();
                startActivity(new Intent(this, SettingsActivity.class));
                Log.i(TAG, "time -- " + (System.currentTimeMillis() - time2));
            break;

            case R.id.achiv:
            //  startActivity(new Intent(this, AchievActivity.class));
            break;



        }
    }



    @Override
    public void onBackPressed() {
        AlertDialog.Builder ad = new AlertDialog.Builder(this);

        ad.setMessage("Вы действительно хотите выйти?");

        ad.setPositiveButton("ДА", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                System.exit(0);

            }
        });

        ad.setNegativeButton("НЕТ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {

            }
        });

        ad.show();

    }
}
