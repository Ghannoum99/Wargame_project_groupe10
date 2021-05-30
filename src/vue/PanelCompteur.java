package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.text.*;

@SuppressWarnings("serial")
public class PanelCompteur extends JPanel {
	
	private JLabel labelCompteur;
	public Timer compteur;
	private int seconde;
	private int minute;
	private String ddSecond;
	private String ddMinute;
	private DecimalFormat dFormat = new DecimalFormat("00");
	public boolean termine = false;
	private int xBounds;
	private int yBounds;

	/**
	 * Create the panel.
	 */
	
	//w : 46, h: 18
	public PanelCompteur(int xBounds, int yBounds) {
		this.xBounds = xBounds;
		this.yBounds = yBounds;
		
		this.setBounds(xBounds, yBounds, 109, 18);
		this.setOpaque(false);
		this.setLayout(null);
		
		afficherLabelCompteur();
		labelCompteur.setText("04:00");
		seconde = 0;
		minute = 4;
		
		countdownTimer();
		compteur.start();
		
	}
	
	public void afficherLabelCompteur() {
		labelCompteur = new JLabel();
		labelCompteur.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 17));
		labelCompteur.setForeground(Color.RED);
		labelCompteur.setBounds(27, 0, 64, 17);
		this.add(labelCompteur);
		
		
	}
	
	public void countdownTimer() {
		System.out.println(termine);
		compteur = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				seconde--;
				ddSecond = dFormat.format(seconde);
				ddMinute = dFormat.format(minute);
				labelCompteur.setText(ddMinute + ":" + ddSecond);
				if(seconde == -1) {
					seconde = 59;
					minute--;
					
					ddSecond = dFormat.format(seconde);
					ddMinute = dFormat.format(minute);
					labelCompteur.setText(ddMinute + ":" + ddSecond);
				}
				
				if (minute == 0 && seconde == 0) {
					
					termine = true;
					compteur.stop();
				}
			}
		});
		
	}

	public int getxBounds() {
		return xBounds;
	}

	public void setxBounds(int xBounds) {
		this.xBounds = xBounds;
	}

	public int getyBounds() {
		return yBounds;
	}

	public void setyBounds(int yBounds) {
		this.yBounds = yBounds;
	}
	
}
