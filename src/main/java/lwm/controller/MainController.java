package lwm.controller;

import lombok.extern.log4j.Log4j2;
import lwm.config.AuthConfig;
import lwm.service.MessageService;
import lwm.util.SignUtil;
import lwm.util.WexinInterfaceUtil;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by LWM on 2019/8/25.
 */
@Controller
@Log4j2
public class MainController {


    @Autowired
    private AuthConfig authConfig;

    @Autowired
    private MessageService messageService;

    /**
     * 用于公众平台的token验证
     * 确认请求来源于微信服务器
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public void handShake(HttpServletRequest request, HttpServletResponse response) {
        log.info("token验证开始");

        //微信加密签名
        String signature = request.getParameter("signature");
        //时间戳
        String timestamp = request.getParameter("timestamp");
        //随机数
        String nonce = request.getParameter("nonce");
        //随机字符串
        String echostr = request.getParameter("echostr");

        PrintWriter out = null;
        try {
            out = response.getWriter();

            //通过signature对请求进行校验，
            //若校验成功则原样返回echostr，表示接入成功，否则接入失败
            if (SignUtil.checkSignature(authConfig.getToken(), signature, timestamp, nonce)) {
                out.print(echostr);
            }

            log.info("token验证结束");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * doPost中用于处理微信服务器发来的消息，
     * 其中EastnetService为业务层，专门用于处理消息并作出响应。
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");

            //调用核心业务类型接受消息、处理消息
            String respMessage = messageService.processRequest(request);
            if (StringUtils.isEmpty(respMessage)) {
                return;
            }

            out = response.getWriter();
            out.println(respMessage);

            log.info("return msg={}",respMessage);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

}
