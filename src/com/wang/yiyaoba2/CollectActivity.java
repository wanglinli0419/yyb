package com.wang.yiyaoba2;

import java.util.ArrayList;
import java.util.List;

import sqliteDatabase.SqliteHelper;
import Data.CollectData;
import adapter.MyCollectAdapter;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CollectActivity extends Activity {

	private ImageView iv_back;
	private ListView lv_collect;
	private MyCollectAdapter adapter;
	private List<CollectData> list;
	private TextView tv_emptyview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.collect);
		iv_back = (ImageView) findViewById(R.id.iv_collect_back);
		lv_collect = (ListView) findViewById(R.id.lv_collect);
		tv_emptyview = (TextView) findViewById(R.id.tv_emptyview);
		getData();

		adapter = new MyCollectAdapter(list, CollectActivity.this);
		lv_collect.setAdapter(adapter);
		lv_collect.setEmptyView(tv_emptyview);
		collectWidgetListener();

		registerForContextMenu(lv_collect);
	}

	// 控件监听
	public void collectWidgetListener() {
		iv_back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		lv_collect.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				CollectData data = list.get(position);
				Intent intent = new Intent(CollectActivity.this, WebViewActivity.class);
				intent.putExtra("url", data.getUrl());
				intent.putExtra("flag", data.getTypeflag());
				intent.putExtra("title", data.getTitle());
				startActivity(intent);
			}
		});
		lv_collect.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

				return false;
			}
		});
	}

	// 重新返回界面时,数据如有变化重新加载
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		getData();
		MyCollectAdapter adapter1 = new MyCollectAdapter(list, CollectActivity.this);
		lv_collect.setAdapter(adapter1);
		// lv_collect.invalidate();
		// adapter.notifyDataSetChanged();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		getData();
		MyCollectAdapter adapter1 = new MyCollectAdapter(list, CollectActivity.this);
		lv_collect.setAdapter(adapter1);
		// lv_collect.invalidate();
		// adapter.notifyDataSetChanged();
	}

	// 从数据库读取数据源
	public void getData() {
		SqliteHelper helper = new SqliteHelper(CollectActivity.this);
		SQLiteDatabase db = helper.getReadableDatabase();
		list = new ArrayList<CollectData>();
		Cursor cursor = db.query("love", null, null, null, null, null, "_id DESC");
		CollectData data;
		while (cursor.moveToNext()) {
			int typeflag = cursor.getInt(cursor.getColumnIndex("typeflag"));
			String url = cursor.getString(cursor.getColumnIndex("url"));
			String title = cursor.getString(cursor.getColumnIndex("title"));
			data = new CollectData(typeflag, url, title);
			list.add(data);
		}
		db.close();
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		getMenuInflater().inflate(R.menu.main, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		String url = list.get(info.position).getUrl();
		SqliteHelper helper = new SqliteHelper(CollectActivity.this);
		SQLiteDatabase db = helper.getReadableDatabase();
		String delete = "delete  from love where url='" + url + "'";
		db.execSQL(delete);
		list.remove(info.position);
		getData();
		// adapter.notifyDataSetChanged();
		MyCollectAdapter adapter1 = new MyCollectAdapter(list, CollectActivity.this);
		lv_collect.setAdapter(adapter1);
		// adapter.notifyDataSetInvalidated();
		return super.onContextItemSelected(item);
	}
}
