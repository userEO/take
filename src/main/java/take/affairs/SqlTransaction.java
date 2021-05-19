package take.affairs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 功能描述：
 *
 * @author EO
 * @date 2021/5/11 19:50
 */
public class SqlTransaction {
    @Autowired
    private TransactionTemplate template;

    public void executeSql(){
        template.execute(new TransactionCallbackWithoutResult(){

            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                try{
                    // 调用sql执行方法
                }catch (Exception e){
                    status.setRollbackOnly();
                }
            }
        });
    }

}
