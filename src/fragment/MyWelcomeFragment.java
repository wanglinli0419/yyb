package fragment;

import com.wang.yiyaoba2.MainActivity;
import com.wang.yiyaoba2.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class MyWelcomeFragment extends Fragment {

	private ImageView iv_welcome, iv_gotonext;
	private Button bt;
	private int tag;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.welcomefragment, null);
		iv_welcome = (ImageView) view.findViewById(R.id.iv_welcomeimg);
		iv_gotonext = (ImageView) view.findViewById(R.id.iv_gotonext);
		bt=(Button) view.findViewById(R.id.bt_welcome_begin);
		Bundle bundle = getArguments();
		tag = bundle.getInt("tag");
		
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getActivity(), MainActivity.class);
				startActivity(intent);
				getActivity().finish();
			}
		});
		
		iv_gotonext.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tag++;
				switchImage();
			}
		});
		switchImage();
		return view;
	}

	private void switchImage() {
		switch (tag) {
		case 1:
			iv_welcome.setImageResource(R.drawable.welcome1);
			break;
		case 2:
			iv_welcome.setImageResource(R.drawable.welcome2);
			break;
		case 3:
			iv_welcome.setImageResource(R.drawable.welcome3);
			bt.setVisibility(View.VISIBLE);
			iv_gotonext.setVisibility(View.GONE);
			break;
		default:
			break;
		}
	}

}
