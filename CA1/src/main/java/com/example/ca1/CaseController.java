package com.example.ca1;

import Resources.LinkedList;
import Resources.Node;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.*;

public class CaseController {
    public static LinkedList<DisplayCase> caselist = new LinkedList<>(); //linked list


    public int valueStock = 0; //int that stores value of the stock


    public ListView<String> itemStock; //fx id for listing all items in stock

    //Cse

    public TextField caseID; // case id for display case
    public ChoiceBox<String> type;// choice box if its wall mounted or free standing
    public ChoiceBox<String> lighting;// choice box to pick the lighting of the case lit or unlit
    public ListView<String> dcase; // Lists all the created display cases


    //Tray
    public TextField trayID;//name of the tray
    public ChoiceBox<String> inlay; //what colour is the tray
    public TextField width;//size of the tray
    public TextField depth;// size of the tray
    @FXML
    public ListView<String> dTray; //lists all the created trays
    @FXML
    public ChoiceBox<DisplayCase> pickCase;//choice box to pick which case to insert it into


    //Item
    public TextField descriptionj;// description of the item
    public ChoiceBox<String> typej;// the type of item it is
    public ChoiceBox<String> gender;// the gender the item is for
    public TextField price;// the price of the item
    public ListView<String> jitem;// lists all the created items

    public ListView<String> value;// shows the value of all the items

    public ChoiceBox<DisplayTray> chooseTray; //pick which tray  the item goes into


    //components
    public ChoiceBox<String> typec; //the type of component it is
    public TextField descriptionc; //description
    public TextField quantity;// the quantity of the item
    public TextField quality;// the quality
    public ListView<JewelleryComponent> listComponents;// shows all components
    public ChoiceBox<JewelleryItem> chooseItem;// pick which item it is for


    public void initialize() {// all the inputs for the choice box
        type.getItems().addAll("Wall-Mounted", "Freestanding");
        lighting.getItems().addAll("Lit", "Unlit");
        inlay.getItems().addAll("Green", "Blue", "Red", "Black");
        gender.getItems().addAll("Male", "Female");
        typec.getItems().addAll("Gold", "Silver", "Platinum", "Diamond", "Emerald", "Topaz");
        typej.getItems().addAll("Watch", "Ring", "Bracelet");


    }

    @FXML
    public void addDisplayCase(ActionEvent actionEvent) {
        //creates a display ccase with the inputed values
        DisplayCase dc = new DisplayCase(caseID.getText(), type.getValue(), lighting.getValue());
        //adds the display case to the linked list
        caselist.addNode(dc);
        popListBox();
        pickCase.getItems().add(dc);

    }

    public void popListBox() {//displays displaycase in list view
        dcase.getItems().clear();
        Node temp = caselist.getHead();//sets the temp of the case list as nodeHead
        while (temp != null) {//keeps going through the temp nodes and adds them to dcase(listView) until there is no more temps and it equals null
            dcase.getItems().add(temp.getData().toString());
            temp = temp.getNext();
        }
    }


    public void deleteDisplayCase(ActionEvent actionEvent) {//removes selected display case from list view
        dcase.getItems().remove(dcase.getSelectionModel().getSelectedItem());
    }


    //Tray

    public void addDisplayTray(MouseEvent event) {

        DisplayCase dc = pickCase.getSelectionModel().getSelectedItem();//puts all the display case into pickCase
        DisplayTray dt = new DisplayTray(trayID.getText(), inlay.getValue(), width.getText(), depth.getText());
        //creates a display tray with inputs
        dc.displayTrays.addNode(dt); //adds a display tray node to display case
        dTray.getItems().clear();// clears the list view
        for (int i = 0; i < dc.displayTrays.listSize(); i++) { //adds trays to the linked list
            DisplayTray tray = (DisplayTray) dc.displayTrays.get(i);
            dTray.getItems().add(tray + "");
        }
        System.out.println(dc.displayTrays.getHead());
        chooseTray.getItems().add(dt);//puts all tarys into chooseTray
        popListBoxTray();
    }


    public void popListBoxTray() {
        dTray.getItems().clear();//clear the list view
        for (int i = 0; i < caselist.listSize(); i++) {//goes through display cases to find all trays then displays them in lst view
            DisplayCase tcase = (DisplayCase) caselist.get(i);
            for (int j = 0; j < tcase.displayTrays.listSize(); j++) {
                DisplayTray tray = (DisplayTray) tcase.displayTrays.get(j);
                dTray.getItems().add(tray + "");
            }
        }

    }

    //item


    public void addJewelleryItem(ActionEvent actionEvent) {
        value.getItems().clear();//value of all the items
        DisplayTray tray = chooseTray.getSelectionModel().getSelectedItem();//puts all trays into chooseTray
        JewelleryItem ji = new JewelleryItem(descriptionj.getText(), typej.getValue(), gender.getValue(), price.getText());
        //creates jewellery item with inputs
        int p = Integer.parseInt(ji.price);//takes the inputs from price
        valueStock += p;//keeps adding the price of items and sets it to valueStock
        System.out.println(valueStock);
        value.getItems().add(valueStock + " ");

        tray.displayItems.addNode(ji);//adds jewellery  item node to tray
        popListBoxItem();
        chooseItem.getItems().add(ji);//puts al jewellery items into to chose item
        System.out.println(ji);

    }

