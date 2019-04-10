/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    

	/*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph<String>();
    }
    
    /*
     * Testing ConcreteEdgesGraph...
     */
    
    @Test
	public void testAdd() {
    	Graph<String> graph = emptyInstance();
		String a="a",b="b",c="c",d="d";
		graph.add(a);
		graph.add(b);
		graph.add(c);
		graph.add(d);
		assertEquals(true, graph.vertices().contains(a));
		assertEquals(true, graph.vertices().contains(b));
		assertEquals(true, graph.vertices().contains(c));
		assertEquals(true, graph.vertices().contains(d));
	}
    @Test
	public void testSet() {
    	Graph<String> graph = emptyInstance();
		String a="a",b="b",c="c",d="d",e="e",f="f";
		graph.add(a);
		graph.add(b);
		graph.add(c);
		graph.add(d);
		assertEquals(0, graph.set(a, b, 3));
		assertEquals(0, graph.set(d, b, 1));
		assertEquals(0, graph.set(a, e, 4));
		assertEquals(0, graph.set(f, b, 2));
		assertEquals(0, graph.set(e, f, 6));
		assertEquals(3, graph.set(a, b, 4));
		assertEquals(0, graph.set(a, c, 0));
		assertEquals(1, graph.set(d, b, 0));
	}

	@Test
	public void testRemove() {
		Graph<String> graph = emptyInstance();
		String a="a",b="b",c="c",d="d";
		graph.add(a);
		graph.add(b);
		graph.add(c);
		graph.add(d);
		graph.remove(a);
		graph.remove(b);
		assertEquals(false, graph.vertices().contains(a));
		assertEquals(false, graph.vertices().contains(b));
		assertEquals(true, graph.vertices().contains(c));
		assertEquals(true, graph.vertices().contains(d));
	}

	@Test
	public void testVertices() {
		Graph<String> graph = emptyInstance();
		String a="a",b="b",c="c",d="d";
		Set<String> v = new HashSet<>();
		v.add(a);
		v.add(b);
		v.add(c);
		v.add(d);
		graph.add(a);
		graph.add(b);
		graph.add(c);
		graph.add(d);
		assertEquals(v, graph.vertices());
	}

	@Test
	public void testSources() {
		Graph<String> graph = emptyInstance();
		String a="a",b="b",c="c",d="d",e="e",f="f";
		Map<String, Integer> mb = new HashMap<>();
		Map<String, Integer> me = new HashMap<>();
		Map<String, Integer> mf = new HashMap<>();
		mb.put(a, 4);
		mb.put(f, 2);
		me.put(a, 4);
		mf.put(e, 6);
		graph.add(a);
		graph.add(b);
		graph.add(c);
		graph.add(d);
		graph.set(a,b,3);
		graph.set(d,b,1);
		graph.set(a,e,4);
		graph.set(f,b,2);
		graph.set(e,f,6);
		graph.set(a,b,4);
		graph.set(a,c,0);
		graph.set(d,b,0);
		assertEquals(mb, graph.sources(b));
		assertEquals(me, graph.sources(e));
		assertEquals(mf, graph.sources(f));
	}

	@Test
	public void testTargets() {
		Graph<String> graph = emptyInstance();
		String a="a",b="b",c="c",d="d",e="e",f="f";
		Map<String, Integer> ma = new HashMap<>();
		Map<String, Integer> me = new HashMap<>();
		Map<String, Integer> mf = new HashMap<>();
		ma.put(b, 4);
		ma.put(e, 4);
		mf.put(b, 2);
		me.put(f, 6);
		graph.add(a);
		graph.add(b);
		graph.add(c);
		graph.add(d);
		graph.set(a,b,3);
		graph.set(d,b,1);
		graph.set(a,e,4);
		graph.set(f,b,2);
		graph.set(e,f,6);
		graph.set(a,b,4);
		graph.set(a,c,0);
		graph.set(d,b,0);
		assertEquals(ma, graph.targets(a));
		assertEquals(me, graph.targets(e));
		assertEquals(mf, graph.targets(f));
	}
	
    // Testing strategy for ConcreteEdgesGraph.toString()
    //   将每个图的点和边存到一个字符串然后和toString的比较
    
    // TODO tests for ConcreteEdgesGraph.toString()
    @Test
	public void testToString() {
		Graph<String> graph = emptyInstance();
		String a="a",b="b",c="c",d="d",e="e",f="f";
		String tests = "P1.graph.ConcreteEdgesGraph\n" + 
				"vertices : a b c d e f\n" + 
				"edges :\n" + 
				"a -> b weight = 4\n" + 
				"a -> e weight = 4\n" + 
				"f -> b weight = 2\n" + 
				"e -> f weight = 6\n";
		graph.add(a);
		graph.add(b);
		graph.add(c);
		graph.add(d);
		graph.set(a,b,3);
		graph.set(d,b,1);
		graph.set(a,e,4);
		graph.set(f,b,2);
		graph.set(e,f,6);
		graph.set(a,b,4);
		graph.set(a,c,0);
		graph.set(d,b,0);
		assertEquals(tests, graph.toString());
	}
    /*
     * Testing Edge...
     */
    
    // Testing strategy for Edge
    //   每一个方法都新建图并加入相应的点，然后通过assertEquals来检测相应功能
    
    // TODO tests for operations of Edge
}
