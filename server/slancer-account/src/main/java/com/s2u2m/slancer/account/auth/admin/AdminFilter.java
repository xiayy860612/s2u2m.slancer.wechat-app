package com.s2u2m.slancer.account.auth.admin;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

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
        subject.login(auth);
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        return false;
    }
}
