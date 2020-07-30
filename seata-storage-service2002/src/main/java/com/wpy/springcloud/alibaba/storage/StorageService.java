package com.wpy.springcloud.alibaba.storage;

public interface StorageService {
    void decrease(Long productId, Integer count);
}
