package take.service;

import java.util.Map;

/**
 * 功能描述：登录账户验证
 *
 * @author EO
 * @date 2019/9/4 9:01
 */
public interface UserService {
    //验证登录者是否存在  存在返回1，不存在返回0
    Map<String,Object> user(String login, String pwd);

    Map<String,Object> userMap(Map<String,Object> map);
}
