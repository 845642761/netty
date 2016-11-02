package org.bo.netty.client.initializer;

import org.bo.netty.client.handler.DefaultClientHandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class DefaultClientInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel sc) throws Exception {
			ChannelPipeline pipeline = sc.pipeline();
			// 字符串解码 和 编码
			pipeline.addLast("decoder", new StringDecoder());
			pipeline.addLast("encoder", new StringEncoder());
			pipeline.addLast("handler",new DefaultClientHandler());
	}

}
