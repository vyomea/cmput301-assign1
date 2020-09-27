package com.example.vyome_gearbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Locale;

public class CustomList extends ArrayAdapter<Gear> {

    private ArrayList<Gear> gears;
    private Context context;

    public CustomList(Context context, ArrayList<Gear> gears) {
        super(context, 0, gears);
        this.gears = gears;
        this.context = context;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.content, parent, false);
        }

        Gear gear = gears.get(position);

        TextView gearDescription = view.findViewById(R.id.description_text);
        TextView gearPrice = view.findViewById(R.id.price_text);
        TextView gearDate = view.findViewById(R.id.date_text);

        gearDescription.setText(gear.getDescription());
        gearPrice.setText(String.format(Locale.getDefault(), "$ %.2f", gear.getPrice()));
        gearDate.setText(gear.getDate());

        return view;
    }
}
