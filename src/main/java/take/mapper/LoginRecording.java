package take.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * 功能描述：记录网站登录情况
 *
 * @author EO
 * @date 2019/10/17 10:36
 */
public interface LoginRecording {

    @Insert("insert into loginnote(name,logintime) values(#{login},now())")
    public void recoding(Map<String,Object> map);
}
