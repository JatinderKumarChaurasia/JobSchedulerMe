package com.example.jatinderkumar.jobschedulerme;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class JobMe extends JobService {
    final int NOTIFICATION_ID = 234;
    String CHANNEL_ID = "my_channel_01";

    @Override
    public boolean onStartJob(JobParameters params) {
        Toast.makeText(this,"Job Started",Toast.LENGTH_SHORT).show();
        Intent intent1 = new Intent(getApplicationContext(),MainActivity.class);
        PendingIntent intent = PendingIntent.getActivity(this,0,intent1,PendingIntent.FLAG_UPDATE_CURRENT);
       // AudioManager manager = (AudioManager) getSystemService(AUDIO_SERVICE);
        NotificationManager manager =(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            CharSequence name = "my_channel";
            String Description = "This is my channel";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
           mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mChannel.setShowBadge(false);
            manager.createNotificationChannel(mChannel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID);
        builder.setContentIntent(intent);
        builder.setContentTitle("Notification Building ");
        builder.setContentText("Notification.................");
        builder.setContentInfo("I am Happy");
        builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        builder.setSmallIcon(R.drawable.ic_assessment_black_24dp);
        builder.setAutoCancel(true);
        builder.setSound(soundUri);
        assert manager != null;
        manager.notify(NOTIFICATION_ID,builder.build());
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
