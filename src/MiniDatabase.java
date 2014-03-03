import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

import com.sun.tools.javac.util.Pair;


public class MiniDatabase {
	
	private boolean appendCommands = false; 
	private LinkedList<CmdNode> commands = new LinkedList<CmdNode>();

	
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

	        	 if(!appendCommands){
//	        		 
//	        		 //create a new node and append to current list
//	        	 }else{
	        		 appendCommands = true;
	        		 CmdNode command = new CmdNode();
	        		 commands.add(command);
	        	 }        	
	         }else if(input.equals("ROLLBACK")){
	        	 if(commands.size() < 1){
	        		 System.out.println("NO TRANSACTION");
	        	 }else{
		        	 //remove the last node from command list
		        	 commands.removeLast();
		        	 if(commands.isEmpty()){
		        		 appendCommands = false;
		        	 }
	        	 }
	         }else if(input.equals("COMMIT")){
	        	
	        	 appendCommands = false;
	        	 //push all commands in command list
	        	 //create a new empty command list
	        	 
	         }else{
	        	 
	        	 String[] fields = input.split(" ");
	        	 
	        	 if(fields[0].equals("SET")){
	        		 assert(fields.length == 3);
	        		 String var = fields[1]; 
	        		 int val = Integer.parseInt(fields[2]);
	        		 if(appendCommands){
	        			 commands.getLast().addItem(var, val);
	        		 }else{
	        			 System.out.println("execute set");
	        		 }
	        	 }else if(fields[0].equals("GET")){
	        		 assert(fields[0].length() == 2);
	        		 String var = fields[1]; 
	        		 if(appendCommands){
	        			 Integer found = commands.getLast().search(var);
	        			 if(found != null){
	        				 System.out.println(found);
	        			 }else{
	        				 System.out.println("NULL");
	        			 }
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
