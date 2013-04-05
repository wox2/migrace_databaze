/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Exceptions;

/**
 *
 * @author woxie
 */
public class GraphGeneratingException extends Exception{
    String message;
    public GraphGeneratingException(String warning){
        message = warning;
    }
}
