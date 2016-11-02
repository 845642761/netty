package org.bo.netty.util;

import java.io.UnsupportedEncodingException;

import io.netty.buffer.ByteBuf;

public class ByteBufUtil {
	
	/**
	 * ByteBuf转String
	 * @author chengbo
	 * @date 2016年4月1日 18:51:15
	 */
	public static String ByteBufToStr(ByteBuf buf) {
		if(buf == null)
			return "";
		
		byte [] req = new byte[buf.readableBytes()];
	    buf.readBytes(req);
	    
	    String str = "";
		try {
			str =  new String(req,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
}
