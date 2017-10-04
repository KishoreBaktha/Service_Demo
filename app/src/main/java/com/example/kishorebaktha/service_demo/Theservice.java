package com.example.kishorebaktha.service_demo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by KISHORE BAKTHA on 4/18/2017.
 */

public class Theservice extends Service
{
    final class TheThread implements Runnable
    {
        int serviceId;
        TheThread(int serviceId)
        {
            this.serviceId=serviceId;
        }
        @Override
        public void run() {
            synchronized (this)
            {
                try {
                    wait(20000);//block service for 20s
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                stopSelf(this.serviceId);
            }
        }
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this,"SERVICE STARTED",Toast.LENGTH_SHORT).show();
        Thread thread=new Thread(new TheThread(startId));
        thread.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this,"SERVICE DESTROYED",Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
