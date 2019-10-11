package take.shirologin;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import take.service.UserService;
import take.service.serviceImpl.UserServiceImpl;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 功能描述：
 *
 * @author EO
 * @date 2019/9/9 9:41
 */
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private  UserService userService;
    /**
     * 权限认证，即登录过后，每个身份不一定，对应的所能看的页面也不一样。
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        String username = (String) SecurityUtils.getSubject().getPrincipal();
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        Set<String> stringSet = new HashSet<>();
//        stringSet.add("user:show");
//        stringSet.add("user:admin");
//        info.setStringPermissions(stringSet);
//        return info;
          return null;
    }

    /**
     * 注入userservice  身份认证。即登录通过账号和密码验证登陆人的身份信息。
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("-------身份认证方法--------");
        //从token获取用户名和密码
        String userName = (String) authenticationToken.getPrincipal();
        String userPwd = new String((char[]) authenticationToken.getCredentials());

        //根据用户名从数据库获取密码   这里写死用户名和密码，有需要可以从数据库获取
//        String password = "20190906";
//        System.out.println("获取token里面的姓名"+userName+";获取的密码"+userPwd);

        //这里是调用数据库，但是服务器暂时没有开发数据库 所以使用默认值
         Map<String,Object> map1 =  userService.user(userName,userPwd);
        //性能较快的map遍历方式
//        for (Map.Entry<String, Object> entry : map1.entrySet()) {
//            System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
//        }
        String count =  map1.get("count(*)").toString();
//        String count="0";
//        if("EO".equals(userName) && "20190925".equals(userPwd)){
//            count="1";
//        }
//        if("WJJ".equals(userName) && "20190925".equals(userPwd)){
//            count="1";
//        }
        System.out.println("数据获取的的值count"+count);
        System.out.println("当前Realm的名称"+getName()+";username"+userName);
        if ("1".equals(count)) {
            return new SimpleAuthenticationInfo(userName, userPwd,getName());
            //new SimpleAuthenticationInfo(user,user.getPassword(),getName());中的参数问题。
            // 第一个参数是从数据库中获取的User对象，第二个参数是数据库获取的密码，第三个参数是当前Realm的名称。
            //如果身份认证验证成功，返回一个AuthenticationInfo实现
            //这里的username可以传一个user对象，可以获取user的更多信息，但是本项目不写实体类，不加对象
        } else {
            return  null;
        }
//        return new SimpleAuthenticationInfo(userName, password,getName());

    }
}
