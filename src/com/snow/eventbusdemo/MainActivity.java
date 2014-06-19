package com.snow.eventbusdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import de.greenrobot.event.EventBus;

public class MainActivity extends Activity implements OnClickListener {

	private TextView textView;
	private String str;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.bt1).setOnClickListener(this);
		textView = (TextView) findViewById(R.id.text);
		System.out.println("MainThreadId:" + Thread.currentThread().getId());

		// 注册订阅事件
		EventBus.getDefault().register(this);

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		// 注销订阅事件
		EventBus.getDefault().unregister(this);
	}

	/**
	 * 与事件源在同一线程
	 * @param event
	 */
	// public void onEvent(AppEvent event) {
	// System.out.println("onEvent:" + Thread.currentThread().getId());
	// if (event.getType() == 1) {
	// System.out.println("111111111111");
	// }
	// }

	/**
	 * 在主线程中执行
	 * @param event
	 */
	public void onEventMainThread(AppEvent event) {
		System.out.println("onEventMainThread:"
				+ Thread.currentThread().getId());
		if (event.getType() == AppEvent.TEST) {
			System.out.println("111111111111");
			str = "onEventMainThread";
			textView.setText(str);
			Toast.makeText(this, "MainActivity", Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 在后台线程执行
	 * @param event
	 */
	// public void onEventBackgroundThread(AppEvent event) {
	// System.out.println("onEventBackgroundThread:"
	// + Thread.currentThread().getId());
	// if (event.getType() == 1) {
	// System.out.println("111111111111");
	// }
	// }

	/**
	 * 在异步线程执行
	 * @param event
	 */
	// public void onEventAsync(AppEvent event) {
	// System.out.println("onEventAsync:" + Thread.currentThread().getId());
	// if (event.getType() == 1) {
	// System.out.println("111111111111");
	// }
	// }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt1:
			// postEvent();
			startActivity();
			break;

		default:
			break;
		}

	}

	public void postEvent() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				EventBus.getDefault().post(new AppEvent(1));
				System.out.println("PostThreadId:"
						+ Thread.currentThread().getId());

			}
		}).start();

	}

	private void startActivity() {
		Intent intent = new Intent(this, TwoActivity.class);
		startActivity(intent);
	}

}
