package take.sqlsession;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

/**
 * 功能描述：让我们一起来探讨一下mybatis的底层到底是怎么执行到一个sql
 * <p>
 * ps:SqlExecute已经执行了一个简单的sql了，但是真正的开发，不可能使用这种方式来编写sql，而且sql的拓展性不高
 * 那么我们就应该引入xml的mapper的方式来使用sql
 *
 * @author EO
 * @date 2021/5/26 23:46
 */
public class SqlFactory {
    // 这里由于是本地测试,所以先暂时注释
//    @Autowired
//    private SqlExecute sqlExecute;

    /**
     * 以下内容来自于mybatis官网
     */
    public SqlSession sqlSession(Connection connection) throws IOException {
        String resource = "mapper/mysql.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream);
        /**
         * 创建 SqlSession 实例
         *
         * 这个地方我们之前创建了connection,所以不适用默认值了,另一个值得注意的点,你可以自己设置connection是否需要事务自动提交
         * 事务隔离级别支持 JDBC 的五个隔离级别（NONE、READ_UNCOMMITTED、READ_COMMITTED、REPEATABLE_READ
         * 和 SERIALIZABLE），并且与预期的行为一致。
         */
        SqlSession session = factory.openSession(ExecutorType.BATCH, connection);
        // 这里有一个疑问  创建sqlfactory的过程中,我们已经对数据库的连接条件给予了
        // 但是connection又再一次的给予了数据库的连接条件
        return session;
    }


}
