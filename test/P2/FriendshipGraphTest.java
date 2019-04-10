package P2;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import P2.FriendshipGraph;
import P2.Person;

class FriendshipGraphTest {

	@Test
	void testAddVertex() {
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross");
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		assertEquals(true, graph.getGraph().vertices().contains(rachel));
		assertEquals(true, graph.getGraph().vertices().contains(ross));
		assertEquals(true, graph.getGraph().vertices().contains(ben));
		assertEquals(true, graph.getGraph().vertices().contains(kramer));
	}

	@Test
	void testAddEdge() {
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross");
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		Map<Person, Integer> mra = new HashMap<>();
		Map<Person, Integer> mro = new HashMap<>();
		Map<Person, Integer> mb = new HashMap<>();
		Map<Person, Integer> mk = new HashMap<>();
		mra.put(ross, 1);
		mro.put(rachel, 1);
		mro.put(ben, 1);
		mb.put(ross, 1);
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		graph.addEdge(rachel, ross);
		graph.addEdge(ross, rachel);
		graph.addEdge(ross, ben);
		graph.addEdge(ben, ross);
		assertEquals(mra,graph.getGraph().sources(rachel));
		assertEquals(mro,graph.getGraph().sources(ross));
		assertEquals(mb,graph.getGraph().sources(ben));
		assertEquals(mk,graph.getGraph().sources(kramer));
	}

	@Test
	void testGetDistance() {
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross");
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		graph.addEdge(rachel, ross);
		graph.addEdge(ross, rachel);
		graph.addEdge(ross, ben);
		graph.addEdge(ben, ross);
		System.out.println(graph.getDistance(rachel, ross));
		//should print 1
		System.out.println(graph.getDistance(rachel, ben));
		//should print 2
		System.out.println(graph.getDistance(rachel, rachel));
		//should print 0
		System.out.println(graph.getDistance(rachel, kramer));
		//should print ‐1
		assertEquals(2,graph.getDistance(rachel, ben));
		assertEquals(1,graph.getDistance(rachel, ross));
		assertEquals(0,graph.getDistance(rachel, rachel));
		assertEquals(-1,graph.getDistance(rachel, kramer));
	}

}
