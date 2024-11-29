package dev.plotnikov.page.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientBean {

    @Bean
    public RestClient productsRestClient(
            @Value("${shop.rest.url:http://localhost:8081}") String url, LoadBalancerClient balancerClient) {
        return RestClient.builder()
                .baseUrl(url)
                .requestInterceptor(new LoadBalancerInterceptor(balancerClient))
                .build();
    }
}
