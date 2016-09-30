package com.example.aaron.recycleviewexample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by User on 9/30/2016.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {


    // Store a member variable for the contacts
    public List<Contact> mContacts;
    // Store the context for easy access
    public Context mContext;
    public static RecyclerViewClickListener itemListener;

    // Pass in the contact array into the constructor
    public ContactsAdapter(Context context, List<Contact> contacts) {
        mContacts = contacts;
        mContext = context;

    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.contact_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(context, contactView, mContacts);
        return viewHolder;


    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ContactsAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Contact contact = mContacts.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.nameTextView;
        textView.setText(contact.getName());
        Button button = viewHolder.messageButton;
        button.setText("Message");
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mContacts.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public ImageView imageConatct;
        public TextView nameTextView;
        public Button messageButton;
        public Context context;
        public List<Contact> listContacts;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(Context context, View itemView, List<Contact> listContacts) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.contact_name);
            messageButton = (Button) itemView.findViewById(R.id.message_button);
            imageConatct = (ImageView) itemView.findViewById(R.id.imageView);


            Glide.with(itemView.getContext()).load("http://www.tutos-android.com/wp-content/uploads/2016/02/glide_logo.png").override(150,150).centerCrop().into(imageConatct);

            this.context = context;
            this.listContacts = listContacts;
            // Attach a click listener to the entire row view
            itemView.setOnClickListener(this);

        }

        // Handles the row being being clicked
        @Override
        public void onClick(View view) {
            int position = getAdapterPosition(); // gets item position

            //RecyclerView rv = ((RecyclerView) view.findViewById(R.id.rvContacts));

            if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                //Contact user = mContacts.get(position);
                // We can access the data within the views
                //Log.d("DDD",  user.getName());
                removeAt(position);
            }
        }
    }

    public void removeAt(int position){
        mContacts.remove(position);
        notifyItemRemoved(position);
        //notifyItemRangeChanged(position, mContacts.size());
    }

    public interface RecyclerViewClickListener
    {

        public void recyclerViewListClicked(View v, int position);
    }

}
