package com.shine.overlap.util.rxSchedulers;

/**
 * Created by youngchanlee on 2018. 1. 10..
 */
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Qualifier;

/**
 * Qualifier to define Scheduler type (io, computation, or ui main thread).
 */
@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface RunOn {
    SchedulerType value() default SchedulerType.IO;
}
