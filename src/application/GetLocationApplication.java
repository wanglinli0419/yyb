package application;

import com.baidu.location.Address;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.mapapi.SDKInitializer;
import android.app.Application;

public class GetLocationApplication extends Application {

	public double x, y;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		initLocation();
	}

	private void initLocation() {
		try {

			SDKInitializer.initialize(GetLocationApplication.this);
			LocationClient client = new LocationClient(GetLocationApplication.this);
			client.registerLocationListener(new BDLocationListener() {

				@Override
				public void onReceiveLocation(BDLocation arg0) {
					// TODO Auto-generated method stub
					x = arg0.getLongitude();
					y = arg0.getLatitude();
				}
			});
			LocationClientOption option = new LocationClientOption();
			option.setLocationMode(LocationMode.Hight_Accuracy);
			option.setCoorType("bd0911");
			option.setIsNeedAddress(true);
			option.setScanSpan(1000);
			client.setLocOption(option);
			client.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
