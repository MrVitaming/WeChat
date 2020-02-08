package lwm.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lwm.cache.LocalCache;
import lwm.pojo.*;

public class WexinInterfaceUtil {

	public static final String menu_create_url="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	
	public static final String individualmenu_create_url="https://api.weixin.qq.com/cgi-bin/menu/addconditional?access_token=ACCESS_TOKEN";
	
	public static final String menu_delete_url="https://api.weixin.qq.com/cgi-bin/menu/delconditional?access_token=ACCESS_TOKEN";
	
	public static final String kfacc_create_url="https://api.weixin.qq.com/customservice/kfaccount/update?access_token=ACCESS_TOKEN";
	
	public static final String eternal_media_batch_get_url="https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";
	
	public static final String eternal_media_create_photo_and_content="https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=ACCESS_TOKEN";
	
	public static final String batch_send_message_url="https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";
	
	public static final String preview_send_message_url="https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token=ACCESS_TOKEN";
	
	public static final String set_my_industry_url="https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKEN";
	
	public static final String	create_tag_url="https://api.weixin.qq.com/cgi-bin/tags/create?access_token=ACCESS_TOKEN";
	
	public static final String get_fans_with_tagid="https://api.weixin.qq.com/cgi-bin/user/tag/get?access_token=ACCESS_TOKEN";
	
	public static final String batch_tagging_url="https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging?access_token=ACCESS_TOKEN";

	public static final String get_tag_with_open_id="https://api.weixin.qq.com/cgi-bin/tags/getidlist?access_token=ACCESS_TOKEN";

	public static final String create_qrcode_url="https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
	
	public static final String get_user_summary_url="https://api.weixin.qq.com/datacube/getusersummary?access_token=ACCESS_TOKEN";
	
	public static final String get_user_cumulate_url="https://api.weixin.qq.com/datacube/getusercumulate?access_token=ACCESS_TOKEN";
	
	public static final String get_card_list_url="https://api.weixin.qq.com/card/user/getcardlist?access_token=ACCESS_TOKEN";
	
	public static final String get_card_info_url="https://api.weixin.qq.com/card/get?access_token=ACCESS_TOKEN";
	
	public static final String get_wifi_token_url="https://api.weixin.qq.com/bizwifi/openplugin/token?access_token=ACCESS_TOKEN";

	public static final String upload_tmp_media_url="https://api.weixin.qq.com/cgi-bin/media/upload";

	public static final String batch_get_material_url="https://api.weixin.qq.com/cgi-bin/material/batchget_material";




	public static final String getBatch_get_material_url(String type,int offset,int count) throws FileNotFoundException, IOException{
		String result="";

		String requestUrl = batch_get_material_url +
				"?access_token="+getToken() +
				"&type="+type+
				"&offset="+offset +
				"&count="+count;

		JSONObject jsonObject=httpRequest(requestUrl, "POST", null);

		if(jsonObject!=null){
			result=jsonObject.toString();
		}

		return result;
	}


	public static final String uploadTmpMedia(String url,String type) throws FileNotFoundException, IOException{
		String result="";

		String json=new JSONObject()
				.fluentPut("access_token",getToken())
				.fluentPut("type",type)
				.fluentPut("media",url)
				.toString();

		String requestUrl = upload_tmp_media_url +
							"?access_token="+getToken() +
							"&type="+type+
							"&media="+url;

		JSONObject jsonObject=httpRequest(requestUrl, "POST", null);

		if(jsonObject!=null){
			result=jsonObject.toString();
		}

		return result;
	}

	public static String getWifiToken() throws FileNotFoundException, IOException{
		String result="";
		String url=get_wifi_token_url.replace("ACCESS_TOKEN", getToken());
		String json=new JSONObject()
						.fluentPut("callback_url","http://weixin.qq.com/")
						.toString();
		
		JSONObject jsonObject=httpRequest(url, "POST", json);

		if(jsonObject!=null){
			result=jsonObject.toString();
		}
		
		return result;
	}
	
