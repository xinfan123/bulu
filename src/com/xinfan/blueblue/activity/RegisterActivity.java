package com.xinfan.blueblue.activity;


import java.util.regex.Pattern;





import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;
import com.xinfan.blueblue.location.LocationEntity;
import com.xinfan.blueblue.request.AnsynHttpRequest;
import com.xinfan.blueblue.request.Constants;
import com.xinfan.blueblue.request.ObserverCallBack;
import com.xinfan.blueblue.request.Request;
import com.xinfan.blueblue.request.SharePreferenceUtil;
import com.xinfan.blueblue.util.ToastUtil;
import com.xinfan.msgbox.http.service.vo.FunIdConstants;
import com.xinfan.msgbox.http.service.vo.param.RegisterParam;
import com.xinfan.msgbox.http.service.vo.param.ValidCodeParam;
import com.xinfan.msgbox.http.service.vo.result.BaseResult;

import com.xinfan.msgbox.http.service.vo.result.ValidCodeResult;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.os.Message;
import android.annotation.SuppressLint;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends Activity implements OnClickListener,TencentLocationListener {
	private EditText mMobile; // 手机号码编辑框
	private EditText mPassword; // 密码编辑框
	private EditText mNickname; // 昵称编辑框
	private  static String mobile,password,nickname;
	private static String gencode;//生成验证码
	
	private TencentLocationManager mLocationManager;
	private static  LocationEntity userLocation;

	private Handler handlerlocation;
	public static RegisterActivity instance;
    @SuppressLint("HandlerLeak")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        mMobile = (EditText)findViewById(R.id.register_mobile_edit);
        mPassword = (EditText)findViewById(R.id.register_passwd_edit);
        mNickname = (EditText)findViewById(R.id.register_nickname_edit);
   
     
  	//获取位置信息
  	startLocation();
  	 handlerlocation=new Handler(){
      	   public void handleMessage(Message msg){ 		 
      		LocationEntity message=(LocationEntity)msg.obj;//obj可以是任何类型
      		userLocation=message;
      		System.out.println(userLocation.getAddress());
      	   }
 	   };
    }
    
 
    
    public void register(View v) {
		mobile=mMobile.getText().toString();
    	password=mPassword.getText().toString();
    	nickname=mNickname.getText().toString();
 
      if(nickname==null||nickname.length()<=0){//判断昵称是否为空
          	ToastUtil.showMessage(RegisterActivity.this,"昵称不能为空！");
          }
    	else if(password==null||password.length()<=0)   //判断密码是否为空
        {
    		ToastUtil.showMessage(RegisterActivity.this,"密码不能为空！");
          }

        else{
        	Request request = new Request(FunIdConstants.GET_USERREGISTER_VALIDCODE);
        	RegisterParam param = new RegisterParam();
    		param.setMobile(mobile);
    		param.setPasswd(password);
    		param.setRegEarea(userLocation.getAddress());
    		param.setRegGpsx(userLocation.getLatitude().toString());
    		param.setRegGpsy(userLocation.getLongitude().toString());
    		request.setParam(param);
    		
    		AnsynHttpRequest.requestSimpleByPost(this, request, new ObserverCallBack() {

    			public void call(Request data) {

    				
    				BaseResult result = (BaseResult) data.getResult();
    				
    				if(result.getResult()==1){
    					
    					ToastUtil.showMessage(RegisterActivity.this,result.getMsg());
    					SharePreferenceUtil util = new SharePreferenceUtil(RegisterActivity.this, Constants.USER_INFO);
    					util.setMobile(mobile);
    					  Intent intent = new Intent();
    			          intent.setClass(RegisterActivity.this,LoadingActivity.class);
    			          startActivity(intent);
    			           RegisterActivity.this.finish();
    					
    				}else{
    					ToastUtil.showMessage(RegisterActivity.this,result.getMsg());
    				}
    				
    			}
    		});
          
        }
    }
    public void mobile_set(String name){
    	mMobile.setText(name);
    }
	public void register_back(View v) { // 返回
		this.finish();
	}

	
    
	@Override
	public void onLocationChanged(TencentLocation location, int error,
			String reason) {
		LocationEntity uersLocation=new LocationEntity();
		if (error == TencentLocation.ERROR_OK) {

		
			uersLocation.setProvince(location.getProvince());
			uersLocation.setLatitude(location.getLatitude());
			uersLocation.setLongitude(location.getLongitude());
			uersLocation.setAddress(location.getAddress());
			uersLocation.setProvider(location.getProvider());
			uersLocation.setCity(location.getCity());
			uersLocation.setDistrict(location.getDistrict());
			uersLocation.setTown(location.getTown());
			uersLocation.setVillage(location.getVillage());
			uersLocation.setStreetNo(location.getStreetNo());
			Message message = Message.obtain();
			message.obj=uersLocation;
			handlerlocation.sendMessage(message);

		
			stopLocation();
		
		}	
	}

	private void startLocation() {
		TencentLocationRequest request = TencentLocationRequest.create();
		mLocationManager = TencentLocationManager.getInstance(this);
		request.setInterval(5000);
		mLocationManager.requestLocationUpdates(request, this);
	
	}

	private void stopLocation() {
		mLocationManager.removeUpdates(this);
	}
	@Override
	public void onStatusUpdate(String arg0, int arg1, String arg2) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
	}


