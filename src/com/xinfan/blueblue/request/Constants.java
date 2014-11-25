package com.xinfan.blueblue.request;


/**
 * 公共静态类
 * 
 * @author chen
 * @date 2012-10-25 下午3:13:44
 */
public final class Constants {

	public static final String SERVER_SETTING = "server_setting";// 保存ip、port的xml文件名

	public static final String USER_INFO = "user_info";// 保存用户信息的xml文件名
	public static final String SYSTEM_SET = "system_set_info";// 保存用户系统设置xml文件名,为区分多个用户这里只是配置XML文件名的前半部分，
															//完整文件名由system_set_info + userid组成

	public static final String DETAULT_VERSION = "1.0.0";

	/**
	 * 分页信息
	 * 
	 * @author chen
	 * @date 2012-11-8 下午1:44:36
	 */
	public static final class page {
		/** 每页数量 */
		public static final int pageSize = 10;
	}


    /**
     * 网络连接
     * @author chen
     * @date 2012-10-25 下午3:14:36
     */
    public static final class http {
    	/** 服务器地址 */
    	public static final String http_request_uri = "MainService";

    	//public static final String http_request_ip = "192.168.1.103:7001";
    	
    	public static final String http_request_ip = "112.124.37.223:9001";

    	//public static final String http_request_head = "http://192.168.1.102:7001/MainService";
    }
}