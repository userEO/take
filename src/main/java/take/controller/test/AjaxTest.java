package take.controller.test;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.ShiroFilter;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.servlet.ShiroHttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述：测试ajax传值问题
 *
 * @author EO
 * @date 2019/9/2 15:33
 */
@RestController
public class AjaxTest {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
//    @Autowired
//    private UserService userService;

    @RequestMapping(value = "/takeAjax/task",method = RequestMethod.POST)
    public  Object test(){
        String resultCode = "200";
        Map<String,Object> map = new HashMap<>();
        map.put("resultCode",resultCode);
        return map;
    }

    //接收登录姓名验证
    @RequestMapping(value = "/Ajax/EO/Login",method = RequestMethod.POST)
    public  Object LoginVerifica(@RequestBody JSONObject jsonParam){
        Map<String,Object> map = JSONObject.parseObject(jsonParam.toString());
        String login = map.get("login").toString();
        String pwd = map.get("pwd").toString();
        // 从SecurityUtils里边创建一个 subject   filter拦截过程太过麻烦，在这里通过使用shiro框架来做登录验证
        // 在认证提交前准备 token（令牌）
        //如果有需要可以加上rememberme  也就是"记住我"
//        UsernamePasswordToken token = new UsernamePasswordToken(login, pwd,true);
        UsernamePasswordToken token = new UsernamePasswordToken(login, pwd);
        Subject currentUser = SecurityUtils.getSubject();
//        Session session = currentUser.getSession();
//        String host = session.getHost();
//        System.out.println("主机地址："+host+"session失效时间:"+session.getTimeout());

//        System.out.println(login+pwd);
//        System.out.println(jsonParam.toJSONString());
//        System.out.println(map);
          //这里原本通过ajax和后台验证，但是不安全，现在通过shiro框架，自定义实现CustomRealmq去验证
        Map<String,Object> map1 =  new HashMap<>();
//                userService.userMap(map);
//        //性能较快的map遍历方式
//        for (Map.Entry<String, Object> entry : map1.entrySet()) {
//            System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
//        }
        try {
//            //主体提交登录请求到SecurityManager  登录即身份验证
            currentUser.login(token);
            System.out.println("shiro登录成功");
            map1.put("flag","success");

            //不采取从前台获取值，这样刷新页面或者进入其他页面会导致重新从1开始，改为从redis获取
            stringRedisTemplate.opsForValue().set("textNum","0",60, TimeUnit.SECONDS);
        }catch (IncorrectCredentialsException ice){
//            map1.put("msg","密码不正确");
            System.out.println("shiro密码不正确");
            map1.put("flag","fail");
        }catch(UnknownAccountException uae){
//            map1.put("msg","账号不存在");
            System.out.println("shiro账号不存在");
            map1.put("flag","fail");
        }catch(AuthenticationException ae){
//            map1.put("msg","状态不正常");
            System.out.println("shiro登录失败");
            map1.put("flag","fail");
        }
        //登出操作
//        currentUser.logout();
//        if(currentUser.isAuthenticated()){
//            System.out.println("认证成功");
//        }else{
//            token.clear();
//        }
        //设置一个flag告诉拦截器，如果有这个flag存在就不拦截
        return  map1;
    }
}
