package com.hshy41.mane.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.text.TextUtils;
import android.util.Xml.Encoding;

public class StringUtils {

	/**
	 * 简单判断是否json
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isJson(String str) {

		if (str.trim().startsWith("{") && str.trim().endsWith("}")) {
			return true;
		}

		return false;

	}

	/**
	 * 按照固定長度截取字符串
	 * 
	 * @param str
	 *            原字符串
	 * @param lenght
	 *            規定長度
	 * @return
	 */
	public static String subStringAsLenght(String string, int lenght) {
		String str = "";
		if (string.length() > lenght) {
			str = string;
		} else {
			str = string.substring(0, lenght) + "...";
		}

		return str;
	}


	/**
	 * 時間戳格式化成 yyyy-MM-dd
	 * 
	 * @param strDate
	 * @return
	 */
	public static String strToDate(String milliseconds) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(new Date(Long.valueOf(milliseconds)));
		
	}
	/**
	 * 時間戳格式化成 MM-dd
	 * 
	 * @param strDate
	 * @return
	 */
	public static String strTodate(String milliseconds) {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd");
		return formatter.format(new Date(Long.valueOf(milliseconds + "000")));
		
	}
	/**
	 * 時間戳格式化成 yyyy-MM-dd HH:mm
	 * 
	 * @param strDate
	 * @return
	 */
	public static String strToTime(String milliseconds) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return formatter.format(new Date(Long.valueOf(milliseconds)*1000));

	}
	/**
	 * yyyy年MM月dd日 HH:mm转换时间戳
	 * @param time
	 * @return
	 */
    public static long getStringToDate(String time) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        Date date = new Date();
        try{
            date = sdf.parse(time);
        } catch(ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime()/1000;
    }
    /**
     * yyyy-MM-dd HH:mm转换时间戳
     * @param time
     * @return
     */
    public static long getStrToDate(String time) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    	Date date = new Date();
    	try{
    		date = sdf.parse(time);
    	} catch(ParseException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	return date.getTime()/1000;
    }
    /**
     * yyyy-MM-dd HH:mm转换时间戳
     * @param time
     * @return
     */
    public static long getStrTodate(String time) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    	Date date = new Date();
    	try{
    		date = sdf.parse(time);
    	} catch(ParseException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	return date.getTime();
    }

	/**
	 * 验证密码
	 * 
	 * @param pwd
	 * @return
	 */
	public static boolean isPwd(String pwd) {

		if (null == pwd || pwd.equals(""))
			return false;
		if (pwd.length() < 6 || pwd.length() > 20)
			return false;
		return true;
	}

	/**
	 * 验证手机号
	 */
	public static boolean isMobileNO(String mobiles) {
		/*
		 * �ƶ���134��135��136��137��138��139��150��151��157(TD)��158��159��187��188
		 * ��ͨ��130��131��132��152��155��156��185��186
		 * ���ţ�133��153��180��189����1349��ͨ��
		 * �ܽ��������ǵ�һλ�ض�Ϊ1���ڶ�λ�ض�Ϊ3��5��8������λ�õĿ���Ϊ0-9
		 */
		String telRegex = "[1][3578]\\d{9}";// "[1]"����1λΪ����1��"[358]"���ڶ�λ����Ϊ3��5��8�е�һ����"\\d{9}"�������ǿ�����0��9�����֣���9λ��
		if (TextUtils.isEmpty(mobiles))
			return false;
		else
			return mobiles.matches(telRegex);
	}

	/**
	 * 判断字符串是否为数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		boolean isNum=true;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c > 58 || c < 47) {
				isNum= false;
				break;
			}
		}
		return isNum;
	}
	/**
	 * 解码base64
	 */
	 private final static String CODE_STR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";  
	    private final static int ORGINAL_LEN = 8;  
	    private final static int NEW_LEN = 6;  
    public static String decodeBase64(String encodeStr) throws Exception{  
        StringBuilder sb = new StringBuilder("");  
        for (int i = 0; i < encodeStr.length(); i++){  
              
            char c = encodeStr.charAt(i);       //把"1tC5sg=="字符串一个个分拆  
            int k = CODE_STR.indexOf(c);        //分拆后的字符在CODE_STR中的位置,从0开始,如果是'=',返回-1  
            if(k != -1){                        //如果该字符不是'='  
                String tmpStr = Integer.toBinaryString(k);  
                int n = 0;  
                while(tmpStr.length() + n < NEW_LEN){  
                    n ++;  
                    sb.append("0");  
                }  
                sb.append(tmpStr);  
            }  
        }  
          
        /** 
         * 8个字节分拆一次，得到总的字符数 
         * 余数是加密的时候补的，舍去 
         */  
        int newByteLen = sb.length() / ORGINAL_LEN;           
          
        /** 
         * 二进制转成字节数组 
         */  
        byte[] b = new byte[newByteLen];  
        for(int j = 0; j < newByteLen; j++){  
            b[j] = (byte)Integer.parseInt(sb.substring(j * ORGINAL_LEN, (j+1) * ORGINAL_LEN),2);  
        }  
          
        /** 
         * 字节数组还原成String 
         */  
        return new String(b, "gb2312");  
    } 
}
