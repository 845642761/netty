package org.bo.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.log4j.Logger;
import org.bo.netty.core.config.NettyConfig;
import org.bo.netty.core.server.NettyServer;
import org.bo.netty.server.initializer.DefaultServerInitializer;

public class DefaultNettyServer implements NettyServer {
	private static Logger log = Logger.getLogger(DefaultNettyServer.class);
	private EventLoopGroup bossGroup = null;
	private EventLoopGroup workerGroup = null;
	
	/**
	 * 启动服务
	 * @author chengbo
	 * @date 2016年3月28日 14:30:35
	 */
	@Override
	public void run(NettyConfig config) {
		bossGroup = new NioEventLoopGroup();
		workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workerGroup);
			bootstrap.channel(NioServerSocketChannel.class);
			bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
			bootstrap.childHandler(new DefaultServerInitializer());
			//绑定端口，同步等待成功
			ChannelFuture f = bootstrap.bind(config.getPort()).sync();
			//等待服务端监听端口关闭
			f.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}

	/**
	 * 关闭服务
	 * @author chengbo
	 * @date 2016年3月28日 14:32:05
	 */
	@Override
	public void stop() {
		workerGroup.shutdownGracefully();
		bossGroup.shutdownGracefully();
	}
}
