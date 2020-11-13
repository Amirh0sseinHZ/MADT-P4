package com.example.madt_p4;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;

public class AddNoteActivity extends AppCompatActivity {

    Resources res;
    EditText edNoteTitle, edNoteBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        res = getResources();

        this.edNoteTitle = findViewById(R.id.edNoteTitle);
        edNoteTitle.setHint(R.string.note_title_placeholder);

        this.edNoteBody = findViewById(R.id.edNoteBody);
        edNoteBody.setHint(R.string.note_body_placeholder);
    }

    public void onBtnSaveAndCloseClick(View view) {

        // Getting Inputs
        String title = this.edNoteTitle.getText().toString().trim();
        String body = this.edNoteBody.getText().toString().trim();

        // Validation
        if(ifEmpty("Title", title)) return;
        if(ifEmpty("Body", body)) return;

        // Creating a Note
        MainActivity.listNoteItems.add("");
        int noteID = MainActivity.listNoteItems.size() - 1;

        // Updating the Note with content
        MainActivity.listNoteItems.set(noteID, title + "&&" + body);

        // Adding the note on SharedPreferences
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.tanay.thunderbird.deathnote", Context.MODE_PRIVATE);
        HashSet<String> set = new HashSet<>(MainActivity.listNoteItems);
        sharedPreferences.edit().putStringSet("notes", set).apply();

        // Notify the adapters
        MainActivity.adapter.notifyDataSetChanged();
        DeleteNoteActivity.adapter.notifyDataSetChanged();

        // Finishing Up and Moving Back To MainActivity
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    private boolean ifEmpty(String tag, String val) {
        if(val.equals("")) {
            Toast.makeText(getApplicationContext(),
                    tag + " " + res.getString(R.string.error_cannot_be_emoty),
                    Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}
