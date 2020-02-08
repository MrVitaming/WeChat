package lwm.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

//签名验证工具
public class SignUtil {

    public static boolean checkSignature(String token,String signature,String timestamp,String nonce){
        String[] array=new String[]{token,timestamp,nonce};

        //对数组进行字典排序
        Arrays.sort(array);

        StringBuilder sb=new StringBuilder();

        //排序后拼接token，timestamp和随机数nonce
        for(int i=0;i<array.length;i++){
            sb.append(array[i]);
        }

        MessageDigest md=null;

        String stnStr=null;

        try {
            md=MessageDigest.getInstance("SHA-1");
            byte[] digest=md.digest(sb.toString().getBytes());
            stnStr=byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        sb=null;

        //将sha1加密后的字符串与signature对比，标识该请求来源于微信
        return stnStr!=null?stnStr.equals(signature.toUpperCase()):false;
    }

    /**
     * 将字节数组转换成16进制字符串
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray){
        String digestStr="";
        for(int i=0;i<byteArray.length;i++){
            digestStr += byteToHexStr(byteArray[i]);
        }
        return digestStr;
    }

    /**
     * 将字节数转换为十六进制字符串
     * @param aByte
     * @return
     */
    private static String byteToHexStr(byte aByte){
        //十六进制对应十进制的0-15，即0-F
        char[] Digit = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

        //一个字节占八位,即8bit,一个十六进制数占4位,即4bit，
        //因此一个字节有两个十进制数
        char[] tmpArr = new char[2];
        tmpArr[0] = Digit[(aByte>>>4)&0X0F];//取当前字节的高四位
        tmpArr[1] = Digit[aByte&0X0F];//取当前字节的低四位
        String s = new String(tmpArr);
        return s;
    }

}
