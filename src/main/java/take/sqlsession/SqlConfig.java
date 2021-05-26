package take.sqlsession;

/**
 * 功能描述：sql的配置文件  datasource
 * 既然不用注解了，那也不用配置文件，全部用代码来访问
 *
 * @author EO
 * @date 2021/5/26 22:15
 */
public interface SqlConfig {
    String URL = "jdbc:mysql://localhost:3306/eo_daren?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=Asia/Shanghai";
    String USERNAME = "root";
    String PASSWORD = "jweodr";
    // 数据库驱动
//    String driverclass = "com.mysql.jdbc.Driver";
    /**
     * 加载类“ com.mysql.jdbc.Driver”。 不推荐使用。
     * 新的驱动程序类为“ com.mysql.cj.jdbc.Driver”。 通过SPI自动注册驱动程序，通常不需要手动加载驱动程序类
     */
    String driverclass = "com.mysql.cj.jdbc.Driver";
}
