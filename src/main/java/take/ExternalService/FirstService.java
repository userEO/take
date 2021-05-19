package take.ExternalService;

import com.alibaba.fastjson.JSONObject;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import take.Util.FastJsonUtils;
import take.mapper.UserMapper;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述：
 *
 * @author EO
 * @date 2019/9/12 14:59
 */
@RestController
public class FirstService {
    @Resource
    UserMapper userMapper;

    @RequestMapping(value = "/EOS/first",method = RequestMethod.POST)
    public Object first(@RequestBody JSONObject jsonObject){
        Map<String,Object> map = JSONObject.parseObject(jsonObject.toString());
        String login = map.get("login").toString();
        String pwd = map.get("pwd").toString();
        String takemap = map.get("takemap").toString();
        Map<String,Object> map1 = JSONObject.parseObject(takemap);
        List<Map<String,Object>> lsit = FastJsonUtils.getJsonToListMap(jsonObject.toString());
        Map<String,Object> map2 = new HashMap<>();
        map2.put("欢迎您","你好");
        return JSONObject.toJSON(map2);
    }

    //注册接口
    @RequestMapping(value = "/EOS/registered",method = RequestMethod.POST)
    @Transactional(propagation=Propagation.REQUIRED )
    public Object registered(@RequestBody JSONObject jsonObject){
        Map<String,Object> map = JSONObject.parseObject(jsonObject.toString());
        String login = map.get("login").toString();
        String pwd = map.get("pwd").toString();
        userMapper.registered(map);
//       map2.put("欢迎您","你好");
        map.put("恭喜注册成功",login);
        return JSONObject.toJSON(map);
    }

    //这个仅为浏览器设置的访问连接 访问的是get请求,所以请求体中不能带有RequestBody
    @RequestMapping(value = "/EOS/Eget",method = RequestMethod.GET)
    public Object firstForGet(){
        Map<String,Object> map2 = new HashMap<>();
        map2.put("吴","祝你今天生日快乐！");
//        map2.put("小主","千里姻缘一线牵");
        return JSONObject.toJSON(map2);
    }

}
