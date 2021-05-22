package take.nettyserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import take.Util.ObjectCodec;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * 功能描述：Netty监听
 *
 * @author EO
 * @date 2021/5/19 22:12
 */
@Component
public class NettyServerListener {
    /**
     * NettyServerListener 日志输出器
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyServerListener.class);
    /**
     * 创建bootstrap   Bootstrap是Netty应用开发的入口
     */
    ServerBootstrap serverBootstrap = new ServerBootstrap();
    /**
     * BOSS  EventLoopGroup负责管理Channel的事件处理任务 结构类似线程池
     * AbstractBootstrap中定义了主线程池group
     */
    EventLoopGroup boss = new NioEventLoopGroup();
    /**
     * Worker
     * childGroup的引用是定义在ServerBootstrap
     */
    EventLoopGroup work = new NioEventLoopGroup();
    /**
     * 通道适配器
     */
    @Resource
    private ServerChannelHandlerAdapter channelHandlerAdapter;
    /**
     * NETT服务器配置类
     */
    @Resource
    private NettyConfig nettyConfig;

    /**
     * 关闭服务器方法  @PreDestroy修饰的方法会在服务器卸载Servlet的时候运行，并且只会被服务器调用一次
     */
    @PreDestroy
    public void close() {
        LOGGER.info("关闭服务器....");
        //优雅退出
        boss.shutdownGracefully();
        work.shutdownGracefully();
    }

    /**
     * 开启及服务线程
     */
    public void start() {
        // 从配置文件中(application.yml)获取服务端监听端口号
        // todo 这里配置文件读取失败，有时间修改下
//        int port = nettyConfig.getPort();
        int port = 12131;
        serverBootstrap.group(boss, work)
                // 服务器端 初始化工厂实例
                .channel(NioServerSocketChannel.class)
                /**
                 * BACKLOG用于构造服务端套接字ServerSocket对象，标识当服务器请求处理线程全满时，
                 * 用于临时存放已完成三次握手的请求的队列的最大长度。
                 * 如果未设置或所设置的值小于1，Java将使用默认值50
                 */
                .option(ChannelOption.SO_BACKLOG, 100)
                /**
                 * 启用心跳机制  两个小时左右上层没有任何数据传输的情况下，这套机制才会被激活。
                 *
                 * 可见option可以多传几个
                 */
                .option(ChannelOption.SO_KEEPALIVE, true)
                // 对入站\出站事件进行日志记录，从而方便我们进行问题排查
                .handler(new LoggingHandler(LogLevel.INFO));
        try {
            //设置事件处理
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast(new LengthFieldBasedFrameDecoder(65535
                            , 0, 2, 0, 2));
                    pipeline.addLast(new LengthFieldPrepender(2));
                    pipeline.addLast(new ObjectCodec());

                    pipeline.addLast(channelHandlerAdapter);
                }
            });
            LOGGER.info("netty服务器在[{}]端口启动监听", port);
            // 操作返回接口 异步通知
            ChannelFuture f = serverBootstrap.bind(port).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            LOGGER.info("[出现异常] 释放资源");
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }
}
