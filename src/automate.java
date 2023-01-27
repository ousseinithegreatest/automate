

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class automate {
	
	/**
	 * This function is used to clean the state line in the file
	 * 
	 * @param txt : 
	 * @return : #ArrayList<>()
	 */
	public static ArrayList cleanState(String txt)
	{
		String data = txt ; 
		
		ArrayList<Character> etats = new ArrayList();
		
		for(int i = 0 ; i < txt.length() ; i++)
		{
			char element = data.charAt(i) ;
			//System.out.println(element);
			etats.add(element);
		}
		
		// clean list : erase characters like (, ] [)
		for(int i = 0 ; i < etats.size() ; i++)
		{
			if(etats.get(i).equals(',') || etats.get(i).equals(']') || etats.get(i).equals('['))
			{
				etats.remove(i);
			}
		}
		
		return etats;
		
	
	}
	
	/**
	 * This function is used to clean the alphabet line in the file
	 * @param txt
	 * @return #ArrayList<>()
	 */
	
	public static ArrayList cleanAlpha(String txt)
	{
		String data = txt ;
		ArrayList<Character> alpha = new ArrayList<Character>();
		
		for(int i = 0 ; i < data.length() ; i++)
		{
			alpha.add(data.charAt(i));
		}
		
		// clean list : erase characters like (, ] [)
		for(int i = 0 ; i < alpha.size() ; i++)
		{
			if(alpha.get(i).equals(']') || alpha.get(i).equals('[') || alpha.get(i).equals(','))
			{
				alpha.remove(i);
			}
		}
		
		return alpha;	
	}
	

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		
		//load file

		File automate = new File("/home/ousseb/Bureau/Abdoulfatah_Ousseini_projet_automate" , "automate.txt");
		
		if(!automate.exists())
		{
			System.out.println("L'automate n'existe pas");
			
			try {
				automate.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Fichier creer");
		}
		
		// If the file exists : start operation
		
		else
		{
			System.out.println("=> L'automate est present");
			System.out.println();	
			int comptLine = 1 ; String etats = "";
			int comptAlpha = 1 ; String alpha = "" ;
	
			
			
			try {
				
				Scanner line = new Scanner(automate);
				Scanner alphabets = new Scanner(automate);
				Scanner firstToEndState = new Scanner(automate);
				
				// Select (line) the list of states
				while(line.hasNextLine())
				{
					if(comptLine == 3)
						break ;
					
					etats = line.nextLine();
					comptLine++;
				}
				
				// Alphabet (line) selections
				while(alphabets.hasNextLine())
				{
					if(comptAlpha == 2)
						break;
					alpha = alphabets.nextLine();
					comptAlpha++;
				}				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			System.out.println("Les etats de l'automate : " + etats);
			System.out.println("L'alphabet de l'automate : " + alpha);
			
			ArrayList<Character> tableau_etats = new ArrayList<>();
			ArrayList<Character> tableau_alpha = new ArrayList<>();
			
			tableau_etats = cleanState(etats);
			tableau_alpha = cleanAlpha(alpha);
			
			System.out.println(tableau_alpha);
			System.out.println(tableau_etats);
			
			// Create matrix :
			int n = tableau_etats.size();
			String[][] tabAutomate = new String[n][n];
			
			// initialize the matrix :
			
			for(int i = 0 ; i < n ; i++)
			{
				for(int j = 0 ; j < n ; j++)
				{
					tabAutomate[i][j] = "*";
				}
			}
			
			// Display matrix :
			System.out.println();
			System.out.println("=> Representation matricielle d'un automate avant les transition");
			System.out.println();
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					
					System.out.print(tabAutomate[i][j]+"\t");
				}	
				System.out.println();
				System.out.println();
			}
			
			// Creation of a list that will store the information of a file
			
			ArrayList<String> phrases = new ArrayList<>();
			
			try
			{
				Scanner transitions = new Scanner(automate);
				int cmp = 1 ; String element = "";
				while(transitions.hasNextLine())
				{
					element = transitions.nextLine();				
					phrases.add(element);
					cmp++;
				}
				
			}catch(FileNotFoundException e)
			{
				e.printStackTrace();
			}
	
			int x = 0 ;
			while(x < 4)
			{
				phrases.remove(0);
				x++;
			}
			
			// Transition function
			
			System.out.println("=> L'ensemble des transitions effectuer");
			System.out.println();
			for(String tmp : phrases)
			{
				System.out.println("index : {" + tmp.charAt(1) + "}{" + tmp.charAt(5) + "} = " + tmp.charAt(3));
				int i ; int j ;
				
				i = Character.getNumericValue(tmp.charAt(1));
				j = Character.getNumericValue(tmp.charAt(5));
				
				tabAutomate[i][j] = String.valueOf(tmp.charAt(3));
				
			}
			System.out.println();
			System.out.println("=> Representation Graphique de l'automate");
			System.out.println();
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					
					System.out.print(tabAutomate[i][j]+"\t");
				}	
				System.out.println();
				System.out.println();
			}
	
		}	
		
	}
	
}