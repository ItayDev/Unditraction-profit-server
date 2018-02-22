package com.android.safe;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener
{
    GridView gridview;
    GridViewAdapter gridviewAdapter;
    ArrayList<Item> data = new ArrayList<Item>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView(); // Initialize the GUI Components
        fillData(); // Insert The Data
        setDataAdapter(); // Set the Data Adapter
    }

    // Initialize the GUI Components
    private void initView()
    {
        gridview = (GridView) findViewById(R.id.gridView);
        gridview.setOnItemClickListener(this);
    }

    // Insert The Data
    private void fillData()
    {
        data.add(new Item("Facebook", getResources().getDrawable(R.mipmap.ic_launcher)));
        data.add(new Item("Twitter", getResources().getDrawable(R.mipmap.ic_launcher)));
        data.add(new Item("Linked In", getResources().getDrawable(R.mipmap.ic_launcher)));
        data.add(new Item("Google", getResources().getDrawable(R.mipmap.ic_launcher)));
        data.add(new Item("Yahoo", getResources().getDrawable(R.mipmap.ic_launcher)));
        data.add(new Item("YouTube", getResources().getDrawable(R.mipmap.ic_launcher)));
        data.add(new Item("Flickr", getResources().getDrawable(R.mipmap.ic_launcher)));
        data.add(new Item("Whatsapp", getResources().getDrawable(R.mipmap.ic_launcher)));
        data.add(new Item("Blogger", getResources().getDrawable(R.mipmap.ic_launcher)));
    }

    // Set the Data Adapter
    private void setDataAdapter()
    {
        gridviewAdapter = new GridViewAdapter(getApplicationContext(), R.layout.item, data);
        gridview.setAdapter(gridviewAdapter);
    }

    @Override
    public void onItemClick(final AdapterView<?> arg0, final View view, final int position, final long id)
    {
        String message = "Clicked : " + data.get(position).getTitle();
        Toast.makeText(getApplicationContext(), message , Toast.LENGTH_SHORT).show();

        Intent i = new Intent(MainActivity.this, ItemActivity.class);
        Bitmap bitmap = ((BitmapDrawable)data.get(position).getImage()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] img = baos.toByteArray();
        i.putExtra("title", data.get(position).getTitle());
        i.putExtra("image", img);
        startActivity(i);
    }

}