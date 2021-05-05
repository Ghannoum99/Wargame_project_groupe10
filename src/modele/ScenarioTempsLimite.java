package modele;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ScenarioTempsLimite extends ScenarioStandard{
	int temps = 0;
	
	public ScenarioTempsLimite(String nom, String description) {
		super(nom, description);
	}
	
	Timer timer = new Timer();
	TimerTask task = new TimerTask() {
		public void run() {
			if(temps < 5) {
				temps++;
				System.out.println("Hello");
			}
			else {
				System.out.println("Jeu terminé");
				timer.cancel();
			}
			
		}
	};
	
	public void commencer() {
		timer.scheduleAtFixedRate(task, 1000, 1000);
	}
}

