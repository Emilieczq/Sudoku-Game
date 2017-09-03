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
import java.util.Objects;

/**
 *
 * @author Zhenqi & Danyang
 */
public abstract class SudokuElements implements Serializable, Cloneable{

    private ArrayList<SudokuField> elements = new ArrayList<>();

    SudokuElements(final ArrayList<SudokuField> elements) {
        for (int i = 0; i < 9; i++) {
            this.elements.add(i, elements.get(i));
        }
    }

    public boolean verify() {
        int[] numbers = new int[10];
        for (int i = 0; i < 9; i++) {
            if (numbers[elements.get(i).getValue()] != 0) {
                return false;
            }
            numbers[elements.get(i).getValue()] = elements.get(i).getValue();
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (SudokuField item : elements) {
            str.append(item.getValue()).append(" ");
        }
        return str.toString();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.elements);
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
        final SudokuElements other = (SudokuElements) obj;
        for (int i = 0; i < 9; i++) {
            if (!other.elements.get(i).equals(this.elements.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public SudokuElements clone() throws CloneNotSupportedException {
        
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
            SudokuElements clone = (SudokuElements) ois.readObject();
            return (SudokuElements) clone;
        } catch (IOException | ClassNotFoundException cnfe) {
            System.out.println(cnfe);
            return null;
        }    
    }
}
