/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author já
 */
public class RowTest {

    public RowTest() {
    }

    @Test
    public void testConstructor() {
        Row example = new Row(0);
        try {
            assertEquals(example.getField(0), 0.0F, 0.0);
        } catch (IndexOutOfBoundsException e) {
            assertFalse(example.getLength() != 0);
        }
        example = new Row(12);
        for (int i = example.getLength() - 1; i > -1; i--) {
            assertTrue(example.removeField(i) == 0.0F);
        }
        example = new Row(12);
        example.setField(7, 2.0F);
        for (int i = example.getLength() - 1; i > -1; i--) {
            if (i != 7) {
                assertTrue(example.removeField(i) == 0.0F);
            }
        }
        assertTrue(example.getField(0)==2.0F);
        assertFalse(example.removeField(0) == 0.0F);
    }

    /**
     * Test of setField method, of class Row.
     */
    @Test
    public void testSetField() {
        System.out.println("setField");
        int index = 0;
        float element = 1.3F;
        Row instance = new Row(3);
        instance.setField(index, element);
        assertEquals(element, instance.getField(index), 0.0F);
    }

    /**
     * Test of getLength method, of class Row.
     */
    @Test
    public void testGetLength() {
        System.out.println("getLength");
        int expResult = 0;
        Row instance = new Row(expResult);
        int result = instance.getLength();
        assertEquals(expResult, result);

        expResult = 55;
        instance = new Row(expResult);
        result = instance.getLength();
        assertEquals(expResult, result);

        try {
            expResult = -1;
            instance = new Row(expResult);
            result = instance.getLength();
        } catch (IllegalArgumentException e) {
            assertTrue(true);
            System.out.println("yes");
        }
    }

    /**
     * Test of setArray method, of class Row.
     */
    @Test
    public void testSetArray() {
        System.out.println("setArray");
        ArrayList<Float> expected = new ArrayList<Float>(0);
        expected.add(1.2F);
        expected.add(2.3F);
        expected.add(3.4F);
        Row instance = new Row(2);
        instance.setArray(expected);
        assertEquals(expected, instance.getArray());
    }

    /**
     * Test of add method, of class Row.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Row expected = new Row(3);
        Row instance = new Row(3);
        ArrayList<Float> settedArray = new ArrayList<Float>(0);
        settedArray.add(1.0F);
        settedArray.add(1.0F);
        settedArray.add(1.0F);
        expected.setArray(settedArray);
        instance=instance.add(expected);
        assertEquals(expected.getArray(), instance.getArray());

        instance = new Row(0);
        assertNotSame(expected.getArray(), instance.getArray());
    }

    /**
     * Test of subtract method, of class Row.
     */
    @Test
    public void testSubtract() {
        System.out.println("subtract");
        Row instance = new Row(3);
        Row expected = new Row(3);
        Row empty = new Row(3);
        ArrayList<Float> settedArray = new ArrayList<Float>();
        settedArray.add(1.0F);
        settedArray.add(1.0F);
        settedArray.add(1.0F);
        instance.setArray(settedArray);
        expected.setArray(settedArray);
        instance=instance.subtract(instance);
        assertEquals(empty.getArray(), instance.getArray());
        empty = new Row(0);
        assertNotSame(empty.getArray(), instance.getArray());
        settedArray.clear();
        settedArray.add(2.0F);
        settedArray.add(5.5F);
        settedArray.add(2.3F);
        instance.setArray(settedArray);
        instance=instance.subtract(expected);
        settedArray.clear();
        settedArray.add(1.0F);
        settedArray.add(4.5F);
        settedArray.add(1.3F);
        expected.setArray(settedArray);
        assertEquals(expected.getArray(), instance.getArray());

    }

    /**
     * Test of getField method, of class Row.
     */
    @Test
    public void testGetField() {
        System.out.println("getField");
        int index = 0;
        Row instance = new Row(2);
        float expResult = 0.0F;
        float result = instance.getField(index);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of scalarMultiply method, of class Row.
     */
    @Test
    public void testScalarMultiply() {
        System.out.println("scalarMultiply");
        Row row = new Row(3);
        Row instance = new Row(3);
        ArrayList<Float> settedArray = new ArrayList<Float>(0);
        settedArray.add(1.0F);
        settedArray.add(1.0F);
        settedArray.add(1.0F);
        row.setArray(settedArray);
        float expResult = 0.0F;
        float result = instance.scalarMultiply(row);
        assertEquals(expResult, result, 0.001F);
        settedArray.clear();
        settedArray.add(1.212356F);
        settedArray.add(1.321345678F);
        settedArray.add(1.4F);
        row.setArray(settedArray);
        result = row.scalarMultiply(row);
        expResult = (float) (1.212356 * 1.212356 + 1.321345678 * 1.321345678 + 1.4 * 1.4);
        assertEquals(expResult, result, 0.001);
    }

    /**
     * Test of isZero method, of class Row.
     */
    @Test
    public void testIsZero() {
        System.out.println("isZero");
        Row instance = new Row(5);
        boolean expResult = true;
        boolean result = instance.isZero();
        assertEquals(expResult, result);
    }

    /**
     * Test of addField method, of class Row.
     */
    @Test
    public void testAddField() {
        System.out.println("addField");
        Row instance = new Row(1);
        instance.addField();
        Row expected = new Row(2);
        assertEquals(expected.getArray(), instance.getArray());
    }

    /**
     * Test of removeField method, of class Row.
     */
    @Test
    public void testRemoveField() {
        System.out.println("removeField");
        Row instance = new Row(2);
        ArrayList<Float> expected = new ArrayList(0);
        expected.add(3.14F);
        expected.add(9.99F);
        expected.add(0.121F);
        instance.setArray(expected);
        instance.removeField(2);
        expected.remove(2);
        assertEquals(expected, instance.getArray());
    }

}