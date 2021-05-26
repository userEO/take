package take.sqlsession;

import java.sql.ResultSet;

/**
 * 功能描述：测试类
 *
 * @author EO
 * @date 2021/5/26 22:57
 */
public class SqlMain {
    public static void main(String[] args) {

        String sql = "select * from user";
        try {
            SqlFactory sqlFactory = new SqlFactory();
            ResultSet resultSet = sqlFactory.getSqlExecuteResult(sql);
            // 最原始的resultSet 只能通过next获取数据
            while (resultSet.next()) {
                System.out.println(sqlFactory.getStringSqlResultColum(resultSet, 2));
                System.out.println(sqlFactory.getIntSqlResultColum(resultSet, 2));
            }
            // 只能在处理完结果集才能关闭connection
            // 所有的Statement的查询对应的结果集是一个，所以如果提前关闭，就会导致错误
            sqlFactory.close();
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
