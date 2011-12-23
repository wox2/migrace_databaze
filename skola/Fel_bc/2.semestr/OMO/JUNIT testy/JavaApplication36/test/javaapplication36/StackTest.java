package javaapplication36;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StackTest {

    @Test
    public void testPush() throws StackEmptyException {
        Stack s = new Stack();
        s.push(5);
        assertEquals( 5, s.pop());
    }

    @Test
    public void testPushTwoElements() throws StackEmptyException {
        Stack s = new Stack();
        s.push(5);
        s.push(3);
        assertEquals( 3, s.pop());
        assertEquals( 5, s.pop());
    }

    @Test
    public void testPopFromEmptyStack() {
        Stack s = new Stack();
        try {
          s.pop();
          fail();
        }
        catch (StackEmptyException e) {}
    }

    @Test
    public void testPushSixElemets() {
        Stack s = new Stack();
        s.push(1);
        s.push(1);
        s.push(1);
        s.push(1);
        s.push(1);
        s.push(1);
    }

    boolean elementPopppedCalled = false;
    @Test
    public void testListener() throws StackEmptyException {
        Stack s = new Stack();
        s.setListener( new StackListener() {
            public void elementPushed() {}
            public void elementPopped() {
                elementPopppedCalled = true;
            }
        });
        s.push(1);
        s.pop();
        assertTrue( elementPopppedCalled);
    }

}