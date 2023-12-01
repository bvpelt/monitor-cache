package nl.bsoft.monitoring.insuranceservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;


@ComponentScan({"nl.bsoft.monitor.library", "nl.bsoft.monitoring.insuranceservice"})
//@EnableSwagger2
@SpringBootApplication
public class InsuranceServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(InsuranceServiceApplication.class, args);
    }
}
