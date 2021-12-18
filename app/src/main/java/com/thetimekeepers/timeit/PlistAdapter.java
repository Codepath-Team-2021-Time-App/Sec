package com.thetimekeepers.timeit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;
 //what can I do from RecyclerView.ViewHolder and RecyclerView.Adapter in order to click
public class PlistAdapter extends RecyclerView.Adapter <PlistAdapter.ViewHolder>{

    private Context context;
    //testing
//    private ArrayList<Plist> posts = new ArrayList<>();
    private List<Plist> posts; //private to protected?
    RelativeLayout container; // not used
    private onListListener monListListener;

    //added clear and addAll
    public void clear(){
        posts.clear();
        notifyDataSetChanged();
    }
//modifying between List to ArrayList!!!
    public void addAll(List<Plist> postList){
        posts.addAll(postList);
        notifyDataSetChanged();
    }

    public PlistAdapter(Context context, List<Plist> posts, onListListener onListListener) {
        this.context = context;
        this.posts = posts;
        this.monListListener = onListListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view, monListListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Plist post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

//    public interface containerListener{
//        void onListClick(int position);
//    }

     //should be inside the function below

     public interface onListListener {
        void onListClick(int position);
     }

     public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

//        private TextView tvUsername;
        private TextView tvItem;

        //added
        onListListener onListListener;

        //from public to public class
        public ViewHolder(@NonNull View itemView, onListListener onListListener) {
            super(itemView);
//            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvItem = itemView.findViewById(R.id.tvItem);
            container = itemView.findViewById(R.id.container);
            this.onListListener = onListListener;

            itemView.setOnClickListener(this);
        }

         @Override
         public void onClick(View v) {
            onListListener.onListClick(getAdapterPosition());
         }


        public void bind(Plist post) {
            // Bind the post data to the view elements
            tvItem.setText(post.getAction());
//            tvUsername.setText(post.getUser().getUsername());
        }

        //method #1 flixster
//        container.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //start clock activity from a different class (ClockFragment.java)
//            }
//        });

//--------------------------------------------------------------------------------------------------

         //method #2 -https://www.youtube.com/watch?v=69C1ljfDvl0&t=82s


    }
}
