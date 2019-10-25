package take.leavemsg;

import org.apache.ibatis.annotations.Insert;
import java.util.Map;

/**
 * 功能描述：
 *
 * @author EO
 * @date 2019/10/24 19:15
 */
public interface MsgRecording {

    //插入留言记录phone
    @Insert("insert into leavemsg(msg,msgtime,msgtitle,name,phone) values(#{msg},now(),#{msgtitle},#{name},#{phone})")
    public void insertMsg(Map<String,Object> map);
}
