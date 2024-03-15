package com.stuflo.inventorymanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestInventoryManagerApplication {

    public static void main(String[] args) {
        SpringApplication.from(InventoryManagerApplication::main).with(TestInventoryManagerApplication.class).run(args);
    }

}
