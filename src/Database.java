
import java.util.*;

public class Database {
	ArrayList<Pair<String, Integer>> _slots;
	
	public Database(){
		_slots = new ArrayList<Pair<String, Integer>>();
	}
	
	public Integer search(String key){
		for(Pair<String, Integer> p: _slots){
			if(p.getKey().equals(key)){
				return p.getValue();
			}
		}
		return null;
	}
	
	public void addItem(String str, Integer val){
		if(str == null){
			System.err.println("You are trying to add a null key");
			return;
		}else if(val == null){
			System.err.println("You are trying to give key "+str+" a null value");
			return;
		}
		assert _slots != null;
		for(Pair<String, Integer> p:_slots){
			if(p.getKey().equals(str)){
				p.setValue(val);
				return;
			}
		}
		_slots.add(new Pair<String, Integer>(str, val));
	}
	
	public int findNum(Integer val){
		int ret = 0;
		for(Pair<String, Integer> p: _slots){
			if(p.getValue().equals(val)){
				ret++;
			}
		}
		return ret;
	}
	
	public void pushFromCmdNode(CmdNode node){
		LinkedList<Pair<String, Integer>> list = node.getList();
		for(Pair<String, Integer> p : list){
			this.addItem(p.getKey(), p.getValue());
		}
	}
	
	public void printAll(){
		for(Pair<String, Integer> p : _slots){
			System.out.println(p.getKey()+","+p.getValue());
		}		
	}
}
