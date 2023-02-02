package xin.ucode;

import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;

/**
 * 启动程序
 *
 * @author ucode
 */
@SpringBootApplication(exclude = {RedissonAutoConfiguration.class})
public class DemoCodeApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoCodeApplication.class, args);
    }
}
