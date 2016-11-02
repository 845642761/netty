package org.bo.netty.core.client;

import org.bo.netty.core.config.NettyConfig;

public interface NettyClient {
	public void run(NettyConfig config);
	public void stop();
}
