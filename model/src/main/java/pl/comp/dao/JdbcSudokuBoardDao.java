/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.comp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import pl.comp.SudokuBoard;

/**
 *
 * @author Zhenqi
 */
public class JdbcSudokuBoardDao implements AutoCloseable, Dao<SudokuBoard> {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/Sudoku";

    static final String USER = "root";
    static final String PASSWORD = "123456";

    private Connection connection = null;
    private int game_id;
    private String game_name;

    public void setGameName(final String game_name) {
        this.game_name = game_name;
    }
    
    public void getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("ERROR: Unable to Connect to Database.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean gameExists(final String game_name) throws DaoException {
        getConnection();
        int num=0;
        try {
            Statement statement = connection.createStatement();
            ResultSet result1 = statement.executeQuery("SELECT COUNT(*) FROM Games WHERE game_name= '" + game_name + "'");
            result1.next();
            num = Integer.parseInt(result1.getString(1));
            System.out.println("num = " + num);
            
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return num != 0;
    }

    @Override
    public SudokuBoard read() throws DaoException {
        getConnection();
        SudokuBoard board = new SudokuBoard();

        try {
            Statement statement = connection.createStatement();
            ResultSet result1 = statement.executeQuery("SELECT * FROM Games WHERE game_name= '" + game_name + "'");
            result1.next();
            game_id = Integer.parseInt(result1.getString(1));
            statement.close();
            
            Statement statement2 = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM Fields WHERE game_id= '" + game_id + "'");
            while (results.next()) {
                int value = Integer.parseInt(results.getString("value"));
                int row = Integer.parseInt(results.getString("board_row"));
                int column = Integer.parseInt(results.getString("board_column"));
                board.set(row, column, value);
            }
            statement2.close();
        } catch (SQLException ex) {
            throw new DaoException(ex);
        } 

        return board;

    }

    @Override
    public void write(final SudokuBoard board) throws DaoException {
        getConnection();

        /* for this game, add values in Fields */
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Games WHERE game_name =  ? ");
            statement.setString(1, game_name);
            ResultSet result = statement.executeQuery();
            
            result.next();
            game_id = Integer.parseInt(result.getString(1));
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    int value = board.get(i, j);
                    PreparedStatement statement2 = connection.prepareStatement("INSERT INTO Fields (game_id, board_row, board_column, value) VALUES ('" +game_id + "','" + i + "','" + j + "','" + value + "')");
                    statement2.executeUpdate( );
                }
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }

    }

    @Override
    public void close() throws Exception {

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            /*Ignore*/
        }
    }

}
