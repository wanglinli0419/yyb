package adapter;

import java.util.List;

import com.wang.yiyaoba2.R;

import Data.CollectData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyCollectAdapter extends BaseAdapter {

	private List<CollectData> list;
	private Context context;

	public MyCollectAdapter(List<CollectData> list, Context context) {
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
			convertView = LayoutInflater.from(context).inflate(
					R.layout.collectlistitem, null);
			holder.iv=(ImageView) convertView.findViewById(R.id.iv_collect_img);
			holder.tv_title=(TextView) convertView.findViewById(R.id.tv_collect_title);
			holder.tv_src=(TextView) convertView.findViewById(R.id.tv_collect_src);
			convertView.setTag(holder);
		}else {
			holder=(ViewHolder) convertView.getTag();
		}
		holder.iv.setVisibility(View.GONE);//隐藏
		CollectData data=list.get(position);
		holder.tv_title.setText(data.getTitle());
		holder.tv_src.setText("来源:"+MyFragmentAdapter.TITLES[data.getTypeflag()-1]);
		return convertView;
	}


	class ViewHolder {
		ImageView iv;
		TextView tv_title, tv_src;

	}
}
