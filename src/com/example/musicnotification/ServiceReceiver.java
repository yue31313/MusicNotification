package com.example.musicnotification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class ServiceReceiver extends BroadcastReceiver {
	public static final String NOTIFICATION_ITEM_BUTTON_LAST 
	                        = "com.example.notification.ServiceReceiver.last";//----通知栏上一首按钮
	public static final String NOTIFICATION_ITEM_BUTTON_PLAY 
	                        = "com.example.notification.ServiceReceiver.play";//----通知栏播放按钮
	public static final String NOTIFICATION_ITEM_BUTTON_NEXT
	                        = "com.example.notification.ServiceReceiver.next";//----通知栏下一首按钮
	  @Override
	  public void onReceive(Context context, Intent intent) {
		  
	  String action = intent.getAction();
	   if (action.equals(NOTIFICATION_ITEM_BUTTON_LAST)) {//----通知栏播放按钮响应事件
	       Toast.makeText(context, "上一首", Toast.LENGTH_LONG).show();
	       
	    }
	   else  if (action.equals(NOTIFICATION_ITEM_BUTTON_PLAY)) {//----通知栏播放按钮响应事件
	       Toast.makeText(context, "暂停", Toast.LENGTH_LONG).show();
	       
	    }
	    else if (action.equals(NOTIFICATION_ITEM_BUTTON_NEXT)) {//----通知栏下一首按钮响应事件
	    	Toast.makeText(context, "下一首", Toast.LENGTH_LONG).show();
	    }
	  }
	}
