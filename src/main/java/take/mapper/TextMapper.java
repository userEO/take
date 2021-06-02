package take.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Map;

/**
 * 功能描述：查找页面的数据的title和text
 *
 * @author EO
 * @date 2019/9/27 14:40
 */
@Mapper
public interface TextMapper {

    //查询数据库为一的文字
    @Select("select id,title,textE from seeyou where noteid = 1")
    Map<String, Object> findOne();

    //更新当前记录为0
    @Update("update seeyou set  noteid = 0 where id=#{id}")
    void updateNoteid(int id);

    //更新下一个记录为1
    @Update("update seeyou set  noteid = 1 where id=#{id}")
    void updateNextNoteid(int id);
}
