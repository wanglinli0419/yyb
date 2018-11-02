package transfer;

import com.wang.yiyaoba2.MainActivity;
import com.wang.yiyaoba2.R;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.widget.Toast;

//判断是否对网络状态进行监听,并实施监听
public class JudgeNetBroadcastReceiver extends BroadcastReceiver {

	

	

	@Override
	public void onReceive(final Context context, Intent intent) {
		// TODO Auto-generated method stub
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo otherinfo = manager.getActiveNetworkInfo();
		NetworkInfo mobInfo = manager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		NetworkInfo wifiInfo = manager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

		if (otherinfo != null && !mobInfo.isConnected()
				&& !wifiInfo.isConnected()) {
			//其他在线
			Toast.makeText(context, "其他在线", 0).show();

		} else if (otherinfo != null && mobInfo.isConnected()
				&& !wifiInfo.isConnected()) {
			
			// 移动网络在线
			AlertDialog.Builder dialogMmobile = new AlertDialog.Builder(
					context);
			dialogMmobile.setTitle("温馨提示").setMessage(R.string.chooseNet);
			dialogMmobile.setPositiveButton("马上切换", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
					context.startActivity(intent);
				}
			});
			dialogMmobile.setNegativeButton("不切换", null);
			dialogMmobile.create().show();
			Toast.makeText(context, "移动网络", 0).show();

		} else if (otherinfo != null && !mobInfo.isConnected()
				&& wifiInfo.isConnected()) {
			// wifi
			Toast.makeText(context, "WiFi在线", 0).show();

		} else if (otherinfo == null && !mobInfo.isConnected()
				&& !wifiInfo.isConnected()) {
			// 无网络
			AlertDialog.Builder dialogNoNet = new AlertDialog.Builder(context);
			dialogNoNet.setTitle("温馨提示").setMessage(R.string.noNet);
			dialogNoNet.setPositiveButton("马上连接", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(
							Settings.ACTION_AIRPLANE_MODE_SETTINGS);
					context.startActivity(intent);
				}
			});
			dialogNoNet.setNegativeButton("不连接", null);
			dialogNoNet.create().show();
			Toast.makeText(context, "无网络", 0).show();
		}
	}

}
