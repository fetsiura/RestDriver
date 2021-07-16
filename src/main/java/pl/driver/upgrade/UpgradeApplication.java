package pl.driver.upgrade;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.driver.upgrade.model.Advice;
import pl.driver.upgrade.repository.AdviceRepository;
import java.io.InputStream;
import java.util.stream.IntStream;

@SpringBootApplication
public class UpgradeApplication {

    public static void main(String[] args) {
        SpringApplication.run(UpgradeApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
//    @Bean
//    CommandLineRunner runner(AdviceRepository adviceRepository){
//        Faker faker = new Faker();
//        return args -> IntStream.range(0,10).forEach(i -> adviceRepository.save(new Advice(faker.superhero().name())));
//    }
}
