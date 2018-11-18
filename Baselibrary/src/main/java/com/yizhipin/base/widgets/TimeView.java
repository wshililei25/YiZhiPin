package com.yizhipin.base.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.yizhipin.base.R;


/**
 * Created by ${XiLei} on 2018/8/19.
 * 倒计时（天时分秒）
 */
public class TimeView extends android.support.v7.widget.AppCompatTextView {

    private long hours;
    private long minutes;
    private long seconds;
    private long diff;
    private long days;
    private long time = 0;

    public TimeView(Context context) {
        this(context, null);
    }

    public TimeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TimeView);
        diff = a.getInteger(R.styleable.TimeView_time, 0) * 1000;
        Log.d("TAG", "再打印  onCreate :" + diff);

        onCreate();
    }


    /**
     * 根据 attrs 设置时间开始
     */
    private void onCreate() {
        start();
    }

    //开始计时
    private void start() {

        handler.removeMessages(1);

        Log.d("TAG", "再打印  onCreate");
        Log.d("TAG", "再打印  setTime/..................................//////////////////////////");
        getTime();
        Message message = handler.obtainMessage(1);
        handler.sendMessageDelayed(message, 1000);
    }

    public void log() {
        Log.d("TAG", "再打印  log/////////////////////////////////////////////////////////////////");
    }


    /**
     * 设置事件
     *
     * @param time
     */
    public void setTime(long time) {
//        this.time = time * 1000;
        this.time = time;
    }

    final Handler handler = new Handler(Looper.getMainLooper()) {

        public void handleMessage(Message msg) {         // handle message
            Log.d("TAG", "再打印  handleMessage");
            switch (msg.what) {
                case 1:
                    setVisibility(View.VISIBLE);
                    diff = diff - 1000;
                    getShowTime();
                    if (diff > 0) {
                        Message message = handler.obtainMessage(1);
                        handler.sendMessageDelayed(message, 1000);
                    } else {
                        setVisibility(View.GONE);
                    }
                    break;
                default:
                    break;
            }
            Log.d("TAG", "再打印");
            super.handleMessage(msg);
        }
    };

    /**
     * 得到时间差
     */
    private void getTime() {
        Log.d("TAG", "再打印 :getTime");

        try {

            days = diff / (1000 * 60 * 60 * 24);
            hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
            seconds = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60) - minutes * (1000 * 60)) / (1000);
            setText("距离本轮结束还剩: " + days + "天" + hours + "小时" + minutes + "分" + seconds + "秒");

        } catch (Exception e) {
        }
    }

    /**
     * 获得要显示的时间
     */
    private void getShowTime() {
        Log.d("TAG", "再打印 :getShowTime");

        days = diff / (1000 * 60 * 60 * 24);
        hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
        seconds = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60) - minutes * (1000 * 60)) / (1000);
        setText("距离本轮结束还剩: " + days + "天" + hours + "小时" + minutes + "分" + seconds + "秒");
    }

    /**
     * 以之前设置的时间重新开始
     */
    public void reStart() {
        this.diff = this.time;
        start();
    }

    /**
     * 设置时间重新开始
     *
     * @param time 重新开始的事件
     */
    public void reStart(long time) {
        if (time > 0) {
            this.diff = time * 1000;
            Log.d("TAG", "+=========================" + diff);
        }
        start();
    }

}
