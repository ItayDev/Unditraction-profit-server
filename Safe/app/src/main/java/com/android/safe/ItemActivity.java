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
        this.content = "bla bla bla bla bla";
        ImageView img = (ImageView) findViewById(R.id.imageView);
        img.setImageBitmap(this.image);
        TextView txt = (TextView) findViewById(R.id.title);
        txt.setText(this.title);
        TextView cntx = (TextView) findViewById(R.id.content);
        cntx.setText(this.content);

    }
}
