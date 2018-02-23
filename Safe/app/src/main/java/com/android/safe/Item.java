package com.android.safe;
import android.graphics.drawable.Drawable;

public class Item
{
    private String description;
    private int price;
    private String vendor;
    String title;
    Drawable image;

    // Empty Constructor
    public Item()
    {

    }

    // Constructor
    public Item(String title, Drawable image)
    {
        super();
        this.title = title;
        this.image = image;
    }

    public Item(String title, String desc, int price, String vendor, Drawable image)
    {
        super();
        this.title = title;
        this.image = image;
        this.vendor = vendor;
        this.price = price;
        this.description = desc;
    }

    // Getter and Setter Method
    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public Drawable getImage()
    {
        return image;
    }

    public void setImage(Drawable image)
    {
        this.image = image;
    }

    public String getDescription()
    {
        return description;
    }
    public String getVendor()
    {
        return vendor;
    }
    public int getPrice()
    {
        return price;
    }

}
