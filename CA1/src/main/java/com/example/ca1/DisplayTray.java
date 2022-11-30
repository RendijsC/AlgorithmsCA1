package com.example.ca1;

import Resources.LinkedList;
import javafx.event.ActionEvent;
import javafx.geometry.Orientation;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class DisplayTray {
    public String trayID;
    public String inlay;
    public String width;
    public String depth;

    public LinkedList<JewelleryItem> displayItems = new LinkedList<>();// creates LL for Items
    public DisplayTray(String trayID, String inlay, String width, String depth) { //defines the method for creating an object
        this.trayID = trayID;
        this.inlay = inlay;
        this.width = width;
        this.depth = depth;
    }

    @Override
    public String toString() {
        return
                "  " + trayID + '\'' +
                "  " + inlay + '\'' +
                " " + width + '\'' +
                " " + depth + '\'' +
                '}';
    }
}
