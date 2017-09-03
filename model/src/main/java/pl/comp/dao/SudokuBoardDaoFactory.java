/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.comp.dao;

import java.io.IOException;
import java.io.Serializable;
import pl.comp.SudokuBoard;

/**
 *
 * @author Zhenqi
 */
public class SudokuBoardDaoFactory implements Serializable {

    private SudokuBoardDaoFactory() {

    }

    public static Dao<SudokuBoard> getFileDao(final String Filename) throws IOException {
        FileSudokuBoardDao dao = new FileSudokuBoardDao(Filename);
        return dao;
    }

}
