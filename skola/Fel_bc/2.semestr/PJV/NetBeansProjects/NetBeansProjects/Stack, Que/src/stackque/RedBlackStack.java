/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package stackque;

/**
 *
 * @author já
 */
public class RedBlackStack {
  private Stack2 redStack=new Stack2();
  private Stack2 blackStack=new Stack2();

  public Integer popRed(){
    return redStack.pop();
  }


  public Integer popBlack(){
    return blackStack.pop();
  }

  public void pushRed(int e){
    redStack.push(e);
  }

  public void pushBlack(int e){
    blackStack.push(e);
  }


}
