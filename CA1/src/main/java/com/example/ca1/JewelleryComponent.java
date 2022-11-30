package com.example.ca1;

public class JewelleryComponent {
    public String typec;
    public String descriptionc;
    public String quantity;
    public String quality;

    public JewelleryComponent(String typec, String descriptionc, String quantity, String quality) {//defines the method for creating an object
        this.typec = typec;
        this.descriptionc = descriptionc;
        this.quantity = quantity;
        this.quality = quality;
    }

    @Override
    public String toString() {
        return "JewelleryComponent{" +
                " " + typec + '\'' +
                " " + descriptionc + '\'' +
                " " + quantity + '\'' +
                " " + quality + '\'' +
                '}';
    }
}
