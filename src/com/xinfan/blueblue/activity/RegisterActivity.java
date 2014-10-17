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
	private EditText mRanCode; // 验证码编辑框
	private EditText mNickname; // 昵称编辑框
	private Button  sendBtn;//获取验证码按钮
	private  static String mobile,password,nickname,rancode,sendBtnStr;
	private static String gencode;//生成验证码
	
	private TencentLocationManager mLocationManager;
	private static  LocationEntity userLocation;
	private Handler handler;
	private Handler handlerlocation;
	private final Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");  
    @SuppressLint("HandlerLeak")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        mMobile = (EditText)findViewById(R.id.register_mobile_edit);
        mPassword = (EditText)findViewById(R.id.register_passwd_edit);
        mNickname = (EditText)findViewById(R.id.register_nickname_edit);
        mRanCode = (EditText)findViewById(R.id.register_rancode_edit);
        sendBtn=(Button) findViewById(R.id.register_rancode_btn);
     
    	  sendBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {
					   @Override
				
			        public void run() {
						   for(int i=60;i>=0;i--){
							  try {
									Message message = Message.obtain();
									message.obj=i;
									handler.sendMessage(message);
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						   }
					   }
					   }).start();	
			}
		});
    	  handler=new Handler(){
       	   public void handleMessage(Message msg){ 		 
       		Integer message=(Integer)msg.obj;//obj可以是任何类型
       		if(message.equals(0)){
       		 	sendBtn.setText("获取验证码");
       		}else{
       	   	sendBtn.setText("过期还剩"+message+"s");
       		}
           } 
  	   };
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
    public Handler getHandler(){
    	return this.handler;
    	}
    public void register(View v) {
		mobile=mMobile.getText().toString();
    	password=mPassword.getText().toString();
    	nickname=mNickname.getText().toString();
    	rancode=mRanCode.getText().toString();
    	sendBtnStr=sendBtn.getText().toString();
       if(!((p.matcher(mobile)).matches())){//判断手机号码格式
        	ToastUtil.showMessage(RegisterActivity.this,"手机号码为空或格式不正确！！");
        }
       else if(nickname==null||nickname.length()<=0){//判断昵称是否为空
          	ToastUtil.showMessage(RegisterActivity.this,"昵称不能为空！");
          }
    	else if(password==null||password.length()<=0)   //判断密码是否为空
        {
    		ToastUtil.showMessage(RegisterActivity.this,"密码不能为空！");
          }
        else if(rancode==null||rancode.length()<=0){
        	ToastUtil.showMessage(RegisterActivity.this,"验证码不能为空");
        }
        else if(sendBtnStr.equals("获取验证码")){
        	ToastUtil.showMessage(RegisterActivity.this,"请先获取验证码");
        }
        else if(!(rancode.equals(gencode))){
        	ToastUtil.showMessage(RegisterActivity.this,"验证码不正确");
        }

        else{
        	Request request = new Request(FunIdConstants.GET_USERREGISTER_VALIDCODE);
        	RegisterParam param = new RegisterParam();
    		param.setMobile(mobile);
    		param.setPasswd(password);
    		param.setValidCode(gencode);
    		request.setParam(param);
    		
    		AnsynHttpRequest.requestSimpleByPost(this, request, new ObserverCallBack() {

    			public void call(Request data) {

    				
    				BaseResult result = (BaseResult) data.getResult();
    				
    				if(result.getResult()==1){
    					
    					ToastUtil.showMessage(RegisterActivity.this,"验证码已经发送请注意查收");
    					SharePreferenceUtil util = new SharePreferenceUtil(RegisterActivity.this, Constants.USER_INFO);
    					util.setMobile(mobile);
    					  Intent intent = new Intent();
    			          intent.setClass(RegisterActivity.this,LoadingActivity.class);
    			          startActivity(intent);
    			           RegisterActivity.this.finish();
    					
    				}else{
    					ToastUtil.showMessage(RegisterActivity.this,"发送不成功");
    				}
    				
    			}
    		});
          
        }
    }
 
	public void register_back(View v) { // 返回
		this.finish();
	}

	// 发送信息

	public void sendSMS(View v) {
		mobile = mMobile.getText().toString();
		if (!((p.matcher(mobile)).matches())) {
			new AlertDialog.Builder(RegisterActivity.this).setIcon(getResources().getDrawable(R.drawable.login_error_icon)).setMessage("手机号码为空或格式不正确！").create().show();
			return;
		}
		Request request = new Request(FunIdConstants.GET_USERREGISTER_VALIDCODE);
		ValidCodeParam param = new ValidCodeParam();
		param.setMobile(mobile);
		request.setParam(param);
		AnsynHttpRequest.requestSimpleByPost(this, request, new ObserverCallBack() {

			public void call(Request data) {

				
				ValidCodeResult result = (ValidCodeResult) data.getResult();
				
				if(result.getResult()==1){
					
					ToastUtil.showMessage(RegisterActivity.this,"验证码已经发送请注意查收");
					gencode=result.getValidCode();
					
				
				}else{
					ToastUtil.showMessage(RegisterActivity.this,"发送不成功");
				}
				
			}
		});
		
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


