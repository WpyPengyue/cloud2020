package com.wpy.springcloud.alibaba.storage.impl;

import com.wpy.springcloud.alibaba.dao.StorageDao;
import com.wpy.springcloud.alibaba.storage.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageDao storageDao;

    @Override
    public void decrease(Long productId, Integer count) {
        log.info("----> storage-service中扣减库存开始");
        storageDao.decrease(productId,count);
        log.info("----> storage-service中扣减库存结束");
    }
}
