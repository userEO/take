package take.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * 功能描述：访问数据库  springboot访问数据库
 * <p>
 * <p>
 * 在mybatis中，无论你指定还是不指定返回类型，mybatis都会默认的先将查询回的值放入一个hashMap中
 * （如果返回的值不止一条就是一个包含hashMap的list）。
 * 这其中的区别在于，如果你指定了返回类型，mybatis将会根据返回类型的实体类来从hashMap中获取值并set到这个实体类中。
 * 如果不指定就默认返回一个HashMap<String,Object>（List<HashMap<String,Object>>）。
 *
 * @author EO
 * @date 2019/9/4 9:05
 */
@Mapper
public interface UserBootMapper {

    //不要用$去获取值，会有外界的sql注入，使用#符号
    //Options注解keyProperty为java对象也急速user类的id，keyColumn是数据库user表的字段 id
    //这里需要注意的是，在controller中调用的时候一定要保证vlaues里面的参数名称和实体类里面的名称一样才可以保证能够拿到数据
//    @Insert("insert into user(username,password,realname) values(#{username},#{password},#{realname})")
//    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
//    int insert(User user);

    /**
     * 查找全部
     */
//    @Select("select * from user")
//    @Results(
//            @Result(column = "realname",property = "realname")
//    )
//    List<User> findAll();

    /**
     * 根据id查找某一条数据
     */
    @Select("select count(*) from user where username=#{name} and password=#{pwd}")
    Map<String, Object> findOne(String name, String pwd);

    /**
     * 根据id查找某一条数据
     */
    @Select("select count(*) from user where username=#{login} and password=#{pwd}")
    Map<String, Object> findOneM(Map<String, Object> map);
    /**
     * 更新数据
     */
//    @Update("update user set username=#{username} where id=#{id}")
//    void  update(String username,int id);
    /**
     * 删除数据
     */
//    @Delete("delete from user where id=#{id}")
//    void delete(int id);

    /**
     * 注册
     *
     * @param map
     * @return
     */
    @Insert("insert into user(username,password) values(#{login},#{pwd})")
    void registered(Map<String, Object> map);

}
