package com.wpy.springcloud.alibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.wpy.springcloud.alibaba.dao")
public class MyBatisConfig {


}
