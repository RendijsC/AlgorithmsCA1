package com.example.ca1;

import Resources.LinkedList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class JewelleryItem {
    public String descriptionj;
    public String typej;
    public String gender;
    public String price;

    public LinkedList<JewelleryComponent> displayComponents = new LinkedList<>();//creates LL for components


    public JewelleryItem(String descriptionj, String typej, String gender, String price) {//defines the method for creating an object
        this.descriptionj = descriptionj;
        this.typej = typej;
        this.gender = gender;
        this.price = price;
    }

    @Override
    public String toString() {
        return "JewelleryItem{" +
                " " + descriptionj + '\'' +
                " " + typej + '\'' +
                " " + gender + '\'' +
                " " + price + '\'' +
                '}';
    }
}
