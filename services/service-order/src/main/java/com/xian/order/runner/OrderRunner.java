package com.xian.order.runner;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class OrderRunner implements ApplicationRunner {
    @Autowired
    private NacosConfigManager nacosConfigManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("ApplicationRunner===============");
        ConfigService configService = nacosConfigManager.getConfigService();
        //此监听器只在应用启动时注册一次。只要应用不停止，它会一直工作。
        Listener listener=new Listener() {
            @Override
            public Executor getExecutor() {
                return Executors.newFixedThreadPool(4);
            }

            @Override
            public void receiveConfigInfo(String configInfo) {
                System.out.println(configInfo);
            }
        };
        configService.addListener("service-order.properties", "DEFAULT_GROUP",listener);
    }
}
