package com.company.springdemo.test.disruptor.generate2;

import com.company.springdemo.test.disruptor.generate1.Trade;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

public class Handler5 implements EventHandler<Trade>, WorkHandler<Trade> {

    @Override
    public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {
        this.onEvent(event);
    }

    @Override
    public void onEvent(Trade event) {
        System.out.println("handler5: get price : " + event.getPrice());
        event.setPrice(event.getPrice() + 3.0);
    }
}  