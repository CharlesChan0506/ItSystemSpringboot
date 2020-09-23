package com.charles.itsystem.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.charles.itsystem.mapper")  //设置mapper接口扫描包,使用后不用在接口上添加mapper注解
public class MybatisPlusConfig {

    @Bean  //配置分页插件
    public PaginationInterceptor paginationInterceptor(){
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        //paginationInterceptor.setOverflow(true);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        // paginationInterceptor.setLimit(500);
        // 开启 count 的 join 优化,只针对部分 left join
        //paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return new PaginationInterceptor();
    }
}
