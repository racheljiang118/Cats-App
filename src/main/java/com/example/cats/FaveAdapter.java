package com.example.cats;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FaveAdapter extends RecyclerView.Adapter<FaveAdapter.FaveViewHolder>{

    private ArrayList<Cats> faveCats = new ArrayList<>();


//change to faveCatsToAdapt
    public void setData(ArrayList<Cats> faveCats) {
        // This is basically a Setter that we use to give data to the adapter
        this.faveCats = faveCats;
    }

    public class FaveViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView nameTextView;
        public ImageView catImageView;


        // This constructor is used in onCreateViewHolder
        public FaveViewHolder(View v) {
            super(v);  // runs the constructor for the ViewHolder superclass
            view = v;
            nameTextView = v.findViewById(R.id.cName);
            //catImageView = v.findViewById(R.id.cImage);


        }
    }

        @NonNull
        @Override
        public FaveAdapter.FaveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view =
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.favecats, parent, false);

            // Then create an instance of your custom ViewHolder with the View you got from inflating
            // the layout.
            FaveAdapter.FaveViewHolder faveViewHolder = new FaveAdapter.FaveViewHolder(view);
            return faveViewHolder;
        }

    @Override
    public void onBindViewHolder(@NonNull FaveAdapter.FaveViewHolder holder, int position) {
        final Cats catsAtPosition = faveCats.get(position);
        holder.nameTextView.setText(catsAtPosition.getName());
    }

    @Override
    public int getItemCount() {
        return faveCats.size();
    }
}
