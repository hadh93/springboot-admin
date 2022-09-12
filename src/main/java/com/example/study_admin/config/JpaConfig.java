package com.example.study_admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration // 설정파일이라는 뜻
@EnableJpaAuditing // JPA에 대한 감시자를 설정한다는 뜻
public class JpaConfig {

}
