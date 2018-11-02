package adapter;

import java.util.List;

import fragment.MyMainFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyFragmentAdapter extends FragmentPagerAdapter {
	private List<MyMainFragment> fragmentList;
	
	public static String[] TITLES = new String[] { "健康新闻", "健康知识", "药品直达", "食疗大全",
			"病状查询","检查项目","医院大全" };
	
	
	public MyFragmentAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}
	public MyFragmentAdapter(FragmentManager fm,List<MyMainFragment> fragmentList) {
		super(fm);
		// TODO Auto-generated constructor stub
		this.fragmentList=fragmentList;
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return fragmentList.get(arg0);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return fragmentList.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		// TODO Auto-generated method stub
		return TITLES[position];
	}
}
