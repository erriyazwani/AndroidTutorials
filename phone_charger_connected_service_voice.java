<!-- broadcast reciver -->
  @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action != null && action.equals(Intent.ACTION_POWER_CONNECTED)) {
            // Charger is connected
            // Do something here, such as display a message or update your app's state
            Log.e("Conneted","Success");
            MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.notification);
            mediaPlayer.setLooping(false);
            mediaPlayer.setVolume(0.5f, 0.5f);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    // Do something when playback is completed
                }
            });
            mediaPlayer.start();
//            mediaPlayer.release();
        }
    }
<!-- end -->
  
//   service code
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.WorkerThread;
import androidx.core.app.NotificationCompat;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {
    private static final int NOTIFICATION_ID = 12345;

    public MyService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        startForeground(NOTIFICATION_ID, new Notification());
        // Create a notification channel (required for API level 26 or higher)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "my_channel_id",
                    "My Channel",
                    NotificationManager.IMPORTANCE_HIGH
            );
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
            Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
        }
// Create a notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "my_channel_id")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("My Service")
                .setContentText("Running in foreground...");
        Notification notification = builder.build();

// Start the service in the foreground with the notification
        startForeground(1, notification);
        ChargerReceiver chargerReceiver = new ChargerReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_POWER_CONNECTED);
        registerReceiver(chargerReceiver, filter);
//        new Timer().scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//
//                Log.e("Shadow","hello");
//                //your method
//            }
//        }, 0, 2000);
        return START_STICKY;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        Toast.makeText(this, "App has been closed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
//   end

// mainactivity
 Intent intent = new Intent(this, MyService.class);
        startService(intent);
