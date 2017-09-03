/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.comp.dao;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import pl.comp.Game;
import pl.comp.Levels;
import pl.comp.SudokuBoard;

/**
 *
 * @author Zhenqi
 */
public class JdbcSudokuBoardDaoTest {

    public JdbcSudokuBoardDaoTest() {
    }

    @Test
    public void testWrite() throws CloneNotSupportedException, DaoException {
        System.out.println("------------------Test write()----------------");
        JdbcSudokuBoardDao dao = new JdbcSudokuBoardDao();
        dao.setGameName("test"); //name should be unique
        Game game = new Game();
        game.setLevel(Levels.MEDIUM);
        game.makePizzle();
        SudokuBoard board = game.getBoard();
        board.print();
        dao.write(board);
    }

    @Test
    public void testRead() throws CloneNotSupportedException {
        System.out.println("----------------Test Read()------------------");
        JdbcSudokuBoardDao dao = new JdbcSudokuBoardDao();
        dao.setGameName("test"); //choose one existed name
        dao.read().print();
    }

    @Test
    public void testExist() {
        System.out.println("----------------Test gameExist()------------------");
        JdbcSudokuBoardDao dao = new JdbcSudokuBoardDao();
        System.out.println(dao.gameExists("test"));
    }

}
