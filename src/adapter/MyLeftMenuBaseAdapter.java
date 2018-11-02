package adapter;

import java.util.List;
import com.wang.yiyaoba2.R;

import Data.MenuItem;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyLeftMenuBaseAdapter extends BaseAdapter {

	private List<MenuItem> list;
	private Context context;
	
	public MyLeftMenuBaseAdapter(List<MenuItem> list,
			Context context) {
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
		ViewHolder holder;
		if (convertView==null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.leftmenuitem, null);
			holder=new ViewHolder();
			holder.iv=(ImageView) convertView.findViewById(R.id.iv_leftmenu);
			holder.tv=(TextView) convertView.findViewById(R.id.tv_leftmenu);
			convertView.setTag(holder);
		}else {
			holder=(ViewHolder) convertView.getTag();
		}
		MenuItem item=(MenuItem) list.get(position);		
		holder.iv.setImageResource(item.getId());
		holder.tv.setText(item.getName());
		return convertView;
	}
	
	class ViewHolder{
		ImageView iv;
		TextView tv;
	}
}
