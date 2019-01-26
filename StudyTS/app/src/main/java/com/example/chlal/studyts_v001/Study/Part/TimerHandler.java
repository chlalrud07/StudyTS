package com.example.chlal.studyts_v001.Study.Part;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

class TimerHandler {
    private int ready;
    private int run;
    private TextView tv;
    private int cnt = 2;

    TimerHandler(int ready, int run, TextView tv) {
        this.ready = ready;
        this.run = run;
        this.tv = tv;
        mHandler.sendEmptyMessage(0);
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressLint("SetTextI18n")
        public void handleMessage(Message msg) {
            if(ready>-1 && cnt > 0){
                if (ready < 10) tv.setText("00:0" + ready);
                else tv.setText("00:" + ready);
                mHandler.sendEmptyMessageDelayed(0, 1000);
                ready--;
            }else {
                if(cnt == 2){
                    ready = run;
                    cnt--;
                    mHandler.removeMessages(0);
                    mHandler.sendEmptyMessage(0);
                }
            }
        }
    };
}