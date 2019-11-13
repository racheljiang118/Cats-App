package com.example.cats;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

public class CatDetail extends AppCompatActivity {


    private TextView nameTextView;
    private TextView descTextView;
    private TextView weightTextView;
    private TextView tempTextView;
    private TextView originTextView;
    private TextView lifeTextView;
    private TextView linkTextView;
    private TextView dogTextView;
    private ImageView catImage;
    private ArrayList<Image> imageList;
    private String imageUrl;
    private Button fave;





    //Description
    //o Weight
    //o Temperament
    //o Origin
    //o Life span
    //o Wikipedia link
    //o Dog friendliness level

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_detail);

        Intent intent = getIntent();
        String catsID = intent.getStringExtra("id");


        final Cats cat = FakeDatabase.getCatsById(catsID);

        nameTextView = findViewById(R.id.name);
        descTextView = findViewById(R.id.description);
        descTextView = findViewById(R.id.description);
        weightTextView = findViewById(R.id.weight);
        tempTextView = findViewById(R.id.temp);
        originTextView = findViewById(R.id.origin);
        lifeTextView = findViewById(R.id.life);
        linkTextView = findViewById(R.id.link);
        dogTextView = findViewById(R.id.dog);
        catImage = findViewById(R.id.catImage);
        fave = findViewById(R.id.addFave);

        nameTextView.setText(cat.getName());
        descTextView.setText(cat.getDescription());
        weightTextView.setText(cat.getWeight().getMetric() + "kg");
        tempTextView.setText(cat.getTemperament());
        originTextView.setText(cat.getOrigin());
        lifeTextView.setText(cat.getLife_span() + " years");
        linkTextView.setText(cat.getWikipedia_url());
        String dogText = String.valueOf(cat.getDog_friendly());
        dogTextView.setText(dogText);
        //catImage.setImageResource(R.drawable.);



        //adding item to favourite
        fave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FaveRecyclerFragment.favourites.contains(cat)) {
                    Toast.makeText(getApplicationContext(), "Already added to Favourites", Toast.LENGTH_SHORT).show();
                } else {
                    FaveRecyclerFragment.favourites.add(cat);
                    Toast.makeText(getApplicationContext(), "Added to Favourites", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //images
        //Image
        String potentialUrl = "https://api.thecatapi.com/v1/images/search?breed_ids=" + cat.getId();

        //Create the context:
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Image[] imageArray = gson.fromJson(response, Image[].class);
                imageList = new ArrayList<>(Arrays.asList(imageArray));
                Image thisImage = imageList.get(0);
                imageUrl = thisImage.getUrl();
                Glide.with(CatDetail.this).load(imageUrl).into(catImage);

            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse (VolleyError error) {
                System.out.println(error.toString());
            }
        };
        StringRequest stringRequest = new StringRequest(Request.Method.GET, potentialUrl, responseListener, errorListener);
        requestQueue.add(stringRequest);

}
}