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
	String NumWordsS;
	
	Random r = new Random();
	int random;
	
	JLabel text = new JLabel();
	
	String word = new String();
	String underscore = new String();
	String IWord = new String();

	
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

		NumWordsS = JOptionPane.showInputDialog("Input a number");
		numWords = Integer.parseInt(NumWordsS);
		
		
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
		
		frame.setSize(500, 100);

		frame.add(panel);

		frame.addKeyListener(this);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		text.setVisible(true);
		
		
		panel.add(text);

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
			underscore = underscore + " _";
		}
		return underscore;

	}


	@Override
	public void keyTyped(KeyEvent e) {
		
		int keyPressed = e.getKeyChar();
		if (keyPressed == KeyEvent.VK_SPACE) {
			word = wordsR.pop();
			underscore = "";
			IWord = "";
			underscore = under(underscore, word);

			System.out.println(word);
			text.setText(underscore);
			
		}
		for(int j = 0; j < word.length(); j++) {
		if(keyPressed == word.charAt(j))
		{
			if(IWord.equals(""))
			{
			
			IWord = underscore.substring(0, j) + word.charAt(j) + underscore.substring(j, word.length());
			text.setText(IWord);
			}
			else
			{
				
				IWord = IWord.substring(0, j) + word.charAt(j) + IWord.substring(j, word.length() - 1);
				text.setText(IWord);
			}
		}
		}
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}
}
