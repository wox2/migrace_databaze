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
public class MatrixTest {

    public MatrixTest() {
    }

    @Test
    public void testEmptyConstructor() {
        Matrix instance = new Matrix();
        Row failRow = new Row(1);
        testEmptyMatrix(instance, 5, 5);
        instance = new Matrix(2, 3);
        testEmptyMatrix(instance, 2, 3);
        instance = new Matrix(4, 3);
        assertTrue(instance.getNumberRows() == 4);
        assertFalse(instance.getNumberRows() == 3);
        try {
            assertNotSame(failRow.getArray(), instance.removeRow().getArray());
        } catch (Exception e) {
            assertFalse(failRow.getArray().size() == instance.removeRow().getArray().size());
        }
        assertFalse(instance.getNumberRows() == 4);
        assertTrue(instance.getNumberColumns() == 3);
        assertTrue(instance.getNumberRows() == 2);

        instance = new Matrix(5);
        assertTrue(instance.getNumberColumns() == instance.getNumberRows());
        testEmptyMatrix(instance, 5, 5);
        assertTrue(instance.getNumberRows() == instance.getNumberRows());
    }

    /**
     * Test of setField method, of class Matrix.
     */
    public void testEmptyMatrix(Matrix instance, int expectedRows, int expectedCollumns) {
        assertTrue(instance.getNumberColumns() == expectedCollumns);
        assertTrue(instance.getNumberRows() == expectedRows);
        Row newRow = new Row(expectedCollumns);
        ArrayList<Float> expectedArray = newRow.getArray();
        while (instance.getNumberRows() > 0) {
            assertEquals(expectedArray, instance.removeRow().getArray());
        }

    }

    /**
     * Test of getField method, of class Matrix.
     */
    @Test
    public void testGetField() {
        System.out.println("getField");
        int rowPos = 0;
        int colPos = 0;
        Matrix instance = new Matrix(5);
        float expResult = 0.0F;
        testEquals(instance, expResult);
    }

    @Test
    public void testSetField() {
        System.out.println("setField");
        float[][] expArray = new float[][]{{2.33F, 2.4567F, 3456F}, {3567.3F, 456F, 335.3F}};
        Matrix instance = new Matrix(expArray.length, expArray[0].length);
        for (int i = 0; i < expArray.length; i++) {
            for (int j = 0; j < expArray[0].length; j++) {
                instance.setField(i, j, expArray[i][j]);
            }
        }
        for (int i = 0; i < expArray.length; i++) {
            for (int j = 0; j < expArray[0].length; j++) {
                assertTrue(expArray[i][j] == instance.getField(i, j));
                assertFalse(instance.getField(i, j) == 0.0F);
            }
        }
    }

    /**
     * Test of getNumberRows method, of class Matrix.
     */
    @Test
    public void testGetNumberRows() {
        System.out.println("getNumberRows");
        Matrix instance = new Matrix(0);
        int expResult = 0;
        int result = instance.getNumberRows();
        assertEquals(expResult, result);
        instance = new Matrix(5, 6);
        assertFalse(instance.getNumberRows() == 6);
        assertTrue(instance.getNumberRows() == 5);
    }

    /**
     * Test of getNumberColumns method, of class Matrix.
     */
    @Test
    public void testGetNumberColumns() {
        System.out.println("getNumberColumns");
        Matrix instance = new Matrix(0);
        int expResult = 0;
        int result = instance.getNumberColumns();
        assertEquals(expResult, result);
        instance = new Matrix(5, 6);
        assertFalse(instance.getNumberColumns() == 5);
        assertTrue(instance.getNumberColumns() == 6);
        instance = new Matrix(0, 5);
        assertTrue(instance.getNumberColumns() == 0);
        instance = new Matrix(-1, 3);
        assertFalse(instance.getNumberColumns() == -1);
        assertFalse(instance.getNumberColumns() == 3);
        assertTrue(instance.getNumberColumns() == 0);

    }

    /**
     * Test of getAllRows method, of class Matrix.
     */
    @Test
    public void testGetAllRows() {
        System.out.println("getAllRows");
        Matrix instance = new Matrix(5);
        ArrayList<Row> expResult = new ArrayList<Row>(0);
        int i = 0;
        while (i < instance.getNumberRows()) {
            expResult.add(new Row(5));
            i++;
        }
        assertTrue(expResult.size() == instance.getNumberRows());
        for (; i > 0; i--) {
            assertEquals(expResult.get(i - 1).getArray(), instance.getAllRows().get(i - 1).getArray());
        }
    }

