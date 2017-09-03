/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.com.i18n;

import java.util.ListResourceBundle;

/**
 *
 * @author Zhenqi
 */
public class save_en_US extends ListResourceBundle {

    private final Object[][] resources = {
        {"name", "Name"},
        {"save", "Save"},
        {"cancel", "Cancel"},
        {"information1", "Attention: The case cannot be empty"},
        {"information2", "Attention: The length of the name should be shorter than 15 characters."},
        {"information3", "Attention: This name has been used, please use another one."}
    };

    @Override
    protected Object[][] getContents() {
        return resources;
    }

    
}
