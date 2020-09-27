package com.example.vyome_gearbook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements FragmentAddGear.EditTextDialogListener {

    ListView gearList;
    ArrayAdapter<Gear> gearAdapter;
    ArrayList<Gear> gearDataList;
    Button addGearButton;
    int pos;
    TextView totalPriceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gearList = findViewById(R.id.gear_list);
        totalPriceTextView = findViewById(R.id.total_price_text_view);

        //Gear[] gears = {new Gear("2018-01-01", "Nike", "football", 100.50), new Gear("2017-01-01", "Adidas", "bat", 50.99, "good")};

        gearDataList = new ArrayList<>();

        //gearDataList.addAll(Arrays.asList(gears));

        gearAdapter = new CustomList(this, gearDataList);

        gearList.setAdapter(gearAdapter);

        addGearButton = findViewById(R.id.add_gear_button);
        addGearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FragmentAddGear().show(getSupportFragmentManager(), "ADD_GEAR");
            }
        });

        gearList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                pos = position;

                Button editButton = findViewById(R.id.edit_gear_button);
                editButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (pos < gearList.getCount() && pos >= 0) {
                            FragmentAddGear f = FragmentAddGear.newInstance(gearAdapter.getItem(pos));
                            f.show(getSupportFragmentManager(), "EDIT_GEAR");
                            pos = -1;
                        }
                    }
                });

                Button deleteButton = findViewById(R.id.delete_gear_button);
                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        // Cannot delete a gear which is not on the list
                        if (pos < gearList.getCount() && pos >= 0) {
                            gearDataList.remove(pos);
                            gearAdapter.notifyDataSetChanged();
                            pos = -1;
                            totalPriceTextView.setText(String.format(Locale.getDefault(), "Total price of gears: $ %.2f", calcTotalPrice(gearAdapter)));
                        }
                    }
                });

            }
        });

    }

    /**
     * Adds the new gear to the ListView and displays that on the screen
     *
     * @param gear  Gear  the Gear object to be added
     */
    public void addGear(Gear gear) {
        gearAdapter.add(gear);
        totalPriceTextView.setText(String.format(Locale.getDefault(), "Total price of gears: $ %.2f", calcTotalPrice(gearAdapter)));

    }

    /**
     * Edits a given gear and update the ListView
     *
     * @param gear          Gear            The Gear object to be edited,
     * @param date          String          New date of the gear
     * @param maker         String          New maker of the gear
     * @param description   String          New description of the gear
     * @param price         double          New price of the gear
     * @param comment       String          New comment of the gear
     */
    public void editGear(Gear gear, String date, String maker, String description, double price, String comment) {
        gear.setDate(date);
        gear.setMaker(maker);
        gear.setDescription(description);
        gear.setPrice(price);
        gear.setComment(comment);
        gearAdapter.notifyDataSetChanged();
        totalPriceTextView.setText(String.format(Locale.getDefault(), "Total price of gears: $ %.2f", calcTotalPrice(gearAdapter)));

    }

    /**
     * Calculates the total price of all the gears in GearAdapter
     *
     * @param gearAdapter   ArrayAdapter<Gear> ArrayAdapter with Gear objects
     * @return  double      The total price of all gears in the ArrayAdapter
     */
    public double calcTotalPrice(ArrayAdapter<Gear> gearAdapter) {

        double sum = 0;

        for (int i = 0; i < gearAdapter.getCount(); i++) {
            sum += Objects.requireNonNull(gearAdapter.getItem(i)).getPrice();
        }

        return sum;

    }
}