	public static String getCardInfo() throws FileNotFoundException, IOException{
		String result="";
		String url=get_card_info_url.replace("ACCESS_TOKEN", getToken());
		String json=new JSONObject()
						.fluentPut("card_id","pov0Av7VSOai6AcxwnIyhIH1ZcMU")
						.toString();
		
		JSONObject jsonObject=httpRequest(url, "POST", json);

		if(jsonObject!=null){
			result=jsonObject.toString();
		}
		
		return result;
	}
	
	public static String getCardList() throws FileNotFoundException, IOException{
		String result="";
		String url=get_card_list_url.replace("ACCESS_TOKEN", getToken());
		String json=new JSONObject()
						.fluentPut("openid", "oov0Av0I6CfDd-8LNu0Nz5gMrNQE")
						.fluentPut("card_id","pov0Av7VSOai6AcxwnIyhIH1ZcMU")
						.toString();
		
		JSONObject jsonObject=httpRequest(url, "POST", json);

		if(jsonObject!=null){
			result=jsonObject.toString();
		}
		
		return result;
	}
	
	/**
	 * 获取累计用户数
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String getUserCumulate() throws FileNotFoundException, IOException{
		String result="";
		String url=get_user_cumulate_url.replace("ACCESS_TOKEN", getToken());
		String json=new JSONObject()
						.fluentPut("begin_date", "2017-07-04")
						.fluentPut("end_date","2017-07-10")
						.toString();
		
		JSONObject jsonObject=httpRequest(url, "POST", json);

		if(jsonObject!=null){
			result=jsonObject.toString();
		}
		
		return result;
	}
	
	/**
	 * 获取用户增减数
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	
	public static String getUserSummary() throws FileNotFoundException, IOException{
		String result="";
		String url=get_user_summary_url.replace("ACCESS_TOKEN", getToken());
		String json=new JSONObject()
						.fluentPut("begin_date", "2017-07-04")
						.fluentPut("end_date","2017-07-10")
						.toString();
		
		JSONObject jsonObject=httpRequest(url, "POST", json);

		if(jsonObject!=null){
			result=jsonObject.toString();
		}
		
		return result;
	}

	
	/**
	 * 创建整数形式的永久二维码参数，
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String createQRCode() throws FileNotFoundException, IOException{
		String result="";
		String url=create_qrcode_url.replace("ACCESS_TOKEN", getToken());
		String json=new JSONObject()
						.fluentPut("actionname", "QR_LIMIT_SCENE")
						.fluentPut("action_info",new JSONObject().fluentPut("scene",new JSONObject().fluentPut("scene_id", 1)))
						.toString();
		
		JSONObject jsonObject=httpRequest(url, "POST", json);
		
		if(jsonObject!=null){
			result=jsonObject.toString();
		}
		
		return result;
	}
	
	public static String getTagWithOpenId() throws FileNotFoundException, IOException{
		String result="";
		String url=get_tag_with_open_id.replace("ACCESS_TOKEN", getToken());
		String json=new JSONObject()
						.fluentPut("openid","oov0Av0I6CfDd-8LNu0Nz5gMrNQE")
						.toString();
		
		JSONObject jsonObject=httpRequest(url, "POST", json);
		
		if(jsonObject!=null){
			result=jsonObject.toString();
		}
		
		return result;
	}
	
	public static String batchTagging() throws FileNotFoundException, IOException{
		String result="";
		String url=batch_tagging_url.replace("ACCESS_TOKEN", getToken());
		String json=new JSONObject()
						.fluentPut("openid_list",new JSONArray().add("oov0Av0I6CfDd-8LNu0Nz5gMrNQE"))
						.fluentPut("tagid", 100)
						.toString();
		
		JSONObject jsonObject=httpRequest(url, "POST", json);
		
		if(jsonObject!=null){
			result=jsonObject.toString();
		}
		
		return result;
	}
	
	public static String getFansWithTagId() throws FileNotFoundException, IOException{
		String result="";
		String url=get_fans_with_tagid.replace("ACCESS_TOKEN", getToken());
		String json=new JSONObject()
						.fluentPut("tagid", 100)
						.fluentPut("next_openid", "")
						.toString();
		
		JSONObject jsonObject=httpRequest(url, "POST", json);
		
		if(jsonObject!=null){
			result=jsonObject.toString();
		}
		
		return result;
	}
	
	public static String createTag() throws FileNotFoundException, IOException{
		String result="";
		String token=getToken();
		String url=create_tag_url.replace("ACCESS_TOKEN", token);
		String json=new JSONObject().fluentPut("tag", new JSONObject().fluentPut("name","MyFans")).toString();
		JSONObject jsonObject=httpRequest(url, "POST", json);
		
		if(jsonObject!=null){
			result=jsonObject.toString();
		}
		
		return result;
	}
	
	/**
	 * 设置公众号所在行业
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String setMyIndustry() throws FileNotFoundException, IOException{
		String result="";
		String token=getToken();
		String url=set_my_industry_url.replace("ACCESS_TOKEN",token);
		String json=new JSONObject().fluentPut("industry_id1","1")
					.fluentPut("industry_id2", "2")
					.toString();
		JSONObject jsonObject=httpRequest(url, "POST", json);
		if(jsonObject!=null){
			result=jsonObject.toString();
		}
		return result;
	}



	/**
	 * 获取缓存token
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private static String getToken() throws IOException, FileNotFoundException {
		return LocalCache.ACCESS_TOKEN;
	}
	
	/**
	 * 默认预览发送图文消息
	 * @param mpMessage
	 * @param token
	 * @return
	 */
	public static String previewSendMessage(MPMessage mpMessage,String token){
		String result="";
		String url=preview_send_message_url.replace("ACCESS_TOKEN", token);
		//jsonObject从前端传入
//		String messageJson=JSON.toJSONString(mpMessage).toString();
		String messageJson=new JSONObject().fluentPut("is_to_all", true)
							.fluentPut("touser","oov0Av0I6CfDd-8LNu0Nz5gMrNQE")
							.fluentPut("mpnews", new JSONObject().fluentPut("media_id","jgwuMixX4L6G9pdKclrUOfb5RDPL4vpy801RNnql8PI"))
							.fluentPut("msgtype","mpnews")
							.toString();
		JSONObject jsonObject=httpRequest(url, "POST", messageJson);
		if(jsonObject!=null){
			result=jsonObject.toString();
		}
		return result;
	}
	
	
	/**
	 * 测试号根据open_id消息群发
	 * @param mpMessage
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static String batchSendMessage(MPMessage mpMessage, String token_) throws FileNotFoundException, IOException {
		String token=getToken();
		String url=batch_send_message_url.replace("ACCESS_TOKEN", token);
//		String messageJson=JSON.toJSONString(mpMessage).toString();
		String messageJson=new JSONObject()
				.fluentPut("filter",new JSONObject().fluentPut("is_to_all",false).fluentPut("tag_id", 100))
				.fluentPut("mpnews", new JSONObject().fluentPut("media_id", "jgwuMixX4L6G9pdKclrUOfb5RDPL4vpy801RNnql8PI"))
				.fluentPut("msgtype", "mpnews")
				.fluentPut("send_ignore_reprint", 1)
				.toString();
		System.out.println("messageJson:"+messageJson);
		JSONObject jsonObject=httpRequest(url, "POST", messageJson);
		
		return jsonObject.toString();
	}

	public static String createPhotoAndContent(Article article,String token){
		String media_id="";
		String url=eternal_media_create_photo_and_content.replace("ACCESS_TOKEN", token);
		
		Article[] articles=new Article[]{article};
		Map<String, Article[]> map=new HashMap<>();
		map.put("articles", articles);
		
		String contentJson= JSON.toJSONString(map);
		
		JSONObject jsonObject=httpRequest(url, "POST", contentJson);
		
		if(jsonObject!=null){
			media_id=jsonObject.getString("media_id");
		}
		
		return media_id;
	}
	
	public static String getEternalMedia(MediaCriteria criteria,String token){
		String mediaJson=null;
		
		String url=eternal_media_batch_get_url.replace("ACCESS_TOKEN", token);
		String criteriaJson=JSON.toJSONString(criteria).toString();
		
		JSONObject jsonObject=httpRequest(url, "POST", criteriaJson);
		
		if(jsonObject!=null){
			mediaJson=jsonObject.toString();
		}
		
		return mediaJson;
	}
	
	public static int createKFAccount(KFAccount account,String token){
		int result=0;
		
		String url=kfacc_create_url.replace("ACCESS_TOKEN", token);
		String accountJson=JSON.toJSONString(account).toString();
		
		JSONObject jsonObject = httpRequest(url, "POST", accountJson);
		
		if(jsonObject!=null){
			if(jsonObject.getInteger("errcode")!=0){
				result=jsonObject.getInteger("errcode");
				System.out.println(jsonObject.getString("errmsg"));
			}
		}
		return result;
	}
	
	public static int createMenu(Menu menu,String token){
		int result=0;
		//拼接创建菜单的url
		String url=individualmenu_create_url.replace("ACCESS_TOKEN", token);
		//将菜单对象转换成json字符串
		String menuJson=JSON.toJSONString(menu).toString();
		//调用微信接口创建菜单
		JSONObject jsonObject=httpRequest(url,"POST",menuJson);
		
		if(jsonObject!=null){
			if(jsonObject.getInteger("errcode")!=0){
				result=jsonObject.getInteger("errcode");
				System.out.println("创建菜单失败:"+jsonObject.getString("errmsg"));
			}
		}
		return result;
	}
	
	public static int deleteMenu(String menuJson,String token){
		int result=0;
		//拼接创建菜单的url
		String url=menu_delete_url.replace("ACCESS_TOKEN", token);
		//调用微信接口创建菜单
		JSONObject jsonObject=httpRequest(url,"POST",menuJson);
		
		if(jsonObject!=null){
			if(jsonObject.getInteger("errcode")!=0){
				result=jsonObject.getInteger("errcode");
				System.out.println("删除菜单失败:"+jsonObject.getString("errmsg"));
			}
		}
		return result;
	}
	
	public static JSONObject httpRequest(String requestUrl,String requestType,String menuJson){
		JSONObject jsonObject=null;
		StringBuffer buffer=new StringBuffer();
		try{
		//创建SSLContext对象，并使用自己指定的信任管理器初始化
		TrustManager[] trustManagers={new MyX509TrustManager()};
		SSLContext sslContext=SSLContext.getInstance("SSL", "SunJSSE");
		sslContext.init(null, trustManagers, new SecureRandom());
		//从SSLContext对象中得到SSLSocketFactory对象
		SSLSocketFactory ssf=sslContext.getSocketFactory();
		
		URL url=new URL(requestUrl);
		//创建连接对象
		HttpsURLConnection httpsURLConnection=(HttpsURLConnection) url.openConnection();
		//设置连接属性
		httpsURLConnection.setSSLSocketFactory(ssf);
		httpsURLConnection.setDoOutput(true);
		httpsURLConnection.setDoInput(true);
		httpsURLConnection.setUseCaches(false);
		//请求方式
		httpsURLConnection.setRequestMethod(requestType);
		
		if(requestType.equalsIgnoreCase("GET")){
			//建立连接
			httpsURLConnection.connect();
		}
		
		//当有数据需要提交时
		if(menuJson!=null){
			OutputStream out=httpsURLConnection.getOutputStream();
			out.write(menuJson.getBytes("utf-8"));
			out.close();
		}
		
		//将返回的输入流转换成字符串
		InputStream in=httpsURLConnection.getInputStream();
		InputStreamReader reader=new InputStreamReader(in, "utf-8");
		BufferedReader bufferedReader=new BufferedReader(reader);
		
		String str=null;
		while((str=bufferedReader.readLine())!=null){
			buffer.append(str);
		}
		//释放
		bufferedReader.close();
		reader.close();
		in.close();
		httpsURLConnection.disconnect();
		System.out.println("微信服务端返回结果:"+buffer.toString());
		jsonObject=JSONObject.parseObject(buffer.toString());
		}catch(Exception e){
			System.out.println("请求发送出现异常:");
			e.printStackTrace();
		}
		return jsonObject;
	}

}
