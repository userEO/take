package take.bean;

/**
 * 功能描述：感觉底层类型并不是准确按照字段名称精准匹配的，所以做个测试
 * <p>
 * <p>
 * 一开始我还以为这是mybatis的底层自带的容错机制，只要有一个值对应就能根据基本类型来进行匹配
 * 事实证明我错了，只有部分匹配的值才能显示，不对应的字段名就会显示默认值
 *
 * @author EO
 * @date 2021/5/29 19:36
 */
public class Nuser {
    private int idd;
    private String userName;
    private String passwrrrd;
    private String realNarrrme;
    // 账号是否已经注销
    private int flffag;

    public int getIdd() {
        return idd;
    }

    public void setIdd(int idd) {
        this.idd = idd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswrrrd() {
        return passwrrrd;
    }

    public void setPasswrrrd(String passwrrrd) {
        this.passwrrrd = passwrrrd;
    }

    public String getRealNarrrme() {
        return realNarrrme;
    }

    public void setRealNarrrme(String realNarrrme) {
        this.realNarrrme = realNarrrme;
    }

    public int getFlffag() {
        return flffag;
    }

    public void setFlffag(int flffag) {
        this.flffag = flffag;
    }
}
