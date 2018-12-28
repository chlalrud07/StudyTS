package com.example.chlal.studyts;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StudyPart2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_part2);

        ActionBar ab = getSupportActionBar() ;
        ab.setTitle("PART2") ;
    }
}
