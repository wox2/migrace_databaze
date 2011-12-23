/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author já
 */
public class Row implements Serializable{

    private ArrayList<Float> array;

    public Row(int i) {
        array = new ArrayList<Float>(i);
        for (int j = 0; j < i; j++) {
            array.add(0.0F);
        }
    }

    public Row(ArrayList<Float> fields){
        this(fields.size());
        setArray(fields);
    }



    public void setField(int index, float element) {
        getArray().set(index, element);
    }

    public int getLength() {
        return getArray().size();
    }

    /**
     * @return the array
     */
    public ArrayList<Float> getArray() {
        return array;
    }

    /**
     * @param array the array to set
     */
    public void setArray(ArrayList<Float> settedArray) {
        ArrayList<Float> a=new ArrayList<Float>(0);
        array=a;
        Iterator i=settedArray.iterator();
        while (i.hasNext()){
            
            array.add((Float)i.next());
        }
    }

    public Row add(Row addedRow) {
        Row result=new Row(addedRow.getLength());
        for (int i = 0; i < array.size(); i++) {
            result.array.set(i, array.get(i) + addedRow.array.get(i));
        }
        return result;
    }

    public Row subtract(Row substractedRow) {
        Row result=new Row(substractedRow.getLength());
        for (int i = 0; i < array.size(); i++) {
            result.array.set(i, array.get(i) - substractedRow.array.get(i));
        }
        return result;
    }

    public float getField(int index) {
        return array.get(index);
    }

    public float scalarMultiply(Row row) {
        float multiply = 0.0F;
        for (int i = 0; i < this.getLength(); i++) {
            multiply += this.getField(i) * row.getField(i);
        }
        return multiply;
    }

    public boolean isZero() {
        Iterator it = getArray().iterator();
        while (it.hasNext()) {
            if (it.next().equals(0.0F)!=true) {
                return false;
            }
        }
        return true; 
    }

    public void addField(){
         array.add(0.0F);
    }

    public float removeField(int index){
       return array.remove(index);
    }

    public Row multiply(float multiplicator){
       ArrayList<Float> newArray=new ArrayList<Float>(0);
        for(int index=0;index<array.size();index++){
       newArray.add(multiplicator*this.getField(index));
       }
       return new Row(newArray);
    }

    public Row copy(){
        Row copy=new Row(this.getLength());
       for(int i=0;i<this.getLength();i++){
          copy.setField(i, this.getField(i));
       }
       return copy;
    }
}