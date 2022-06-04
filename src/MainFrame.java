/*Game: Candy Collector
 *Developed by: Anupam Sarkar
 *M.Sc Computer & Information Science
 *Sem II : Roll 22*/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MainFrame extends JFrame  implements ActionListener{
	
	GamePanel p= new GamePanel();
	JButton refreshBtn,exitBtn;
	JLabel score;
	static MainFrame f;
	public MainFrame(){
		setTitle("|| Candy Collector ||");
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initComponents();
	}
	
	public void initComponents(){
		//game controls
		/*start top panel*/
		JPanel topPanel = new JPanel();

		topPanel.add(new JLabel("Score:"));
		score= new JLabel("0");
	//	score.setAlignmentX(Component.LEFT_ALIGNMENT);
		topPanel.add(score);		
		
		
		refreshBtn = new JButton("Replay");
		refreshBtn.addActionListener(this);
		topPanel.add(refreshBtn);
		
		
		exitBtn = new JButton("Exit");
		exitBtn.addActionListener(this);
		topPanel.add(exitBtn);	
		
		topPanel.setBackground(Color.yellow);
		//topPanel.setOpaque(false);
		add(topPanel, BorderLayout.NORTH);
		/*end top panel*/
		
		
		//my frog: Ninja
		Ninja nj= new Ninja(p);
		addMouseListener(nj);
		p.objects.add(nj);
		
		//my pendulum
		Pendulum pdlm = new Pendulum(300,score,p);
		addMouseMotionListener(pdlm);
		p.objects.add(pdlm);			    
		add(p);
		
		//scissor
	/*	Scissor sc= new Scissor(p);
		addMouseMotionListener(sc);
		p.objects.add(sc);
		
		*/
	}
	
	
	public static void main(String[] args) {
		f= new MainFrame();
		f.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equalsIgnoreCase("Replay")){
			p.objects.clear();
			f.dispose();
			f=new MainFrame();
			f.setVisible(true);
			System.gc();
		}else if(e.getActionCommand().equalsIgnoreCase("Exit")){
			System.exit(0);
		}
	}

}
