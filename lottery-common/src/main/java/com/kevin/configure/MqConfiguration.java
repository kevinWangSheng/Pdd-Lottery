package com.kevin.configure;

import com.kevin.common.Constance;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wang
 * @create 2023-11-09-19:56
 */
@Configuration
public class MqConfiguration {

    @Bean
    public NewTopic  topic(){
        return new NewTopic(Constance.KAFKA.HELLO_KAFKA,1,(short)1);
    }
}
