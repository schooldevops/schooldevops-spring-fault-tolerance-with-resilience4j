package com.schooldevops.springjava.resilience4jtutorials.predicate;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Predicate;

@Slf4j
public class CommandPredicate implements Predicate<String> {

    @Override
    public boolean test(String s) {
        // 커맨드가 E인경우만 Retry가 동작한다.
        log.info("Predicate --------------------- A");
        return "A".equals(s);
    }
}
