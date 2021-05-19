package take.leetCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述：获取缓存的list
 *
 * @author EO
 * @date 2019/12/8 16:11
 */
public class getList {
    public <K,V> List<?> page(Map<K,V>  map,int pageSize,int nextKey,String listname){
        Map<String,List<Map<String,Object>>> test = new HashMap<>();
        List<?> ctxList = (List<?>)map.get(listname);
        List<?> pageList = null;
        int count = 0;
        if(ctxList.isEmpty()){
            //从银行接口重新获取
        }else {
            count = ctxList.size();
            int formIndex = pageSize*nextKey;
            int toIndex =(nextKey+1)*pageSize;
            if(toIndex>count){
                toIndex=count;
            }
            pageList = ctxList.subList(formIndex, toIndex);
        }
        return  null;
    }
}
