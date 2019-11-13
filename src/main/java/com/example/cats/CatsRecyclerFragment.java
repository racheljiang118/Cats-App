
        package com.example.cats;

        import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CatsRecyclerFragment extends Fragment {

    //declare elements
    private RecyclerView recyclerView;
    private SearchView searchView;
    public CatsAdapter newAdapter;
    List<Cats> newList;
    public CatsRecyclerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final CatsAdapter catsAdapter = new CatsAdapter();

        //set Recycler View
        View view = inflater.inflate(R.layout.fragment_cats_recycler, container, false);
        recyclerView = view.findViewById(R.id.rv_main);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);


        //set up search
        //Reference: https://codinginflow.com/tutorials/android/searchview-recyclerview
        searchView = view.findViewById(R.id.search);
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setQueryHint("Search");
        //super.onCreateOptionsMenu(menu, inflater);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //MAYBE NEED TO CHANGE
                if (newText.matches(".*\\d+.*")){
                    searchView.setQueryHint("Please enter valid cat breed");
                }
                else {
                    catsAdapter.getFilter().filter(newText);
                }
                return false;
            }




        });


        //retrive JSON

        final RequestQueue requestQueue =  Volley.newRequestQueue(getActivity());
        String url = "https://api.thecatapi.com/v1/breeds/?api-key=e2e6da98-840f-4463-9aab-e479fd3a45f3" ;
        //e2e6da98-840f-4463-9aab-e479fd3a45f3 <- key
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Cats[] catsArray = gson.fromJson(response, Cats[].class);
                ArrayList<Cats> catsToAdapt = new ArrayList<>(Arrays.asList(catsArray));
                catsAdapter.setData(catsToAdapt);
                FakeDatabase.saveCatsToFakeDatabase(catsToAdapt);
                recyclerView.setAdapter(catsAdapter);
                requestQueue.stop();

            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"The request failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                requestQueue.stop();
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener,
                errorListener);
        requestQueue.add(stringRequest);

        return view;
    }

    /*
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        //MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView) searchItem.getActionView();
*/

    }



/*

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        //catsAdapter = new CatsAdapter(catsToAdapt);
        inflater.inflate(R.menu.toolbar_menu, menu);
        final MenuItem searchItem = menu.findItem(R.id.search_bar);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(catsAdapter!=null) {
                    catsAdapter.getFilter().filter(newText);
                }
                return false;

            }
        });

    }
        //return true;


 */