    public void popListBoxItem() {
        jitem.getItems().clear();//goes through display case then into trays then looks for items in trays and puts them into jewellery list view
        for (int i = 0; i < caselist.listSize(); i++) {
            DisplayCase icase = (DisplayCase) caselist.get(i);
            for (int j = 0; j < icase.displayTrays.listSize(); j++) {
                DisplayTray tray = (DisplayTray) icase.displayTrays.get(j);
                for (int k = 0; k < tray.displayItems.listSize(); k++){
                    JewelleryItem item = (JewelleryItem) tray.displayItems.get(k);
                    jitem.getItems().add(item + "");

                }

            }
        }

    }


    public void smartAdd(ActionEvent event) {
        boolean added = false;
        JewelleryItem newItem = new JewelleryItem(descriptionj.getText(), typej.getValue(), gender.getValue(), price.getText());
        //create a jewellery item with smart add
        //keeps going through all the LL looking for dc trays and items
        for (int i = 0; i < caselist.listSize(); i++) {
            if (!added) {
                DisplayCase icase = (DisplayCase) caselist.get(i);
                for (int j = 0; j < icase.displayTrays.listSize(); j++) {
                    if (!added) {
                        DisplayTray tray = (DisplayTray) icase.displayTrays.get(j);
                        for (int k = 0; k < tray.displayItems.listSize(); k++) {
                            if (!added) {
                                JewelleryItem item = (JewelleryItem) tray.displayItems.get(k);
                                // if item with same string has been found adds new item to the same tray
                                if (item.toString().contains(newItem.typej)) {
                                    tray.displayItems.addNode(newItem);
                                    added = true;

                                }
                            }
                        }
                    }
                }
            }

        }
        if (!added) {// if there is no items with the selected string it will add it to the first display case and first tray
            DisplayCase dc = (DisplayCase) caselist.get(0);
            DisplayTray dt = (DisplayTray) dc.displayTrays.get(0);
            dt.displayItems.addNode(newItem);
        }
    }





    @FXML
    public void viewAllStock(ActionEvent event) {
        String str = "" + '\n';
        for (int i = 0; i < caselist.listSize(); i++) {
            DisplayCase dc = (DisplayCase) caselist.get(i);
            itemStock.getItems().add(dc + "");//puts all items from LL into itemStock
            for (int k = 0; k < dc.displayTrays.listSize();k++){
                DisplayTray dt = (DisplayTray) dc.displayTrays.get(k);
                itemStock.getItems().add("   " + dt);//puts all items from display trays into itemStock
                for (int l = 0; l < dt.displayItems.listSize(); l++) {
                    JewelleryItem ji = (JewelleryItem) dt.displayItems.get(l);
                    itemStock.getItems().add("      " + ji);//puts all Items into displayItems LL into itemStock

                }

            }
            //puts a gap between case and trays
            itemStock.getItems().add(str);
        }
    }

    public void deleteItem(ActionEvent actionEvent) {
        jitem.getItems().remove(jitem.getSelectionModel().getSelectedItem());
        //removes item from listview
    }

    public  void reset(ActionEvent event){
        caselist.deleteAll();
        dcase.getItems().clear();
        dTray.getItems().clear();
        jitem.getItems().clear();
        listComponents.getItems().clear();
        //deletes all data and clears all listviews

    }








        public void addComponent(ActionEvent actionEvent) {
            JewelleryItem item = chooseItem.getSelectionModel().getSelectedItem();//gets all jewellery items

            JewelleryComponent jc = new JewelleryComponent(typec.getValue(), descriptionc.getText(), quantity.getText(), quality.getText());
            //creates component with inputs
            item.displayComponents.addNode(jc);//adds component to LL
            popListBoxComponents();

        }

    public void popListBoxComponents() {
        if(listComponents.getItems() != null) {listComponents.getItems().clear();}
        for (int i = 0; i < caselist.listSize(); i++) {
            DisplayCase icase = (DisplayCase) caselist.get(i);
            for (int j = 0; j < icase.displayTrays.listSize(); j++) {
                DisplayTray tray = (DisplayTray) icase.displayTrays.get(j);
                for (int k = 0; k < tray.displayItems.listSize(); k++){
                    JewelleryItem item = (JewelleryItem) tray.displayItems.get(k);
                    for (int l = 0; l < item.displayComponents.listSize(); l++){
                        JewelleryComponent comp = (JewelleryComponent) item.displayComponents.get(l);
                        System.out.println(comp);
                        listComponents.getItems().add(comp);// Goes Through all LL until in components takes all the components and displays them in ListView
                    }

                }

            }
        }

    }









    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("displaycase.xml"));
        LinkedList<DisplayCase> list = caselist;
        out.writeObject(list);

        out.close();
    }


    public void load() throws Exception {
        //list of classes that you wish to include in the serialisation, separated by a comma
        Class<?>[] classes = new Class[] { DisplayCase.class, DisplayTray.class, JewelleryItem.class, JewelleryComponent.class, LinkedList.class, Node.class };

        //setting up the xstream object with default security and the above classes
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        //doing the actual serialisation to an XML file
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("displaycase.xml"));
        caselist = (LinkedList<DisplayCase>)  is.readObject();
        is.close();
        popListBox();
        popListBoxTray();
        popListBoxItem();
    }

    public void saveCase() {
        try {
            save();
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e);
        }
    }


    @FXML
    private void loadCase () {
        try {
            load();
        } catch (Exception e) {
            System.err.println("Error reading from file: " + e);
        }
    }


}
