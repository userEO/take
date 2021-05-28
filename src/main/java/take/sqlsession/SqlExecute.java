package take.sqlsession;

import java.sql.*;

/**
 * 功能描述：sql底层逻辑实现
 * <p>
 * 最近面试问到了，当然也是不记得了，现在来学习下底层，到底是怎么查询数据的
 * 当然springboot的简单查询是很简单，加个注解就可以了，当然最好的方式还是使用xml的方式，这样可以使用resultmap的方式自己去自定义数据
 * <p>
 * 环境 ：本地的mysql
 *
 * @author EO
 * @date 2021/5/26 19:35
 */
public class SqlExecute {
    // 连接对象
    private Connection connection;
    // sql的执行基础
    private Statement statement;

    public void close() {
        try {
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        /**
         *  Class.forName的作用是为了装载静态的类 等于装载了mysql的驱动，之后这个静态就不会再加载了
         *  也可以点进去看一下，这个方法是static修饰的，所以你就知道了
         */
        Class.forName(SqlConfig.driverclass);
        /**
         * 当数据库的自动提交关了之后，如果commit生效，java得手动执行第二次
         */
        connection = DriverManager.getConnection(SqlConfig.URL, SqlConfig.USERNAME, SqlConfig.PASSWORD);
        // 这个地方还得自己手动加上自动提交为false才行
        connection.setAutoCommit(true);
        return connection;
    }

    public Statement getStatement() throws SQLException {
        // 传统的传递Statement Id 和查询参数给 SqlSession 对象，使用 SqlSession对象完成和数据库的交互
        statement = connection.createStatement();
        return statement;
    }

    /**
     * 获取sql执行的结果
     *
     * @return
     */
    public ResultSet getSqlExecuteResult(String sql) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = getStatement().executeQuery(sql);
        return resultSet;
    }

    /**
     * 插入数据 现在默认没有添加
     * <p>
     * =====》当前测试是没有添加commit方法的：看是否可以插入成功
     * 插入成功了，我使用的mysql，默认情况下，MySQL启用自动提交模式
     * =====》明天晚上来尝试关闭mysql的自动提交测试   现在执行mysql自动提交关闭
     * 1.控制台输入 set  autocommit = 0 （这种好处是cmd登录之后，退出就不生效了）
     * 但是cmd登录设置对于java来说不生效
     * 2.mysql配置文件  autocommit=0
     * 设置配置文件之后，插入就不会生效了
     * <p>
     * tips：忘记mysql安装位置：在sqlyog执行   SHOW VARIABLES LIKE "%char%"
     * 就可以看到安装位置了
     *
     * @param sql
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int getInsertSqlExecuteResult(String sql) throws SQLException, ClassNotFoundException {
        int result = getStatement().executeUpdate(sql);
        // 这个时候我们手动加上commit
        connection.commit();
        return result;
    }

    /**
     * 根据结果集里的第几列来返回String对象
     * 这个index对象就是表中字段的顺序第几列
     *
     * @param resultSet resultSet
     * @param index     第几列
     * @return return
     */
    public String getStringSqlResultColum(ResultSet resultSet, int index) throws SQLException {
        return resultSet.getString(index);
    }

    /**
     * int
     *
     * @param resultSet resultSet
     * @param index     第几列
     * @return return
     */
    public int getIntSqlResultColum(ResultSet resultSet, int index) throws SQLException {
        return resultSet.getInt(index);
    }

}
