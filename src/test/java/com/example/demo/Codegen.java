package com.example.demo;

import com.mybatisflex.codegen.Generator;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.mybatisflex.core.service.IService;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zaxxer.hikari.HikariDataSource;

public class Codegen {

    public static void main(String[] args) {
        //配置数据源
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/club?characterEncoding=utf-8");
        dataSource.setUsername("root");
        dataSource.setPassword("12345678");

        //创建配置内容，两种风格都可以。
        GlobalConfig globalConfig = createGlobalConfigUseStyle1();
        //GlobalConfig globalConfig = createGlobalConfigUseStyle2();

        //通过 datasource 和 globalConfig 创建代码生成器
        Generator generator = new Generator(dataSource, globalConfig);

        //生成代码
        generator.generate();
    }

    public static GlobalConfig createGlobalConfigUseStyle1() {
        //创建配置内容
        GlobalConfig globalConfig = new GlobalConfig();

        //设置根包
        globalConfig.setBasePackage("com.test");

        //设置生成 entity 并启用 Lombok
        globalConfig.setEntityGenerateEnable(true);
        globalConfig.setEntityWithLombok(true);
        //设置项目的JDK版本，项目的JDK为14及以上时建议设置该项，小于14则可以不设置
        globalConfig.setEntityJdkVersion(23);

        //设置生成 mapper
        globalConfig.setMapperGenerateEnable(true);
        globalConfig.getTableDefConfig().setOverwriteEnable(true);

        // 设置生成 controller
        globalConfig.setControllerGenerateEnable(true);
        globalConfig.setControllerPackage("com.test.controller"); // 设置 Controller 的包路径
        globalConfig.setServiceImplCacheExample(true);
        globalConfig.setServiceImplGenerateEnable(true);
        globalConfig.setServiceImplPackage("com.test.service.impl");
        globalConfig.setServiceImplSuperClass(ServiceImpl.class);

        globalConfig.setServiceGenerateEnable(true);
        globalConfig.setServicePackage("com.test.service");
        globalConfig.setServiceSuperClass(IService.class);
        return globalConfig;
    }
}