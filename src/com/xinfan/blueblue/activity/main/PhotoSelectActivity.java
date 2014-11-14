package com.xinfan.blueblue.activity.main;

import java.io.ByteArrayOutputStream;
import java.io.File;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.xinfan.blueblue.activity.MainActivity;
import com.xinfan.blueblue.activity.R;
import com.xinfan.blueblue.activity.base.BaseActivity;
import com.xinfan.blueblue.activity.context.LoginUserContext;
import com.xinfan.blueblue.request.AnsynHttpRequest;
import com.xinfan.blueblue.request.Request;
import com.xinfan.blueblue.request.RequestSucessCallBack;
import com.xinfan.msgbox.http.service.vo.FunIdConstants;
import com.xinfan.msgbox.http.service.vo.param.UserAvatarParam;
import com.xinfan.msgbox.http.service.vo.result.UserAvatarResult;

public class PhotoSelectActivity extends BaseActivity {
	// private MyDialog dialog;
	private LinearLayout layout;

	private Button photo_select_1, photo_select_2;

	public static final int NONE = 0;
	public static final int PHOTOHRAPH = 1;
	public static final int PHOTOZOOM = 2;
	public static final int PHOTORESOULT = 3;

	public static final String IMAGE_UNSPECIFIED = "image/*";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photo_select);

		layout = (LinearLayout) findViewById(R.id.photo_select_layout);

		photo_select_1 = (Button) this.findViewById(R.id.photo_select_1);
		photo_select_2 = (Button) this.findViewById(R.id.photo_select_2);

		photo_select_1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "temp.jpg")));
				System.out.println("=============" + Environment.getExternalStorageDirectory());
				startActivityForResult(intent, PHOTOHRAPH);
			}
		});

		photo_select_2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
				intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_UNSPECIFIED);
				startActivityForResult(intent, PHOTOZOOM);
			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == NONE)
			return;

		if (requestCode == PHOTOHRAPH) {
			File picture = new File(Environment.getExternalStorageDirectory() + "/temp.jpg");
			System.out.println("------------------------" + picture.getPath());
			startPhotoZoom(Uri.fromFile(picture));
		}

		if (data == null)
			return;

		if (requestCode == PHOTOZOOM) {
			startPhotoZoom(data.getData());
		}

		if (requestCode == PHOTORESOULT) {
			Bundle extras = data.getExtras();
			if (extras != null) {
				Bitmap photo = extras.getParcelable("data");
				MainActivity.instance.menuWindow.updateAvatar(photo);
				saveAvatar(photo);
				this.finish();
			}

		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	public void saveAvatar(final Bitmap images) {
		new Thread() {

			public void run() {

				Request request = new Request(FunIdConstants.USER_AVATAR_SET);
				UserAvatarParam param = new UserAvatarParam();

				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				images.compress(Bitmap.CompressFormat.JPEG, 75, stream);

				param.setAvatar(Base64.encodeToString(stream.toByteArray(), Base64.DEFAULT));
				request.setParam(param);
				request.setShowDialog(false);

				AnsynHttpRequest.requestSimpleByPost(PhotoSelectActivity.this, request, new RequestSucessCallBack() {
					public void call(Request data) {
						UserAvatarResult result = (UserAvatarResult) data.getResult();
						String avatar = result.getAvatar();
						LoginUserContext.setAvatar(MainActivity.instance, avatar);
					}
				});
			}
		}.start();

	}

	public void startPhotoZoom(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", 64);
		intent.putExtra("outputY", 64);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, PHOTORESOULT);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		finish();
		return true;
	}

}
