package com.inspur.configsingleclient.controller;

import com.inspur.configsingleclient.GitAutoRefreshConfig;
import com.inspur.configsingleclient.GitConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 修改配置文件内容后，不会自动刷新，它提供了一个刷新机制，但是需要我们主动触发。
// 1.需要引入spring-boot-starter-actuator 包
// 2.需要在 config client 端配置中增加 actuator 配置：
//management:
//  endpoint:
//    shutdown:
//      enabled: false
//  endpoints:
//    web:
//      exposure:
//        include: "*"

@RefreshScope
@RestController
public class GitController {
    @Autowired
    private GitConfig gitConfig;
    @Autowired
    private GitAutoRefreshConfig gitAutoRefreshConfig;

    // 启动后访问
    // http://localhost:3302/show
    // http://localhost:3302/autoShow
    //
    // 刷新配置文件： http://localhost:3302/actuator/refresh

    @GetMapping(value = "show")
    public Object show(){
        return gitConfig;
    }
    @GetMapping(value = "autoShow")
    public Object autoShow(){
        return gitAutoRefreshConfig;
    }
}

