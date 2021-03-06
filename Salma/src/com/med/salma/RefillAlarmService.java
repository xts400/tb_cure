package com.med.salma;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class RefillAlarmService extends IntentService {

	public RefillAlarmService() {
		super("RefillAlarmService");
	}

	@Override
	public void onHandleIntent(Intent intent) {			
		sendNotification("Please refill your medicine!");
	}

	private void sendNotification(String msg) {
		Log.d("RefillAlarmService", "Preparing to send notification...: " + msg);
//		Intent ignore_intent = new Intent().setClass(getApplicationContext(),
//				NonAdherenceReportsListActivity.class);
//		Intent accept_intent = new Intent().setClass(getApplicationContext(),
//				VideoCaptureActivity.class);
//
//		PendingIntent ignore_pintent = PendingIntent.getActivity(this, 0,
//				ignore_intent, 0);
//		PendingIntent accept_pintent = PendingIntent.getActivity(this, 0,
//				accept_intent, 0);

		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				this);
		mBuilder.setSmallIcon(R.drawable.ic_launcher);
		mBuilder.setContentTitle("Medication Refill");
		mBuilder.setContentText(msg);
		mBuilder.setAutoCancel(true);

//		mBuilder.addAction(android.R.drawable.ic_menu_close_clear_cancel,
//				"Ignore", ignore_pintent);
//		mBuilder.addAction(android.R.drawable.ic_menu_camera, "Accept",
//				accept_pintent);
		Intent resultIntent = new Intent(this, MainActivity.class);

		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		stackBuilder.addParentStack(ReminderListActivity.class);
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
				PendingIntent.FLAG_UPDATE_CURRENT);
		mBuilder.setContentIntent(resultPendingIntent);
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(0, mBuilder.build());
		Log.d("AlarmService", "Notification sent.");
	}

}