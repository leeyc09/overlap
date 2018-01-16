package com.shine.overlap.data.repository;

/**
 * Created by youngchanlee on 2018. 1. 10..
 */

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Qualifier;

/**
 * @Qualifier
 * 클래스의 유형이 종속성을 식별하기 불충분할 때 사용하는 어노테이션입니다.
 * 예를 들어 안드로이드의 경우, 많은 경우 컨텍스트의 다양한 타입이 필요합니다,
 * 그래서 “@ForApplication”, “@ForActivity” 같은 식별자 어노테이션을 정의합니다.
 * 컨텍스트를 주입할 때 이 식별자 어노테이션을 이용해서 Dagger 가 어떤 타입을 제공할지 정해줍니다.
 *
 * @Documented. - 해당 어노테이션을 Javadoc에 포함한다
 * @Retention - 어노테이션의 범위(?)라고 할 수 있겠습니다. 어떤 시점까지 어노테이션이 영향을 미치는지 결정합니다
 *  - @Retention(RetentionPolicy.RUNTIME) // 컴파일 이후에도 JVM에 의해서 참조가 가능합니다.
 *  - @Retention(RetentionPolicy.CLASS) // 컴파일러가 클래스를 참조할 때까지 유효합니다.
 *  - @Retention(RetentionPolicy.SOURCE) // 어노테이션 정보는 컴파일 이후 없어집니다.
 *
 *  커스텀 어노테이션을 만들기 위해서 적는 어노테이션 같음
 */
@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Local {
}
