
        package com.example.cats;

        import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class CatsAdapter extends RecyclerView.Adapter<CatsAdapter.CatsViewHolder> implements Filterable {

    private ArrayList<Cats> catsToAdapt;
    private List<Cats> catsToAdaptFull;
    //private CardView catsCardView;


    //public CatsAdapter(List<Cats> catsToAdapt){this.catsToAdapt = catsToAdapt;}
/*
    CatsAdapter(List<Cats> catsToAdapt) {
        this.catsToAdapt = catsToAdapt;
        catsToAdaptFull = new ArrayList<>(catsToAdapt);
    }



 */


    public void setData(ArrayList<Cats> catsToAdapt) {
        // This is basically a Setter that we use to give data to the adapter
        this.catsToAdapt = catsToAdapt;
        catsToAdaptFull = new ArrayList<>(catsToAdapt);
    }

    public static class CatsViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView nameTextView;


        // This constructor is used in onCreateViewHolder
        public CatsViewHolder(View v) {
            super(v);  // runs the constructor for the ViewHolder superclass
            view = v;
            nameTextView = v.findViewById(R.id.cName);


        }
    }

    @NonNull
    @Override
    public CatsAdapter.CatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.cat, parent, false);

        // Then create an instance of your custom ViewHolder with the View you got from inflating
        // the layout.
        CatsViewHolder catViewHolder = new CatsViewHolder(view);
        return catViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CatsAdapter.CatsViewHolder holder, int position) {
        final Cats catsAtPosition = catsToAdapt.get(position);
        holder.nameTextView.setText(catsAtPosition.getName());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();

                Intent intent = new Intent(context, CatDetail.class);
                intent.putExtra("id", catsAtPosition.getId());
                context.startActivity(intent);
/*


        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();

                Intent intent = new Intent(context, CatsDetail.class);
                intent.putExtra("ArticleID", catsAtPosition.getId());
                context.startActivity(intent);
            }
        });
        */
            }
        });
    }

    @Override
    public int getItemCount() {
        return catsToAdapt.size();
    }

    //reference: https://codinginflow.com/tutorials/android/searchview-recyclerview
    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Cats> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(catsToAdaptFull);
            }

            else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Cats item : catsToAdaptFull) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }


        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            catsToAdapt.clear();
            catsToAdapt.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };

}





