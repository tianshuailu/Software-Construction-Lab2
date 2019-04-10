/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;

import P1.graph.Graph;

/**
 * A graph-based poetry generator.
 * 
 * <p>GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph.
 * Vertices in the graph are words. Words are defined as non-empty
 * case-insensitive strings of non-space non-newline characters. They are
 * delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 * 
 * <p>For example, given this corpus:
 * <pre>    Hello, HELLO, hello, goodbye!    </pre>
 * <p>the graph would contain two edges:
 * <ul><li> ("hello,") -> ("hello,")   with weight 2
 *     <li> ("hello,") -> ("goodbye!") with weight 1 </ul>
 * <p>where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 * 
 * <p>Given an input string, GraphPoet generates a poem by attempting to
 * insert a bridge word between every adjacent pair of words in the input.
 * The bridge word between input words "w1" and "w2" will be some "b" such that
 * w1 -> b -> w2 is a two-edge-long path with maximum-weight weight among all
 * the two-edge-long paths from w1 to w2 in the affinity graph.
 * If there are no such paths, no bridge word is inserted.
 * In the output poem, input words retain their original case, while bridge
 * words are lower case. The whitespace between every word in the poem is a
 * single space.
 * 
 * <p>For example, given this corpus:
 * <pre>    This is a test of the Mugar Omni Theater sound system.    </pre>
 * <p>on this input:
 * <pre>    Test the system.    </pre>
 * <p>the output poem would be:
 * <pre>    Test of the system.    </pre>
 * 
 * <p>PS2 instructions: this is a required ADT class, and you MUST NOT weaken
 * the required specifications. However, you MAY strengthen the specifications
 * and you MAY add additional methods.
 * You MUST use Graph in your rep, but otherwise the implementation of this
 * class is up to you.
 */
public class GraphPoet {
    
    private final Graph<String> graph = Graph.empty();
    
    // Abstraction function:
    //   将语料库的内容存储为图，然后根据输入字符串生成诗
    // Representation invariant:
    //   graph用来存储语料库的内容所生成的图
    // Safety from rep exposure:
    //   该类的field均为private，因此不会泄露
    
    /**
     * Create a new poet with the graph from corpus (as described above).
     * 
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    public GraphPoet(File corpus) throws IOException {
    	BufferedReader reader = new BufferedReader(new FileReader(corpus));
    	String str = new String();
    	while((str = reader.readLine()) != null) {
    		String[] s = str.split(" ");
    		for(String string : s) {
    			string.toLowerCase();
    			graph.add(string);
    		}
    		int i = 0;
     	   	int j = 1;
     	   	while(j<s.length) {
     		   	int value = graph.set(s[i], s[j], 1);
     		   	if(value>0) {
     		   		graph.set(s[i], s[j], value+1);
     		   	}   	
     		   	i++;
     		   	j++;
     	   }
    	}
    	reader.close();
    }
    
    // TODO checkRep
    
    /**
     * Generate a poem.
     * 
     * @param input string from which to create the poem
     * @return poem (as described above)
     */
    public String poem(String input) {
        String[] str =  input.split(" ");
        String poem = "";
        String para = new String();
        int w = 0;
        ArrayList<String> list = new ArrayList<>();
        for(int i =0;i<str.length;i++)
        {
        	list.add(str[i]);
        }
        int j=0;
        for(int i=0;i<list.size()-1;i++) {
        	for(Entry<String, Integer> entry : graph.targets(list.get(i).toLowerCase()).entrySet()) {
        		if(graph.sources(list.get(i+1).toLowerCase()).containsKey(entry.getKey())) {
        			if(graph.targets(list.get(i).toLowerCase()).get(entry.getKey()) 
        					+ graph.sources(list.get(i+1).toLowerCase()).get(entry.getKey()) > w) {
        				para = entry.getKey();
        				w = graph.targets(list.get(i).toLowerCase()).get(entry.getKey()) 
        					+ graph.sources(list.get(i+1).toLowerCase()).get(entry.getKey());
        			}
        			list.add(i+j, para);
        			j++;
        		}
        	}
        }
        for(int i=1;i<list.size();i++) {
        	poem = poem + list.get(i) + " ";
        }
        return poem;
    }
    
    // TODO toString()
    
}
