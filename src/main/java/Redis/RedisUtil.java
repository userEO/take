package Redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述：
 *
 * @author EO
 * @date 2021/5/19 21:17
 */
public class RedisUtil {
    @Autowired
    private static StringRedisTemplate stringRedisTemplate;
    @Autowired
    private static RedisTemplate redisTemplate;
    private RedisUtil() {
    }

    /**
     * 塞入值
     * @param key
     * @param value
     */
    public static void setKeyValue(String key,String value){
        stringRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * 带过期时间
     * @param key
     * @param value
     * @param time
     */
    public static void setKeyValueTime(String key, String value, Long time){
        stringRedisTemplate.opsForValue().set(key, value,time);
    }
    public static void setKeyValue(String key,Object value){
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 向左边的list添加元素
     * @param list list
     * @param value value
     */
    public static void setList(List list, Object value){
        redisTemplate.opsForList().leftPush(list, value);
    }
    //    删除key
    public void delete(String key){
        redisTemplate.delete(key);
    }
    //    删除多个key
    public void deleteKey (String ...keys){
        redisTemplate.delete(keys);
    }
    //    指定key的失效时间
    public void expire(String key,long time){
        redisTemplate.expire(key,time,TimeUnit.MINUTES);
    }
}
