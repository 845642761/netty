package org.bo.netty.test.discard;

import org.bo.netty.client.DefaultNettyClient;
import org.bo.netty.core.config.NettyConfig;

public class DefaultNettyClient_Test {
	public static void main(String args[]) {
		NettyConfig nc = new NettyConfig();
		nc.setPort(9000);
		nc.setHost("127.0.0.1");
		
		DefaultNettyClient dnc = new DefaultNettyClient();
		dnc.run(nc);
		for (int i = 0; i < 10; i++) {
			dnc.sendMessage("\n****" + i);
		}
		dnc.stop();
	}
}
