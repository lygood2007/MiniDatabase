import java.util.*;

/*
 * CmdNode: Used for storing the temporary variables
 *          that have not been pushed into database  
 */
public class CmdNode {

	private LinkedList<Pair<String, Integer>> _tmp;
	// constructor
	public CmdNode(){
		_tmp = new LinkedList<Pair<String, Integer> >();
	}
	
	public static CmdNode createFromNode(CmdNode node){
		assert node != null;
		
		CmdNode newNode = new CmdNode();
		LinkedList<Pair<String, Integer> > list = node.getList();
		for(Pair<String, Integer> p: list){
			newNode.addItem(p.getKey(), p.getValue());
		}
		return newNode;
	}
	
	public void addItem(String str, Integer val){
		if(str == null){
			System.err.println("You are trying to add a null key");
			return;
		}else if(val == null){
			System.err.println("You are trying to give key "+str+" a null value");
			return;
		}
		assert _tmp != null;
		_tmp.add(new Pair<String, Integer>(str, val));
	}
	
	private LinkedList<Pair<String, Integer> > getList(){
		return _tmp;
	}
	
	public Integer search(String str){
		assert _tmp != null;
		for(Pair<String, Integer> p: _tmp){
			if(p.getKey().equals(str)){
				return p.getValue();
			}
		}
		return null;
	}
}
