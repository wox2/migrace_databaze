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
 * @author wox2
 */
public class Row implements Serializable{

    private ArrayList<Float> array;

    /**
     *
     * Kontruktor vytvarejici instanci Row delky length.
     * @param length
     */

    public Row(int length) {
        array = new ArrayList<Float>(length);
        for (int j = 0; j < length; j++) {
            array.add(0.0F);
        }
    }

    /**
     * Kontruktor vytvarejici instanci Row s obsahem fields.
     * @param fields
     */
    public Row(ArrayList<Float> fields){
        this(fields.size());
        setArray(fields);
    }


    /**
     * Nastavuje pole radku s danym indexem jako dany element.
     * @param index
     * @param element
     */

    public void setField(int index, float element) {
        getArray().set(index, element);
    }

    /**
     * Navraci delku radku
     * @return
     */
    public int getLength() {
        return getArray().size();
    }

    /** Navraci ArrayList s "obsahem instance".
     * @return the array
     */
    public ArrayList<Float> getArray() {
        return array;
    }

    /** Nastavi array jako "obsah" radku.
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

    /**
     * Secte instanci, z ktere se vola s parametrem addedRow a vysledek vrati.
     * @param addedRow
     * @return
     */
    public Row add(Row addedRow) {
        Row result=new Row(addedRow.getLength());
        for (int i = 0; i < array.size(); i++) {
            result.array.set(i, array.get(i) + addedRow.array.get(i));
        }
        return result;
    }

    /**
     * Odecte od instance, z ktere se vola parametr subtracted Row a vysledek vrati.
     * @param substractedRow
     * @return
     */
    public Row subtract(Row substractedRow) {
        Row result=new Row(substractedRow.getLength());
        for (int i = 0; i < array.size(); i++) {
            result.array.set(i, array.get(i) - substractedRow.array.get(i));
        }
        return result;
    }

    /**
     * Vrati pole s danym indexem.
     * @param index
     * @return
     */
    public float getField(int index) {
        return array.get(index);
    }

    /**
     * Vrati skalarni soucin dvou radku, nekontroluje stejnou delku radku.
     * @param row
     * @return
     */
    public float scalarMultiply(Row row) {
        float multiply = 0.0F;
        for (int i = 0; i < this.getLength(); i++) {
            multiply += this.getField(i) * row.getField(i);
        }
        return multiply;
    }

    /**
     * Vrati true, kdyz je radek nulovy, jinak false.
     * @return
     */
    public boolean isZero() {
        Iterator it = getArray().iterator();
        while (it.hasNext()) {
            if (it.next().equals(0.0F)!=true) {
                return false;
            }
        }
        return true; 
    }

    /**
     * Prida nulovy prvek na konec radku.
     */
    public void addField(){
         array.add(0.0F);
    }

    /**
     * Odstrani field s danym indexem a navraci ho.
     * @param index
     * @return
     */
    public float removeField(int index){
       return array.remove(index);
    }

    /**
     *
     */
    public Row multiply(float multiplicator){
       ArrayList<Float> newArray=new ArrayList<Float>(0);
        for(int index=0;index<array.size();index++){
       newArray.add(multiplicator*this.getField(index));
       }
       return new Row(newArray);
    }

    /**
     *  Udela kopii radku.
     */
    public Row copy(){
        Row copy=new Row(this.getLength());
       for(int i=0;i<this.getLength();i++){
          copy.setField(i, this.getField(i));
       }
       return copy;
    }
}