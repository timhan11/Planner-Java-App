package com.zybooks.projectthreecs360;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeJava extends AppCompatActivity {
    private ArrayList<String> events;
    //array named events to store the events
    private ArrayAdapter<String> eventAdapter;
    //array adapter named eventAdapter
    private ListView listView;
    //
    private Button button3, logoutbutton;
//private for each one




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        logoutbutton = findViewById(R.id.logoutbutton);


        listView = findViewById(R.id.listView);
        button3 = findViewById(R.id.button3);
//set listview and button3 to their respective IDs
        //onclick listener, call onClick, get view, then call addEvent function
        button3.setOnClickListener(view -> addEvent());

        events = new ArrayList<>();
        //create new array list
        eventAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, events);
        listView.setAdapter(eventAdapter);
        //set the list view to the event adapter
        listViewListener();

        logoutbutton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), LoginJava.class);
            startActivity(intent);
        });
        //on click listener for log out, send user back to sign in screen




    }

    private void listViewListener() {
        listView.setOnItemLongClickListener((adapterView, view, i, l) -> {
            Context context = getApplicationContext();
            Toast.makeText(context, "Event Removed", Toast.LENGTH_LONG).show();
            events.remove(i);
            eventAdapter.notifyDataSetChanged();
            return true;
            //on user holding down on event, delete the event and inform the user the event
            //has been deleted

        });
    }

    private void addEvent() {
        EditText input = findViewById(R.id.editTextText2);
        //edit text set equal to its ID
        String eventText = input.getText().toString();
        //get the item text and set it equal to a string
        if (!(eventText.equals(""))) {
            eventAdapter.add(eventText);
            input.setText("");
            //if the event text doesnt equal blank text, add the event to the array adapter
            //then set the edit take to an empty string
        }
        else {
            Toast.makeText(getApplicationContext(), "Enter Text", Toast.LENGTH_LONG).show();
            //else, don't add it and inform the user to enter text
        }
    }


}
