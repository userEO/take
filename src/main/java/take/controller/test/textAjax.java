package take.controller.test;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import take.mapper.TextMapper;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述：
 *
 * @author EO
 * @date 2019/9/27 10:53
 */
@RestController
public class textAjax {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Resource
    TextMapper textMapper;
    @RequestMapping(value = "/Ajax/EO/text",method = RequestMethod.POST)
    public Map<String,Object> one(){


//        Map<String,Object> map = JSONObject.parseObject(jsonObject.toString());
        int num = Integer.valueOf(stringRedisTemplate.opsForValue().get("textNum"))+1;
        stringRedisTemplate.opsForValue().set("textNum",String.valueOf(num),60, TimeUnit.SECONDS);
//                Integer.valueOf(map.get("num").toString());
        Map<String,Object> map1=textMapper.findOne(num);
        map1.put("textnum",num);
        return map1;
    }
}
