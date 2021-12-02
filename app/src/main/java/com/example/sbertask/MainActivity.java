package com.example.sbertask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, View.OnClickListener{

    public static final int PERCENT = 3;
    private TextView sumTView, timeTView, valueTView, percentTView;
    private SeekBar barSum, barTime, barValue;
    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sumTView = findViewById(R.id.sumView);
        timeTView = findViewById(R.id.timeView);
        valueTView = findViewById(R.id.valueView);
        percentTView = findViewById(R.id.percentView);

        barSum = findViewById(R.id.barSum);
        barSum.setOnSeekBarChangeListener(this);
        barTime = findViewById(R.id.barTime);
        barTime.setOnSeekBarChangeListener(this);
        barValue = findViewById(R.id.barValue);
        barValue.setOnSeekBarChangeListener(this);

        calculateButton = findViewById(R.id.calculateButton);
        calculateButton.setOnClickListener(this);

        setDefaultValues();
    }

    public void setDefaultValues() {
        percentTView.setText(PERCENT + "%");
        sumTView.setText(String.valueOf(barSum.getProgress()));
        timeTView.setText(String.valueOf(barTime.getProgress()));
        valueTView.setText(String.valueOf(barValue.getProgress()));
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if (seekBar.getId() == R.id.barSum) {
            sumTView.setText(String.valueOf(seekBar.getProgress()));
        }
        else if (seekBar.getId() == R.id.barTime) {
            timeTView.setText(String.valueOf(seekBar.getProgress()));
        }
        else {
            valueTView.setText(String.valueOf(seekBar.getProgress()));
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, CalculationActivity.class);
        if (sumTView.getText() != null && timeTView.getText() != null && valueTView.getText() != null) {
            int[] data = new int[]{
                    Integer.parseInt(sumTView.getText().toString()),
                    Integer.parseInt(timeTView.getText().toString()),
                    Integer.parseInt(valueTView.getText().toString()),
                    PERCENT
            };
            intent.putExtra("data", data);
            startActivity(intent);
        }
    }
}