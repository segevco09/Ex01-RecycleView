package com.example.ex01_recycleview.activitys;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ex01_recycleview.R;

import java.util.ArrayList;
import java.util.List;

public class CustomeAdapter extends RecyclerView.Adapter<CustomeAdapter.MyViewHolder> implements Filterable {

    private ArrayList<DataModel> dataSet;
    private ArrayList<DataModel> filteredList;
    private Context context;

    public CustomeAdapter(ArrayList<DataModel> dataSet, Context context) {
        this.dataSet = dataSet;
        this.filteredList = new ArrayList<>(dataSet);
        this.context = context;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView textViewName;
        TextView textViewDescription;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardViewShow);
            textViewName = itemView.findViewById(R.id.textViewNameOfCharacter);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    @NonNull
    @Override
    public CustomeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomeAdapter.MyViewHolder holder, int position) {
        DataModel item = filteredList.get(position);

        holder.textViewName.setText(item.getName());
        holder.textViewDescription.setText(item.getDesc());
        holder.imageView.setImageResource(item.getImage());

        holder.cardView.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(item.getName());
            builder.setMessage(item.getDesc());
            builder.setPositiveButton("Close", (dialog, which) -> dialog.dismiss());
            builder.show();
        });
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<DataModel> filteredResults = new ArrayList<>();

                if (constraint == null || constraint.length() == 0) {
                    filteredResults.addAll(dataSet);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();

                    for (DataModel item : dataSet) {
                        if (item.getName().toLowerCase().contains(filterPattern)) {
                            filteredResults.add(item);
                        }
                    }
                }

                FilterResults results = new FilterResults();
                results.values = filteredResults;
                results.count = filteredResults.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredList.clear();
                filteredList.addAll((List<DataModel>) results.values);
                notifyDataSetChanged();
            }
        };
    }
}