    /**
     * Test of fill method, of class Matrix.
     */
    @Test
    public void testFill() {
        System.out.println("fill");
        float[][] array = new float[][]{{2.3F, 334.F, 334455F}, {2.3F, 333.F, 333567.F}};
        Matrix instance = new Matrix();
        instance.fill(array);
        assertNotSame(instance.getNumberColumns(), 5);
        assertNotSame(instance.getNumberRows(), 5);
        assertEquals(instance.getNumberColumns(), 3);
        assertEquals(instance.getNumberRows(), 2);
        testEquals(instance, array);
        try {
            assertFalse(instance.getField(5, 5) == 0.0F);
        } catch (Exception e) {
        }
    }

    /**
     * Test of add method, of class Matrix.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Matrix zeroMatrix = new Matrix(3);
        Matrix expResult = new Matrix();
        float[][] array1 = new float[][]{{1.0F, 1.0F, 1.0F}, {1.0F, 1.0F, 1.0F}, {1.0F, 1.0F, 1.0F}};
        expResult.fill(array1);
        Matrix instance = expResult.add(zeroMatrix);
        testEquals(instance, expResult);
        float[][] array = new float[][]{{-1.0F, -1.0F, -1.0F}, {-1.0F, -1.0F, -1.0F}, {-1.0F, -1.0F, -1.0F}};
        instance.fill(array);
        expResult = instance.add(expResult);
        testEquals(zeroMatrix, expResult);
        expResult = expResult.add(instance);
        testEquals(instance, expResult);
        float[][] array2 = new float[][]{{-2.1F, -2.1F, -2.1F}, {-1.0F, -1.0F, -1.0F}, {-1.0F, -1.0F, -1.0F}};
        instance.fill(array);
        expResult.fill(array2);
        expResult = expResult.add(instance);
        float[][] array4 = new float[][]{{-3.1F, -3.1F, -3.1F}, {-2.0F, -2.0F, -2.0F}, {-2.0F, -2.0F, -2.0F}};
        testEquals(expResult, array4);
        instance.fill(array1);
        expResult = expResult.add(instance);
        testEquals(expResult, array2);
        expResult.add(instance);
        float[][] array5 = new float[][]{{-1.1F, -1.1F, -1.1F}, {0.0F, 0.0F, 0.0F}, {0.0F, 0.0F, 0.0F}};
        testEquals(expResult, array5);
        try {}catch (Exception e){}
    }

    /**
     * Test of subtract method, of class Matrix.
     */
    @Test
    public void testSubtract() {
        System.out.println("subtract");
        Matrix zeroMatrix = new Matrix(3);
        Matrix instance = new Matrix();
        float[][] arrayOneRow = new float[][]{{-1.1F, -1.1F, -1.1F}, {0.0F, 0.0F, 0.0F}, {0.0F, 0.0F, 0.0F}};
        instance.fill(arrayOneRow);
        Matrix expResult = instance.subtract(zeroMatrix);
        testEquals(instance, expResult);
        expResult = zeroMatrix.subtract(zeroMatrix);
        testEquals(zeroMatrix, expResult);
        expResult = instance.subtract(instance);
        testEquals(zeroMatrix, expResult);
        expResult = zeroMatrix.subtract(instance);
        float[][] arrayPossitive = new float[][]{{1.1F, 1.1F, 1.1F}, {0.0F, 0.0F, 0.0F}, {0.0F, 0.0F, 0.0F}};
        testEquals(expResult, arrayPossitive);
    }

    /**
     * Test of transpose method, of class Matrix.
     */
    @Test
    public void testTranspose() {
        System.out.println("transpose");
        Matrix instance = new Matrix();
        Matrix expResult = instance.transpose();
        testEquals(instance, expResult);
        testEquals(expResult, expResult);
        float[][] array = new float[][]{{1F, 0F, 0F}, {0F, 1F, 0F}, {0F, 0F, 1F}};
        expResult.fill(array);
        instance = expResult.transpose();
        testEquals(expResult, instance);
        array = new float[][]{{1F, 1F, 1F}, {0F, 0F, 0F}, {0F, 0F, 0F}};
        instance.fill(array);
        expResult = instance.transpose();
        float[][] expectedArray = new float[][]{{1F, 0F, 0F}, {1F, 0F, 0F}, {1F, 0F, 0F}};
        testEquals(expResult, expectedArray);
        testEquals(expResult, expResult.transpose().transpose());
    }

