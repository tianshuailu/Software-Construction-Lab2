/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {
    
    // Testing strategy
    //   通过assertEquals来测试输出字符串和期望是否一致
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    // TODO tests
    @Test
    public void testGraphPoet() throws IOException {
    	 final GraphPoet nimoy = new GraphPoet(new File("C:\\Users\\LTS\\workspace\\Lab2_1163710208\\src\\P1\\poet\\mugar-omni-theater.txt"));
         final String input = "Test the computer science system.";
         String s = "Test of the computer science system. ";
         System.out.println(input + "\n>>>\n" + nimoy.poem(input));
         assertEquals(s, nimoy.poem(input));
    }
}
