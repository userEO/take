package take.bean;

import java.io.Serializable;

/**
 * 功能描述：
 *
 * @author EO
 * @date 2021/5/26 23:53
 */
public class User implements Serializable {// 二级缓存，实体类需要进行序列化
    private int id;
    private String userName;
    private String passWord;
    private String realName;
    // 账号是否已经注销
    private int flag;

    public User(String userName, String passWord, String realName, int flag) {
        this.userName = userName;
        this.passWord = passWord;
        this.realName = realName;
        this.flag = flag;
    }

    /**
     * 这个地方我为什么耀写2个构造方法呢   原因是我在sqlmain查询数据库的时候，是select * ,
     * 但是构造方法如果只写上面的话，数据结构不匹配，在底层就无法进行匹配了，就会导致报错。
     *
     * @param id       id
     * @param userName userName
     * @param passWord passWord
     * @param realName realName
     * @param flag     flag
     */
    public User(int id, String userName, String passWord, String realName, int flag) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.realName = realName;
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
