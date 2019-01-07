package com.example.chlal.studyts_v001.Study.Part;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.example.chlal.studyts_v001.R;

public class StudyPart2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_part2);

        ActionBar ab = getSupportActionBar() ;
        ab.setTitle("PART2") ;
    }
}
