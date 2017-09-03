/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.com.i18n;

/**
 *
 * @author Zhenqi & Danyang
 */
import java.util.ListResourceBundle;


public class author_zh_CN extends ListResourceBundle {

    private final Object[][] resources = {
        {"overview", "简介"},
        {"information", "这款数独游戏是由柴真琦和刘丹阳设计于2017年6月."},
        {"contact", "联系我们"},
        {"email", "关于用户反馈,您可以发送信息至此邮箱:emilieczq@gmail.com."},
        {"close", "关闭"}
    };

    @Override
    protected Object[][] getContents() {
        return resources;
    }
}
