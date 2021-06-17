package com.example.nonstopcutter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.DropBoxManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    Button right,left;
    ImageView Tree,Tree1,Tree2,Tree3,Tree4,Tree5,Tree6,newTree;
    TextView scoreTV;
    DbHelper DB;
    int score=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        scoreTV=findViewById(R.id.scoreTV);
        right = findViewById(R.id.buttonsag);
        left = findViewById(R.id.buttonsol);
        Tree=findViewById(R.id.Tree);
        Tree.setTag(R.drawable.chright);
        Tree1=findViewById(R.id.Tree1);
        Tree1.setTag(R.drawable.anadal);
        Tree2=findViewById(R.id.Tree1);
        Tree2.setTag(R.drawable.anadal);
        Tree3=findViewById(R.id.Tree3);
        Tree3.setTag(R.drawable.anadal);
        Tree4=findViewById(R.id.Tree4);
        Tree4.setTag(R.drawable.anadal);
        Tree5=findViewById(R.id.Tree5);
        Tree5.setTag(R.drawable.anadal);
        Tree6=findViewById(R.id.Tree6);
        Tree6.setTag(R.drawable.anadal);

        right.setOnClickListener(this);
        left.setOnClickListener(this);
        DB=new DbHelper(this);
        DB.getWritableDatabase();
    }
    @Override
    public void onClick(View view){
        if(right.getId()==view.getId()){
            //Sağ butonuna basılırsa

           if((int)Tree1.getTag()!=R.drawable.sagdal){
               //Karakter sağa geçti ama dal solda veya yok o yüzden devam...
               Tree.setImageResource(R.drawable.chright);
               Generate();
               updateScore();
           }
           else{
               DB.updatemaxscore(DB.getReadableDatabase(),score);
               Intent i= new Intent(MainActivity2.this,MainActivity3.class);
               int maxscore=DB.getmaxscore(DB.getReadableDatabase());
               i.putExtra("maxscore",maxscore);
               startActivity(i);
               //kaybetti
           }



        }
        else{
            //Sola basılırsa

            if((int)Tree1.getTag()!=R.drawable.soldal){
                //Karakter sola geçti ama dal sağda veya yok o yüzden devam...
                Tree.setImageResource(R.drawable.chleft);
                Generate();
                updateScore();
            }
            else{
                DB.updatemaxscore(DB.getReadableDatabase(),score);
                Intent i= new Intent(MainActivity2.this,MainActivity3.class);
                int maxscore=DB.getmaxscore(DB.getReadableDatabase());
                i.putExtra("maxscore",maxscore);
                startActivity(i);
            }

        }
    }
    public void Generate(){

        Random random= new Random();
        int k=random.nextInt(3);
        Tree1.setImageResource((int)Tree2.getTag());
        Tree1.setTag(Tree2.getTag());
        Tree2.setImageResource((int)Tree3.getTag());
        Tree2.setTag(Tree3.getTag());
        Tree3.setImageResource((int)Tree4.getTag());
        Tree3.setTag(Tree4.getTag());
        Tree4.setImageResource((int)Tree5.getTag());
        Tree4.setTag(Tree5.getTag());
        Tree5.setImageResource((int)Tree6.getTag());
        Tree5.setTag(Tree6.getTag());

        switch (k){
            case 0:
               newTree=Tree6;
               newTree.setImageResource(R.drawable.anadal);
               newTree.setTag(R.drawable.anadal);
               Tree6=newTree;
                break;
            case 1:
                newTree=Tree6;
                newTree.setImageResource(R.drawable.sagdal);
                newTree.setTag(R.drawable.sagdal);
                Tree6=newTree;
                break;
            case 2:
                newTree=Tree6;
                newTree.setImageResource(R.drawable.soldal);
                newTree.setTag(R.drawable.soldal);
                Tree6=newTree;
                break;
        }

    }
    public void updateScore(){
        score++;
        scoreTV.setText(getString(R.string.scoreTV)+score);
    }

}