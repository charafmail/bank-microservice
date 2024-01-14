package org.charaf.customerservice.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@ConfigurationProperties(prefix = "customer.params")
@RefreshScope
@Setter @Getter @ToString @NoArgsConstructor
public class GlobalConfig {

    private int x;
    private int y;
}
