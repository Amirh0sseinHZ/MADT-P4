package com.example.madt_p4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    static ArrayList<String> listNoteItems = new ArrayList<String>();
    static NotesAdapter adapter;
    ListView lvNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvNotes = (ListView)findViewById(R.id.lvNotes);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.tanay.thunderbird.deathnote", Context.MODE_PRIVATE);
        HashSet<String> set = (HashSet<String>)sharedPreferences.getStringSet("notes", null);

        if(set == null)
        {
            listNoteItems.add(String.valueOf(R.string.empty_list));
        }
        else
        {
            listNoteItems = new ArrayList<>(set);
        }

        adapter = new NotesAdapter(this, android.R.layout.simple_list_item_1, listNoteItems);
        lvNotes.setAdapter(adapter);
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