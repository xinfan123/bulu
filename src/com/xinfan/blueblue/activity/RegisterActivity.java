package com.xinfan.blueblue.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

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
import com.xinfan.msgbox.http.service.vo.result.LoginResult;
import com.xinfan.msgbox.http.service.vo.result.ValidCodeResult;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.annotation.SuppressLint;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends Activity {
	private EditText mMobile; // 手机号码编辑框
	private EditText mPassword; // 密码编辑框
	private EditText mRanCode; // 验证码编辑框
	private EditText mNickname; // 昵称编辑框
	private Button  sendBtn;//获取验证码按钮
	private  static String mobile,password,nickname,rancode,sendBtnStr;
	private static String gencode;//生成验证码
	private Handler handler;

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
//       gencode= genRanCode(4);
//       handler=new Handler(){
//    	   public void handleMessage(Message msg){
//    	   String message=(String)msg.obj;//obj可以是任何类型
//    	   if(message.equals("发送成功")){
//    		   sendBtn.setText("60s");
//    		   sendBtnStr=sendBtn.getText().toString();
//    		   new AlertDialog.Builder(RegisterActivity.this)
//  				.setIcon(getResources().getDrawable(R.drawable.login_editbox))
//  				.setMessage("发送成功！请注意查收！")
//  				.create().show();	
//   		}else{
//   			 new AlertDialog.Builder(RegisterActivity.this)
//    			.setIcon(getResources().getDrawable(R.drawable.login_error_icon))
//    			.setMessage("发送失败！发生未知错误！")
//    			.create().show();
//   		} 								                                      
//             } 
//    	   };
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
//		// 短信内容
//		String msg = "您在布鲁上注册账号的验证码是" + gencode;
//		// 发送的数据字段
//		final List<NameValuePair> params = new ArrayList<NameValuePair>();
//		NameValuePair pair1 = new BasicNameValuePair("name", "aasdkjasdkj");
//		NameValuePair pair2 = new BasicNameValuePair("password", "asdsjka");
//		NameValuePair pair3 = new BasicNameValuePair("mobile", mobile);
//		NameValuePair pair4 = new BasicNameValuePair("msg", msg);
//		params.add(pair1);
//		params.add(pair2);
//		params.add(pair3);
//		params.add(pair4);
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				try {
//					HttpPost httpPost = new HttpPost("http://www.baidu.com");
//					HttpClient httpClient = new DefaultHttpClient();
//					HttpEntity httpEntity = new UrlEncodedFormEntity(params, "UTF-8");
//					httpPost.setEntity(httpEntity);
//					HttpResponse httpResponse = httpClient.execute(httpPost);
//					// HttpStatus.SC_OK代表返回成功
//					if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//						Message message = Message.obtain();
//						message.obj = "发送成功";
//						handler.sendMessage(message);
//					}
//
//				} catch (ClientProtocolException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}).start();
//		Looper.loop();
		
    }
    
  		//生成验证码
//	    public String genRanCode(int lenght){
//		 String base = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM0123456789";     
//		    Random random = new Random();     
//		    StringBuffer sb = new StringBuffer();     
//		    for (int i = 0; i < lenght; i++) {     
//		        int number = random.nextInt(base.length());     
//		        sb.append(base.charAt(number));     
//		    }      
//		    System.out.println(sb.toString());
//		    return sb.toString();
//	    }
	}


