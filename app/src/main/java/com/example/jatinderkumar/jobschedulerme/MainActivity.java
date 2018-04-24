package com.example.jatinderkumar.jobschedulerme;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.pm.ComponentInfo;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    JobScheduler scheduler;
    JobInfo jobInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }
    public void startJob(View view)
    {
        Toast.makeText(MainActivity.this,"Start Job",Toast.LENGTH_SHORT).show();
        scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        ComponentName info = new ComponentName(this, JobMe.class);
        JobInfo.Builder builder = new JobInfo.Builder(0,info);
        //builder.setRequiresCharging(true);
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        builder.setRequiresDeviceIdle(false);
        jobInfo = builder.build();
        scheduler.schedule(jobInfo);
    }
    public void stopJob(View view)
    {
        if (scheduler!= null)
        {
            scheduler.cancelAll();
            scheduler = null;
            Toast.makeText(MainActivity.this,"Job Stopped",Toast.LENGTH_SHORT).show();
        }
    }
}
