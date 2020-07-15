package com.wpy.springcloud;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class PaymentHystrixMain8001 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentHystrixMain8001.class, args);
    }

    /**
     * 此配置只是为了服务监控而配置，与服务容错本身无关，spring丑陋的升级后的坑
     * ServletRegistrationBean因为springboot的默认路径不是“/hystrix.stream”,
     * 只要在自己的项目里配置上下面的servlet就可以了
     * @return
     */
    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registationBean = new ServletRegistrationBean(streamServlet);
        registationBean.setLoadOnStartup(1);
        registationBean.addUrlMappings("/hystrix/stream");
        registationBean.setName("HystrixMetricsStreamServlet");
        return registationBean;
    }

}
