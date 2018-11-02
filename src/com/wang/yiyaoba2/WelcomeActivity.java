package com.wang.yiyaoba2;

import java.util.ArrayList;
import java.util.List;

import fragment.MyWelcomeFragment;
import adapter.MyFragmentWelcomeAdapter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class WelcomeActivity extends FragmentActivity {
	private ViewPager viewPager_welcome;
	private List<MyWelcomeFragment> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcomemain);
		viewPager_welcome = (ViewPager) findViewById(R.id.viewpager_welcome);
		initWelcomeFragment();
		viewPager_welcome.setAdapter(new MyFragmentWelcomeAdapter(
				getSupportFragmentManager(), list));
	}

	// 初始化三个欢迎页面
	public void initWelcomeFragment() {
		list = new ArrayList<MyWelcomeFragment>();
		MyWelcomeFragment fragment;
		Bundle bundle;
		for (int i = 1; i <= 3; i++) {
			fragment = new MyWelcomeFragment();
			bundle = new Bundle();
			bundle.putInt("tag", i);
			fragment.setArguments(bundle);
			list.add(fragment);
		}
	}
}
