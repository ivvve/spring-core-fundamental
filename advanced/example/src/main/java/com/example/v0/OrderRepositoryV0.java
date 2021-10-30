package com.example.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV0 {
    public void save(final String itemId) {
        if ("ex".equals(itemId)) {
            throw new IllegalStateException("예외 발생!");
        }

        // TODO 저장 로직
        this.sleep(1_000);
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
