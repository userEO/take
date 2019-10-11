package take.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * 功能描述：查找页面的数据的title和text
 *
 * @author EO
 * @date 2019/9/27 14:40
 */
public interface TextMapper {

    @Select("select title,textE from seeyou where id = #{num}")
    Map<String,Object> findOne(int num);
}
