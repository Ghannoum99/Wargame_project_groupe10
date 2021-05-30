package controleur;

import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.List;
import modele.Joueur;
import modele.ScenarioStandard;
import vue.*;

public class Application {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					/*MenuPrincipal frame = new MenuPrincipal();
					frame.setVisible(true);*/
					JsonController json = new JsonController();
					ScenarioStandard read = json.read_file_json();
					System.out.print(read.getTypeScenario());
					System.out.print(read.getJoueurs());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
