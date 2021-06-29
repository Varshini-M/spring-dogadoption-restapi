import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableConfigurationProperties
@ComponentScan(basePackages = {"org.varsh"})
@EntityScan(basePackages = {"org.varsh"})
@PropertySource(value = {"classpath:api-application.properties"},ignoreResourceNotFound = true)
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
