package com.example.babyneeds.modal;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "itemtable")
public class Item {

    @PrimaryKey (autoGenerate = true)
    private int id;

    @ColumnInfo (name = "Name")
    private String Itemname;

    @ColumnInfo(name = "Quantity")
    private int ItemQuantity;

    @ColumnInfo(name="color")
    private String itemcolor;

    @ColumnInfo(name="Size")
    private int itemsize;

    public Item(String itemname, int itemQuantity, String itemcolor, int itemsize) {
        Itemname = itemname;
        ItemQuantity = itemQuantity;
        this.itemcolor = itemcolor;
        this.itemsize = itemsize;
    }

    public Item(int id, String itemname, int itemQuantity, String itemcolor, int itemsize) {
        this.id = id;
        Itemname = itemname;
        ItemQuantity = itemQuantity;
        this.itemcolor = itemcolor;
        this.itemsize = itemsize;
    }

    public Item() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemname() {
        return Itemname;
    }

    public void setItemname(String itemname) {
        Itemname = itemname;
    }

    public int getItemQuantity() {
        return ItemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        ItemQuantity = itemQuantity;
    }

    public String getItemcolor() {
        return itemcolor;
    }

    public void setItemcolor(String itemcolor) {
        this.itemcolor = itemcolor;
    }

    public int getItemsize() {
        return itemsize;
    }

    public void setItemsize(int itemsize) {
        this.itemsize = itemsize;
    }
}
