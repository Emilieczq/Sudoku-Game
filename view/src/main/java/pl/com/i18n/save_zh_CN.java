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
public class save_zh_CN extends ListResourceBundle {

    private final Object[][] resources = {
        {"name", "文件名"},
        {"save", "保存"},
        {"cancel", "取消"},
        {"information1", "注意:空格不能为空"},
        {"information2", "注意:名字应该为长度小于15的字符串"},
        {"information3", "注意:改名字已被使用,请另选一个名字"}
    };

    @Override
    protected Object[][] getContents() {
        return resources;
    }

    
}
