package dev.mvc.team2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//★★★★★★ 패키지 주의 ★★★★★★, dev.mvc로 시작하는 모든 패키지의 class 자동 생성 
@ComponentScan(basePackages = {"dev.mvc"}) 
public class Team2V2sbm3cApplication {

  public static void main(String[] args) {
    SpringApplication.run(Team2V2sbm3cApplication.class, args);
  }

}
