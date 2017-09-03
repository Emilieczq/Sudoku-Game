/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.comp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Zhenqi & Danyang
 */
public class SudokuBoard implements Serializable, Cloneable {

    private List<List<SudokuField>> board = Arrays.asList(new List[9]);

    public SudokuBoard() {
        for (int j = 0; j < 9; j++) {
            List<SudokuField> fields = Arrays.asList(new SudokuField[9]);
            for (int i = 0; i < 9; i++) {
                fields.set(i, new SudokuField());
            }
            board.set(j, fields);
        }
    }

    public void print() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board.get(i).get(j));
            }
            System.out.println();
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.board);
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SudokuBoard other = (SudokuBoard) obj;
        for (int i = 0; i < 9; i++) {
            if (!other.board.get(i).equals(this.board.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            str.append(board.get(i).toString());
        }
        return str.toString();
    }

    public int get(int x, int y) {
        return board.get(x).get(y).getValue();
    }

    public SudokuField getField(int x, int y) {
        return board.get(x).get(y);
    }

    public void set(int x, int y, int value) {
        board.get(x).get(y).setValue(value);
    }

    public SudokuRow getRow(int x) {
        ArrayList<SudokuField> fields = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            fields.add(board.get(x).get(i));
        }
        SudokuRow row = new SudokuRow(fields);
        return row;
    }

    public SudokuColumn getColumn(int y) {
        ArrayList<SudokuField> fields = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            fields.add(board.get(i).get(y));
        }
        SudokuColumn column = new SudokuColumn(fields);
        return column;
    }

    public SudokuBox getBox(int x, int y) {
        ArrayList<SudokuField> fields = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            fields.add(board.get((x / 3) * 3 + i / 3).get((y / 3) * 3 + i % 3));
        }
        SudokuBox box = new SudokuBox(fields);
        return box;
    }

    boolean checkBoard() {
        for (int i = 0; i < 9; i++) {
            if (!(getRow(i).verify() && getColumn(i).verify() && getBox((i / 3) * 3, (i % 3) * 3).verify())) {
                return false;
            }
        }
        return true;
    }

    public boolean checkAnswer() { //to modify
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.get(i).get(j).getValue() == 0) {
                    return false;
                }
            }
        }
        for (int i = 0; i < 9; i++) {
            if (!(getRow(i).verify() && getColumn(i).verify() && getBox((i / 3) * 3, (i % 3) * 3).verify())) {
                return false;
            }
        }
        return true;
    }

    public void copy(final SudokuBoard b) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board.get(i).get(j).setValue(b.get(i, j));
            }
        }
    }

    @Override
    public SudokuBoard clone() throws CloneNotSupportedException {

        super.clone();

        byte[] object;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);) {
            oos.writeObject(this);
            object = baos.toByteArray();
        } catch (IOException ioe) {
            System.out.println(ioe);
            return null;
        }

        try (ByteArrayInputStream bais = new ByteArrayInputStream(object);
                ObjectInputStream ois = new ObjectInputStream(bais);) {
            SudokuBoard clone = (SudokuBoard) ois.readObject();
            return (SudokuBoard) clone;
        } catch (IOException | ClassNotFoundException cnfe) {
            System.out.println(cnfe);
            return null;
        }
    }

}
