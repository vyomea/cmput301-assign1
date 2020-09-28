package com.example.vyome_gearbook;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Locale;
import java.util.Objects;
import java.util.regex.Pattern;

public class FragmentAddGear extends DialogFragment {
    private EditText dateEditText;
    private EditText makerEditText;
    private EditText descriptionEditText;
    private EditText priceEditText;
    private EditText commentEditText;
    private EditTextDialogListener listener;

    interface EditTextDialogListener {
        void addGear(Gear gear);
        void editGear(Gear gear, String date, String maker, String description, double price, String comment);
    }

    static FragmentAddGear newInstance(Gear gear) {
        Bundle args = new Bundle();
        args.putSerializable("gear", gear);

        FragmentAddGear fragment = new FragmentAddGear();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (EditTextDialogListener) context;

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        final Gear gearArg;

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_add_gear, null);

        dateEditText = view.findViewById(R.id.date_edit_text);
        makerEditText = view.findViewById(R.id.maker_edit_text);
        descriptionEditText = view.findViewById(R.id.description_edit_text);
        priceEditText = view.findViewById(R.id.price_edit_text);
        commentEditText = view.findViewById(R.id.comment_edit_text);

        if (getArguments() != null) {
            gearArg = (Gear) getArguments().getSerializable("gear");

            assert gearArg != null;
            dateEditText.setText(gearArg.getDate());
            makerEditText.setText(gearArg.getMaker());
            descriptionEditText.setText(gearArg.getDescription());
            priceEditText.setText(String.format(Locale.getDefault(), "%.2f", gearArg.getPrice()));
            commentEditText.setText(gearArg.getComment());
        } else {
            gearArg = null;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder
                .setView(view)
                .setTitle("Enter the gear details (* are required)")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String date = dateEditText.getText().toString();
                        String maker = makerEditText.getText().toString();
                        String description = descriptionEditText.getText().toString();
                        String priceString = priceEditText.getText().toString();
                        double price = -1;

                        if (!priceString.isEmpty()) {
                            price = Double.parseDouble(priceString);
                        }

                        String comment = commentEditText.getText().toString();

                        // Input validation, checking if all required fields are filled correctly
                        // Regex expression is from here: https://stackoverflow.com/a/22061879/11656468
                        if (date.isEmpty() || maker.isEmpty() || description.isEmpty() || priceString.isEmpty() || price < 0 || maker.length() > 20
                            || description.length() > 40 || comment.length() > 20 || !Pattern.compile("^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$").matcher(date).matches()) {
                            Toast toast = Toast.makeText(Objects.requireNonNull(getActivity()).getBaseContext(), "Enter valid values for the required fields", Toast.LENGTH_LONG);
                            toast.show();
                            return;
                        }

                        Gear gear;

                        if (comment.isEmpty()) {
                            gear = new Gear(date, maker, description, price);

                        } else {
                            gear = new Gear(date, maker, description, price, comment);
                        }

                        if (getArguments() != null) {
                            listener.editGear(gearArg, date, maker, description, price, comment);

                        } else {
                            listener.addGear(gear);
                        }

                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
    }
}
