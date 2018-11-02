package com.wang.yiyaoba2;

import org.json.JSONObject;

import sqliteDatabase.SqliteHelper;

import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.QQShareContent;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.SinaSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;

import adapter.MyFragmentAdapter;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class WebViewActivity extends Activity {
	private TextView tv_title;
	private WebView webView;
	private TextView tv_web_title;
	private ProgressDialog dialog;
	private static final String mimeType = "text/html";
	private static final String encoding = "utf-8";
	private ImageView iv_love, iv_share,iv_back;
	// private Intent intent;
	private int flag;
	private String url;
	private String title;
	private SqliteHelper helper;
	private SQLiteDatabase db;
	private boolean loveflag;

	// 网络请求数据
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.itemwebview);

		tv_title = (TextView) findViewById(R.id.tv_menutitle);
		webView = (WebView) findViewById(R.id.webview_item);
		iv_love = (ImageView) findViewById(R.id.iv_love);
		iv_share = (ImageView) findViewById(R.id.iv_share);
		iv_back=(ImageView) findViewById(R.id.iv_web_back);
		tv_web_title = (TextView) findViewById(R.id.tv_web_title);

		// 得到intent传过来的值
		Intent intent = getIntent();
		flag = intent.getIntExtra("flag", 1);
		url = intent.getStringExtra("url");
		title = intent.getStringExtra("title");
		tv_web_title.setText(title);

		helper = new SqliteHelper(WebViewActivity.this);
		db = helper.getReadableDatabase();
		String query = "select * from love where typeflag='" + flag
				+ "'and url='" + url + "'";
		Cursor cursor = db.rawQuery(query, null);
		
		// if(cursor.moveToNext()){
		// System.out.println("非空");
		// }else{
		// System.out.println("kong");
		// }
		if (cursor.moveToNext()) {
			iv_love.setImageResource(R.drawable.collect);
			loveflag = false;
		}
		db.close();

		JsonObjectRequest request = new JsonObjectRequest(url, null,
				new Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject arg0) {
						// TODO Auto-generated method stub
						JSONObject obj;
						String htmlData = null;
						try {
							obj = arg0.getJSONObject("yi18");
							if (flag < 5) {
								htmlData = obj.getString("message");
							} else if (flag == 6 || flag == 5) {
								htmlData = obj.getString("detailText");
							} else if (flag == 7) {
								htmlData = obj.getString("summary");
							}
							webView.loadDataWithBaseURL(null, htmlData,
									mimeType, encoding, null);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}, null);
		tv_title.setText(MyFragmentAdapter.TITLES[flag - 1]);

		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				view.loadUrl(url);

				return true;
			}
		});
		webView.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				// TODO Auto-generated method stub
				super.onProgressChanged(view, newProgress);
				if (newProgress == 100) {
					closedialog();
				} else {
					opendialog(newProgress);
				}
			}
		});
		webView.canGoBack();
		Volley.newRequestQueue(WebViewActivity.this).add(request);
		webView.getSettings().setBuiltInZoomControls(true);// 可控制
		webView.getSettings().setSupportZoom(true);// 可缩放
		// webView.getSettings().setLoadWithOverviewMode(true);
		// webView.getSettings().setUseWideViewPort(true); //适应屏幕大小
		// 调用分享收藏的监听
		widgetsListener();
	}

	// 打开progressDialog
	protected void opendialog(int newProgress) {
		if (dialog == null) {
			dialog = new ProgressDialog(WebViewActivity.this);
			dialog.setTitle("正在玩命加载");
			dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			dialog.setProgress(newProgress);
			dialog.show();
		} else {
			dialog.setProgress(newProgress);
		}

	}

	// 关闭progressDialog
	protected void closedialog() {
		if (dialog != null && dialog.isShowing()) {
			dialog.dismiss();
			dialog = null;
		}
	}

	// 监听返回键,返回到上一个页面而不是回到上一个activity
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
			webView.goBack();
			return true;// true不执行父类的点击事件

		}
		return super.onKeyDown(keyCode, event);
	}

	// 分享和收藏以及返回的监听
	private void widgetsListener() {
		iv_share.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 系统自带分享
				// Intent intent = new Intent(Intent.ACTION_SEND);
				// intent.setType("image/*");
				// intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
				// intent.putExtra(Intent.EXTRA_TEXT,
				// "I have successfully share my message through my app");
				// intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				// startActivity(Intent.createChooser(intent, getTitle()));
				// 友盟分享
				UMSocialService mController = UMServiceFactory
						.getUMSocialService("com.umeng.share");
				// 设置分享内容
				mController.setShareContent(title);
				// 设置分享图片, 参数2为图片的url地址
				mController
						.setShareMedia(new UMImage(WebViewActivity.this, url));
				// 设置分享图片，参数2为本地图片的资源引用
				mController.setShareMedia(new UMImage(WebViewActivity.this,
						R.drawable.applogo));

				UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(
						WebViewActivity.this, "100424468",
						"c7394704798a158208a74ab60104f0ba");
				qqSsoHandler.addToSocialSDK();
				QQShareContent content = new QQShareContent();
				content.setTargetUrl(url);
				// 新浪分享
				mController.getConfig().setSsoHandler(new SinaSsoHandler());
				mController.openShare(WebViewActivity.this, false);
				mController.setShareMedia(content);
			}
		});
		iv_love.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				db = helper.getReadableDatabase();
				if (loveflag) {
					iv_love.setImageResource(R.drawable.collect);
					ContentValues values = new ContentValues();
					values.put("typeflag", flag);
					values.put("url", url);
					values.put("title",title);
					db.insert("love", null, values);
					Toast.makeText(WebViewActivity.this, "已收藏", 0).show();
					loveflag = false;
				} else {
					String delete = "delete  from love where typeflag='"
							+ flag+ "'and url='"
							+ url + "'";
					db.execSQL(delete);
					iv_love.setImageResource(R.drawable.uncollect);
					loveflag = true;
				}
				db.close();
			}
		});
		iv_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
	// public void shareUse(){
	// // 首先在您的Activity中添加如下成员变量
	// final UMSocialService mController =
	// UMServiceFactory.getUMSocialService("com.umeng.share");
	// // 设置分享内容
	// mController.setShareContent(title);
	// // 设置分享图片, 参数2为图片的url地址
	// mController.setShareMedia(new UMImage(WebViewActivity.this,url));
	// // 设置分享图片，参数2为本地图片的资源引用
	// mController.setShareMedia(new UMImage(WebViewActivity.this,
	// R.drawable.applogo));
	// // 设置分享图片，参数2为本地图片的路径(绝对路径)
	// //mController.setShareMedia(new UMImage(getActivity(),
	// // BitmapFactory.decodeFile("/mnt/sdcard/icon.png")));
	//
	// // 设置分享音乐
	// //UMusic uMusic = new UMusic("http://sns.whalecloud.com/test_music.mp3");
	// //uMusic.setAuthor("GuGu");
	// //uMusic.setTitle("天籁之音");
	// // 设置音乐缩略图
	// //uMusic.setThumb("http://www.umeng.com/images/pic/banner_module_social.png");
	// //mController.setShareMedia(uMusic);
	//
	// // 设置分享视频
	// //UMVideo umVideo = new UMVideo(
	// // "http://v.youku.com/v_show/id_XNTE5ODAwMDM2.html?f=19001023");
	// // 设置视频缩略图
	// //umVideo.setThumb("http://www.umeng.com/images/pic/banner_module_social.png");
	// //umVideo.setTitle("友盟社会化分享!");
	// //mController.setShareMedia(umVideo);
	// }

}
