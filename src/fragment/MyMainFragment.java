package fragment;

import com.wang.yiyaoba2.R;
import getAndParseJson.GetAndParseJson;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

@SuppressLint("ValidFragment")
public class MyMainFragment extends Fragment {

	private String path_news = "http://api.1ccf.com/news/list?page=1";
	private String path_knowledge = "http://api.1ccf.com/lore/list?page=1";
	private String path_drug = "http://api.1ccf.com/drug/list?page=1";
	private String path_cook = "http://api.1ccf.com/cook/list?page=1";
	private String path_saearch = "http://api.1ccf.com/symptom/list?page=1";
	private String path_check = "http://api.1ccf.com/check/list?page=1";
	private String path_hospital = "http://api.1ccf.com/hospital/list?page=1";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment, container, false);
		ListView lv = (ListView) view.findViewById(R.id.lv_fragment);
		switchPath(lv);
		return view;
	}

	protected void switchPath(ListView lv) {
		Bundle bundle = getArguments();
		int path = bundle.getInt("path");
		switch (path) {
		case 1:
			GetAndParseJson.getAndParseJson(path_news, getActivity(), lv, path);
			break;
		case 2:
			GetAndParseJson.getAndParseJson(path_knowledge, getActivity(), lv,
					path);
			break;
		case 3:
			GetAndParseJson.getAndParseJson(path_drug, getActivity(), lv, path);
			break;
		case 4:
			GetAndParseJson.getAndParseJson(path_cook, getActivity(), lv, path);
			break;
		case 5:
			GetAndParseJson.getAndParseJson(path_saearch, getActivity(), lv, path);

			break;
		case 6:
			GetAndParseJson.getAndParseJson(path_check, getActivity(), lv, path);
			break;
		case 7:
			GetAndParseJson.getAndParseJson(path_hospital, getActivity(), lv, path);
			break;
		default:
			break;
		}
	}
}
