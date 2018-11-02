package com.wang.yiyaoba2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SettingActivity extends Activity {

	private ImageView iv_checkNet, iv_useCache, iv_set_back;
	private TextView tv_cleanCache, tv_update, tv_about;
	private SharedPreferences preferences ;
	private boolean netFlag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settingview);
		initview();
		widgetListener();
	}

	// 初始化页面
	public void initview() {
		iv_set_back = (ImageView) findViewById(R.id.iv_set_back);
		iv_checkNet = (ImageView) findViewById(R.id.iv_checknet);
		iv_useCache = (ImageView) findViewById(R.id.iv_usecache);
		tv_cleanCache = (TextView) findViewById(R.id.iv_cleancache);
		tv_update = (TextView) findViewById(R.id.tv_update);
		tv_about = (TextView) findViewById(R.id.tv_about);
		preferences = getSharedPreferences("info", Context.MODE_PRIVATE);
		netFlag = preferences.getBoolean("netFlag", true);
		if (netFlag) {
			iv_checkNet.setImageResource(R.drawable.pic_mode);
		}else {
			iv_checkNet.setImageResource(R.drawable.list_mode);
		}
	}

	// 监听控件
	public void widgetListener() {
		// 左上角返回监听
		iv_set_back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		// 检查网络监听
		iv_checkNet.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				netFlag = preferences.getBoolean("netFlag", true);
				Editor editor = preferences.edit();
				if (netFlag) {
					iv_checkNet.setImageResource(R.drawable.list_mode);
					editor.putBoolean("netFlag", false);
				} else {
					iv_checkNet.setImageResource(R.drawable.pic_mode);
					editor.putBoolean("netFlag", true);
				}
				editor.commit();

			}
		});
		// 启用缓存监听
		// 读取是否进行缓存
		iv_useCache.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				boolean cacheFlag = preferences.getBoolean("cacheFlag",
						true);
				Editor editor = preferences.edit();
				if (cacheFlag) {
					iv_useCache.setImageResource(R.drawable.list_mode);
					editor.putBoolean("cacheFlag", false);
				} else {
					iv_useCache.setImageResource(R.drawable.pic_mode);
					editor.putBoolean("cacheFlag", true);
				}
				editor.commit();

			}
		});
		// 清除缓存监听
		tv_cleanCache.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
		// 升级监听
		tv_update.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
		// 关于 监听
		tv_about.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				AlertDialog.Builder dialog = new AlertDialog.Builder(
						SettingActivity.this);
				dialog.setTitle("关于");
				dialog.setMessage(R.string.about);

				dialog.setNeutralButton("知道了", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub

					}
				});
				dialog.setCancelable(false);// 设置点击屏幕不消失
				dialog.create().show();
			}
		});

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}
