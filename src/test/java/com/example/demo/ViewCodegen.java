package com.example.demo;

import com.mybatisflex.codegen.Generator;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ViewCodegen {

    public static void main(String[] args) {
        // 配置数据源
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/club?characterEncoding=utf-8");
        dataSource.setUsername("root");
        dataSource.setPassword("12345678");

        // 创建配置内容
        GlobalConfig globalConfig = createViewConfig();

        // 通过 datasource 和 globalConfig 创建代码生成器
        Generator generator = new Generator(dataSource, globalConfig);

        // 生成代码
        generator.generate();
    }

    public static GlobalConfig createViewConfig() {
        // 创建配置内容
        GlobalConfig globalConfig = new GlobalConfig();

        // 设置根包
        globalConfig.setBasePackage("com.test");

        // 设置视图相关配置
        globalConfig.setGenerateForView(true);  // 启用视图生成
        // 设置生成的视图实体类包路径
        globalConfig.setEntityPackage("com.test.entity");
        globalConfig.setEntityGenerateEnable(true);
        globalConfig.setEntityWithLombok(true);
        globalConfig.setEntityJdkVersion(23);

        // 设置生成的视图 Mapper 包路径
        globalConfig.setMapperPackage("com.test.mapper");
        globalConfig.setMapperGenerateEnable(true);
        globalConfig.getTableDefConfig().setOverwriteEnable(true);

        // 禁用不需要的生成器
        globalConfig.setServiceGenerateEnable(false);
        globalConfig.setServiceImplGenerateEnable(false);
        globalConfig.setControllerGenerateEnable(false);

        // 可选：指定要生成的视图，如果不设置则生成所有视图
        // globalConfig.getTableDefConfig().setViewIncludes("v_user_info", "v_order_detail");
        
        // 可选：排除不需要生成的视图
        // globalConfig.getTableDefConfig().setViewExcludes("v_ignored_view");

        return globalConfig;
    }
} 