package com.s2u2m.slancer.account.auth.admin;

import com.s2u2m.slancer.core.exception.S2u2mSpringException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * class AdminFilter
 *
 * @author xiayy860612
 * @date 2018/5/3
 */
public class AdminFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(
            ServletRequest servletRequest, ServletResponse servletResponse,
            Object o) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = request.getHeader("token");
        AdminTokenAuth auth = new AdminTokenAuth(token);
        Subject subject = getSubject(servletRequest, servletResponse);
        try {
            subject.login(auth);
        } catch (AuthenticationException ex) {
            return false;
        }
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.sendRedirect("/unauth");
        return false;
    }
}
