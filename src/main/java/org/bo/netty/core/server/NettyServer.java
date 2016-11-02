package org.bo.netty.core.server;

import org.bo.netty.core.config.NettyConfig;

/**
 * netty服务接口
 * @author chengbo
 * @date 2016年3月25日 14:30:37
 */
public interface NettyServer {
	public void run(NettyConfig config);
	public void stop();
}
