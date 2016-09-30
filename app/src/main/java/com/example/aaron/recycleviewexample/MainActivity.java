package com.example.aaron.recycleviewexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Contact> contacts;
    RecyclerView rvContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvContacts = (RecyclerView) findViewById(R.id.rvContacts);

        // Initialize contacts
        contacts = Contact.createContactsList(20);
        // Create adapter passing in the sample user data
        ContactsAdapter adapter = new ContactsAdapter(this, contacts);
        // Attach the adapter to the recyclerview to populate items
        rvContacts.setAdapter(adapter);
        // Set layout manager to position the items
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        // That's all!


    }

    public void addContact(View view) {

        // Add a new contact
        contacts.add(0, new Contact("Barney", true));
        // Notify the adapter that an item was inserted at position 0
        rvContacts.getAdapter().notifyItemInserted(0);

    }

    public void showMess(View view) {
        TextView tx = ((TextView) findViewById(R.id.contact_name));
        Toast.makeText(this, tx.getText().toString(), Toast.LENGTH_SHORT);
        Log.d("DEB", tx.getText().toString());
    }
}
