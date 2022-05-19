package com.briup.util;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * ���ڹ�����
 * @author 
 *
 */
public class DateUtil {
	//���ڸ�ʽת��
	public static String formatDate(Date date,String format){
		String result="";
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		if(date!=null){
			result=sdf.format(date);
		}
		return result;
	}
	//��ʱ���ַ�תת��ΪDate��	
	public static Date formatString(String str,String format) throws Exception{
		if(StringUtil.isEmpty(str)){
			return null;
		}
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		return sdf.parse(str);
	}
	//��ȡ��ǰʱ���ַ���
	public static String getCurrentDateStr()throws Exception{
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
		return sdf.format(date);
	}
}
