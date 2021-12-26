package rhirabay.mystream;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBinding(MySource.class)
public class MyMessageBinderConfiguration {
    @Bean
    public MyProvisioningProvider myProvisioningProvider() {
        return new MyProvisioningProvider();
    }

    @Bean
    public MyMessageChannelBinder myMessageChannelBinder(MyProvisioningProvider myProvisioningProvider) {
        return new MyMessageChannelBinder(null, myProvisioningProvider);
    }
}
