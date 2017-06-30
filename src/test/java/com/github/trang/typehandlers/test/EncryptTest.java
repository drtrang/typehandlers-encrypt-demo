package com.github.trang.typehandlers.test;

import com.github.trang.typehandlers.util.ConfigUtil;
import com.github.trang.typehandlers.util.EncryptUtil;
import org.junit.Test;

import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author trang
 */
public class EncryptTest {

    @Test
    public void resource() {
        ResourceBundle bundle = ResourceBundle.getBundle("test");
        System.out.println(bundle);
        System.out.println(bundle.getLocale());
    }

    @Test
    public void test() throws InterruptedException {
        final AtomicInteger atomic = new AtomicInteger();
        Runnable task = () -> {
            int i = atomic.getAndAdd(1);
            String encrypt = EncryptUtil.encrypt("" + i);
            System.out.println(EncryptUtil.decrypt(encrypt));
        };
        ExecutorService executor = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++) {
            executor.execute(task);
        }
        Thread.sleep(3000);
    }

    @Test
    public void e() throws InterruptedException {
        System.out.println(EncryptUtil.encrypt("A"));
        System.out.println(EncryptUtil.encrypt("A"));

        ConfigUtil.bundleNames("test");
        System.out.println(EncryptUtil.encrypt("A"));
        System.out.println(EncryptUtil.encrypt("A"));
    }

}