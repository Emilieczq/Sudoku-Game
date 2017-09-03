/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.comp.dao;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import pl.comp.BacktrackingSudokuSolver;
import pl.comp.Game;
import pl.comp.Levels;
import pl.comp.SudokuBoard;

/**
 *
 * @author dell
 */
public class FileSudokuBoardDaoTest {

    public FileSudokuBoardDaoTest() {
    }

//    @Test
//    public void testRead(){
//        FileSudokuBoardDao file = new FileSudokuBoardDao("");
//        
//        
//        
//    }
    @Test
    public void testWrite() throws DaoException, IOException {
        FileSudokuBoardDao file = new FileSudokuBoardDao("test.txt");
        SudokuBoard board = new SudokuBoard();
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(board);
        file.write(board);
        SudokuBoard read = file.read();
        assertEquals(read,board);
    }
}
