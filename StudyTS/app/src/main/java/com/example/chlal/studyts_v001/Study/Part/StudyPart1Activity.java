package com.example.chlal.studyts_v001.Study.Part;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.chlal.studyts_v001.R;

import java.util.Timer;
import java.util.TimerTask;

public class StudyPart1Activity extends AppCompatActivity {
    TextView timeText;
    int time = 45;
    int cnt = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_part1);
        timeText = (TextView) findViewById(R.id.timer_textView);
    }

    public void start(View v){
        mHandler.sendEmptyMessage(0);
        View b = findViewById(R.id.start_button);
        b.setVisibility(View.INVISIBLE);
    }

    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            if(time>-1 && cnt > 0){
                if (time < 10) timeText.setText("00:0" + time);
                else timeText.setText("00:" + time);
                mHandler.sendEmptyMessageDelayed(0, 1000);
                time--;
            }else {
                if(cnt == 2){
                    time = 45;
                    cnt--;
                    mHandler.removeMessages(0);
                    mHandler.sendEmptyMessage(0);
                }else{

                }
            }
        }
    };
}
