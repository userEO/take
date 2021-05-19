package take.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import take.service.UserService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 功能描述：动态代理
 *
 *在Java中要想实现动态代理机制，需要java.lang.reflect.InvocationHandler接口和 java.lang.reflect.Proxy 类的支持
 * @author EO
 * @date 2019/12/8 16:43
 */
//java.lang.reflect.InvocationHandler接口的定义如下
//Object proxy:被代理的对象
//Method method:要调用的方法
//Object[] args:方法调用时所需要参数
//public interface InvocationHandler {
//    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
//}
//    java.lang.reflect.Proxy类的定义如下：
//
////CLassLoader loader:类的加载器
////Class<?> interfaces:得到全部的接口
////InvocationHandler h:得到InvocationHandler接口的子类的实例
//public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h) throws IllegalArgumentException
//
public class userServiceImplProxyHandler implements InvocationHandler {
    @Autowired
    private UserService userService;

    //绑定关系，也就是关联到哪个接口（与具体的实现类绑定）的哪些方法将被调用时，执行invoke方法。
    public Object newProxyInstance(UserService userService){
        this.userService=userService;
        //该方法用于为指定类装载器、一组接口及调用处理器生成动态代理类实例
        //第一个参数指定产生代理对象的类加载器，需要将其指定为和目标对象同一个类加载器
        //第二个参数要实现和目标对象一样的接口，所以只需要拿到目标对象的实现接口
        //第三个参数表明这些被拦截的方法在被拦截时需要执行哪个InvocationHandler的invoke方法
        //根据传入的目标返回一个代理对象
        return Proxy.newProxyInstance(userService.getClass().getClassLoader(),
                userService.getClass().getInterfaces(),this);
    }

    @Override
    //关联的这个实现类的方法被调用时将被执行
    /*InvocationHandler接口的方法，proxy表示代理，method表示原对象被调用的方法，args表示方法的参数*/
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("start-->>");
        for(int i=0;i<args.length;i++){
            System.out.println(args[i]);
        }
        Object ret=null;
        try{
            /*原对象方法调用前处理日志信息*/
            System.out.println("satrt-->>");

            //调用目标方法
            ret=method.invoke(userService, args);
            /*原对象方法调用后处理日志信息*/
            System.out.println("success-->>");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("error-->>");
            throw e;
        }
        return ret;
    }
}
//客户端调用代码 从这里可以看出动态代理是在想调用的时候，去调对应方法和传入参数
/*public class Client {

    public static void main(String[] args){
        LogHandler logHandler=new LogHandler();
        UserManager userManager=(UserManager)logHandler.newProxyInstance(new UserManagerImpl());
        //UserManager userManager=new UserManagerImpl();
        userManager.addUser("1111", "张三");
    }
}*/


