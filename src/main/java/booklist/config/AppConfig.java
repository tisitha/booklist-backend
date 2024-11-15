package booklist.config;

import booklist.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public User user(){
        User user = new User();
        return user;
    }
}
