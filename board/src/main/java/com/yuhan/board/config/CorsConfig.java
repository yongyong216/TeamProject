package com.yuhan.board.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //설정과 관련된 클래스임을 명시함
public class CorsConfig implements WebMvcConfigurer{
    //cors 정책에 대해 맞춘다. 
    // 프로토콜이 다르다던지 .... 다른출처와 자원을 공유하려고 할 때 정책
     public void addCorsMappings(CorsRegistry registry) {
        registry
        .addMapping("/**")
        .allowedOrigins("*") //모든 출처에 대해 허용
        .allowedMethods("*"); // 모든 메서드에 대해 허용
    }
}
