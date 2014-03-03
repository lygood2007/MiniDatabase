import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//import com.sun.tools.javac.util.Pair;


public class MiniDatabase {
	
	private boolean appendCommands = false; 

	public void readUserInput(){
	    //  open up standard input
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    
	   	while(true){
	      //  prompt the user to enter their name
	      System.out.print("Enter your command: ");
	      String input;
	      
	      
	      
	      try {
	         input = br.readLine();
	         
	         if(input.equals("END")){
	        	 System.out.println("Exiting simple database");
	        	 break;
	         }else if(input.equals("BEGIN")){

	        	 if(appendCommands){
	        		 //create a new node and append to current list
	        	 }else{
	        		 appendCommands = true;
	        		 //create a new list, append current command to list
	        	 }        	
	         }else if(input.equals("ROLLBACK")){
	        	
	        	 
	        	 //remove the last node from command list
	        	 
	         }else if(input.equals("COMMIT")){
	        	
	        	 appendCommands = false;
	        	 //push all commands in command list
	        	 //create a new empty command list
	        	 
	         }else{
	        	 
	        	 String[] fields = input.split(" ");
	        	 
	        	 if(fields[0].equals("SET")){
	        		 if(appendCommands){
	        			 System.out.println("add set command to list");
	        		 }else{
	        			 System.out.println("execute set");
	        		 }
	        	 }else if(fields[0].equals("GET")){
	        		 if(appendCommands){
	        			 System.out.println("get elements from current command node"); 
	        		 }else{
	        			 System.out.println("execute get");
	        		 }	        		 
	        	 }else if(fields[0].equals("NUMEQUALTO")){
	        		 if(appendCommands){
	        			 System.out.println("get num count from list and tree");
	        		 }else{
	        			 System.out.println("get num count from tree");
	        		 }	        		 
	        	 }
	         }

	      
	      } catch (IOException ioe) {
	    	  ioe.printStackTrace();
	    	  System.exit(1);
	      }

	   }//while
	}
	public static void main(String[] args) {
		MiniDatabase miniDB = new MiniDatabase();
		miniDB.readUserInput();
	}

}
