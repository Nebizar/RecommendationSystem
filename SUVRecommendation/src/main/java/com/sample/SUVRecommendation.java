package com.sample;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * This is a sample class to launch a rule.
 */
public class SUVRecommendation {

    public static final void main(String[] args) {
        try {
            // load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-rules");

            // go !
        	new SUVRecommendation().init(true);
            Message message = new Message();
            message.setMessage("Hello World");
            message.setStatus(Message.HELLO);
            kSession.insert(message);
            kSession.fireAllRules();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static class Message {

        public static final int HELLO = 0;
        public static final int GOODBYE = 1;

        private String message;

        private int status;

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

    }
    
    public SUVRecommendation()
    {
    }
    
    public void init(boolean exitOnClose) {
    		 
    		SUVRecommendationUI ui = new SUVRecommendationUI();
    		ui.startGUI(exitOnClose);
 	        
    }
    
    @SuppressWarnings("serial")
	public static class SUVRecommendationUI extends JPanel implements ActionListener {
    
    	
    	private JLabel questionLabel;
    	private JButton answers[] = new JButton[2];
    	
    	public SUVRecommendationUI()
    	{
    		this.setPreferredSize(new Dimension(400,300));
    		
    		questionLabel = new JLabel("Pytanie", SwingConstants.CENTER);
    		questionLabel.setBorder(LineBorder.createGrayLineBorder());
    		questionLabel.setBounds(0,0,400,100);
    		
			this.add(questionLabel);
			
			answers[0] = new JButton("Answer 1");
			answers[0].setBounds(100, 120, 200, 50);
			answers[0].addActionListener(this);
			
			this.add(answers[0]);
			
			answers[1] = new JButton("Answer 2");
			answers[1].setBounds(100, 200, 200, 50);
			answers[1].addActionListener(this);
			
			this.add(answers[1]);
			
    	}
    	
    	
    
    
    	public void startGUI(boolean exitOnClose) {
        //Create and set up the window.
    		JFrame frame = new JFrame( "SUV Recommendation System" );
    		frame.setDefaultCloseOperation(exitOnClose ? JFrame.EXIT_ON_CLOSE : JFrame.DISPOSE_ON_CLOSE);

    		setOpaque( true );
    		frame.setContentPane( this );
    		frame.setLayout(null);

        //Display the window.
    		frame.pack();
    		frame.setLocationRelativeTo(null); // Center in screen
    		frame.setVisible( true );
    	}
    	
    	public static void setQandA(String question, String[] answers){
			
			// Ustawianie pytania i odpowiedzi
		}




		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			if(e.getSource() == answers[0]){
				System.out.println("0 button");
			}
			else if(e.getSource() == answers[1]){
				System.out.println("1 button");
			}
			
		}
    
    }

}
