package com.company.springdemo.test.disruptor.generate2;

import com.company.springdemo.test.disruptor.generate1.Trade;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

public class Handler4 implements EventHandler<Trade>, WorkHandler<Trade> {

    @Override
    public void onEvent(Trade event, long sequence, boolean endOfBatch) {
        this.onEvent(event);
    }

    @Override
    public void onEvent(Trade event) {
        System.out.println("handler4: get name : " + event.getName());
        event.setName(event.getName() + "h4");
    }
}  