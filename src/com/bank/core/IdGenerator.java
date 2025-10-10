package com.bank.core;

import java.util.concurrent.atomic.AtomicLong;

public final class IdGenerator {
    private static final AtomicLong COUNTER = new AtomicLong(System.currentTimeMillis() % 10000);
    private IdGenerator() {}
    public static String nextId(String prefix) {
        return prefix + COUNTER.getAndIncrement();
    }
}
