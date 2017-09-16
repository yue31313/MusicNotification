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
		
		
		receiver = new ServiceReceiver();//----注册广播
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(ServiceReceiver.NOTIFICATION_ITEM_BUTTON_LAST);
		intentFilter.addAction(ServiceReceiver.NOTIFICATION_ITEM_BUTTON_PLAY);
		intentFilter.addAction(ServiceReceiver.NOTIFICATION_ITEM_BUTTON_NEXT);
		
		registerReceiver(receiver, intentFilter);
		
		init();
	}

	private void init(){
		// 获取通知服务
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
				showCustomView("稻香-周杰伦");
				break;

			default:
				break;
			}
		}
	};
	
	private void showCustomView(String name) {
		RemoteViews remoteViews = new RemoteViews(getPackageName(),
				R.layout.music_notification);
		
		remoteViews.setTextViewText(R.id.title_music_name, name); //设置textview
		
		//设置按钮事件 -- 发送广播 --广播接收后进行对应的处理
		
		Intent buttonPlayIntent = new Intent(ServiceReceiver.NOTIFICATION_ITEM_BUTTON_LAST); //----设置通知栏按钮广播
		PendingIntent pendButtonPlayIntent = PendingIntent.getBroadcast(this, 0, buttonPlayIntent, 0);
		remoteViews.setOnClickPendingIntent(R.id.last_music, pendButtonPlayIntent);//----设置对应的按钮ID监控
		
		
		Intent buttonPlayIntent1 = new Intent(ServiceReceiver.NOTIFICATION_ITEM_BUTTON_PLAY); //----设置通知栏按钮广播
		PendingIntent pendButtonPlayIntent1 = PendingIntent.getBroadcast(this, 0, buttonPlayIntent1, 0);
		remoteViews.setOnClickPendingIntent(R.id.paly_pause_music, pendButtonPlayIntent1);//----设置对应的按钮ID监控
		
		Intent buttonPlayIntent2 = new Intent(ServiceReceiver.NOTIFICATION_ITEM_BUTTON_NEXT); //----设置通知栏按钮广播
		PendingIntent pendButtonPlayIntent2 = PendingIntent.getBroadcast(this, 0, buttonPlayIntent2, 0);
		remoteViews.setOnClickPendingIntent(R.id.next_music, pendButtonPlayIntent2);//----设置对应的按钮ID监控
		
		
		NotificationCompat.Builder builder = new Builder(MainActivity.this);
		builder.setContent(remoteViews).setSmallIcon(R.drawable.ic_launcher)
				.setLargeIcon(icon).setOngoing(true)
				.setTicker("music is playing");
		
		manager.notify(1, builder.build());
		
	
		
	}
}
