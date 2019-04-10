/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph<L> implements Graph<L> {
    
    private final Set<L> vertices = new HashSet<>();
    private final List<Edge<L>> edges = new ArrayList<>();
    
    // Abstraction function:
    //   用边图的结构来存储有向带权图
    // Representation invariant:
    //   vertices里存储L类型的点，L为抽象数据型
    //   edges存储图里的每一条边
    // Safety from rep exposure:
    //   该类的field均为private，因此不会泄露
    
    // TODO constructor
    
    // TODO checkRep
    public void checkRep() {
    	boolean flag = false;
    	Set<Edge<L>> set = new HashSet<>(edges);
    	if(set.size() == edges.size()) {
    		flag = true;
    	}
    	assert flag;
    }
    
    @Override public boolean add(L vertex) {
    	checkRep();
        return vertices.add(vertex);
    }
    
    @Override public int set(L source, L target, int weight) {
        if(weight != 0) {
        	if(!vertices.contains(source)) {
        		vertices.add(source);
        	}
        	if(!vertices.contains(target)) {
        		vertices.add(target);
        	}
        	for(int i=0;i<edges.size();i++) {
        		if(edges.get(i).getSource().equals(source)&&edges.get(i).getTarget().equals(target)) {
        			int w = edges.get(i).getWeight();
        			edges.get(i).updateWeight(weight);
        			return w;
        		}
        	}
        	Edge<L> e = new Edge<>(source,target,weight);
        	edges.add(e);
        	checkRep();
        	return 0;
        }
        else {
        	for(int i=0;i<edges.size();i++) {
        		if(edges.get(i).getSource().equals(source)&&edges.get(i).getTarget().equals(target)) {
        			int w = edges.get(i).getWeight();
        			edges.remove(i);
        			return w;
        		}
        	}
        	return 0;
        }
    }
    
    @Override public boolean remove(L vertex) {
    	return vertices.remove(vertex);
    }
    
    @Override public Set<L> vertices() {
        return vertices;
    }
    
    @Override public Map<L, Integer> sources(L target) {
        Map<L, Integer> map = new HashMap<>();
    	for(int i=0;i<edges.size();i++) {
        	if(edges.get(i).getTarget().equals(target)&&edges.get(i).getWeight() != 0) {
        		map.put(edges.get(i).getSource(), edges.get(i).getWeight());
        	}
        }
    	return map;
    }
    
    @Override public Map<L, Integer> targets(L source) {
    	Map<L, Integer> map = new HashMap<>();
    	for(int i=0;i<edges.size();i++) {
        	if(edges.get(i).getSource().equals(source)&&edges.get(i).getWeight() != 0) {
        		map.put(edges.get(i).getTarget(), edges.get(i).getWeight());
        	}
        }
    	return map;
    }
    
    // TODO toString()
    @Override public String toString() {
    	String se = "";
    	for(int i=0;i<edges.size();i++) {
    		se += edges.get(i).toString();
    	}
    	String sv = "";
    	for(Iterator<L> it = vertices.iterator(); it.hasNext();){
    	   sv = sv + " " + it.next();
    	}
    	return getClass().getName() + "\nvertices :" + sv + "\nedges :\n" + se;
    }
}

/**
 * TODO specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Edge<L> {
    
    // TODO fields
	private L source;
	private L target;
	private int weight;
	
    // Abstraction function:
    //   该类存储每一条边的source，target和权weight
    // Representation invariant:
    //   source和target均为类型是L的点，weight是该边的权
    // Safety from rep exposure:
    //   该类的field均为private，因此不会泄露
    
    // TODO constructor
    public Edge(L source,L target,int weight){
    	this.source = source;
    	this.target = target;
    	this.weight = weight;
    }
    // TODO checkRep
    
    // TODO methods
    public L getSource() {
    	return source;
    }
    
    public L getTarget() {
    	return target;
    }
    
    public int getWeight() {
    	return weight;
    }
    
    public void updateWeight(int weight) {
    	this.weight = weight;
    }
    // TODO toString()
    @Override public String toString() {
    	return source + " -> " + target + " weight = " + weight + "\n";
    }
}
