package com.xinfan.blueblue.activity;

import java.util.regex.Pattern;

import com.xinfan.blueblue.request.AnsynHttpRequest;
import com.xinfan.blueblue.request.ObserverCallBack;
import com.xinfan.blueblue.request.Request;
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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RegisterVerifyActivity extends Activity {
	private EditText mMobile; // 手机号码编辑框
	private Button  sendBtn;//获取验证码按钮
	private EditText mRanCode; // 验证码编辑框
	private static String gencode;//生成验证码
	private  static String mobile,rancode,sendBtnStr;
	private Handler handler;
	private final Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");  
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.register_verify);
	        mMobile = (EditText)findViewById(R.id.regirst_verofy_mobile_edit);
	        mRanCode = (EditText)findViewById(R.id.regirst_verofy_rancode_edit);
	        sendBtn=(Button) findViewById(R.id.regirst_verofy_get_btn);
	     
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
	 }
	// 发送信息

		public void sendSMS(View v) {
			mobile = mMobile.getText().toString();
			if (!((p.matcher(mobile)).matches())) {
				new AlertDialog.Builder(RegisterVerifyActivity.this).setIcon(getResources().getDrawable(R.drawable.login_error_icon)).setMessage("手机号码为空或格式不正确！").create().show();
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
						
						ToastUtil.showMessage(RegisterVerifyActivity.this,"验证码已经发送请注意查收");
						gencode=result.getValidCode();
						
					
					}else{
						ToastUtil.showMessage(RegisterVerifyActivity.this,"发送不成功");
					}
					
				}
			});
			
	    }
		//提交验证
		public void regirstVerofy(View v){
			mobile=mMobile.getText().toString();
			rancode=mRanCode.getText().toString();
			sendBtnStr=sendBtn.getText().toString();
		    if(!((p.matcher(mobile)).matches())){//判断手机号码格式
	        	ToastUtil.showMessage(RegisterVerifyActivity.this,"手机号码为空或格式不正确！！");
	        }     
		    else if(sendBtnStr.equals("获取验证码")){
	        	ToastUtil.showMessage(RegisterVerifyActivity.this,"请先获取验证码");
	        }
		    else if(rancode==null||rancode.length()<=0){
	        	ToastUtil.showMessage(RegisterVerifyActivity.this,"验证码不能为空");
	        }
		    else{
	        	Request request = new Request(FunIdConstants.GET_USERREGISTER_VALIDCODE);
	        	RegisterParam param = new RegisterParam();
	    		param.setMobile(mobile);
	    		param.setValidCode(gencode);
	    		request.setParam(param);
	    		
	    		AnsynHttpRequest.requestSimpleByPost(this, request, new ObserverCallBack() {

	    			public void call(Request data) {

	    				
	    				BaseResult result = (BaseResult) data.getResult();
	    				
	    				if(result.getResult()==1){
	    					
	    					ToastUtil.showMessage(RegisterVerifyActivity.this,result.getMsg());
	    					RegisterActivity.instance.mobile_set(mobile);
	    					  Intent intent = new Intent();
	    			          intent.setClass(RegisterVerifyActivity.this,RegisterActivity.class);
	    			          startActivity(intent);
	    			          RegisterVerifyActivity.this.finish();
	    					
	    				}else{
	    					ToastUtil.showMessage(RegisterVerifyActivity.this,result.getMsg());
	    				}
	    				
	    			}
	    		});
	          
	        }
		}
		
		public void regirst_verofy_back(View v) { // 返回
			this.finish();
		}

}