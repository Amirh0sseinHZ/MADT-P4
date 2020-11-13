package com.example.madt_p4;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;

public class DeleteNoteActivity extends AppCompatActivity {

    static NotesDeleteAdapter adapter;
    ListView lvNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_note);

        // Loading the notes from MainActivity
        lvNotes = (ListView)findViewById(R.id.lvNotes);
        adapter = new NotesDeleteAdapter(this, android.R.layout.simple_list_item_1,  MainActivity.listNoteItems);
        lvNotes.setAdapter(adapter);

        // Set Long Click Listener On The Items In Order To Remove Them
        lvNotes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id)
            {

                // Create and Show Confirmation Dialog
                new AlertDialog.Builder(DeleteNoteActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle(R.string.delete_title)
                        .setMessage(R.string.delete_confirmation)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)                        // to remove the selected note once "Yes" is pressed
                            {

                                // Removing the Item From the List
                                MainActivity.listNoteItems.remove(position);

                                // Notify the Adapters
                                MainActivity.adapter.notifyDataSetChanged();
                                DeleteNoteActivity.adapter.notifyDataSetChanged();

                                // Update the SharedPreferences
                                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.tanay.thunderbird.deathnote", Context.MODE_PRIVATE);
                                HashSet<String> set = new HashSet<>(MainActivity.listNoteItems);
                                sharedPreferences.edit().putStringSet("notes", set).apply();
                            }
                        })

                        .setNegativeButton(R.string.no, null)
                        .show();

                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.notes_options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.list_note:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.add_note:
                startActivity(new Intent(this, AddNoteActivity.class));
                break;
            case R.id.delete_note:
                startActivity(new Intent(this, DeleteNoteActivity.class));
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
