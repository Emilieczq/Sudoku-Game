/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.comp.dao;

import java.io.IOException;

/**
 *
 * @author Zhenqi & Danyang
 * @param <SudokuBoard>
 */ 
public interface Dao<SudokuBoard> extends AutoCloseable{
    SudokuBoard read()throws DaoException;
    void write(SudokuBoard board) throws DaoException;
}
