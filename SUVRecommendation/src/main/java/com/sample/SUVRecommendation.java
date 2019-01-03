package com.sample;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.stage.Window;

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
    	private JButton answers[];
    	private Question question;
    	
    	public SUVRecommendationUI(Question q)
    	{
    		this.setPreferredSize(new Dimension(400,600));
    		question=q;
    		answers=new JButton[question.getNumberOfAnswers()];
    		questionLabel = new JLabel(question.getQuestion(), SwingConstants.CENTER);
    		questionLabel.setBorder(LineBorder.createGrayLineBorder());
    		questionLabel.setBounds(0,0,400,100);
    		
			this.add(questionLabel);
			
			ArrayList<String> answersNames=question.getAnswers();
			
			for(int i=0;i<question.getNumberOfAnswers();i++) {
				answers[i] = new JButton(answersNames.get(i));
				answers[i].setBounds(50, 120+i*80, 300, 50);
				answers[i].addActionListener(this);
				
				this.add(answers[i]);
			}
			
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

		public void actionPerformed(ActionEvent e) {
			for(int i=0;i<question.getNumberOfAnswers();i++) {
				if(e.getSource() == answers[i]){
					question.setChosen(i);
					question.setGotAnswer(true);
				}
			}
			
			//Access the currently opened JFrame
			Component component = (Component) e.getSource();
	        JFrame frame = (JFrame) SwingUtilities.getRoot(component);
	        
			//Close the window after getting the answer
			frame.setVisible(false);
			frame.dispose();
			
		}
    
    }
    
    public static class Question{
    	private String question;
    	private int numberOfAnswers;
    	private ArrayList<String> answers;
    	public boolean gotResult;
    	private String result;
    	public int chosen;
    	public int questionID;
    	private boolean gotAnswer;
    	
    	public Question() {
    		answers=new ArrayList<String>();
    		gotResult=false;
    		gotAnswer=false;
    	}
    	
    	public Question(String q, int n, ArrayList<String> a, boolean g, int c,int i) {
    		answers=new ArrayList<String>();
    		question=q;
    		numberOfAnswers=n;
    		answers=a;
    		gotResult=g;
    		chosen=c;
    		questionID=i;
    	}
    	
    	
    	public void setValues(String q, int n, ArrayList<String> a, boolean g, int c,int i) {
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
    	
    	public ArrayList<String> getAnswers() {
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
