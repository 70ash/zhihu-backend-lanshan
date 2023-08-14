package com.forzlp.zhihubackend.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zlp
 * @date 2023/8/4 17:43
 */
@Configuration
@MapperScan("com.forzlp.zhihubackend.mapper")
public class MybatisConfig {

}
