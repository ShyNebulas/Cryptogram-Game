package src;	
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UndoLetter {


	public static void get_input() {
		
			Scanner reader = new Scanner(System.in);  
		    System.out.println("What letter would you like to undo?");
		      while(true) {
		        	 String undo = reader.nextLine();
		        if (Pattern.matches("[a-zA-Z]",undo)) {
		        	replace_letter(undo);
		        }
		        else{
		          System.out.println("You have not entered a letter. What letter would you like to replace?"); 
		          continue;
		        }
		        }
		  	
		  	
		      }


	public static void replace_letter(String undo) {
		
		String letter_replace = undo;
		
		// To Test the code (REMOVE)
		 HashMap<String, String> main_string1 = new HashMap<>();

		    // add entries to HashMap
		 main_string1.put("s", "a");
		 main_string1.put("v", "b");
		 main_string1.put("a", "a");
		 main_string1.put("a", "a");
		 main_string1.put("d", "w");
		 main_string1.put("s", "q");
		 main_string1.put("c", "a");
		
		
		  Iterator it = main_string1.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
		        main_string1.replace((String) pair.getKey(), letter_replace, "");
		        
		    }
		
		System.out.println(main_string1);
		
		
	}



	}


