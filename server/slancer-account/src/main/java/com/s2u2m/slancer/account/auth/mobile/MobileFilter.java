package com.s2u2m.slancer.account.auth.mobile;

import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.PathMatchingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * class MobileFilter
 *
 * @author xiayy860612
 * @date 2018/5/2
 */
public class MobileFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(
            ServletRequest servletRequest,
            ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        return false;
    }
}
