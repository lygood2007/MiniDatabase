
import java.util.*;


public class Database {
	private DBTree<String, Integer> _dbKey;
	private DBTree<Integer, Integer> _dbVal; 
	private class DBNode<K extends Comparable,V>
	{
		private Pair<K,V> _pair;
		private DBNode<K,V> _left;
		private DBNode<K,V> _right;
		
		public DBNode(Pair<K,V> p){
			_pair = new Pair(p.getKey(), p.getValue());
		}
		public DBNode(K key, V val){
			_pair = new Pair<K,V>(key, val);
			_left = null;
			_right = null;
		}
		
		public DBNode<K,V> getLeft(){
			return _left;
		}
		
		public DBNode<K,V> getRight(){
			return _right;
		}
		
		public void setLeft(DBNode<K,V> left){
			_left = left;
		}
		
		public void setRight(DBNode<K,V> right){
			_right = right;
		}
		
		public K getKey(){
			return _pair.getKey();
		}
		
		public V getVal(){
			return _pair.getValue();
		}
		
		public void setVal(V val){
			_pair.setValue(val);
		}
	}
	
	private class DBNodePair<K extends Comparable,V>{
		DBNode<K,V> _parent;
		DBNode<K,V> _node;
		
		DBNodePair(DBNode<K,V> parent, DBNode<K,V> node){
			_parent = parent;
			_node = node;
		}
	}
	private class DBTree<K extends Comparable,V>
	{
		private DBNode<K,V> _root = null;
		
		public DBTree(){
			// nothing to do here
		}
		
		public void preorder(){
			preorderRecur(_root);
		}
		
		public void preorderRecur(DBNode<K,V> node){
			if(node != null){
				preorderRecur(node.getLeft());
				System.out.println("("+node.getKey()+" "+node.getVal()+")");
				preorderRecur(node.getRight());
			}
		}
		
		public void remove(K key){
			DBNodePair<K,V> pair = search(key);
			if(pair._node == null){ // is not there
				return;
			}else{
				DBNode<K,V> toDelete = pair._node;
				DBNode<K,V> parent = pair._parent;
				// check the child of it
				if(toDelete.getLeft() == null){
					if(parent != null){
						parent.setLeft(toDelete.getRight());
					}else{
						_root = toDelete.getRight();
					}
				}else if(toDelete.getRight() == null){
					if(parent != null){
						parent.setLeft(toDelete.getLeft());
					}else{
						_root = toDelete.getLeft();
					}
				}else{
					// both are not null
					DBNode<K,V> right = toDelete.getRight();
					// find the leftmost of the right
					DBNode<K,V> leftmost = right;
					DBNode<K,V> subParent = null;
					while(leftmost.getLeft() != null){
						subParent = leftmost;
						leftmost = leftmost.getLeft();
					}
					V val = leftmost.getVal();
					if(subParent != null){
						subParent.setLeft(leftmost.getRight());
					}else{
						parent.setRight(leftmost.getRight());
					}
					toDelete.setVal(val);
				}
			}
		}
		
		public void insert(K key, V val){
			DBNodePair<K,V> pair = search(key);
			if(pair._node != null){
				pair._node.setVal(val);
			}else{
				if(pair._parent == null){
					_root = new DBNode<K,V>(key, val);
				}else{
					if(key.compareTo(pair._parent.getKey()) > 0){
						pair._parent.setRight(new DBNode<K,V>(key, val));
					}else if(key.compareTo(pair._parent.getKey()) < 0){
						pair._parent.setLeft(new DBNode<K,V>(key, val));
					}else{
						assert false;
					}
				}
			}
		}
		
		// return the node and the parent
		public DBNodePair<K,V> search(K key){
			DBNode<K,V> node = _root;
			DBNode<K,V> parent = null;
			while(node != null){
				if(node.getKey().compareTo(key) < 0){
					parent = node;
					node = node.getRight();
				}else if(node.getKey().compareTo(key) > 0){
					parent = node;
					node = node.getLeft();
				}else{
					break;
					//return new DBNodePair<K,V>(parent,node);
				}
			}
			return new DBNodePair<K,V>(parent, node);
		}
	}
	
	public Database(){
		_dbKey = new DBTree<String, Integer>();
		_dbVal = new DBTree<Integer, Integer>();
	}
	
	public Integer search(String key){
		DBNodePair<String,Integer> pair = _dbKey.search(key);
		
		/*for(Pair<String, Integer> p: _slots){
			if(p.getKey().equals(key)){
				return p.getValue();
			}
		}*/
		if(pair._node == null)
			return null;
		else
			return pair._node.getVal();
	}
	
	public void addItem(String str, Integer val){
		if(str == null){
			System.err.println("You are trying to add a null key");
			return;
		}else if(val == null){
			System.err.println("You are trying to give key "+str+" a null value");
			return;
		}
		assert _dbKey != null;
		assert _dbVal != null;
		
		/**
		 * Can be optimized
		 */
		DBNodePair<String, Integer> p1 = _dbKey.search(str);
		if(p1._node == null){
			_dbKey.insert(str, val);
			DBNodePair<Integer, Integer> p2 = _dbVal.search(val);
			if(p2._node == null){
				_dbVal.insert(val, 1);
			}else{
				p2._node.setVal(p2._node.getVal()+1);
			}
		}else{
			if(p1._node.getVal() == val){
				// no need to set
				return;
			}else{
				int oldVal = p1._node.getVal();
				p1._node.setVal(val);
				
				DBNodePair<Integer, Integer> pair = _dbVal.search(oldVal);
				assert pair._node != null;
				if(pair._node.getVal() == 1){
					_dbVal.remove(oldVal);
				}else{
					pair._node.setVal(pair._node.getVal()-1);
				}
				DBNodePair<Integer, Integer> pair2 = _dbVal.search(val);
				if(pair2._node == null){
					_dbVal.insert(val, 1);
				}else{
					// increment it
					pair2._node.setVal(pair2._node.getVal()+1);
				}
			}
		}
		
	}
	
	public int findNum(Integer val){
		DBNodePair<Integer, Integer> pair = _dbVal.search(val);
		if(pair._node == null){
			return 0;
		}
		return pair._node.getVal();
	}
	
	public void pushFromCmdNode(CmdNode node){
		LinkedList<Pair<String, Integer>> list = node.getList();
		for(Pair<String, Integer> p : list){
			this.addItem(p.getKey(), p.getValue());
		}
	}
	
	public void printAll(){
		System.out.println("Key");
		_dbKey.preorder();
		System.out.println("Val");
		_dbVal.preorder();
	}
}
