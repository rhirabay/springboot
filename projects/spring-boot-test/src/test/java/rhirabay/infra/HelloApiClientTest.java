package rhirabay.infra;

import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

class HelloApiClientTest {
    private HelloApiClient target;

    private MockRestServiceServer server;

    private HelloApiClient proxy;

    public HelloApiClientTest() {
        RestTemplate restTemplate = new RestTemplate();
        this.target = new HelloApiClient(restTemplate);
        this.server = MockRestServiceServer.bindTo(restTemplate).build();

        AspectJProxyFactory factory = new AspectJProxyFactory(target);
        factory.addAspect(new HelloApiClientAdvice());
        this.proxy = factory.getProxy();
    }

    @Test
    void test() {
        this.server.expect(requestTo("/hello"))
                .andRespond(withSuccess("hello", MediaType.TEXT_PLAIN));

        String actual = this.proxy.hello();
        assertThat(actual).isEqualTo("hello");

    }
}