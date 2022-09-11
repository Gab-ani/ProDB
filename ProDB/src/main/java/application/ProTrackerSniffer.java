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
			matchWatcher.savePortion();  //<--- on the launch then sheduled for 0 minute hourly
			
			
//			Hero[] dire = predictor.createTeam("вивер", "дизраптор", "тайд", "петух", "шторм");
//			Hero[] radiant = predictor.createTeam("ТА", "тини", "марси", "мышь", "клок");
//			
//			predictor.predict(dire, radiant);
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
		SpringApplication.run(ProTrackerSniffer.class, args);
	}

}
