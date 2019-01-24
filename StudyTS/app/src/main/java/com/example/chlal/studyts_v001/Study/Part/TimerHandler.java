package com.example.chlal.studyts_v001.Study.Part;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class TimerHandler {
    private int ready;
    private int run;
    private TextView tv;

    int cnt = 2;
    int time;

    public TimerHandler(int ready, int run, TextView tv) {
        this.ready = ready;
        this.run = run;
        this.tv = tv;
        time = ready;
        mHandler.sendEmptyMessage(0);
    }
    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            if(time>-1 && cnt > 0){
                if (time < 10) tv.setText("00:0" + time);
                else tv.setText("00:" + time);
                mHandler.sendEmptyMessageDelayed(0, 1000);
                time--;
            }else {
                if(cnt == 2){
                    time = run;
                    cnt--;
                    mHandler.removeMessages(0);
                    mHandler.sendEmptyMessage(0);
                }
            }
        }
    };
}