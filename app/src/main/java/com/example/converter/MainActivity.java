package com.example.converter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner spinnerOptions;
    ImageButton weight, temperature, length;
    EditText numberInput;
    TextView unit1, unit2, unit3, value1, value2, value3;
    String chosen;
    Double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Spinner assignment/setup with string array of options in resource folder.
        spinnerOptions = findViewById(R.id.spinnerOptions);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource( this, R.array.options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOptions.setAdapter(adapter);
        spinnerOptions.setOnItemSelectedListener(this);

        // String containing currently selected spinner item for comparisons.
        chosen = spinnerOptions.getSelectedItem().toString();

        // Button image assignments and listeners.
        temperature = findViewById(R.id.temperatureButton);
        temperature.setOnClickListener(new TemperatureOnClickListener());

        weight = findViewById(R.id.weightButton);
        weight.setOnClickListener(new WeightOnClickListener());

        length = findViewById(R.id.lengthButton);
        length.setOnClickListener(new LengthOnClickListener());

        // EditText & TextView assignments.
        numberInput = findViewById(R.id.numberInput);
        value1 = findViewById(R.id.value1);
        value2 = findViewById(R.id.value2);
        value3 = findViewById(R.id.value3);
        unit1 = findViewById(R.id.unit1);
        unit2 = findViewById(R.id.unit2);
        unit3 = findViewById(R.id.unit3);

    }

    // Displays a Toast of the error encountered.
    private void displayError(){
        // Error for no user input.
        if (numberInput.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, R.string.input_error, Toast.LENGTH_LONG).show();
        }
        // Error for not matching spinner conversion with conversion image.
        else {
            Toast.makeText(MainActivity.this, R.string.conversion_error, Toast.LENGTH_LONG).show();
        }
    }
    // Setting the text for values and units.
    private void conversionText (TextView value, TextView unit, Double result, String conversion) {
        value.setText(String.format("%.2f", result));
        unit.setText(conversion);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // Sets current selection to a String for comparison with button selection.
        chosen = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private class TemperatureOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // Check spinner and button matches and input isn't blank.
            if ("Celsius".equals(chosen) & !numberInput.getText().toString().isEmpty()) {

                // Celsius to Fahrenheit.
                result = ((Double.parseDouble(numberInput.getText().toString())) * 9/5) + 32;
                conversionText(value1, unit1, result, "Fahrenheit");

                // Celsius to Kelvin.
                result = (Double.parseDouble(numberInput.getText().toString())) + 273.15;
                conversionText(value2, unit2, result, "Kelvin");

                // Empty third row.
                value3.setText(null);
                unit3.setText(null);
            }
            else {
                displayError();
            }
        }
    }

    private class WeightOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if ("Kilograms".equals(chosen) & !numberInput.getText().toString().isEmpty()) {

                // Kilograms to Grams.
                result = (Double.parseDouble(numberInput.getText().toString())) * 1000;
                conversionText(value1, unit1, result, "Gram");

                // Kilograms to Ounces.
                result = (Double.parseDouble(numberInput.getText().toString())) * 35.274;
                conversionText(value2, unit2, result, "Ounce(Oz)");

                // Kilograms to Pounds.
                result = (Double.parseDouble(numberInput.getText().toString())) * 2.205;
                conversionText(value3, unit3, result, "Pound(lb)");
            }
            else {
                displayError();
            }
        }
    }

    private class LengthOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if ("Metre".equals(chosen) & !numberInput.getText().toString().isEmpty()) {

                // Metre to Centimetre.
                result = (Double.parseDouble(numberInput.getText().toString())) * 100;
                conversionText(value1, unit1, result, "Centimetre");

                // Metre to Foot.
                result = (Double.parseDouble(numberInput.getText().toString())) * 3.281;
                conversionText(value2, unit2, result, "Foot");

                // Metre to Inch.
                result = (Double.parseDouble(numberInput.getText().toString())) * 39.37;
                conversionText(value3, unit3, result, "Inch");
            }
            else {
                displayError();
            }
        }
    }
}