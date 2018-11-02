package com.wang.yiyaoba2;

import adapter.MyFragmentAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import getAndParseJson.GetAndParseJson;

public class SearchActivity extends Activity {

	// 搜索 http://api.1ccf.com/cook/search?keyword=西瓜
	private String path=null;
	private int intPath=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
		initView();
	}

	private void initView() {
		Spinner spinner=(Spinner) findViewById(R.id.spinner_search);
		ArrayAdapter<String> adp2=new ArrayAdapter<String>
        (SearchActivity.this, android.R.layout.simple_spinner_item, MyFragmentAdapter.TITLES);
		spinner.setAdapter(adp2);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				String item[]={"news","lore","drug","symptom","cook"};
				String type=item[arg2];
				intPath=arg2+1;
				path="http://api.1ccf.com/"+type+"/search?keyword=";
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				String type="news";
				intPath=0;
				path="http://api.1ccf.com/"+type+"/search?keyword=";
			}
		});
		
		ImageView search=(ImageView) findViewById(R.id.iv_searchdo);
		search.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText et=(EditText) findViewById(R.id.et_serarch);
				String point=et.getText().toString().trim();
				if (point==null) {
					point="健康";
				}
				String url=path+point;
				ListView lv=(ListView) findViewById(R.id.lv_search);
				GetAndParseJson.getAndParseJson(url, SearchActivity.this, lv, intPath);
				System.out.println("====="+url);
			}
		});
	}
}
