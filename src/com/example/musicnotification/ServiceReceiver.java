package com.example.musicnotification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class ServiceReceiver extends BroadcastReceiver {
	public static final String NOTIFICATION_ITEM_BUTTON_LAST 
	                        = "com.example.notification.ServiceReceiver.last";//----֪ͨ����һ�װ�ť
	public static final String NOTIFICATION_ITEM_BUTTON_PLAY 
	                        = "com.example.notification.ServiceReceiver.play";//----֪ͨ�����Ű�ť
	public static final String NOTIFICATION_ITEM_BUTTON_NEXT
	                        = "com.example.notification.ServiceReceiver.next";//----֪ͨ����һ�װ�ť
	  @Override
	  public void onReceive(Context context, Intent intent) {
		  
	  String action = intent.getAction();
	   if (action.equals(NOTIFICATION_ITEM_BUTTON_LAST)) {//----֪ͨ�����Ű�ť��Ӧ�¼�
	       Toast.makeText(context, "��һ��", Toast.LENGTH_LONG).show();
	       
	    }
	   else  if (action.equals(NOTIFICATION_ITEM_BUTTON_PLAY)) {//----֪ͨ�����Ű�ť��Ӧ�¼�
	       Toast.makeText(context, "��ͣ", Toast.LENGTH_LONG).show();
	       
	    }
	    else if (action.equals(NOTIFICATION_ITEM_BUTTON_NEXT)) {//----֪ͨ����һ�װ�ť��Ӧ�¼�
	    	Toast.makeText(context, "��һ��", Toast.LENGTH_LONG).show();
	    }
	  }
	}
