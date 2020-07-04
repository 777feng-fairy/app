package cdgy.pqv.mamage.vuedemo3;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "cdgy.pqv.mamage.vuedemo3.mapper")
public class Vuedemo3Application {

    public static void main(String[] args) {
        SpringApplication.run(Vuedemo3Application.class, args);
    }

}
