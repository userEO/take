package take.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 功能描述：登录拦截器
 *百度了一天之后，我才发现原来filter拦截的请求，而不是某一个特定的页面，而我一直执着于去拦截某个页面，又或者去放行某些css和js页面
 * 但是这并不是解决问题的办法，我们要想到一个问题，filter只是拦截请求，无论是单纯。do请求，还是html。js。css等其他结尾的方式的请求
 * 都会去拦截
 *
 * 方法想不到更好，这个方法被抛弃，改用shiro-spring安全框架 来做登录验证
 * @author EO
 * @date 2019/9/6 10:04
 */
//@WebFilter(urlPatterns = {"/index.html","/contact.html","/single.html"},filterName = "loginFilter")
//@WebFilter(urlPatterns = "/index.html",filterName = "loginFilter")
public class LoginFilter implements Filter {
    private FilterConfig filterConfig;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        System.out.println("特定页面,强制跳转登录页面");
        //需要进行强转，获取信息
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        //失败
//        httpServletRequest.setAttribute("","");
        //获取cookie信息   失败
//        Cookie[] cookie = httpServletRequest.getCookies();
//        if(cookie.length!=0){
//            for(Cookie cookies:cookie){
//                System.out.println("获取cookie的数据名字"+cookies.getName()+"cookie数据的vlaue"+cookies.getValue());
//
//            }
//        }
        //测试session   失败
//        HttpSession httpSession = httpServletRequest.getSession();
//        String use = String.valueOf(httpSession.getAttribute("login"));
//        System.out.println("session值"+use);
//
//        System.out.println("cookie的信息"+cookie.toString());
//        String httpr = httpServletRequest.getParameter("login");
//        System.out.println("尝试获取http厘米的数据"+httpr);

        //获取配置参数   失败
//        String loginurl = filterConfig.getInitParameter("loginurl");
        //获取访问路径
//        String url = String.valueOf(httpServletRequest.getRequestURL());

        //设置过滤路径 这个是下下策，比较麻烦
//        String[] urls = {"/login","/json",".js",".css",".ico",".jpg",".png"};
//        String name = request.getParameter("name");
        //放行login
//        if(url.contains(loginurl)){
//            chain.doFilter(httpServletRequest,response);
//        }else{
        //所有路径直接重定向到登录页面
        //最后使用的是  在跳转路径的时候携带参数  但是这不是很好的办法，后期添加一个redis来验证，加强安全
        String name = httpServletRequest.getParameter("name");
        if("EO".equals(name) || "WU".equals(name)){
            chain.doFilter(request,response);
        }else{
            httpServletResponse.sendRedirect("/login.html");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //容器也就是程序启动的时候加载
        System.out.println("my login filter");

    }

    @Override
    public void destroy() {
        System.out.println("destroy filter");
    }
}
