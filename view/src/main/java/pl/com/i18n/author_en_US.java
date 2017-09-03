/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.com.i18n;

import java.util.ListResourceBundle;

/**
 *
 * @author Zhenqi & Danyang
 */
public class author_en_US extends ListResourceBundle {

    private final Object[][] resources = {
        {"overview", "Overview"},
        {"information", "This Sudoku was launhced in Join 2017 by Zhenqi Chai and Danyang Liu."},
        {"contact", "Contact us"},
        {"email", "For user feedback, business development and other inquiries, you can send email to the address emilieczq@gmail.com."},
        {"close", "Close"}
    };

    @Override
    protected Object[][] getContents() {
        return resources;
    }

}
