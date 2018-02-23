package com.android.safe;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends Activity implements OnItemClickListener
{
    GridView gridview;
    String server = "130.61.54.23:3000";
    GridViewAdapter gridviewAdapter;
    ArrayList<Item> data = new ArrayList<Item>();
    ArrayList<Item> benefits = new ArrayList<Item>();
    // the http client
    public static DefaultHttpClient httpClient = new DefaultHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView(); // Initialize the GUI Components
        fillData(); // Insert The Data
        new GetBenefits().execute("http://" + server + "/benefit");
        RelativeLayout r = (RelativeLayout) findViewById(R.id.swipe_right);
        r.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            /**
             * if we find out that were swipe right then move to the friend activity
             */
            @Override
            public void onSwipeRight() {
                Intent intent = new Intent(MainActivity.this, DriveActivity.class);
                startActivity(intent);
                finish();
            }
        });
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
        gridviewAdapter = new GridViewAdapter(getApplicationContext(), R.layout.item, benefits);
        gridview.setAdapter(gridviewAdapter);
    }

    @Override
    public void onItemClick(final AdapterView<?> arg0, final View view, final int position, final long id)
    {
        String message = "Clicked : " + benefits.get(position).getTitle();
        Toast.makeText(getApplicationContext(), message , Toast.LENGTH_SHORT).show();

        Intent i = new Intent(MainActivity.this, ItemActivity.class);
        Bitmap bitmap = ((BitmapDrawable)benefits.get(position).getImage()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] img = baos.toByteArray();
        i.putExtra("title", benefits.get(position).getTitle());
        i.putExtra("image", img);
        i.putExtra("description", benefits.get(position).getDescription());
        i.putExtra("price", benefits.get(position).getPrice());
        i.putExtra("vendor", benefits.get(position).getVendor());
        startActivity(i);
    }

    /**
     * This class is asynchronous task to send for the server the post request of the logout
     */
    private class GetBenefits extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            HttpContext localContext = new BasicHttpContext();
            HttpGet httpGet = new HttpGet(params[0]);
            String text = null;
            try {
                HttpResponse response = httpClient.execute(httpGet, localContext);
                HttpEntity entity = response.getEntity();
                text = getASCIIContentFromEntity(entity);
                JSONArray objList = new JSONArray(text);

                for (int i = 0; i < objList.length(); i++) {
                    String title = objList.getJSONObject(i).getString("title");
                    String vendor = objList.getJSONObject(i).getString("vendor");
                    String description = objList.getJSONObject(i).getString("description");
                    String imgStr = objList.getJSONObject(i).getString("picture");
                    int price = objList.getJSONObject(i).getInt("price");
                    byte[] decodeByte = Base64.decode(imgStr, Base64.DEFAULT);
                    Bitmap img = BitmapFactory.decodeByteArray(decodeByte, 0, decodeByte.length);
                    benefits.add(new Item(title,description, price, vendor, new BitmapDrawable(getResources(), img)));
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return text;
        }

        protected void onPostExecute(String result) {
            setDataAdapter(); // Set the Data Adapter
        }
    }

    // this function return string out of http entity
    protected String getASCIIContentFromEntity(HttpEntity entity)
            throws IllegalStateException, IOException {
        InputStream in = entity.getContent();
        StringBuffer out = new StringBuffer();
        int n = 1;
        while (n > 0) {
            byte[] b = new byte[4096];
            n = in.read(b);
            if (n > 0)
                out.append(new String(b, 0, n));
        }
        return out.toString();
    }
}