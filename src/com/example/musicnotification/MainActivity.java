package com.example.musicnotification;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RemoteViews;

public class MainActivity extends Activity {
	private Button mComeOnBtn;
	private NotificationManager manager;
	private Bitmap icon;
	private ServiceReceiver receiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		receiver = new ServiceReceiver();//----ע��㲥
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(ServiceReceiver.NOTIFICATION_ITEM_BUTTON_LAST);
		intentFilter.addAction(ServiceReceiver.NOTIFICATION_ITEM_BUTTON_PLAY);
		intentFilter.addAction(ServiceReceiver.NOTIFICATION_ITEM_BUTTON_NEXT);
		
		registerReceiver(receiver, intentFilter);
		
		init();
	}

	private void init(){
		// ��ȡ֪ͨ����
		manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		
		mComeOnBtn = (Button) findViewById(R.id.main_come_on_btn);
		mComeOnBtn.setOnClickListener(onClickListener);
	}
	
	private View.OnClickListener  onClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.main_come_on_btn:
				showCustomView("����-�ܽ���");
				break;

			default:
				break;
			}
		}
	};
	
	private void showCustomView(String name) {
		RemoteViews remoteViews = new RemoteViews(getPackageName(),
				R.layout.music_notification);
		
		remoteViews.setTextViewText(R.id.title_music_name, name); //����textview
		
		//���ð�ť�¼� -- ���͹㲥 --�㲥���պ���ж�Ӧ�Ĵ���
		
		Intent buttonPlayIntent = new Intent(ServiceReceiver.NOTIFICATION_ITEM_BUTTON_LAST); //----����֪ͨ����ť�㲥
		PendingIntent pendButtonPlayIntent = PendingIntent.getBroadcast(this, 0, buttonPlayIntent, 0);
		remoteViews.setOnClickPendingIntent(R.id.last_music, pendButtonPlayIntent);//----���ö�Ӧ�İ�ťID���
		
		
		Intent buttonPlayIntent1 = new Intent(ServiceReceiver.NOTIFICATION_ITEM_BUTTON_PLAY); //----����֪ͨ����ť�㲥
		PendingIntent pendButtonPlayIntent1 = PendingIntent.getBroadcast(this, 0, buttonPlayIntent1, 0);
		remoteViews.setOnClickPendingIntent(R.id.paly_pause_music, pendButtonPlayIntent1);//----���ö�Ӧ�İ�ťID���
		
		Intent buttonPlayIntent2 = new Intent(ServiceReceiver.NOTIFICATION_ITEM_BUTTON_NEXT); //----����֪ͨ����ť�㲥
		PendingIntent pendButtonPlayIntent2 = PendingIntent.getBroadcast(this, 0, buttonPlayIntent2, 0);
		remoteViews.setOnClickPendingIntent(R.id.next_music, pendButtonPlayIntent2);//----���ö�Ӧ�İ�ťID���
		
		
		NotificationCompat.Builder builder = new Builder(MainActivity.this);
		builder.setContent(remoteViews).setSmallIcon(R.drawable.ic_launcher)
				.setLargeIcon(icon).setOngoing(true)
				.setTicker("music is playing");
		
		manager.notify(1, builder.build());
		
	
		
	}
}
