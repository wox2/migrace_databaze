/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package geometry;

/**
 *
 * @author já
 */
public class Square2 extends Rectangle{
public Square2(int x, int y, int width){super(x,y,width,width);}
public Square2(){this(0,0);}
public Square2(int x, int y){this(x,y,10);}
public Square2(int width){this(0,0,width);}
}
