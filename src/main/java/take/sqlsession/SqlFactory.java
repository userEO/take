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

    private SqlSessionFactory factory;

    public void getSqlFactory() throws IOException {
        String resource = "mapper/mysql.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(inputStream);
    }

    /**
     * 以下内容来自于mybatis官网
     */
    public SqlSession sqlSession(Connection connection) throws IOException {
        /**
         * 创建 SqlSession 实例
         *
         * 这个地方我们之前创建了connection,所以不适用默认值了,另一个值得注意的点,你可以自己设置connection是否需要事务自动提交
         * 事务隔离级别支持 JDBC 的五个隔离级别（NONE、READ_UNCOMMITTED、READ_COMMITTED、REPEATABLE_READ
         * 和 SERIALIZABLE），并且与预期的行为一致。
         */
        SqlSession session = factory.openSession(ExecutorType.SIMPLE, connection);
        // 这里有一个疑问  创建sqlfactory的过程中,我们已经对数据库的连接条件给予了
        // 但是connection又再一次的给予了数据库的连接条件
        return session;
    }

    public SqlSession getSession() throws IOException {
        /**
         * 创建 SqlSession 实例
         *
         * 这个地方我们之前创建了connection,所以不适用默认值了,另一个值得注意的点,你可以自己设置connection是否需要事务自动提交
         * 事务隔离级别支持 JDBC 的五个隔离级别（NONE、READ_UNCOMMITTED、READ_COMMITTED、REPEATABLE_READ
         * 和 SERIALIZABLE），并且与预期的行为一致。
         */
        SqlSession session = factory.openSession(true);
        // 这里有一个疑问  创建sqlfactory的过程中,我们已经对数据库的连接条件给予了
        // 但是connection又再一次的给予了数据库的连接条件
        return session;
    }
/**
 * 1、SimpleExecutor：每执行一次update或select，就开启一个Statement对象，用完立刻关闭Statement对象。
 *
 * 2、ReuseExecutor：执行update或select，以sql作为key查找Statement对象，存在就使用
 * ，不存在就创建，用完后，不关闭Statement对象，而是放置于Map内，供下一次使用。简言之，就是重复使用Statement对象。
 *
 * 3、BatchExecutor：执行update（没有select，JDBC批处理不支持select），将所有sql都添加到批处理中（addBatch()），
 * 等待统一执行（executeBatch()），它缓存了多个Statement对象，
 * 每个Statement对象都是addBatch()完毕后，等待逐一执行executeBatch()批处理。与JDBC批处理相同。

 */

}
