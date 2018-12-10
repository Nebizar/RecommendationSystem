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

import java.util.ArrayList;

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
        	Question question = new Question();
        	new SUVRecommendation().init(true,question);
            ArrayList <String> answers = new ArrayList<String>();
            answers.add("Not if I can help it");
            answers.add("Roads are for choads");
            answers.add("Sometimes for work");
            question.setValues("Will you ever actually take it off road?", 3, answers, false, 0,0);
            question.waitForAnswer();
            kSession.insert(question);
            kSession.fireAllRules();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
    public SUVRecommendation()
    {
    }
    
    public void init(boolean exitOnClose,Question question) {
    		 
    		SUVRecommendationUI ui = new SUVRecommendationUI(question);
    		ui.startGUI(exitOnClose);
 	        
    }
    
    @SuppressWarnings("serial")
	public static class SUVRecommendationUI extends JPanel implements ActionListener {
    
    	
    	private JLabel questionLabel;
    	private JButton answers[] = new JButton[3];
    	private Question question;
    	
    	public SUVRecommendationUI(Question q)
    	{
    		this.setPreferredSize(new Dimension(400,600));
    		question=q;
    		questionLabel = new JLabel("Will you ever actually take it off road ?", SwingConstants.CENTER);
    		questionLabel.setBorder(LineBorder.createGrayLineBorder());
    		questionLabel.setBounds(0,0,400,100);
    		
			this.add(questionLabel);
			
			answers[0] = new JButton("Not if I can help it");
			answers[0].setBounds(100, 120, 200, 50);
			answers[0].addActionListener(this);
			
			this.add(answers[0]);
			
			answers[1] = new JButton("Roads are for choads");
			answers[1].setBounds(100, 200, 200, 50);
			answers[1].addActionListener(this);
			
			this.add(answers[1]);
			
			answers[2] = new JButton("Sometimes for work");
			answers[2].setBounds(100, 280, 200, 50);
			answers[2].addActionListener(this);
			
			this.add(answers[2]);
			
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
				question.setChosen(0);
				question.setGotAnswer(true);
			}
			else if(e.getSource() == answers[1]){
				question.setChosen(1);
				question.setGotAnswer(true);
			}
			else if(e.getSource() == answers[2]){
				question.setChosen(2);
				question.setGotAnswer(true);
			}
			
		}
    
    }
    
    public static class Question{
    	private String question;
    	private int numberOfAnswers;
    	private ArrayList<String> answers;
    	private boolean gotResult;
    	private String result;
    	public int chosen;
    	public int questionID;
    	private boolean gotAnswer;
    	
    	public Question() {
    		answers=new ArrayList<String>();
    		gotResult=false;
    		gotAnswer=false;
    	}
    	
    	public Question(String q, int n, ArrayList a, boolean g, int c,int i) {
    		answers=new ArrayList<String>();
    		question=q;
    		numberOfAnswers=n;
    		answers=a;
    		gotResult=g;
    		chosen=c;
    		questionID=i;
    	}
    	
    	
    	public void setValues(String q, int n, ArrayList a, boolean g, int c,int i) {
    		question=q;
    		numberOfAnswers=n;
    		answers=a;
    		gotResult=g;
    		chosen=c;
    		questionID=i;
    	}
    	
    	public void setChosen(int c) {
    		chosen=c;
    	}
    	
    	public void setResult(String r) {
    		result=r;
    	}
    	
    	public String getQuestion() {
    		return question;
    	}
    	
    	public int getNumberOfAnswers() {
    		return numberOfAnswers;
    	}
    	
    	public ArrayList getAnswers() {
    		return answers;
    	}
    	
    	public boolean getGotResult() {
    		return gotResult;
    	}
    	
    	public void setGotAnswer(boolean ga) {
    		gotAnswer=ga;
    	}
    	
    	public boolean getGotAnswer() {
    		return gotAnswer;
    	}
    	
    	public void waitForAnswer() {
    		while(!gotAnswer) {
    			System.out.println("Randomly working");
    		}
    		
    		gotAnswer=false;
    	}
    }
}
