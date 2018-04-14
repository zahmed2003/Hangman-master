import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.*;

public class Hangman implements KeyListener
{
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	
	int numWords;
	int wordsCorrect = 0;
	String NumWordsS;
	
	Random r = new Random();
	int random;
	
	int currentNum = 1;
	
	int numLives = 9;
	JLabel Lives = new JLabel();
	
	JLabel text = new JLabel();
	
	String word = new String();
	String underscore = new String();
	String IWord = new String();
	String IWordC = new String();

	
	ArrayList<String> words = new ArrayList<String>();
	Stack<String> wordsR = new Stack<String>();

	public static void main(String[] args) 
	{
		Hangman h = new Hangman();
		h.createUI();
		h.ArrayToStack(h.words);
		h.createUI2();
		
	}
	
	
	
	public Stack<String> ArrayToStack(ArrayList<String> al)
	{
		for(int i = 0; i < al.size(); i++)
		{
			random = r.nextInt(al.size());
			wordsR.push(al.get(random));
		}
		return wordsR;
		
	}
	
	public void createUI() {

		//NumWordsS = JOptionPane.showInputDialog("Input a number");
		//numWords = Integer.parseInt(NumWordsS);
		numWords = 3000;
		
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/dictionary.txt"));
			
			for(int i = 0; i < numWords; i++)
			{
				words.add(br.readLine());
			}
			
			br.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		frame.setVisible(true);
		panel.setVisible(true);
		Lives.setVisible(true);
		
		Lives.setText(Integer.toString(numLives));
		Lives.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		
		frame.setSize(500, 100);

		frame.add(panel);

		frame.addKeyListener(this);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		text.setVisible(true);
		
		
		panel.add(text);
		panel.add(Lives);

	}
	
	public void createUI2()
	{
		word = wordsR.pop();
		underscore = under(underscore, word);
		text.setText(underscore);
	}
	
	public String under(String underscore, String word)
	{
		for(int i = 0; i < word.length(); i++)
		{
			underscore = underscore + "_";
		}
		return underscore;

	}


	@Override
	public void keyTyped(KeyEvent e) {
		
		int keyPressed = e.getKeyChar();
		if ((keyPressed == KeyEvent.VK_SPACE)) 
		{
			
			if(currentNum == numWords)
			{
				
			}
			else
			{
			numLives = 9;
			Lives.setText(Integer.toString(numLives));
			word = wordsR.pop();
			underscore = "";
			IWord = "";
			underscore = under(underscore, word);
			IWord = underscore;
			
			currentNum +=1; 

			System.out.println(word);
			text.setText(underscore);
			}
		}
		
		else
		{
			IWordC = IWord;
			for(int i = 0; i < word.length(); i++)
			{
				if(word.charAt(i) == keyPressed)
				{
					IWord = IWord.substring(0, i) + word.charAt(i) + IWord.substring(i + 1, word.length());
					text.setText(IWord);
				}
			}
			if(IWordC.equals(IWord))
			{
				numLives -= 1;
				Lives.setText(Integer.toString(numLives));
				
			}
			
			
		}
		
		if(numLives <= 0)
		{
			JOptionPane.showMessageDialog(null, "You got " + wordsCorrect + " words correct.");
			frame.dispose();
			
		}
		
		if(IWord.equals(word))
		{
			numLives = 9;
			Lives.setText(Integer.toString(numLives));
			word = wordsR.pop();
			underscore = "";
			IWord = "";
			underscore = under(underscore, word);
			IWord = underscore;
			
			currentNum +=1; 
			wordsCorrect +=1;

			System.out.println(word);
			text.setText(underscore);
		}
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}
}
