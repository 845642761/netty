package org.bo.netty.test.discard;

import org.bo.netty.core.config.NettyConfig;
import org.bo.netty.server.DefaultNettyServer;

public class DefaultNettyServer_Test {

	public static void main(String[] args) {
		DefaultNettyServer ns = new DefaultNettyServer();
		NettyConfig nc = new NettyConfig();
		nc.setPort(9000);
		ns.run(nc);
	}
}
