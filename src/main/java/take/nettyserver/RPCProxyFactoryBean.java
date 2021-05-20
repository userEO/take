package take.nettyserver;

import org.springframework.beans.factory.config.AbstractFactoryBean;
import take.Util.MethodInvokeMeta;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 功能描述：
 *
 * @author EO
 * @date 2021/5/20 22:53
 */
public class RPCProxyFactoryBean extends AbstractFactoryBean implements InvocationHandler {
    private Class interfaceClass;

//    private NettyClient nettyClient;

    @Override
    public Class<?> getObjectType() {
        return interfaceClass;
    }

    @Override
    protected Object createInstance() throws Exception {
//        logger.info("[代理工厂] 初始化代理Bean : {}", interfaceClass);
        return Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
//        final MethodInvokeMeta methodInvokeMeta = WrapMethodUtils.readMethod(interfaceClass, method, args);
//        if (!methodInvokeMeta.getMethodName().equals("toString")) {
//            logger.info("[invoke] 调用接口{},调用方法名：{}，入参：{},参数类型：{}，返回值类型{}",
//                    methodInvokeMeta.getInterfaceClass(), methodInvokeMeta.getMethodName()
//                    , methodInvokeMeta.getArgs(), methodInvokeMeta.getParameterTypes(), methodInvokeMeta.getReturnType());
//        }
//        return nettyClient.remoteCall(methodInvokeMeta, 0);
        return null;
    }

//    public void setInterfaceClass(Class interfaceClass) {
//        this.interfaceClass = interfaceClass;
//    }
//
//    public void setNettyClient(NettyClient nettyClient) {
//        this.nettyClient = nettyClient;
//    }
}
