package springmvcrestful.config;

/**
 * Created by Yevhen Pohiba on 08.12.2017.
 */
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan("springmvcrestful.*")
@EnableWebMvc
public class ApplicationContextConfig {


    // No need ViewSolver



    // Other declarations if needed ...

}