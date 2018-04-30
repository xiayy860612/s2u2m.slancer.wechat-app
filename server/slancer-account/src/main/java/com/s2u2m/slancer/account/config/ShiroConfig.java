package com.s2u2m.slancer.account.config;

import com.s2u2m.slancer.account.auth.admin.AdminFilter;
import com.s2u2m.slancer.account.auth.admin.AdminUserRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.*;

/**
 * @author Amos Xia
 * @date 2018/4/28
 */
@Configuration
public class ShiroConfig {

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager sm = new DefaultWebSecurityManager();

        // set realms
        List<Realm> realms = new ArrayList<>();
        realms.add(new AdminUserRealm());

        sm.setRealms(realms);
        return sm;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager sm) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(sm);

        // filter
        Map<String, Filter> filters = new HashMap<>();
        filters.put("adminFilter", new AdminFilter());

        factoryBean.setFilters(filters);

        // filter chain
        Map<String, String> chainMap = new LinkedHashMap<>();
        chainMap.put("/**", "anon");

        factoryBean.setFilterChainDefinitionMap(chainMap);
        return factoryBean;
    }
}
