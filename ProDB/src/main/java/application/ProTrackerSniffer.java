package application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import application.parsing.*;
import application.data_processing.*;
import application.data.*;

@EnableJpaRepositories
@ComponentScan
@EntityScan
@SpringBootApplication
@Configuration
@EnableScheduling
public class ProTrackerSniffer {

	@Autowired
	private ProMatchesFlowSaver matchWatcher;
	@Autowired
	private MatchupService matchupService;
	@Autowired
	private MatchAnalyser predictor;
	
	
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
//			matchupService.updateAll();
//			matchWatcher.savePortion();  //<--- on the launch then sheduled for 0 minute hourly

			matchWatcher.saveOfficial(6728333090l);
			
//			Hero[] radiant = predictor.createTeam("зомби", "мышь", "войд", "бист", "шейкер");
//			Hero[] dire = predictor.createTeam("бейн", "нигма", "зевс", "тини", "ВК");
//			
//			System.out.println(predictor.predict(radiant, dire));

//			System.setProperty("java.awt.headless", "true");
			
//			predictor.cyclePredictions();
		};
	}
	
	@Bean
	public TaskScheduler  taskScheduler() {
	    ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
	    threadPoolTaskScheduler.setPoolSize(5);
	    threadPoolTaskScheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");
	    return threadPoolTaskScheduler;
	}
	
	public static void main(String[] args) {
//		SpringApplication.run(ProTrackerSniffer.class, args);
		SpringApplicationBuilder builder = new SpringApplicationBuilder(ProTrackerSniffer.class);

		builder.headless(false);

		ConfigurableApplicationContext context = builder.run(args);
	}

}
