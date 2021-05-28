package take.service.serviceImpl;

import org.springframework.stereotype.Service;
import take.mapper.UserBootMapper;
import take.service.UserService;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 功能描述：实现类
 *
 * @author EO
 * @date 2019/9/4 9:03
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserBootMapper userMapper;

    @Override
    public Map<String, Object> user(String login, String pwd) {
        Map<String, Object> map = userMapper.findOne(login, pwd);
        System.out.println(map);
        return map;
    }

    @Override
    public Map<String, Object> userMap(Map<String, Object> map) {
        System.out.println("传输的map" + map);
        Map<String, Object> map1 = userMapper.findOneM(map);
        System.out.println("数据库map" + map1);
        return map1;
    }
}
