package dev.mvc.team2;

 import javax.sql.DataSource;

 import org.mybatis.spring.annotation.MapperScan;
 import org.apache.ibatis.session.SqlSessionFactory;
 import org.mybatis.spring.SqlSessionFactoryBean;
 import org.mybatis.spring.SqlSessionTemplate;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.boot.context.properties.ConfigurationProperties;
 import org.springframework.context.ApplicationContext;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.context.annotation.PropertySource;

 import com.zaxxer.hikari.HikariConfig;
 import com.zaxxer.hikari.HikariDataSource;

 @Configuration
 @PropertySource("classpath:/application.properties")  // 설정 파일 위치
<<<<<<< HEAD
@MapperScan(basePackages= { "dev.mvc.admin",
                                           "dev.mvc.admin_reply",
                                           "dev.mvc.cate",
                                           "dev.mvc.customer_post",
                                           "dev.mvc.email",
                                           "dev.mvc.member", 
                                           "dev.mvc.msurvey",
                                           "dev.mvc.notice",
                                           "dev.mvc.post",
                                           "dev.mvc.post_ratings",
                                           "dev.mvc.recommend",
                                           "dev.mvc.reply",
                                           "dev.mvc.review",
                                           "dev.mvc.servicecate",
                                           "dev.mvc.sms",
                                           "dev.mvc.survey",
                                           "dev.mvc.surveyitem"}) // DAO interface를 찾는 위치
=======
@MapperScan(basePackages= {  "dev.mvc.cate",
                                           "dev.mvc.member", 
                                           "dev.mvc.admin",
                                           "dev.mvc.sms",
                                           "dev.mvc.notice",
                                           "dev.mvc.survey",
                                           "dev.mvc.post",
                                           "dev.mvc.post_ratings",
                                           "dev.mvc.servicecate",
                                           "dev.mvc.customer_post",
                                           "dev.mvc.review",
                                           "dev.mvc.admin_reply",
                                           "dev.mvc.reply"}) // DAO interface를 찾는 위치
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
public class DatabaseConfiguration {
     
     @Autowired
     private ApplicationContext applicationContext;
     
     @Bean
<<<<<<< HEAD
     @ConfigurationProperties(prefix="spring.datasource.hikari")  // 설정 파일의 접두사 선언 spring.datasource.hikari....
=======
     @ConfigurationProperties(prefix="spring.datasource.hikari") // 설정 파일의 접두사 선언 spring.datasource.hikari....
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
     public HikariConfig hikariConfig() {
         return new HikariConfig();
     }
     
     @Bean
     public DataSource dataSource() throws Exception{
         DataSource dataSource = new HikariDataSource(hikariConfig());
         System.out.println(dataSource.toString());  // 정상적으로 연결 되었는지 해시코드로 확인
        return dataSource;
     }
     
     @Bean
     public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
         SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
         sqlSessionFactoryBean.setDataSource(dataSource);
         // "/src/main/resources/mybatis" 폴더의 파일명이 "xml"로 끝나는 파일 매핑
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mybatis/**/*.xml"));
         
         return sqlSessionFactoryBean.getObject();
     }
     
     @Bean
     public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory){
         return new SqlSessionTemplate(sqlSessionFactory);
     }
 }