package com.thetimekeepers.timeit.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thetimekeepers.timeit.Plist;
import com.thetimekeepers.timeit.PlistAdapter;
import com.thetimekeepers.timeit.Post;
import com.thetimekeepers.timeit.R;
import com.thetimekeepers.timeit.list;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;


import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
//added PlistAdapter.OnClickListener
public class ClockFragment extends Fragment implements PlistAdapter.onListListener{
    public static final String TAG = "PlistFragment";
    Button btnadd, btnstop, btnschedule;
    ImageView icanchor;
    Animation roundingalone;
    Chronometer timerHere;
    private RecyclerView rvPosts;
    protected PlistAdapter adapter;
    //testing!!!
//    protected ArrayList<Plist> allPosts;
    protected List<Plist> allPosts;




    public ClockFragment() {
        //required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_stop_watch_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnstop = view.findViewById(R.id.btn_stop);
        icanchor = view.findViewById(R.id.icanchor);
        timerHere = view.findViewById(R.id.timerHere);
        btnadd = view.findViewById(R.id.btn_add);
        btnschedule = view.findViewById(R.id.btn_schedule);
        rvPosts = view.findViewById(R.id.actPosts);


        allPosts = new ArrayList<>();
        adapter = new PlistAdapter(getContext(),allPosts, this);

        rvPosts.setAdapter(adapter);

        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));
        queryPosts();


        //load animations
        roundingalone = AnimationUtils.loadAnimation(getContext(), R.anim.roundingalone);
//        ListView listView= (ListView) view.findViewById(R.id.listView);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,test);
//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
////                Toast.makeText(getContext(),"starting " +test[position],Toast.LENGTH_SHORT).show();
//                timerHere.stop();
//                icanchor.startAnimation(roundingalone);
//                timerHere.setBase(SystemClock.elapsedRealtime());
//                timerHere.start();
//            }
//        });


        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                icanchor.clearAnimation();
                timerHere.stop();
                Toast.makeText(getContext(),"stopped!",Toast.LENGTH_SHORT).show();
            }
        });

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), list.class);
                startActivity(intent);
            }
        });

        // Create dedicated button with an intent that takes the list of toDo's and takes you back
        // to the original ClickFragment

        btnschedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    protected void queryPosts() {
        ParseQuery<Plist> query = ParseQuery.getQuery(Plist.class);
        query.include(Post.KEY_USER);
        query.setLimit(20);
        query.addDescendingOrder(Post.KEY_CREATED_KEY);
        query.findInBackground(new FindCallback<Plist>() {
            @Override// changing from List to ArrayList
            public void done(List<Plist> posts, ParseException e) {
                if(e!=null){
                    Log.e(TAG, "Issue with getting posts",e);
                    return;
                }
                for(Plist post: posts){
                    Log.i(TAG, "Post: "+post.getAction());
                }
                posts.addAll(posts); //allPosts.addAll(posts) - changed to mimic arraylist in Plistadapter posts
                adapter.notifyDataSetChanged();
            }
        });
    }
    public void onListClick(int position){
        //ex: mnotes.get(position)
//        allPosts.get(position);
        Toast.makeText(getContext(),"clicked list! "+position,Toast.LENGTH_SHORT).show();
        icanchor.startAnimation(roundingalone);
                timerHere.setBase(SystemClock.elapsedRealtime());
                timerHere.start();
    }
}
