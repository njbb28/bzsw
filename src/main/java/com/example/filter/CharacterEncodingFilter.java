package com.example.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "CharacterEncodingFilter", urlPatterns = "/*")
public class CharacterEncodingFilter implements Filter {
    private String encoding = "UTF-8"; // 设置默认编码

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String configEncoding = filterConfig.getInitParameter("encoding");
        if (configEncoding != null) {
            encoding = configEncoding;
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // 清理资源
    }
}