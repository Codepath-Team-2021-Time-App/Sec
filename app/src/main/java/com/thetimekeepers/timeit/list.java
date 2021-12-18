package com.thetimekeepers.timeit;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.thetimekeepers.timeit.PostsAdapter;


public class list extends AppCompatActivity implements Serializable {
    //    public List<String> items;
    ArrayList<String> items = new ArrayList<>();
    Button btnAdd;
    Button btnSendDataBack;
    EditText etItem;
    RecyclerView rvItems;
    ItemsAdapter itemsAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        btnAdd = findViewById(R.id.btnAdd);
        etItem = findViewById(R.id.etItem); //why?
        rvItems = findViewById(R.id.rvItems);
        btnSendDataBack = findViewById(R.id.btnSend);

        allPosts = new ArrayList<>();
        loadItems();


        ItemsAdapter.OnLongClickListener onLongClickListener= new ItemsAdapter.OnLongClickListener(){
            @Override
            public void onItemLongClicked(int position) {
                //Delete the item from the model
                //deleting from back4app
                String value = items.get(position);
                items.remove(position);

                //same for both posts - removing from back4app
                post.remove(value);

                //notify the adapter
                itemsAdapter.notifyItemRemoved(position);
                Toast.makeText(getApplicationContext(),"item was removed", Toast.LENGTH_SHORT).show();
                saveItems();
            }
        };

        itemsAdapter = new ItemsAdapter(items, onLongClickListener);
        rvItems.setAdapter(itemsAdapter);
        rvItems.setLayoutManager(new LinearLayoutManager(this));

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todoItem = etItem.getText().toString();
                Toast.makeText(getApplicationContext(),todoItem+" item was added", Toast.LENGTH_SHORT).show();
                //add item to the model
                items.add(todoItem);

                //adding to back4app!!
                post.put("action",todoItem);

                //SAVING TO DATABASE!!!
                post.saveInBackground();

                //Notify adapter that an item is inserted
                itemsAdapter.notifyItemInserted(items.size() - 1);
                etItem.setText("");
                Toast.makeText(getApplicationContext(),"item was added", Toast.LENGTH_SHORT).show();
                saveItems();
            }
        });
    }

    public File getDataFile() {
        return new File(getFilesDir(), "data.txt");
    }

    //This function will load items by reading every line of the data file
    public void loadItems() {

        try {
            items = new ArrayList<>(FileUtils.readLines(getDataFile(), Charset.defaultCharset()));
        } catch (IOException e) {
            Log.e("MainActivity", "Error reading items", e);
            items = new ArrayList<>();
        }
    }
    //This function saves items by writing them into the data file
    public void saveItems(){
        try {
            FileUtils.writeLines(getDataFile(),items);
        } catch (IOException e) {
            Log.e("MainActivity", "Error writing items", e);
        }
    }
}

// this is what I tried to do without using parse (tried to use parcel or Serializable
//remove extends Serializable
//        btnSendDataBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                 Create an intent
//                Intent intent = new Intent(list.this, ClockFragment.class);
//                intent.putExtra("list",Parcels.wrap(items));
//                //not sure of this:
//                startActivity(intent);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                finish();
//                goes back to original but don't think I go back with it
//                 wrap the list object in a Parcel
//                Intent intent = new Intent(list.class, ClockFragment.class);
//                don't know if it's setup this way
//                Fragment ClockFragment = new Fragment(); // this does not work as proper
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("key", items);
//                ClockFragment.setArguments(bundle);
//                return ClockFragment;
//                intent.putExtra(bundle);
//                startActivity(intent);
//
//                 go to the mainPage with the new data and extract it:
//            }
//        });
