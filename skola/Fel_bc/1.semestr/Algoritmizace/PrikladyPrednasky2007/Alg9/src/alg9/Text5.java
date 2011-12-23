package alg9;

import java.io.*;


public class Text5 {
  public static void main(String[] args) throws Exception {
    StreamTokenizer st = new StreamTokenizer(new FileReader("text5.txt"));
    st.eolIsSignificant(true);
    for (;;) {
      switch (st.nextToken()) {
        case StreamTokenizer.TT_NUMBER:
          System.out.print(st.nval+" "); break;
        case StreamTokenizer.TT_WORD:
          System.out.print(st.sval+" "); break;
        case StreamTokenizer.TT_EOF:
          return;
        case StreamTokenizer.TT_EOL:
          System.out.println(); break;
      }
    }
  }
}