package com.example.converter;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Locale;

public class MainActivity extends Activity{

    Spinner spinnerFrom, spinnerTo;
    EditText edtAmount;
    TextView txtResult;

    String [] currencies = {"VND", "USD", "EUR", "JPY", "INR", "ZAR",
            "LAK", "HUF", "THB", "CLP"};
    double[] toVND = {1, 23180.34, 27502.75, 221.39, 313.97, 1431.84, 2.51, 75.30, 739.64, 29.85};

    int fromPos = 0;
    int toPos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtAmount = findViewById(R.id.edt_amount);
        txtResult = findViewById(R.id.txt_result);

        spinnerFrom = findViewById(R.id.spinner_from);
        spinnerTo = findViewById(R.id.spinner_to);

        ArrayAdapter<String> aa = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                currencies);

        spinnerFrom.setAdapter(aa);
        spinnerTo.setAdapter(aa);

        edtAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculate();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fromPos = position;
                calculate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                toPos = position;
                calculate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void calculate() {
        double amount = Double.parseDouble(edtAmount.getText().toString().length() > 0 ? edtAmount.getText().toString() : "0");
        double vnd = amount * toVND[fromPos];
        double res = vnd / toVND[toPos];
        txtResult.setText(String.format(Locale.US,"%.2f", res));
    }
}