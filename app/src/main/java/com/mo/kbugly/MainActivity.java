package com.mo.kbugly;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.tencent.bugly.crashreport.CrashReport;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.aaaaaaaaaa);
        textView.setText(CrashReport.getBuglyVersion(MainActivity.this));
        final String abc = null;
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CrashReport.testJavaCrash();
//                CrashReport.testANRCrash();
//                Log.i(abc.toString(), abc);
            }
        });
    }
}
