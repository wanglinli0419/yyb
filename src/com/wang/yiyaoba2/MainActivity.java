package com.wang.yiyaoba2;

import java.util.ArrayList;
import java.util.List;

import transfer.JudgeNetBroadcastReceiver;
import transfer.TransferNetState;
import Data.MenuItem;
import adapter.MyFragmentAdapter;
import adapter.MyLeftMenuBaseAdapter;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.viewpagerindicator.TabPageIndicator;

import fragment.MyMainFragment;

public class MainActivity extends FragmentActivity {

	
	private List<MyMainFragment> fragmentList;
	private ViewPager viewPager;
	private TabPageIndicator tabPageIndicator;
	private MyFragmentAdapter fragmentAdapter;
	private DrawerLayout drawerLayout;
	private ListView lv_menu;
	private ImageView iv_more,iv_search;

	// 声明广播
	private JudgeNetBroadcastReceiver receiver;
	private boolean flag = true;
	private SharedPreferences preferences;
	private Editor editor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initFragment();
		initView();
		judgeWelcome();
		widgetListener();
		boolean readNetFlag = preferences.getBoolean("netFlag", true);
		if (readNetFlag) {
			loginrecever();
		}
	}

	// 判断是否进入欢迎页面
	private void judgeWelcome() {
		preferences = getSharedPreferences("info", MODE_PRIVATE);
		int count = preferences.getInt("flag", 0);
		editor = preferences.edit();
		if (count == 0) {
			editor.putInt("flag", ++count);
			editor.commit();
			Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
			startActivity(intent);
			finish();
		}
	}

	// 初始化主页面
	private void initView() {
		drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
		lv_menu = (ListView) findViewById(R.id.lv_leftmenu);
		iv_more = (ImageView) findViewById(R.id.iv_more);
		iv_search=(ImageView) findViewById(R.id.iv_search);
		viewPager = (ViewPager) findViewById(R.id.mainviewpager);
		tabPageIndicator = (TabPageIndicator) findViewById(R.id.tabPageIndicator);
		receiver = new JudgeNetBroadcastReceiver();

		List<MenuItem> list = new ArrayList<MenuItem>();
		MenuItem item = new MenuItem("", 0);
		list.add(item);
		item = new MenuItem("附近医院", R.drawable.location_arrows);
		list.add(item);
		item = new MenuItem("收藏夹", R.drawable.collect);
		list.add(item);
		item = new MenuItem("反馈", R.drawable.feedback);
		list.add(item);
		item = new MenuItem("设置", R.drawable.setting);
		list.add(item);
		item = new MenuItem("退出", R.drawable.close);
		list.add(item);

		lv_menu.setAdapter(new MyLeftMenuBaseAdapter(list, MainActivity.this));
		fragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager(), fragmentList);
		viewPager.setAdapter(fragmentAdapter);
		viewPager.setOffscreenPageLimit(7);
		// 将viewpager添加到tabPageIndicator上
		tabPageIndicator.setViewPager(viewPager, 0);
	}

	// 初始化7个fragment
	public void initFragment() {
		fragmentList = new ArrayList<MyMainFragment>();
		MyMainFragment fragment;
		Bundle bundle = null;
		for (int i = 1; i <= 7; i++) {
			fragment = new MyMainFragment();
			bundle = new Bundle();
			bundle.putInt("path", i);
			fragment.setArguments(bundle);
			fragmentList.add(fragment);
		}
	}

	// 控件的监听
	private void widgetListener() {
		// 监听点击弹出菜单键
		iv_more.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (flag) {
					drawerLayout.openDrawer(Gravity.LEFT);
					flag = false;
				} else {
					drawerLayout.closeDrawer(Gravity.LEFT);
					flag = true;
				}
			}
		});
		
		// 搜索框的监听
		iv_search.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int currentPage=viewPager.getCurrentItem();
				Intent intent=new Intent(MainActivity.this,SearchActivity.class);
				intent.putExtra("serarch", currentPage);
				startActivity(intent);
			}
		});
		
		// 监听viewpager的滑动
		// viewPager.setOnTouchListener(new OnTouchListener() {
		//
		// @Override
		// public boolean onTouch(View v, MotionEvent event) {
		// // TODO Auto-generated method stub
		//
		// if (viewPager.getCurrentItem() == 0) {
		// float downx = 0, upx = 0;
		// switch (event.getAction()) {
		// case MotionEvent.ACTION_DOWN:
		// downx = event.getX();
		// System.out.println("==ACTION_DOWN==");
		// break;
		// case MotionEvent.ACTION_UP:
		// upx = event.getX();
		// System.out.println("==ACTION_UP==");
		// break;
		// default:
		// break;
		// }
		// if (upx - downx > 50) {
		// drawerLayout.openDrawer(Gravity.LEFT);
		// }
		//
		//
		// }
		//
		// return false;
		// }
		// });
		// 监听左侧菜单栏
		lv_menu.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				// 第0条为空 不监听
				switch (position) {
				case 1:// 附近医院
					Intent intent = new Intent(MainActivity.this, LocationActivity.class);
					startActivity(intent);
					break;
				case 2:// 收藏夹
					Intent intent2 = new Intent(MainActivity.this, CollectActivity.class);
					startActivity(intent2);
					break;
				case 3:// 反馈
					Intent intent3 = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:15037659259"));
					intent3.putExtra("sms_body", "我要请你吃饭");
					startActivity(intent3);
					break;
				case 4:// 设置
					Intent intent4 = new Intent(MainActivity.this, SettingActivity.class);
					startActivity(intent4);
					break;
				case 5:// 退出
					AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
					dialog.setTitle("提示").setMessage("是否确定退出?");
					dialog.setPositiveButton("确定", new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							finish();
						}
					});
					dialog.setNegativeButton("取消", null);
					dialog.create().show();
					break;

				default:
					break;
				}
			}
		});
	}

	// 手指按下监听
	private long time = 0;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		// 回退键的监听
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (System.currentTimeMillis() - time > 2000) {
				Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
				time = System.currentTimeMillis();
				return true;// 不执行父类点击事件
			} else {
				finish();
			}
		}
		return true;
	}

	// @Override
	// public boolean onTouchEvent(MotionEvent event) {
	// // TODO Auto-generated method stub
	// super.onTouchEvent(event);
	// if (viewPager.getCurrentItem() == 0) {
	// float downx = 0,upx = 0;
	// switch (event.getAction()) {
	// case MotionEvent.ACTION_DOWN:
	// downx = event.getX();
	// System.out.println("==ACTION_DOWN==");
	// break;
	// case MotionEvent.ACTION_UP:
	// upx=event.getX();
	// System.out.println("==ACTION_UP==");
	// break;
	// default:
	// break;
	// }
	// if (upx - downx > 50) {
	// drawerLayout.openDrawer(Gravity.LEFT);
	// }
	// return true;
	//
	// }
	// return true;
	// }

	// 注销广播
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		boolean readNetFlag = preferences.getBoolean("netFlag", true);
		if (readNetFlag) {
			unregisterReceiver(receiver);
		}
	}

	// 重新获得界面时判断网络
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		boolean readNetFlag = preferences.getBoolean("netFlag", true);
		if (readNetFlag) {
			loginrecever();
		} else {
			loginrecever();
			unregisterReceiver(receiver);
		}
	}

	// 注册广播
	public void loginrecever() {
		IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
		registerReceiver(receiver, filter);
	}
}