    /**
     * Test of multiple method, of class Matrix.
     */
    @Test
    public void testMultiple() {
        System.out.println("multiple");
        Matrix matrix = new Matrix(3);
        testEquals(matrix, matrix.multiple(matrix));
        float[][] array = new float[][]{{1F, 0F, 0F}, {0F, 1F, 0F}, {0F, 0F, 1F}};
        Matrix matrixE = new Matrix();
        matrixE.fill(array);
        testEquals(matrix, matrixE.multiple(matrix));
        testEquals(matrixE, matrixE.multiple(matrixE));
        matrix.fill(new float[][]{{1.1F, 1.2F, 1.3F}, {1.1F, 1.2F, 1.3F}, {1.1F, 1.2F, 1.3F}});
        testEquals(matrix, matrix.multiple(matrixE));
        matrix.fill(new float[][]{{1F, 1F}, {1F, 2F}});
        float[][] expArray = new float[][]{{2F, 3F}, {3F, 5F}};
        testEquals(matrix.multiple(matrix), expArray);
    }

    /**
     * Test of addRow method, of class Matrix.
     */
    @Test
    public void testAddRow() {
        System.out.println("addRow");
        Matrix instance = new Matrix();
        assertEquals(5, instance.getNumberColumns());
        assertEquals(instance.getNumberRows(), instance.getNumberColumns());
        instance.addRow();
        assertFalse(instance.getNumberColumns() == instance.getNumberRows());
        assertEquals(instance.getNumberColumns(), 5);
        assertFalse(5 == instance.getNumberRows());
        assertEquals(6, instance.getNumberRows());
        instance.fill(new float[][]{{1.F, 1.F}, {2.F, 2.F}});
        instance.addRow();
        Matrix expResult = new Matrix();
        expResult.fill(new float[][]{{1.F, 1.F}, {2.F, 2.F}, {0F, 0F}});
        testEquals(instance, expResult);
    }

    /**
     * Test of addCollumn method, of class Matrix.
     */
    @Test
    public void testAddCollumn() {
        System.out.println("addCollumn");
        Matrix instance = new Matrix();
        assertEquals(5, instance.getNumberColumns());
        assertEquals(instance.getNumberRows(), instance.getNumberColumns());
        instance.addColumn();
        assertFalse(instance.getNumberColumns() == instance.getNumberRows());
        assertFalse(instance.getNumberColumns() == 5);
        assertEquals(5, instance.getNumberRows());
        assertFalse(6 == instance.getNumberRows());
        assertEquals(6, instance.getNumberColumns());
        instance.fill(new float[][]{{1.F, 1.F}, {2.F, 2.F}});
        instance.addColumn();
        Matrix expResult = new Matrix();
        expResult.fill(new float[][]{{1.F, 1.F, 0.F}, {2.F, 2.F, 0.F}});
        testEquals(instance, expResult);
        instance.addRow();
        expResult.fill(new float[][]{{1.F, 1.F, 0.F}, {2.F, 2.F, 0.F},{0F,0F,0F}});
        testEquals(instance, expResult);
    }

    /**
     * Test of removeRow method, of class Matrix.
     */
    @Test
    public void testRemoveRow() {
        System.out.println("removeRow");
        Matrix instance = new Matrix();
        assertEquals(5, instance.getNumberColumns());
        assertEquals(instance.getNumberRows(), instance.getNumberColumns());
        instance.removeRow();
        assertFalse(instance.getNumberColumns() == instance.getNumberRows());
        assertEquals(instance.getNumberColumns(), 5);
        assertFalse(5 == instance.getNumberRows());
        assertEquals(4, instance.getNumberRows());
        assertFalse(4 == instance.getNumberColumns());
        instance.fill(new float[][]{{1.F, 1.F}, {2.F, 2.F}});
        instance.removeRow();
        Matrix expResult = new Matrix();
        expResult.fill(new float[][]{{1.F, 1.F}});
        testEquals(instance, expResult);
        expResult.addRow();
        expResult.removeRow();
        testEquals(instance, expResult);
    }

    /**
     * Test of removeCollumn method, of class Matrix.
     */
    @Test
    public void testRemoveCollumn() {
        System.out.println("removeCollumn");
        Matrix instance = new Matrix();
        assertEquals(5, instance.getNumberColumns());
        assertEquals(instance.getNumberRows(), instance.getNumberColumns());
        instance.removeCollumn();
        assertFalse(instance.getNumberColumns() == instance.getNumberRows());
        assertFalse(instance.getNumberColumns() == 5);
        assertEquals(5, instance.getNumberRows());
        assertFalse(4 == instance.getNumberRows());
        assertEquals(4, instance.getNumberColumns());
        instance.fill(new float[][]{{1.F, 1.F}, {2.F, 2.F}});
        assertEquals(instance.getNumberColumns(), 2);
        instance.removeCollumn();
        assertEquals(instance.getNumberColumns(), 1);
        Matrix expResult = new Matrix();
        expResult.fill(new float[][]{{1.F}, {2.F}});
        testEquals(instance, expResult);
        expResult.addColumn();
        assertEquals(expResult.getNumberColumns(), expResult.getNumberRows());
        assertEquals(expResult.getNumberColumns(), 2);
        expResult.removeCollumn();
        testEquals(instance, expResult);
    }

