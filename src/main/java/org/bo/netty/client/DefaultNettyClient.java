package org.bo.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.log4j.Logger;
import org.bo.netty.client.initializer.DefaultClientInitializer;
import org.bo.netty.core.client.NettyClient;
import org.bo.netty.core.config.NettyConfig;

/**
 * DefaultNettyClient
 * @author chengbo
 * @date 2016年3月28日 14:35:57
 */
public class DefaultNettyClient implements NettyClient {
	private static Logger log = Logger.getLogger(DefaultNettyClient.class);
	private EventLoopGroup eventLoopGroup;
	private Channel channel;
	

	@Override
	public void run(NettyConfig config) {
		eventLoopGroup = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.channel(NioSocketChannel.class);
			bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
			bootstrap.option(ChannelOption.TCP_NODELAY, true);
			bootstrap.group(eventLoopGroup);
			bootstrap.handler(new DefaultClientInitializer());
			ChannelFuture future = bootstrap.connect(config.getHost(), config.getPort()).sync();
			channel = future.channel();
			//等待客户端链路关闭
			//channel.closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
	}
	
	/**
	 * 发送消息
	 * @author chengbo
	 * @date 2016年3月30日 11:20:13
	 */
	public void sendMessage(String msg) {
		channel.writeAndFlush(msg);
	}

	@Override
	public void stop() {
		eventLoopGroup.shutdownGracefully();
	}
}
