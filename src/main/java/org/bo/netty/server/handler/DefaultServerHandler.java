package org.bo.netty.server.handler;

import org.apache.log4j.Logger;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class DefaultServerHandler extends ChannelInboundHandlerAdapter{
	private static Logger log = Logger.getLogger(DefaultServerHandler.class);
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		log.info(msg);
	}
}
