package lwm.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.log4j.Log4j2;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

@Log4j2
public class MessageUtil {
	/**
	 * 返回信息类型：文本
	 */
	public static final String  RESP_MESSSAGE_TYPE_TEXT = "text";
	/**
	 * 返回信息类型：音乐
	 */
	public static final String  RESP_MESSSAGE_TYPE_MUSIC = "music";
	/**
	 * 返回信息类型：图文
	 */
	public static final String  RESP_MESSSAGE_TYPE_NEWS = "news";
	/**
	 * 请求信息类型：文本
	 */
	public static final String  REQ_MESSSAGE_TYPE_TEXT = "text";
	/**
	 * 请求信息类型：图片
	 */
	public static final String  REQ_MESSSAGE_TYPE_IMAGE = "image";
	/**
	 * 请求信息类型：链接
	 */
	public static final String  REQ_MESSSAGE_TYPE_LINK = "link";
	/**
	 * 请求信息类型：地理位置
	 */
	public static final String  REQ_MESSSAGE_TYPE_LOCATION = "location";
	/**
	 * 请求信息类型：音频
	 */
	public static final String  REQ_MESSSAGE_TYPE_VOICE = "voice";
	/**
	 * 请求信息类型：推送
	 */
	public static final String  REQ_MESSSAGE_TYPE_EVENT = "event";
	/**
	 * 事件类型：subscribe（订阅）
	 */
	public static final String  EVENT_TYPE_SUBSCRIBE = "subscribe";
	/**
	 * 事件类型：unsubscribe（取消订阅）
	 */
	public static final String  EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
	/**
	 * 事件类型：click（自定义菜单点击事件）
	 */
	public static final String  EVENT_TYPE_CLICK= "CLICK";
	
	/**
	 * 事件类型：view（自定义菜单点击事件,返回url）
	 */
	public static final String  EVENT_TYPE_VIEW= "VIEW";
	
	/**
	 * 事件类型：scancode_waitmsg（自定义菜单扫码事件，返回消息）
	 */
	public static final String EVENT_TYPE_SCANCODE_WAITMSG="scancode_waitmsg";
	
	public static Map<String, String> parseXml(HttpServletRequest request) throws IOException, DocumentException{
		Map<String, String> map=new HashMap<>();
		
		//获取输入流
		InputStream in=request.getInputStream();

		//读取输入流
		SAXReader reader=new SAXReader();
		Document document=reader.read(in);
		
		//得到xml根元素
		Element root=document.getRootElement();
		
		//得到根元素的所有节点
		List<Element> list=root.elements();

		//遍历所有子节点并取得信息内容
		log.info("xml内容:");
		for (Element element : list) {
			log.info(element.getName()+"->"+element.getText());
			map.put(element.getName(), element.getText());
		}

		//释放资源
		in.close();
		
		return map;
	}
}
