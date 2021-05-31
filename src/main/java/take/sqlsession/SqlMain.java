package take.sqlsession;

import org.apache.ibatis.session.SqlSession;
import take.bean.User;

import java.sql.Connection;
import java.util.List;

/**
 * 功能描述：测试类
 *
 * @author EO
 * @date 2021/5/26 22:57
 */
public class SqlMain {
    public static void main(String[] args) throws Exception {

        // 查询可以  =================测试sqlexecute start===================
//        String sql = "select * from user";
//        // 插入
//        String insertSql = "insert into user(username,password,realName,flag) values('take','eo','to',1)";
//        try {
//            SqlExecute sqlExecute = new SqlExecute();
//            ResultSet resultSet = sqlExecute.getSqlExecuteResult(sql);
//            // 最原始的resultSet 只能通过next获取数据
//            while (resultSet.next()) {
//                System.out.println(resultSet.getInt(1));
//            }
//            int result = sqlExecute.getInsertSqlExecuteResult(insertSql);
//            System.out.println(result);
//            // 只能在处理完结果集才能关闭connection
//            // 所有的Statement的查询对应的结果集是一个，所以如果提前关闭，就会导致错误
//            sqlExecute.close();
//            System.out.println();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        // 查询可以  =================测试sqlexecute end===================
        //   =================测试sqlfactory start===================
        SqlFactory sqlFactory = new SqlFactory();
        SqlExecute sqlExecute = new SqlExecute();
        sqlFactory.getSqlFactory();
        Connection connection = sqlExecute.getConnection();
        Connection connection1 = sqlExecute.getConnection();
        try (SqlSession session = sqlFactory.sqlSession(connection);
             SqlSession session1 = sqlFactory.sqlSession(connection1)) {

//        try (SqlSession session = sqlFactory.getSession();
//             SqlSession session1 = sqlFactory.getSession()) {
            // 这个地方我们点进去就能看到具体是怎么匹配的  就能看到底部的id是根据路径到方法进行全匹配的
            /**
             * 但是这个地方的底层赋值好像并不是按照属性名称准确匹配的 而是按照基本类型来进行匹配的
             */
            long time1 = System.currentTimeMillis();
//            List<User> list = session.selectList("take.mapper.UserMapper.getUsers");
            List<User> list = session.selectList("take.mapper.UserMapper.getUsers");
            list.forEach(x -> System.out.println(x.getId() + "==" + x.getUserName() + "===" + x.getRealName()));
            long time2 = System.currentTimeMillis();
            System.out.println("第一次查询" + (time2 - time1));

            // 如果需要测试二级缓存，需要提前关闭session
            // 来测试下事务
            System.out.println(session.getConnection());
            /**
             * 如果需要使用二级缓存，必须要手动调用colse方法或者commit方法
             */
            session.close();
            System.out.println(session1.getConnection());

            List<User> list1 = session1.selectList("take.mapper.UserMapper.getUsers");
            list1.forEach(x -> System.out.println(x.getId() + "==" + x.getUserName() + "===" + x.getRealName()));
            long time3 = System.currentTimeMillis();
            // 这个地方的第二次查询非常快，比第一次快很多，但是我并没有启用二级缓存，这个地方我想了断点了很久
            /**
             * 第一个想法是mysql有缓存支持二次查询
             * 第二个想法是 sqlfactory启用了connection回收机制，也就是第二次查询并没有新建connection，所以很快
             */
            System.out.println("第二次查询" + (time3 - time2));
            // 试试插入
//            int index = session.insert("take.mapper.UserMapper.addUser", new User("EO", "PWD", "QIAN", 1));
            // 这个地方的index 是 ：-2147482646，这是因为我们设置sqlsession的时候 ExecutorType.BATCH造成的，由于底层的固定返回值造成的
            // 改成simple就是正确的1了
//            System.out.println(index);
            List<User> list2 = session1.selectList("take.mapper.UserMapper.getUsers");
            list2.forEach(x -> System.out.println(x.getId() + "==" + x.getUserName() + "===" + x.getRealName()));
            long time4 = System.currentTimeMillis();
            System.out.println("第三次查询" + (time4 - time3));
            /**
             * 这个地方假如我们把connection的自动提交改为false，就会报下面的错误
             * 13:28:25.326 [main] DEBUG org.apache.ibatis.transaction.jdbc.JdbcTransaction - Rolling back JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@1ca3b418]
             * 13:28:25.376 [main] DEBUG org.apache.ibatis.transaction.jdbc.JdbcTransaction - Resetting autocommit to true on JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@1ca3b418]
             * 13:28:25.376 [main] DEBUG org.apache.ibatis.transaction.jdbc.JdbcTransaction - Closing JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@1ca3b418]
             */


//            throw new Exception();
        }

        //   =================测试sqlfactory end===================

    }
}
