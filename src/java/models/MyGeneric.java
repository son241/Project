/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import DAL.DBContext;
import com.sun.javafx.fxml.expression.UnaryExpression;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Admin
 */
public class MyGeneric<T> extends DBContext {

    ArrayList<T> allItem;

    public MyGeneric(ArrayList<T> allItem) {
        this.allItem = allItem;
    }

    public ArrayList<T> dataPaging(int pageTh, int numberOfItemPerPage) {

        ArrayList<T> dataPaging = new ArrayList<T>();
        int startIndex = pageTh * numberOfItemPerPage - numberOfItemPerPage;
        int endIndex = (startIndex + numberOfItemPerPage) - 1;

        for (int i = startIndex; i <= endIndex; i++) {
            if (i == allItem.size()) {
                break;
            }
            dataPaging.add(allItem.get(i));
        }

        return dataPaging;
    }

}
