package com.example.thesamespace.serice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private MyService myService;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("printlog", "onServiceConnected");
            MyService.MyServiceBinder myServiceBinder = (MyService.MyServiceBinder) service;
            myServiceBinder.pause();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("printlog", "onServiceDisconnected");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_start = (Button) findViewById(R.id.btn_start);
        Button btn_stop = (Button) findViewById(R.id.btn_stop);
        Button btn_pause = (Button) findViewById(R.id.btn_pause);

        btn_start.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
        btn_pause.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MyService.class);
        switch (v.getId()) {
            case R.id.btn_start:
                bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
//                startService(intent);
                break;
            case R.id.btn_stop:
                unbindService(serviceConnection);
//                stopService(intent);
                break;
            case R.id.btn_pause:
                break;
        }
    }
}
