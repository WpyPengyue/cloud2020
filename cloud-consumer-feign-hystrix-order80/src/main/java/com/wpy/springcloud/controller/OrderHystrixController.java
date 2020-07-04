package com.wpy.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.wpy.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderHystrixController {

    @Resource
    PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    String paymentInfo_OK(@PathVariable("id") Integer id){
        return paymentHystrixService.paymentInfo_OK(id);
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_timeoutHandler", commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="1000")
    })
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    String paymentInfo_timeout(@PathVariable("id") Integer id){
        return paymentHystrixService.paymentInfo_timeout(id);
    }

    public String paymentInfo_timeoutHandler(Integer id){
        return "线程池： " + Thread.currentThread().getName() + " paymentInfo_TimeOutHandler, id: " + id + "\t";
    }


}
