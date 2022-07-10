package fr.dawan.cfa2022.tasks;

import org.springframework.stereotype.Component;

@Component
public class MyScheduledTask {

	//Autowired des services nécessaires pour réaliser votre tâche planifiée
	
	
//	@Scheduled(fixedDelay = 3000) //toutes les 3 secondes 
//	@Async("myTaskExecutor")
	public void checkXYZ() {
//		System.out.println("Vérification 1");
	}
	
	//On peut lancer autant de tâches planifiées que l'on souhaite
//	@Scheduled(cron = "0 0 4 1/1 * ?") //chaque jour à 4h du matin 
//	@Async("myTaskExecutor")
	public void check2() {
		System.out.println("Vérification 2");
	}
	
}
