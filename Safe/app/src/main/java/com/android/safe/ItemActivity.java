package com.android.safe;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemActivity extends AppCompatActivity {

    private Bitmap image;
    private String content;
    private String title;
    private String vendor;
    private int price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        Intent i= getIntent();
        Bundle b = i.getExtras();

        // get image
        byte[] byteArray = b.getByteArray("image");
        this.image = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        // content, title
        this.title = (String) b.get("title"); // title
        this.content = (String) b.get("description");
        ImageView img = (ImageView) findViewById(R.id.imageView);
        img.setImageBitmap(this.image);
        TextView txt = (TextView) findViewById(R.id.title);
        txt.setText(this.title);
        TextView cntx = (TextView) findViewById(R.id.content);
        cntx.setText("description - " + this.content);
        this.vendor = (String) b.get("vendor");
        TextView vend = (TextView) findViewById(R.id.vendor);
        vend.setText("vendor - " +this.vendor);
        this.price = (int) b.get("price");
        TextView prc = (TextView) findViewById(R.id.price);
        prc.setText("price - " + (Integer.toString(this.price)));

    }
}
