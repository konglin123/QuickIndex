package com.example.quickindexview;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends Activity {

    private QuickIndexView quickIndexView;
    private TextView tv;

    private Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        quickIndexView=findViewById(R.id.quickindex);
        tv=findViewById(R.id.tv);
        initListener();
    }

    private void initListener() {
        quickIndexView.setOnWordChangeListener(new QuickIndexView.OnWordChangeListener() {
            @Override
            public void onWordChange(String word) {
                tv.setVisibility(View.VISIBLE);
                tv.setText(word);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tv.setVisibility(View.GONE);
                    }
                },3000);
            }
        });
    }
}
