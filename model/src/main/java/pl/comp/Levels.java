/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.comp;

import java.util.Random;

/**
 *
 * @author Zhenqi & Danyangr
 */
public enum Levels {
    EASY(50),
    MEDIUM(60),
    HARD(70);
    Random r = new Random();

    private int number;

    Levels(int n) {
        this.number = n;
    }

    public void digHoles(final SudokuBoard board) {
        
        for (int i = 0; i < number; i++) {
            //dig a hole
            int index = r.nextInt(81); //random a number of index for digging a hole
            if (board.get(index / 9, index % 9) != 0) {

                board.set(index / 9, index % 9, 0);
            } else { //if the case of that index is 0, it should change another index
                i--;
            }
        }
    }
}
