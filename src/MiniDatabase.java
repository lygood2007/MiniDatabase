import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

//import com.sun.tools.javac.util.Pair;


public class MiniDatabase {
	private Database  db = new Database();
	private boolean appendCommands = false; 
	private LinkedList<CmdNode> commands = new LinkedList<CmdNode>();
	public void readUserInput() throws FileNotFoundException{
	    //  open up standard input
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    
	    BufferedReader test = new BufferedReader(new FileReader("test") );
	    String input = "";
	   	//while(true){
	    while(input != null){
	      //  prompt the user to enter their name
	      System.out.print("Enter your command: ");
	  
	      try {
	         //input = br.readLine();
	    	 input = test.readLine();
		     System.out.println(input);
		     //db.printAll();
	         if(input.equals("END")){
	        	 //db.printAll();
	        	 System.out.println("Exiting simple database");
	        	 break;
	         }else if(input.equals("BEGIN")){
	        	 //CmdNode command = new CmdNode();
	        	 //commands.add(command);
	        	 CmdNode command = null;
	        	 if(!appendCommands){
	        		 appendCommands = true;
	        		 command = new CmdNode();
	        		 
	        	 }else{
	        		 command = CmdNode.createFromNode(commands.getLast());
	        		 //commands.add(cmd);
	        	 }
	        	 commands.add(command);
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
	        	 if(commands.size() > 0){
	        		 db.pushFromCmdNode(commands.getLast());
	        		 commands.clear();
	        	 }
	        	 
	         }else{
	        	 
	        	 String[] fields = input.split(" ");
	        	 
	        	 if(fields[0].equals("SET")){
	        		 assert(fields.length == 3);
	        		 String var = fields[1]; 
	        		 int val = Integer.parseInt(fields[2]);
	        		 if(appendCommands){
	        			 commands.getLast().addItem(var, val);
	        		 }else{
	        			 db.addItem(var, val);
	        		 }
	        	 }else if(fields[0].equals("GET")){
	        		 assert(fields[0].length() == 2);
	        		 String var = fields[1]; 
	        		 Integer found = null;
	        		 if(appendCommands){
	        			 found = commands.getLast().search(var);
	        		 }
	        		 if(found == null){
	        			 found = db.search(var);
	        		 }
	        		 System.out.println(found==null? "NULL":found);
	        		 
	        	 }else if(fields[0].equals("NUMEQUALTO")){
	        		 int num = 0;
	        		 if(appendCommands){
	        			 num += commands.getLast().findNum(Integer.parseInt(fields[1]));
	        			 
	        			 //System.out.println("get num count from list and tree");
	        		 }
	        		 num += db.findNum(Integer.parseInt(fields[1]));
	        		 System.out.println(num);
	        	 }
	         }

	      
	      } catch (IOException ioe) {
	    	  ioe.printStackTrace();
	    	  System.exit(1);
	      }

	   }//while
	}
	public static void main(String[] args) throws FileNotFoundException {
		MiniDatabase miniDB = new MiniDatabase();
		miniDB.readUserInput();
	}

}
