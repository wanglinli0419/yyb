package getAndParseJson;

import java.util.List;

import Data.Check;
import Data.Cook;
import Data.Drug;
import Data.Hospitial;
import Data.Lore;
import Data.News;
import Data.Symptom;

import adapter.MyBaseAdapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.wang.yiyaoba2.R;
import com.wang.yiyaoba2.WebViewActivity;

public class GetAndParseJson {

	public static void getAndParseJson(String path, final Context context, final ListView lv, final int intPath) {
		StringRequest request = new StringRequest(path, new Listener<String>() {

			@Override
			public void onResponse(String arg0) {
				// TODO Auto-generated method stub
				JSONObject jsonObject = JSONObject.parseObject(arg0);
				JSONArray array = jsonObject.getJSONArray("yi18");
				List list = whitchPath(intPath, array);
				MyBaseAdapter adapter = new MyBaseAdapter(list, context);
				lv.setAdapter(adapter);
				View view=LayoutInflater.from(context).inflate(R.layout.emptyview, null);
				lv.setEmptyView(view);
				lvListener(lv, list, context);
			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "网络无连接", Toast.LENGTH_SHORT).show();
			}
		});

		Volley.newRequestQueue(context).add(request);
	}

	// 判断是哪个页面进行网络请求
	public static List whitchPath(int intPath, JSONArray array) {
		if (intPath == 1) {
			List<News> list = JSONArray.parseArray(array.toString(), News.class);
			return list;
		} else if (intPath == 2) {
			List<Lore> list = JSONArray.parseArray(array.toString(), Lore.class);
			return list;
		} else if (intPath == 3) {
			List<Drug> list = JSONArray.parseArray(array.toString(), Drug.class);
			return list;
		} else if (intPath == 4) {
			List<Cook> list = JSONArray.parseArray(array.toString(), Cook.class);
			return list;
		} else if (intPath == 5) {
			List<Symptom> list = JSONArray.parseArray(array.toString(), Symptom.class);
			return list;
		} else if (intPath == 6) {
			List<Check> list = JSONArray.parseArray(array.toString(), Check.class);
			return list;
		} else if (intPath == 7) {
			List<Hospitial> list = JSONArray.parseArray(array.toString(), Hospitial.class);
			return list;
		}
		return null;
	}

	// 监听listview的点击事件
	public static void lvListener(ListView lv, final List list, final Context context) {
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				// 判断是哪个fragment的对象
				String name = list.get(position).getClass().getName();
				String url = null;
				// "http://api.1ccf.com/hospital/show?id=12"
				String title = null;
				int flag = 0;
				if (name.equals("Data.News")) {
					News news = (News) list.get(position);
					url = "http://api.1ccf.com/news/show?id=" + news.getId();
					title = news.getTitle();
					flag = 1;
				} else if (name.equals("Data.Lore")) {
					Lore lore = (Lore) list.get(position);
					url = "http://api.1ccf.com/lore/show?id=" + lore.getId();
					title = lore.getTitle();
					flag = 2;
				} else if (name.equals("Data.Drug")) {
					Drug drug = (Drug) list.get(position);
					url = "http://api.1ccf.com/drug/show?id=" + drug.getId();
					title = drug.getName();
					flag = 3;
				} else if (name.equals("Data.Cook")) {
					Cook cook = (Cook) list.get(position);
					url = "http://api.1ccf.com/cook/show?id=" + cook.getId();
					title = cook.getName();
					flag = 4;
				} else if (name.equals("Data.Symptom")) {
					Symptom symptom = (Symptom) list.get(position);
					url = "http://api.1ccf.com/symptom/show?id=" + symptom.getId();
					title = symptom.getName();
					flag = 5;
				} else if (name.equals("Data.Check")) {
					Check check = (Check) list.get(position);
					url = "http://api.1ccf.com/check/show?id=" + check.getId();
					title = check.getName();
					flag = 6;
				} else if (name.equals("Data.Hospitial")) {
					Hospitial hospitial = (Hospitial) list.get(position);
					url = "http://api.1ccf.com/hospital/show?id=" + hospitial.getId();
					title = hospitial.getName();
					flag = 7;
				}
				Intent intent = new Intent(context, WebViewActivity.class);
				intent.putExtra("url", url);
				intent.putExtra("flag", flag);
				intent.putExtra("title", title);
				context.startActivity(intent);
			}
		});
	}

}
