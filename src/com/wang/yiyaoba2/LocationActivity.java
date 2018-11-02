package com.wang.yiyaoba2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import application.GetLocationApplication;
import getAndParseJson.GetAndParseJson;

public class LocationActivity extends Activity {

	// http://api.1ccf.com/hospital/location?x=113.6&&y=34.7

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nearhospitial);		
		getData();
	}
	//获取数据并对界面初始化
	private void getData() {
		ListView lv=(ListView) findViewById(R.id.lv_nearhoptial);
		GetLocationApplication application = (GetLocationApplication) this.getApplication();
		double xx = application.x;
		double yy = application.y;
		String path="http://api.1ccf.com/hospital/location?x="+xx+"&&y="+yy;
		GetAndParseJson.getAndParseJson(path, LocationActivity.this, lv, 7);
		//上方回退键的监听
		ImageView iv_back=(ImageView) findViewById(R.id.iv_near_back);
		iv_back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
	}
	
	
}
