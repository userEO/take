package take.nettyserver;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import take.Util.MethodInvokeMeta;

import javax.annotation.Resource;

/**
 * 功能描述：通道适配器
 *
 * @author EO
 * @date 2021/5/19 22:14
 */
@Component
@ChannelHandler.Sharable
public class ServerChannelHandlerAdapter extends ChannelInboundHandlerAdapter {
    /**
     * 日志处理
     */
    private Logger logger = LoggerFactory.getLogger(ServerChannelHandlerAdapter.class);
    /**
     * 注入请求分排器
     */
    @Resource
    private RequestDispatcher dispatcher;

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        MethodInvokeMeta invokeMeta = (MethodInvokeMeta) msg;
        // 屏蔽toString()方法
        if (invokeMeta.getMethodName().endsWith("toString()")
                && !"class java.lang.String".equals(invokeMeta.getReturnType().toString()))
            logger.info("客户端传入参数 :{},返回值：{}",
                    invokeMeta.getArgs(), invokeMeta.getReturnType());
        dispatcher.dispatcher(ctx, invokeMeta);
    }
}
