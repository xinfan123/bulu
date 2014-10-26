package com.xinfan.blueblue.activity;

import com.xinfan.blueblue.activity.base.BaseActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.WindowManager.LayoutParams;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;

public class Main extends BaseActivity {

    public static Main instance = null;
	//public static Main instance ;
    private PopupWindow menuWindow;
    private LinearLayout mClose;
    private LinearLayout mCloseBtn;
    private LayoutInflater inflater; //����ǽ�xml�еĲ�����ʾ����Ļ�ϵĹؼ���
    private View layout;	
	private boolean menu_display = false;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.main);        
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title); 
        instance = this;
        //Main instance = Main.this;
    }
	public void restartbutton(View v) {  
      	Intent intent = new Intent();
		intent.setClass(Main.this,ViewpagerActivity.class);
		startActivity(intent);
		this.finish();
      }  

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {  //��ȡ back��
    		
        	if(menu_display){         //��� Menu�Ѿ��� ���ȹر�Menu
        		menuWindow.dismiss();
        		menu_display = false;
        		}
        	else {
        		Intent intent = new Intent();
            	intent.setClass(Main.this,ExitActivity.class);
            	startActivity(intent);
        	}
    	}
    	
    	else if(keyCode == KeyEvent.KEYCODE_MENU){   //��ȡ Menu��			
			if(!menu_display){
				//��ȡLayoutInflaterʵ��
				inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
				//�����main��������inflate�м����Ŷ����ǰ����ֱ��this.setContentView()�İɣ��Ǻ�
				//�÷������ص���һ��View�Ķ����ǲ����еĸ�
				layout = inflater.inflate(R.layout.main_menu, null);
				
				//��������Ҫ�����ˣ����������ҵ�layout���뵽PopupWindow���أ������ܼ�
				menuWindow = new PopupWindow(layout,LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT); //������������width��height
				//menuWindow.showAsDropDown(layout); //���õ���Ч��
				//menuWindow.showAsDropDown(null, 0, layout.getHeight());
				menuWindow.showAtLocation(this.findViewById(R.id.main), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0); //����layout��PopupWindow����ʾ��λ��
				//��λ�ȡ����main�еĿؼ��أ�Ҳ�ܼ�
				mClose = (LinearLayout)layout.findViewById(R.id.menu_close);
				mCloseBtn = (LinearLayout)layout.findViewById(R.id.menu_close_btn);
				
				
				//�����ÿһ��Layout���е����¼���ע��ɡ�����
				//���絥��ĳ��MenuItem��ʱ�����ı���ɫ�ı�
				//����׼����һЩ����ͼƬ������ɫ
				mCloseBtn.setOnClickListener (new View.OnClickListener() {					
					@Override
					public void onClick(View arg0) {						
						//Toast.makeText(Main.this, "�˳�", Toast.LENGTH_LONG).show();
						Intent intent = new Intent();
			        	intent.setClass(Main.this,ExitActivity.class);
			        	startActivity(intent);
			        	menuWindow.dismiss(); //��Ӧ����¼�֮��ر�Menu
					}
				});				
				menu_display = true;				
			}else{
				//�����ǰ�Ѿ�Ϊ��ʾ״̬������������
				menuWindow.dismiss();
				menu_display = false;
				}
			
			return false;
		}
    	return false;
    }
    
    
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	
    	menu.add(Menu.NONE, Menu.FIRST +1, 1, "�˳�").setIcon(R.drawable.btn_close);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

        case Menu.FIRST +1: 	        	
        	Intent intent = new Intent();
        	intent.setClass(Main.this,Exit.class);
        	startActivity(intent);
            break;}
            return false;
        }      */



}




