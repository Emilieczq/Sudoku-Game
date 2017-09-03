/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.comp;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Zhenqi
 */
public class GameTest {
    
    public GameTest() {
    }
    

    @Test
    public void testGame() throws CloneNotSupportedException {
        Game game = new Game();
        System.out.println("-------Easy pizzle---------");
        game.setLevel(Levels.EASY);
        game.makePizzle();
        game.getBoard().print();
        System.out.println("-------Medium pizzle---------");
        game.setLevel(Levels.MEDIUM);
        game.makePizzle();
        System.out.println(game.getBoard().checkAnswer());
        game.getBoard().print();
        System.out.println("-------Hard pizzle---------");
        game.setLevel(Levels.HARD);
        game.makePizzle();
        game.getBoard().print();
        
        System.out.println("-------getRow(5)---------");
        System.out.println(game.getBoard().getRow(5).toString());
    }
    
}
