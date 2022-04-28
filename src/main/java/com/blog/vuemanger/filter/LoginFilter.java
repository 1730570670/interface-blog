package com.blog.vuemanger.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter",urlPatterns = "/*")
@Slf4j
public class LoginFilter implements Filter {
    /*
    * 登陆状态
    * 0为未登录,否则为登录
    * */
    public static int grade=0;
    //路径匹配器，支持通配符
    private static final AntPathMatcher PATH_MATCHER =new AntPathMatcher();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setContentType("text/html;charset=utf-8");//解决乱码
        //1、获取本次请求的URI
        String requestURI = request.getRequestURI();
        log.info("拦截到请求：{}",requestURI);
        //定义不需要处理的请求路径(只有登录接口不被拦截)
        String[] urls = new String[]{
                "/login/*/*"
        };
        //2、判断本次请求是否需要处理
        boolean check = check(urls, requestURI);
        //3、如果不需要处理，则直接放行
        if(check){
            log.info("本次请求{}不需要处理",requestURI);
            filterChain.doFilter(request,response);
            return;
        }
        //判断登录状态，如果已登录，则直接放行
        if(grade != 0){
            log.info("用户已登录");
            filterChain.doFilter(request,response);
            return;
        }
        log.info("用户未登录");
            //5、如果未登录则返回未登录结果，通过输出流方式向客户端页面响应数据
            response.getWriter().write("请求接口失败,请登录重试");
        return;
    }

    /**
     * 路径匹配，检查本次请求是否需要放行
     * @param urls
     * @param requestURI
     * @return
     */
    public boolean check(String[] urls,String requestURI){
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if(match){
                return true;
            }
        }
        return false;
    }
}
