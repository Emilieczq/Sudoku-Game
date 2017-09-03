/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.comp;

import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;

/**
 *
 * @author Zhenqi & Danyang
 */
public class SudokuField implements Serializable, Cloneable, Comparable<SudokuField> {

    private int value;

    public SudokuField() {
        value = 0;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.value;
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
        final SudokuField other = (SudokuField) obj;
        if (this.value != other.value) {
            return false;
        }

        EqualsBuilder builder = new EqualsBuilder();
        builder.append(this.value, other.value).append(this.value, other.value);
        return builder.isEquals();
    }

    @Override
    protected SudokuField clone() throws CloneNotSupportedException {

        return (SudokuField) super.clone();

    }

    @Override
    public int compareTo(final SudokuField o) {
        return this.value - o.value;
    }
}
