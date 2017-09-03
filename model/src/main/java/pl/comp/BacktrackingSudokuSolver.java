/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.comp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Zhenqi & Danyang
 */
public class BacktrackingSudokuSolver implements SudokuSolver {

    private final List<Integer> list = new ArrayList<>();

    @Override
    public boolean solve(final SudokuBoard board) {
        for (int i = 1; i < 10; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        fillBoard(board, 0, 0);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.get(i, j) == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void print(final SudokuBoard board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board.get(i, j));
            }
            System.out.println();
        }
    }

    private boolean fillBoard(final SudokuBoard board, int row, int col) {
        if (row == 9) {
            return true;
        }
        if (board.get(row, col) != 0) {
            if (col == 8) {
                if (fillBoard(board, row + 1, 0)) {
                    return true;
                }
            } else {
                if (fillBoard(board, row, col + 1)) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < 9; i++) {
                board.set(row, col, list.get(i));
                if (board.checkBoard()) {
                    if (col == 8) {
                        if (fillBoard(board, row + 1, 0)) {
                            return true;
                        }
                    } else {
                        if (fillBoard(board, row, col + 1)) {
                            return true;
                        }
                    }
                }
            }
        }
        board.set(row, col, 0);
        return false;
    }
}
