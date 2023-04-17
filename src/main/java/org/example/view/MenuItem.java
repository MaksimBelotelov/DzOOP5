package org.example.view;

public abstract class MenuItem {
    private String itemName;

    public MenuItem(String itemName) {
        this.itemName = itemName;
    }

    public abstract void processItem();

    public String getItemName() {
        return itemName;
    }
}
