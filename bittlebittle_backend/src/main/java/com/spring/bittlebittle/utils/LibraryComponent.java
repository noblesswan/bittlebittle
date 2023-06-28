package com.spring.bittlebittle.utils;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.google.gson.Gson;

@Configuration
public class LibraryComponent {

    @Bean
    public Gson getGson(){
        return new Gson();
    }

    // �닔�룞�쑝濡� Bean �쓣 Application context�뿉 �벑濡� �븯�젮硫�,
    // Bean �쑝濡� �벑濡앺븯�젮�뒗 硫붿냼�뱶�뿉 @Bean �뼱�끂�뀒�씠�뀡�쓣 遺숈뿬二쇨퀬,
    // @Bean �뼱�끂�뀒�씠�뀡�씠 議댁옱�븯�뒗 �겢�옒�뒪�뿉 @Configuration �뼱�끂�뀒�씠�뀡�쓣 遺숈씤�떎.
    // getBean �쓽�씪�뒗 bean 硫붿냼�뱶�쓽 id �뒗 硫붿냼�뱶 �씠由꾩씠�떎.


    // 비밀번호 암호화

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
