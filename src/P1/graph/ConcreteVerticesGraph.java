/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph<L> implements Graph<L> {
    
    private final List<Vertex<L>> vertices = new ArrayList<>();
    
    // Abstraction function:
    //   用点图的结构来存储有向带权图
    // Representation invariant:
    //   vertices里存储图的每个点
    // Safety from rep exposure:
    //   该类的field均为private，因此不会泄露
    
    // TODO constructor
    
    // TODO checkRep
    public void checkRep(L vertex) {
    	boolean flag = true;
    	for(int i=0;i<vertices.size();i++) {
    		if(vertices.contains(vertex)) {
    			flag = false;
    		}
    	}
    	assert flag;
    }
    
    @Override public boolean add(L vertex) {
    	for(int i=0;i<vertices.size();i++) {
    		if(vertices.get(i).getSource() == vertex) {
    			return false;
    		}
    	}
    	Vertex<L> e = new Vertex<>(vertex);
    	vertices.add(e);
    	checkRep(vertex);
    	return true;
    	
    }
    
    @Override public int set(L source, L target, int weight) {
    	int w = 0,flag1 = 0,flag2 = 0;
    	if(weight != 0) {
    		for(int i=0;i<vertices.size();i++) {
        		if(vertices.get(i).getSource() == source) {
        			flag1 = 1;
        			break;
        		}
        	}
    		if(flag1 == 0) {
    			Vertex<L> es = new Vertex<>(source);
    			vertices.add(es);
    		}
    		for(int i=0;i<vertices.size();i++) {
        		if(vertices.get(i).getSource() == target) {
        			flag2 = 1;
        			break;
        		}
        	}
    		if(flag2 == 0) {
    			Vertex<L> et = new Vertex<>(target);
    			vertices.add(et);
    		}
        	for(int i=0;i<vertices.size();i++) {
        		if(vertices.get(i).getSource().equals(source)&&vertices.get(i).updateEdge(target)) {
        			w = vertices.get(i).getEdge().put(target, weight);
        		}
        		else if(vertices.get(i).getSource().equals(source)&&!vertices.get(i).updateEdge(target)) {
        			vertices.get(i).newEdge(target,weight);
        			w = 0;
        		}
        	}
        	return w;
        }
        else {
        	for(int i=0;i<vertices.size();i++) {
        		if(vertices.get(i).getSource().equals(source)&&vertices.get(i).updateEdge(target)) {
        			int rw = vertices.get(i).getEdge().get(target);
        			vertices.get(i).getEdge().remove(target, rw);
        			return rw;
        		}
        	}
        	return 0;
        }
    }
    
    @Override public boolean remove(L vertex) {
        for(int i=0;i<vertices.size();i++) {
        	if(vertices.get(i).getSource() == vertex) {
        		return vertices.remove(vertices.get(i));
        	}
        }
    	return false;
    }
    
    @Override public Set<L> vertices() {
        Set<L> v = new HashSet<>();
        for(int i=0;i<vertices.size();i++) {
        	v.add(vertices.get(i).getSource());
        }
        return v;
    }
    
    @Override public Map<L, Integer> sources(L target) {
    	Map<L, Integer> s = new HashMap<>();
    	for(int i=0;i<vertices.size();i++) {
        	if(vertices.get(i).getEdge().containsKey(target)) {
        		s.put(vertices.get(i).getSource(), vertices.get(i).getEdge().get(target));
        	}
        }
    	return s;
    }
    
    @Override public Map<L, Integer> targets(L source) {
    	Map<L, Integer> t = new HashMap<>();
    	for(int i=0;i<vertices.size();i++) {
    		if(vertices.get(i).getSource().equals(source)) {
    			t = vertices.get(i).getEdge();
    		}
    	}
    	return t;
    }
    
    // TODO toString()
    @Override public String toString() {
    	String se = "";
    	for(int i=0;i<vertices.size();i++) {
    		se += vertices.get(i).toString();
    	}
    	String sv = "";
    	for(int i=0;i<vertices.size();i++) {
    		sv = sv + " " + vertices.get(i).getSource();
    	}
    	return getClass().getName() + "\nvertices :" + sv + "\nedges :\n" + se;
    }
}

/**
 * TODO specification
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Vertex<L> {
    
    // TODO fields
    private L source;
    private Map<L, Integer> edge = new HashMap<>();
    
    // Abstraction function:
    //   该抽象类存储一个源节点和每个以此为source的边
    // Representation invariant:
    //   source是类型为L的点
    // Safety from rep exposure:
    //   该类的field均为private，因此不会泄露
    
    // TODO constructor
    public Vertex(L source) {
    	this.source = source;
    }
    // TODO checkRep
    
    
    // TODO methods
    public void newEdge(L target,int weight) {
    	edge.put(target, weight);
    }
    
    public L getSource() {
    	return source;
    }
    
    public Map<L, Integer> getEdge(){
    	return edge; 
    }
    
    public boolean updateEdge(L target) {
    	for(Entry<L, Integer> entry : edge.entrySet()) {
    		if(entry.getKey() == target) {
    			return true;
    		}
    	}
    	return false;
    }
    
    // TODO toString()
    @Override public String toString() {
    	String s = "";
    	for(Entry<L, Integer> entry : edge.entrySet()) {
    		s += source + " -> " + entry.getKey() + " weight = " + entry.getValue() + "\n";
    	}
    	return s;
    }
}
