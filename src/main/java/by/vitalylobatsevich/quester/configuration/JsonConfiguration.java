package by.vitalylobatsevich.quester.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.databind.Module;
import io.vavr.jackson.datatype.VavrModule;

@Configuration
public class JsonConfiguration {

    @Bean
    public Module module() {
        return new VavrModule();
    }

}
