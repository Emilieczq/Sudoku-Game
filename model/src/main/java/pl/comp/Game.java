/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.comp;


/**
 *
 * @author Zhenqi & Dangyang
 */
public class Game {

    private SudokuBoard board = new SudokuBoard();
    private SudokuBoard pizzle = new SudokuBoard();
    private Levels level = Levels.EASY;
    
    
    public Game() {
    }

    public void setLevel(final Levels level) {
        this.level = level;
        
    }

    public Levels getLevel() {
        return level;
    }

    public void makePizzle() throws CloneNotSupportedException {
        board = new SudokuBoard();
        BacktrackingSudokuSolver s = new BacktrackingSudokuSolver();
        s.solve(board);
        level.digHoles(board);
        pizzle = board.clone();
    }

    public void restart() throws CloneNotSupportedException {
        board.copy(pizzle);
    }

    public SudokuBoard getBoard() {
        return board;
    }

}
