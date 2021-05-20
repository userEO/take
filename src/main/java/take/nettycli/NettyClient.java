package take.nettycli;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import jdk.nashorn.internal.runtime.linker.Bootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.MBeanServer;

/**
 * 功能描述：
 *
 * @author EO
 * @date 2021/5/20 22:57
 */
//public class NettyClient {
//    private Logger logger = LoggerFactory.getLogger(MBeanServer.class);
//    private Bootstrap bootstrap;
//    private EventLoopGroup worker;
//    private int port;
//    private String url;
//    private int MAX_RETRY_TIMES = 10;
//
//    public NettyClient(String url, int port) {
//        this.url = url;
//        this.port = port;
//        bootstrap = new Bootstrap();
//        worker = new NioEventLoopGroup();
//        bootstrap.group(worker);
//        bootstrap.channel(NioSocketChannel.class);
//    }
//
//    public void close() {
//        logger.info("关闭资源");
//        worker.shutdownGracefully();
//    }
//
//    public Object remoteCall(final MethodInvokeMeta cmd, int retry) {
//        try {
//            CustomChannelInitializerClient customChannelInitializer = new CustomChannelInitializerClient(cmd);
//            bootstrap.handler(customChannelInitializer);
//            ChannelFuture sync = bootstrap.connect(url, port).sync();
//            sync.channel().closeFuture().sync();
//            Object response = customChannelInitializer.getResponse();
//            return response;
//        } catch (InterruptedException e) {
//            retry++;
//            if (retry > MAX_RETRY_TIMES) {
//                throw new RuntimeException("调用Wrong");
//            } else {
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e1) {
//                    e1.printStackTrace();
//                }
//                logger.info("第{}次尝试....失败", retry);
//                return remoteCall(cmd, retry);
//            }
//        }
//    }
//}
