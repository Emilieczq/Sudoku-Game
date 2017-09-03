/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.comp;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Zhenqi & Danyang
 */
public class BacktrackingSudokuSolverTest {

    public BacktrackingSudokuSolverTest() {
    }

    @Test
    public void testSolve() {

        BacktrackingSudokuSolver s = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard();
        s.solve(board);
        s.print(board);
        
        assertTrue(board.checkAnswer());
    }
}
