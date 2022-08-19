package application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import parcing.ProTrackerMatchSelector;
import parcing.StratzMatchSniffer;

@EnableJpaRepositories({"data"})
@ComponentScan({"data", "parsing"})
@EntityScan({"data"})
@SpringBootApplication
@Configuration
@Import({ProTrackerMatchSelector.class, StratzMatchSniffer.class})
public class ProTrackerSniffer {

	@Autowired
	ProTrackerMatchSelector selector;
	@Autowired
	StratzMatchSniffer stratzSniffer;
	
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			System.out.println(stratzSniffer.formById(6712506154l));
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ProTrackerSniffer.class, args);
	}

}
//952
