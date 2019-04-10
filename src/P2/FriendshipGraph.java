package P2;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

import P1.graph.Graph;
import P2.Person;

public class FriendshipGraph {

	private final Graph<Person> graph = Graph.empty();
	
	public void addVertex(Person A) {
		graph.add(A);
	}
	
	public void addEdge(Person A, Person B) {
		graph.set(A, B, 1);
	}
	
	public int getDistance(Person A, Person B) {
		if(A == B) {
			return 0;
		}
		Queue<Person> q = new LinkedList<Person>();
		Map<Person, Integer> d = new HashMap<>();
		Person u = new Person("unkown");
		
		for(Iterator<Person> i = graph.vertices().iterator(); i.hasNext();) {
			d.put(i.next(), 1000); //初始化路径长度
		}
		for(Iterator<Person> i = graph.vertices().iterator(); i.hasNext();) {
			i.next().tag(false);
		}
		A.tag(true);
		d.put(A, 0);
		q.add(A);
	    while(!q.isEmpty()) {
	    	u = q.poll();
	    	for(Entry<Person, Integer> entry : graph.targets(u).entrySet()) {
	    		if(entry.getKey().getTag() == false) {
	    			entry.getKey().tag(true);
	    			d.put(entry.getKey(), d.get(u) + 1);
	    			q.add(entry.getKey());
	    		}
	    	}
	    }   
	    if(d.get(B) >= 1000) {
	    	return -1;
	    }
	    else {
	    	return d.get(B);
	    }
	  }
	
	public Graph<Person> getGraph() {
		return graph;
	}
}
