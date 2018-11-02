package adapter;

import java.util.List;

import fragment.MyWelcomeFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyFragmentWelcomeAdapter extends FragmentPagerAdapter {
	
	private List<MyWelcomeFragment> list;
	
	public MyFragmentWelcomeAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	public MyFragmentWelcomeAdapter(FragmentManager fm,
			List<MyWelcomeFragment> list) {
		super(fm);
		this.list = list;
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

}
