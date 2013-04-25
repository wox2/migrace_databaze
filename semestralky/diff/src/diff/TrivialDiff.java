/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diff;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author woxie
 */
public class TrivialDiff {
    
    protected class TableCell{
        private int direction;
        private int stringLength;

        public TableCell(int direction, int stringLength) {
            this.direction = direction;
            this.stringLength = stringLength;
        }

    }
    
    /**
     *  Directions constants for reconstruction of String
     *  there are four directions, STOP - eg root of tree, LEFT, UP and CROSS
     *  LEFT + RIGHT can be merged and divided throught bit multiplication and 
     *  bit shift in traceback step, the directions reprezentations must 
     *  have different bits.
     */
    //** NULL String  **/
    private final int STOP = 0;
    /** Copy LEFT neighbour in table during traceback step **/
    private final int LEFT = 1;
    /** APPEND current symbol to neighbour end of diagonal neighbour in table 
     * during traceback step **/
    private final int DIAGONAL = 2;
    /** Copy UPper neighbour in table during traceback step **/
    private final int UP = 4;
    /**
     * Table of directions for backward tracking. Space efficient way to store information about Strings
     * See http://en.wikipedia.org/wiki/Longest_common_subsequence_problem for more infos
     * 
     */
    private TableCell [][] expandDirections;
    
    private String firstString;
    private String secondString;
    
    private Set<String> latestSubstrings;
    
    public TrivialDiff (String firstString, String secondString){
        this.firstString = firstString;
        this.secondString = secondString;
        this.latestSubstrings = new TreeSet<String>();
    }
    
    public Set<String> getLatestSubstrings(){
        if(latestSubstrings.size() > 0){
            return latestSubstrings;
        }
        initExpandArray();
        processAlgorithm();
        traceBack("", expandDirections.length - 1, expandDirections[0].length - 1);
        return latestSubstrings;
    }
    
    private void initExpandArray(){
        expandDirections = new TableCell [firstString.length() + 1][secondString.length() + 1];
        //expandDirections[0][0] = new TableCell(STOP,0);
        for(int i = 0 ; i < firstString.length() + 1 ; i++){
            for(int j = 0 ; j < secondString.length() + 1 ; j++){
                expandDirections[i][j] = new TableCell(STOP,0);
            }
        }
        
    }
    
    private void processAlgorithm(){
        for(int i = 1 ; i < firstString.length() + 1 ; i++){
            for(int j = 1 ; j < secondString.length() + 1 ; j++){
                if(firstString.charAt(i) == secondString.charAt(j)){
                    int newLength = expandDirections[i-1][j-1].stringLength + 1;
                    expandDirections[i][j].stringLength = newLength;
                    expandDirections[i][j].direction = DIAGONAL;    
                } else if(expandDirections[i-1][j].stringLength == 
                        expandDirections[i][j-1].stringLength){
                    expandDirections[i][j].direction = LEFT + UP;
                    expandDirections[i][j].stringLength = 
                            expandDirections[i][j-1].stringLength;
                } else if(expandDirections[i-1][j].stringLength >
                        expandDirections[i][j-1].stringLength){
                    expandDirections[i][j].direction = LEFT;
                    expandDirections[i][j].stringLength = 
                            expandDirections[i - 1][j].stringLength;
                } else {
                    expandDirections[i][j].direction = UP;
                    expandDirections[i][j].stringLength = 
                            expandDirections[i][j-1].stringLength;
                }
            }
        }
        printExpandTable();
    }
    
    public void printExpandTable(){
        if(expandDirections == null){
            System.out.println("Table not created yet");
            return;
        }
        for(int i = 0 ; i < expandDirections.length ; i++){
            for(int j = 0 ; j < expandDirections[0].length ; j++){
                System.out.print(expandDirections[i][j].stringLength + " ");
            }
            System.out.println();
        }
    }
    
