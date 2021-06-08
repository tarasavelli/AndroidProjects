package com.example.teddytodolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView listView;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        addButton = (Button) findViewById(R.id.button);

        // will add whatever is in the edit text box whenever the button is pressed
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               addItem(v);
            }
        });

        items = new ArrayList<String>();
        itemsAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                items);

        listView.setAdapter(itemsAdapter);

        // deleting items and clearing list.
        setUpListViewListener();



    }

    private void addItem(View view){
        EditText input = findViewById(R.id.addItem);

        String inputText = input.getText().toString();

        if(!inputText.equals("")) {
            itemsAdapter.add(inputText);
            input.setText("");

        }
        else{
            Toast.makeText(getApplicationContext(), "Please enter a non-empty string", Toast.LENGTH_LONG).show();
        }

    }

    private void setUpListViewListener(){
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // telling user that item has been removed
                Context context = getApplicationContext();
                Toast.makeText(context, "Item removed", Toast.LENGTH_LONG).show();

                //removing item from array list
                items.remove(position);
                // refreshes the item adapter (makes sure the screen is displaying correct elements)
                itemsAdapter.notifyDataSetChanged();

                return true;
            }
        });
    }
}