    /**
     * Test of setRow method, of class Matrix.
     */
    @Test
    public void testSetRow() {
        System.out.println("setRow");
        Matrix instance = new Matrix(0);
        int index = 0;
        Row row = new Row(3);
        float[] rowArray = new float[]{1.2F, 1.3F, 3.12F};
        for (int i = 0; i < rowArray.length; i++) {
            row.setField(i, rowArray[i]);
        }
        try {
            instance.setRow(index, row);
        } catch (Exception e) {
            index = 1;
        }
        assertFalse(index == 0);
        assertEquals(index, 1);
        example(instance, row);
    }

    public static Matrix example(Matrix instance, Row row) {
        float[] rowArray = new float[row.getLength()];
        for (int i = 0; i < row.getLength(); i++) {
            rowArray[i] = row.getField(i);
        }
        instance = new Matrix(row.getLength());
        float[][] array2D = new float[row.getLength()][row.getLength()];
        for (int index = 0; index < row.getLength(); index++) {
            instance.setRow(index, row);
            array2D[index] = rowArray;
            testEquals(instance, array2D);
        }
        return instance;
    }

    /**
     * Test of getRow method, of class Matrix.
     */
    @Test
    public void testGetRow() {
        System.out.println("getRow");
        Row row = new Row(4);
        for (int i = 0; i < row.getLength(); i++) {
            row.setField(i, i);
        }
        Matrix instance = new Matrix();
        example(instance, row);
        Matrix matrix2 = new Matrix(1, 4);
        matrix2.setRow(0, row);
        assertEquals(row, matrix2.getRow(0));
    }

    /**
     * Test of multipleByConstant method, of class Matrix.
     */
    @Test
    public void testMultipleByConstant() {
        System.out.println("multipleByConstant");
        float constant = 0.0F;
        Matrix instance = new Matrix();
        testEquals(instance.multipleByConstant(constant), instance);
        testEquals(instance.multipleByConstant(3.34567F), instance);
        float[] rowArray = new float[]{1.2F, 1.3F, 3.12F};
        Row row = new Row(rowArray.length);
        for (int i = 0; i < row.getLength(); i++) {
            row.setField(i, rowArray[i]);
        }
        Matrix example=example(instance, row);
        testEquals(example.multipleByConstant(constant), new Matrix(3));
        testEquals(example.multipleByConstant(1.F), example);

        Matrix expResult = null;
        Matrix result = instance.multipleByConstant(constant);
        int fixed=0;
        try {testEquals(expResult, result);} catch (Exception e){fixed++;}
        assertEquals(fixed, 1);

    }

    public static void testEquals(Matrix matrix, float value) {
        for (int i = 0; i < matrix.getNumberRows(); i++) {
            for (int j = 0; j < matrix.getNumberColumns(); j++) {
                assertEquals(matrix.getField(i, j), value, 0.01F);
            }
        }
    }

    public static void testEquals(Matrix firstMatrix, Matrix secondMatrix) {
        assertTrue(firstMatrix.getNumberColumns() == secondMatrix.getNumberColumns());
        assertTrue(firstMatrix.getNumberRows() == secondMatrix.getNumberRows());
        for (int i = 0; i < firstMatrix.getNumberRows(); i++) {
            for (int j = 0; j < firstMatrix.getNumberColumns(); j++) {
                assertEquals(firstMatrix.getField(i, j), secondMatrix.getField(i, j), 0.01F);
            }
        }
    }

    public static void testEquals(Matrix matrix, float[][] field) {
        assertTrue(matrix.getNumberColumns() == field[0].length);
        assertTrue(matrix.getNumberRows() == field.length);
        for (int i = 0; i < matrix.getNumberRows(); i++) {
            for (int j = 0; j < matrix.getNumberColumns(); j++) {
                assertEquals(matrix.getField(i, j), field[i][j], 0.01F);
            }
        }
    }

    public void testToXML(){
        System.out.println("toXML - tested when used");
    }

    public void testToString(){
        System.out.println("toString - tested when used");
    }
}