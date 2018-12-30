package com.example.chlal.studyts;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StudyPart1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_part1);

        ActionBar ab = getSupportActionBar() ;
        ab.setTitle("PART1") ;
    }
}
