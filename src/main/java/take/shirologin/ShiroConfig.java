package take.shirologin;

import net.sf.ehcache.Ehcache;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 功能描述：
 *
 * @author EO
 * @date 2019/9/9 9:23
 */
//注释这个就可以认登录拦截失效
//@Configuration
public class ShiroConfig {

    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //权限不足跳转页面 这里也设置为登录页面
//        shiroFilterFactoryBean.setUnauthorizedUrl("/error");
        //配置拦截链 使用LinkedHashMap,因为LinkedHashMap是有序的，shiro会根据添加的顺序进行拦截
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>(18);
        //authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
        filterChainDefinitionMap.put("/login", "anon");
        //静态资源不需要控制
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/font-awesome/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/images/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/layui/**", "anon");
        filterChainDefinitionMap.put("/logincss/**", "anon");
        filterChainDefinitionMap.put("/loginimg/**", "anon");
        filterChainDefinitionMap.put("/loginjs/**", "anon");
        //注意这里的一点，ajax请求，本质上来说，ajax请求也是通过路径来请求的，所以在最下面的放开所有路径拦截之后，ajax请求也会无法访问
        //在这里固定格式封装ajax请求格式，确保ajax可以访问到
        filterChainDefinitionMap.put("/Ajax/EO/**", "anon");
        //对外暴露接口服务
        filterChainDefinitionMap.put("/EOS/**", "anon");

        filterChainDefinitionMap.put("/contact", "authc");
        filterChainDefinitionMap.put("/index", "authc");
        filterChainDefinitionMap.put("/single", "authc");
        filterChainDefinitionMap.put("/player", "authc");
        filterChainDefinitionMap.put("/louver", "authc");
        filterChainDefinitionMap.put("/photo", "authc");
        filterChainDefinitionMap.put("/music", "authc");
        System.out.println("登录拦截生效");
        //主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截 剩余的都需要认证
        //shiro也会回拦截ajax请求，虽然查了很多资料 都说shiro默认不拦截ajax请求，但是我注释下面代码就能接收到ajax
        //请求，解注释就无法接收到ajax请求

        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
    @Bean
    public SecurityManager securityManager() {
//        Realm userRealm = (Realm) applicationContext.getBean(CustomRealm.class);
        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        defaultSecurityManager.setRealm(customRealm());

//        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
//        sessionManager.setCacheManager();
        return defaultSecurityManager;
    }

    //Qualifier注解是必须,这也算是 shiro配置的一个坑了 使用new CustomRealm()会导致该方法里面的autowired注解失效，直接为空
    //因为spring加载bean的顺序是 lister filter servlet，如果不加，会默认先加载shiro框架的注解，这个时候前面注解已经加载完成
    //而后面的注解注入直接为空
    @Bean
    @Qualifier
    public CustomRealm customRealm() {
        CustomRealm customRealm = new CustomRealm();
        return customRealm;
    }
    //用来在html页面使用shiro标签，不使用，不需要添加
//    @Bean
//    public ShiroDialect shiroDialect() {
//        return new ShiroDialect();
//    }

    /**
     * 配置session监听
     * @return
     */
//    @Bean
//    public SessionFilter sessionFilter(){
//      SessionFilter sessionFilter = new SessionFilter();
//      return  sessionFilter;
//   }
    /**
     * 配置会话ID生成器
     * @return
     */
//    @Bean
//    public SessionIdGenerator sessionIdGenerator() {
//        return new JavaUuidSessionIdGenerator();
//    }
//
//
//    @Bean
//    public SessionDAO sessionDAO() {
//        EnterpriseCacheSessionDAO enterpriseCacheSessionDAO = new EnterpriseCacheSessionDAO();
//        //使用ehCacheManager
////        enterpriseCacheSessionDAO.setActiveSessionsCache(cacheManager());
////        enterpriseCacheSessionDAO.setCacheManager();
//        //设置session缓存的名字 默认为 shiro-activeSessionCache
//        enterpriseCacheSessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
//        //sessionId生成器
//        enterpriseCacheSessionDAO.setSessionIdGenerator(sessionIdGenerator());
//        return enterpriseCacheSessionDAO;
//    }

    /**remember 记住我
     * cookie对象;会话Cookie模板 ,默认为: JSESSIONID 问题: 与SERVLET容器名冲突,重新定义为sid或rememberMe，自定义
     * @return
     */
//    @Bean
//    public SimpleCookie rememberMeCookie(){
//        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
//        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
//        //setcookie的httponly属性如果设为true的话，会增加对xss防护的安全系数。它有以下特点：
//
//        //setcookie()的第七个参数
//        //设为true后，只能通过http访问，javascript无法访问
//        //防止xss读取cookie
//        simpleCookie.setHttpOnly(true);
//        simpleCookie.setPath("/");
//        //<!-- 记住我cookie生效时间1个小时 ,单位秒;-->
//        simpleCookie.setMaxAge(3600);
//        return simpleCookie;
//    }

    /**
     * cookie管理对象;记住我功能,rememberMe管理器
     * @return
     */
//    @Bean
//    public CookieRememberMeManager rememberMeManager(){
//        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
//        cookieRememberMeManager.setCookie(rememberMeCookie());
//        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
//        cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
//        return cookieRememberMeManager;
//    }
    /**
     * shiro缓存管理器;
     * 需要添加到securityManager中
     * @return
     */
//    @Bean
//    public EhCacheManager ehCacheManager(){
//        EhCacheManager cacheManager = new EhCacheManager();
//        cacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
//        return cacheManager;
//    }

    /**
     * 配置核心安全事务管理器
     * @param
     * @return
     */
//    @Bean(name="securityManager")
//    public SecurityManager securityManager() {
//        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
//        //设置自定义realm.
//        securityManager.setRealm(shiroRealm());
//        //配置记住我 参考博客：
//        securityManager.setRememberMeManager(rememberMeManager());
//
//        //配置 ehcache缓存管理器 参考博客：
//        securityManager.setCacheManager(ehCacheManager());
//
//        //配置自定义session管理，使用redis 参考博客：
//        //securityManager.setSessionManager(sessionManager());
//
//        return securityManager;
//    }

    /**
     *  身份认证realm; (这个需要自己写，账号密码校验；权限等)
     * @return
     */
//    @Bean
//    public ShiroRealm shiroRealm(){
//        ShiroRealm shiroRealm = new ShiroRealm();
//        shiroRealm.setCachingEnabled(true);
//        //启用身份验证缓存，即缓存AuthenticationInfo信息，默认false
//        shiroRealm.setAuthenticationCachingEnabled(true);
//        //缓存AuthenticationInfo信息的缓存名称 在ehcache-shiro.xml中有对应缓存的配置
//        shiroRealm.setAuthenticationCacheName("authenticationCache");
//        //启用授权缓存，即缓存AuthorizationInfo信息，默认false
//        shiroRealm.setAuthorizationCachingEnabled(true);
//        //缓存AuthorizationInfo信息的缓存名称  在ehcache-shiro.xml中有对应缓存的配置
//        shiroRealm.setAuthorizationCacheName("authorizationCache");
//        return shiroRealm;
//    }

}
