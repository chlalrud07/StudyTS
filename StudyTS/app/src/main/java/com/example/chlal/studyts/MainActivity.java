package com.example.chlal.studyts;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void part1(View v){
        Intent intent_part1 = new Intent(getApplicationContext(), StudyPart1Activity.class);
        startActivity(intent_part1);
    }

    public void part2(View v){
        Intent intent_part2 = new Intent(getApplicationContext(), StudyPart2Activity.class);
        startActivity(intent_part2);
    }

    public void part3(View v){

    }

    public void part4(View v){

    }

    public void part5(View v){

    }

    public void part6(View v){

    }

}
