package com.example.nonstopcutter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity3 extends Activity implements View.OnClickListener {

    Button playAgainBt;
    TextView maxScoreTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        playAgainBt=findViewById(R.id.playAgainBt);
        playAgainBt.setOnClickListener(this);
        maxScoreTv=findViewById(R.id.maxScoreTv);

        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        getWindow().setLayout((int)(width*.6),(int)(height*.6));

        Intent intent = getIntent();
        int maxscore = intent.getExtras().getInt("maxscore");
        maxScoreTv.setText(getString(R.string.maxScoreTv)+""+maxscore);

    }

    @Override
    public void onClick(View v) {
        if(playAgainBt.getId()==v.getId()){
            Intent i= new Intent(MainActivity3.this,MainActivity2.class);
            startActivity(i);
        }
    }
}