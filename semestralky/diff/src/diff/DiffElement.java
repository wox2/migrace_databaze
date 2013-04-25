/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diff;

/**
 *
 * @author woxie
 */
    public class DiffElement{
        /** index of change - changes are indexed by the original file - eg 
         *  first parameter**/
        public final int index;
        public final int fileIndex;
        public final String change;
        
        public DiffElement(int index, int fileIndex, String change){
            this.index = index;
            this.fileIndex = fileIndex;
            this.change = change;
        }
        
        public String toString(){
            return "i:" +index + " f:" + fileIndex + " ch:" + change; 
        }
    }