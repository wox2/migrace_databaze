package javaapplication36;

public class Stack {
    int[] contents = new int[5];
    int count = 0;
    StackListener listener;

    public void setListener(StackListener l) {
        listener = l;
    }

    public void push(int element) {
        if ( count == contents.length) {
            int[] newContents = new int[contents.length*2];
            for ( int i = 0; i < contents.length; i++)
                newContents[i] = contents[i];
            contents = newContents;
        }
        contents[count++] = element;
        listener.elementPushed();
    }

    public int pop() throws StackEmptyException {
        if ( count == 0) throw new StackEmptyException();
        int result = contents[--count];
        listener.elementPopped();
        return result;
    }
}
