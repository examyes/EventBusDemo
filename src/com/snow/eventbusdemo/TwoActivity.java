package com.snow.eventbusdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import de.greenrobot.event.EventBus;

public class TwoActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_two);
		findViewById(R.id.bt1).setOnClickListener(this);
		System.out.println("MainThreadId:" + Thread.currentThread().getId());

	}

	public void postEvent() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				//推送一个事件
				EventBus.getDefault().post(new AppEvent(1));
				System.out.println("PostThreadId:"
						+ Thread.currentThread().getId());

			}
		}).start();

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt1:
			postEvent();
			break;

		default:
			break;
		}

	}

}
