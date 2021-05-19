package take.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import take.service.UserService;

import java.util.Map;

/**
 * 功能描述：静态代理
 *
 * @author EO
 * @date 2019/12/8 16:34
 */
public class userServiceImplProxy implements UserService{
      @Autowired
      private UserService userService;
    // 通过构造方法传入目标对象
      public userServiceImplProxy(UserService userService){
          this.userService = userService;
      }

    @Override
    public Map<String, Object> user(String login, String pwd) {
        try{

            userService.user(login, pwd);
            //添加用户成功
            System.out.println("success-->User()");
        }catch(Exception e){
            //添加用户失败
            System.out.println("error-->User()");
        }
       return null;
    }

    @Override
    public Map<String, Object> userMap(Map<String, Object> map) {
        userService.userMap(map);
        return null;
    }
}
