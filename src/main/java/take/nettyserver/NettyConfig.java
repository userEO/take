package take.nettyserver;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 功能描述：
 *
 * @author EO
 * @date 2021/5/19 22:17
 */
@Component
// 默认配置文件是在resource文件夹下的 但是不想加一层目录，所有加个配置
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "spring.netty.port")
public class NettyConfig {
    private int port;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
