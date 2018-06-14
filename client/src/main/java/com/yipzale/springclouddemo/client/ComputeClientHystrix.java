package com.yipzale.springclouddemo.client;

import org.springframework.stereotype.Component;

@Component
public class ComputeClientHystrix implements ComputeClient {
    @Override
    public Integer add(Integer a, Integer b) {
        return Integer.MIN_VALUE;
    }
}
