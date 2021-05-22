package vue;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


@SuppressWarnings("serial")
public class Scroll extends JFrame {

	private JPanel contentPane;
	private JPanel panelPrincipal ;
	private JButton boutonLeft;
	private JButton boutonRight;
	private JLabel backgroundimage;

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Scroll frame = new Scroll();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Scroll() {
		setTitle("WarGame");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 1300, 781);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		afficherPanelPrincipal();
		
		
		/** Creation d'un panel Scénario qui contient tous les images des scenarios possibles **/
		PanelScenario panelScroll = new PanelScenario();
		panelPrincipal.add(panelScroll);
		
		
		panelScroll.boutonImage.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				PlateauVue plateau = new PlateauVue();
				plateau.show();
				dispose();
			}
		});
		
		
		
		afficherBoutonGoLeft(panelScroll.scrollPane);
		afficherBoutonGoRight(panelScroll.scrollPane);
		
		/** BACKGROUND **/
		backgroundimage = new JLabel("");
		backgroundimage.setBounds(0, 0, 1296, 767);
		panelPrincipal.add(backgroundimage);
		backgroundimage.setIcon(new ImageIcon("images/thumb-1920-646077.jpg"));
		
	}
	
	public void afficherPanelPrincipal() {
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(null);
		panelPrincipal.setBounds(0, 0, 1296, 753);
		panelPrincipal.setLayout(null);
		contentPane.add(panelPrincipal);
	}
	
	public void afficherBoutonGoLeft(JScrollPane scrollPane) {
		
		boutonLeft = new JButton("Go Left");
		boutonLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(scrollPane.isEnabled()) {
					scrollPane.getHorizontalScrollBar().setValue(scrollPane.getHorizontalScrollBar().getValue()- 855);
				}
			}
		});
		boutonLeft.setBounds(101, 155, 97, 381);
		panelPrincipal.add(boutonLeft);
	}
	
	private void afficherBoutonGoRight(JScrollPane scrollPane) {
		boutonRight = new JButton("Go Right");
		boutonRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(scrollPane.isEnabled()) {
					scrollPane.getHorizontalScrollBar().setValue(scrollPane.getHorizontalScrollBar().getValue() + 855);
				}	
			}
		});
		boutonRight.setBounds(1084, 155, 97, 381);
		panelPrincipal.add(boutonRight);
	}
	
}
