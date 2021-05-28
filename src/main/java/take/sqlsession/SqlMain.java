package take.sqlsession;

import org.apache.ibatis.session.SqlSession;
import take.bean.User;

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
        try (SqlSession session = sqlFactory.sqlSession(sqlExecute.getConnection())) {
            // 这个地方我们点进去就能看到具体是怎么匹配的  就能看到底部的id是根据路径到方法进行全匹配的
            List<User> list = session.selectList("take.mapper.UserMapper.getUsers");
            list.forEach(x -> System.out.println(x));
        }

        //   =================测试sqlfactory end===================

    }
}
