package com.example.chlal.studyts_v001.Study.Part;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.chlal.studyts_v001.R;
public class StudyPart2Activity extends AppCompatActivity {
    TextView timeText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_part2);
        timeText = (TextView) findViewById(R.id.timer_textView);
    }

    public void start(View v){
        TimerHandler th = new TimerHandler(30, 45, timeText);
        View b = findViewById(R.id.start_button);
        b.setVisibility(View.INVISIBLE);
    }
}

