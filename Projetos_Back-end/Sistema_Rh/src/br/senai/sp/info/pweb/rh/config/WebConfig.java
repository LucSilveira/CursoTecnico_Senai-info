package br.senai.sp.info.pweb.rh.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@Import({PersistenceConfig.class})
@ComponentScan("br.senai.sp.info.rh")
public class WebConfig {

}
