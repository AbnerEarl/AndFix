package com.frank.ycj520.testcplusplus;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private Button btnCaculate,btnFix;
    private TextView tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());

        tvShow=(TextView)findViewById(R.id.tv_show);
        btnCaculate=(Button)findViewById(R.id.btn_compute);
        btnFix=(Button)findViewById(R.id.btn_fix);

        btnCaculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                caculate();
            }
        });

        btnFix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fix();
            }
        });
    }

    private void caculate() {
        Caculator caculator=new Caculator();
        int result=caculator.caculate();
        tvShow.setText("100/10="+result);
    }

    private void fix() {
        DxManager dxManager=new DxManager(this);
        dxManager.loadDex(new File(Environment.getExternalStorageDirectory(),"out.dex"));
    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();



}
