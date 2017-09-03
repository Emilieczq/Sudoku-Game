/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.comp.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.comp.SudokuBoard;

/**
 *
 * @author Zhenqi
 */
public class FileSudokuBoardDao implements AutoCloseable, Dao<SudokuBoard> {
    
    private ObjectInputStream ois;
    private FileInputStream fis;
    private ObjectOutputStream oos;
    private FileOutputStream fos;

    public FileSudokuBoardDao(final String filename) throws IOException {
        fos = new FileOutputStream(filename);
        oos = new ObjectOutputStream(fos);
        fis = new FileInputStream(filename);
        ois = new ObjectInputStream(fis);
    }

   @Override
    public SudokuBoard read() throws DaoException {
        try {
            return (SudokuBoard) ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(FileSudokuBoardDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FileSudokuBoardDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }

    @Override
    public void write(final SudokuBoard obj) throws DaoException {
        try {
            oos.writeObject(obj);
        } catch (IOException ex) {
            Logger.getLogger(FileSudokuBoardDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }

    @Override
    public void finalize() throws Exception {
        close();
    }

    @Override
    public void close() throws Exception {
        if (oos != null) {
            oos.close();
        }
        if (ois != null) {
            ois.close();
        }

    }
}
