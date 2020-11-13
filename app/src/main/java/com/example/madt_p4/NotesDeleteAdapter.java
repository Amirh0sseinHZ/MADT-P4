package com.example.madt_p4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class NotesDeleteAdapter extends ArrayAdapter {

    private static LayoutInflater inflater = null;
    ArrayList<String> notes;

    public NotesDeleteAdapter(@NonNull Context context, int resource, @NonNull ArrayList<String> notes) {
        super(context, resource, notes);
        this.notes = notes;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null)
            view = inflater.inflate(R.layout.note_view_item, null);
        String note[] = notes.get(position).split("&&");
        TextView title = (TextView) view.findViewById(R.id.tvTitle);
        if(note[0].length() > 30) {
            title.setText(note[0].substring(0, 30) + "...");
        } else {
            title.setText(note[0]);
        }
        return view;
    }
}
