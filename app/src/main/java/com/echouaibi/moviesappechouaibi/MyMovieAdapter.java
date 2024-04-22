package com.echouaibi.moviesappechouaibi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyMovieAdapter extends
        RecyclerView.Adapter<MyMovieAdapter.ViewHolder> {
    List<MyMovieData> myMovieData;
    Context context;
    public MyMovieAdapter(List<MyMovieData> myMovieData,MainActivity
            activity) {
        this.myMovieData = myMovieData;
        this.context = activity;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup
                                                 parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        View view =
                layoutInflater.inflate(R.layout.movie_item_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int
            position) {
        final MyMovieData myMovieDataList =
                myMovieData.get(position);

        holder.textViewName.setText(myMovieDataList.getTitle());

        holder.textViewDate.setText(myMovieDataList.getRelease_date().toString());

        Picasso.get().load("https://image.tmdb.org/t/p/w500/"+myMovieDataList.getPoster_path())
                .into(holder.movieImage);

        holder.itemView.setOnClickListener(new
                                                   View.OnClickListener() {
                                                       @Override
                                                       public void onClick(View v) {
                                                           Intent intent = new Intent(context,DetailActivity.class);
                                                           intent.putExtra("movieData",myMovieDataList);
                                                           context.startActivity(intent);
                                                       }
                                                   });
    }
    @Override
    public int getItemCount() {
        if(myMovieData==null)
            return 0;
        else
        return myMovieData.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView movieImage;
        TextView textViewName;
        TextView textViewDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.imageview);
            textViewName = itemView.findViewById(R.id.textName);
            textViewDate = itemView.findViewById(R.id.textdate);
        }
    }
}