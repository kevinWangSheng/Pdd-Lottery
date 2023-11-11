package com.kevin.lottery.work;

import com.xxl.job.core.executor.XxlJobExecutor;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wang
 * @create 2023-11-10-16:15
 */
@Configuration
public class LotteryXxlJobConfig {

    private final Logger logger = LoggerFactory.getLogger(LotteryXxlJobConfig.class);

    @Value("${xxl.job.admin.addresses}")
    private String adminAddress;

    @Value("${xxl.job.accessToken}")
    private String accessToken;

//    @Value("${xxl.job.executor.address}")
//    private String address;

    @Value("${xxl.job.executor.appname}")
    private String appName;

    @Value("${xxl.job.executor.ip}")
    private String ip;

    @Value("${xxl.job.executor.port}")
    private int port;

    @Value("${xxl.job.executor.logpath}")
    private String logPath;

    @Value("${xxl.job.executor.logretentiondays}")
    private int logRetentionDays;

    @Bean
    public XxlJobSpringExecutor jobExecutor(){
        logger.info(">>>>>>>>>>> xxl-job config init.");
        XxlJobSpringExecutor xxlJobExecutor = new XxlJobSpringExecutor();
        xxlJobExecutor.setAccessToken(accessToken);
//        xxlJobExecutor.setAddress(address);
        xxlJobExecutor.setAdminAddresses(adminAddress);
        xxlJobExecutor.setAppname(appName);
        xxlJobExecutor.setIp(ip);
        xxlJobExecutor.setPort(port);
        xxlJobExecutor.setLogPath(logPath);
        xxlJobExecutor.setLogRetentionDays(logRetentionDays);

        return xxlJobExecutor;
    }

}
