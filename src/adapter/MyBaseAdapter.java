package adapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.wang.yiyaoba2.R;

import Data.Check;
import Data.Cook;
import Data.Drug;
import Data.Hospitial;
import Data.Lore;
import Data.News;
import Data.Symptom;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyBaseAdapter extends BaseAdapter {

	private List list;
	private Context context;
	private Map<String, Bitmap> map=new HashMap<String, Bitmap>();

	public MyBaseAdapter(List list, Context context) {
		super();
		this.list = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.item,
					null);
			holder.tv_title = (TextView) convertView
					.findViewById(R.id.tv_title);
			holder.tv_tag = (TextView) convertView.findViewById(R.id.tv_tag);
			holder.tv_count = (TextView) convertView
					.findViewById(R.id.tv_count);
			holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
			holder.iv = (ImageView) convertView.findViewById(R.id.iv_img);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		whichData(list, position, holder);
		return convertView;
	}

	class ViewHolder {
		TextView tv_title, tv_tag, tv_count, tv_time;
		ImageView iv;
	}

	// 判断是哪个Data对象
	public void whichData(List list, int position, ViewHolder holder) {
		String name = list.get(position).getClass().getName();
		String str="http://www.yi18.net/";
		String path = null;
		if (name.equals("Data.News")) {
			News news = (News) list.get(position);
			holder.tv_title.setText(news.getTitle());
			holder.tv_tag.setText("分类:" + news.getTag());
			holder.tv_count.setText("浏览次数:" + news.getCount());
			holder.tv_time.setText("发布时间:" + timeFormat(news.getTime()));
			path=str+news.getImg();
		} else if (name.equals("Data.Lore")) {
			Lore lore = (Lore) list.get(position);
			holder.tv_title.setText(lore.getTitle());
			holder.tv_tag.setText("分类:" + lore.getClassName());
			holder.tv_count.setText("浏览次数:" + lore.getCount());
			holder.tv_time.setText("发布时间:" + timeFormat(lore.getTime()));
			path=str+lore.getImg();
		} else if (name.equals("Data.Drug")) {
			Drug drug = (Drug) list.get(position);
			holder.tv_title.setText(drug.getName());
			holder.tv_tag.setText("功效:" + drug.getPType());
			holder.tv_count.setText("浏览次数:" + drug.getCount());
			holder.tv_time.setText("厂商:" + drug.getFactory());
			path=str+drug.getImage();
		} else if (name.equals("Data.Cook")) {
			Cook cook = (Cook) list.get(position);
			holder.tv_title.setText(cook.getName());
			holder.tv_tag.setText("食材:" + cook.getFood());
			holder.tv_count.setText("浏览次数:" + cook.getCount());
			holder.tv_time.setText("类别:"+cook.getTag());
			path=str+cook.getImg();
		} else if (name.equals("Data.Symptom")) {
			Symptom symptom = (Symptom) list.get(position);
			holder.tv_title.setText(symptom.getName());
			holder.tv_tag.setVisibility(View.GONE);
			holder.tv_count.setText("浏览次数:" + symptom.getCount());
			holder.tv_time.setText("部位:"+symptom.getPlace());
			path=str+symptom.getImg();
		} else if (name.equals("Data.Check")) {
			Check check = (Check) list.get(position);
			holder.tv_title.setText(check.getName());
			holder.tv_tag.setText("检查类型:" + check.getMenu());
			holder.tv_count.setText("浏览次数:" + check.getCount());
			holder.tv_time.setVisibility(View.GONE);
			path=str+check.getImg();
		} else if (name.equals("Data.Hospitial")) {
			Hospitial hospitial = (Hospitial) list.get(position);
			holder.tv_title.setText(hospitial.getName());
			holder.tv_tag.setText("等级:" + hospitial.getLevel());
			holder.tv_count.setText("营业性质:" + hospitial.getNature());
			holder.tv_time.setText("地址:" + hospitial.getAddress());
			path=str+hospitial.getLogo();
		}
		holder.iv.setTag(path);
		holder.iv.setImageResource(R.drawable.ic_launch);
		if (map.containsKey(path)) {
			holder.iv.setImageBitmap(map.get(path));
		}else {
			loadImg(path.trim(), holder.iv);
		}
	}

	// 下载图片   图片前缀    http://www.yi18.net/ 
	public void loadImg(final String path,final ImageView iv) {
		ImageRequest imageRequest = new ImageRequest(path,
				new Listener<Bitmap>() {

					@Override
					public void onResponse(Bitmap arg0) {
						// TODO Auto-generated method stub
						map.put(path, arg0);
						iv.setImageBitmap(arg0);
					}
				}, 0, 0, Config.RGB_565, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						// TODO Auto-generated method stub
//						Toast.makeText(context, "图片加载失败", Toast.LENGTH_SHORT)
//								.show();
					}
				});
		Volley.newRequestQueue(context).add(imageRequest);
	}

	// 转换时间
	public String timeFormat(String date) {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		SimpleDateFormat format2 = new SimpleDateFormat("MMM d, yyyy HH:mm:ss",
				Locale.ENGLISH);
		String time = null;
		try {
			time = format1.format(format2.parse(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return time;
	}
}