    private void traceBack(String word, int rowIndex, int columnIndex){
        if(expandDirections[rowIndex][columnIndex].direction == STOP){
            latestSubstrings.add(word);
            return;
        } 
        if((expandDirections[rowIndex][columnIndex].direction & LEFT) == LEFT){
            traceBack(word, rowIndex-1, columnIndex);
        }
        if((expandDirections[rowIndex][columnIndex].direction & UP) == UP){
            traceBack(word, rowIndex, columnIndex-1);
        }
        if((expandDirections[rowIndex][columnIndex].direction & DIAGONAL) 
                == DIAGONAL){
            traceBack(firstString.charAt(rowIndex) + word, rowIndex-1, 
                    columnIndex-1);
        }       
    }
    
    //pridat vsechny indexy...sakra
    public List<String> getFullDiff(Boolean isEqualPartsShown){
        List<DiffElement> diffElements = getDiffElements();
        List<String> diff = new ArrayList<String>();
        
        List<String> firstFile = new ArrayList<String>();
        List<String> secondFile = new ArrayList<String>();
        int lastZeroIndex = 0;
        for(DiffElement element : diffElements){
            if(element.fileIndex == 0){
                if(!firstFile.isEmpty() && !secondFile.isEmpty()){
                    //lastZeroIndex + ta zmena - change...
                    StringBuilder builder = new StringBuilder("c");
                    builder.append(lastZeroIndex);
                    builder.append("\n");
                    for(String el : firstFile){
                        builder.append(el);
                    }
                    builder.append("----------------------------");
                    for(String el : secondFile){
                        builder.append(el);
                    }
                    builder.append("----------------------------");
                    diff.add(builder.toString());
                } else if(!firstFile.isEmpty() && secondFile.isEmpty()){
                    //remove
                } else if(firstFile.isEmpty() && !secondFile.isEmpty()){
                    //add
                }
                lastZeroIndex = element.index;
                firstFile = new ArrayList<String>();
                secondFile = new ArrayList<String>();
            } else if(element.fileIndex == 1){
                firstFile.add(element.change);
            } else {
                secondFile.add(element.change);
            }
        }
        
        return diff;
    }
    
    public List<DiffElement> getDiffElements(){
        initExpandArray();
        processAlgorithm();
        return getDiff(firstString.length(), secondString.length(),
                new ArrayList<DiffElement>());
    }
    
    public List<DiffElement> getPatch(){
        List<DiffElement> fullDiff = getDiffElements();
        return fullDiff;
    }
            
    private List<DiffElement> getDiff(int firstStringIndex, int secondStringIndex, 
            List<DiffElement> tempDiff){
            int realFirst = firstStringIndex - 1;
            int realSecond = secondStringIndex - 1;
         if (firstStringIndex > 0 && secondStringIndex > 0 && 
                 firstString.charAt(realFirst) == secondString.
                    charAt(realSecond)){
             tempDiff.add(new DiffElement(firstStringIndex, 
                     0, 
                     "" + firstString.charAt(realFirst)));
             return getDiff(firstStringIndex-1, secondStringIndex-1, tempDiff);
         } else if(secondStringIndex > 0 && (firstStringIndex == 0 || 
                 expandDirections[firstStringIndex][secondStringIndex-1].stringLength >= 
                 expandDirections[firstStringIndex-1][secondStringIndex].stringLength)){
             tempDiff.add(new DiffElement(firstStringIndex, 1, 
                     ""+ secondString.charAt(realSecond)));
             return getDiff(firstStringIndex, secondStringIndex-1, tempDiff);
         } else if(firstStringIndex > 0 && (secondStringIndex == 0 || 
                 expandDirections[firstStringIndex][secondStringIndex-1].stringLength < 
                 expandDirections[firstStringIndex-1][secondStringIndex].stringLength)){
             tempDiff.add(new DiffElement(firstStringIndex, 2,
                     "" + firstString.charAt(realFirst)));
             return getDiff(firstStringIndex-1, secondStringIndex, tempDiff);
         }
        return tempDiff;
    }
}
