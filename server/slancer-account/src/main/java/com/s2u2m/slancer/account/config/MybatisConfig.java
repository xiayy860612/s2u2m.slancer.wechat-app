package com.s2u2m.slancer.account.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.s2u2m.slancer.account.dao")
public class MybatisConfig {
}
