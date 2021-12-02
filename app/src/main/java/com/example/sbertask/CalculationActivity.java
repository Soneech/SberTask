package com.example.sbertask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalculationActivity extends AppCompatActivity{

    private TextView benefitTView;
    private TextView fullSumTView;
    private Button backButton;
    public static final int CALCULATION_ACTIVITY_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);
        Bundle bundle = getIntent().getExtras();

        int months = 12;

        benefitTView = findViewById(R.id.benefitView);
        fullSumTView = findViewById(R.id.fullSumView);
        backButton = findViewById(R.id.backButton);

        if (bundle != null) {
            int[] data = bundle.getIntArray("data");
            int startSum = data[0];
            int time = data[1];
            int value = data[2];
            float koeff = (float)data[3] / months / 100 + 1;

            float fullSum = startSum * koeff;
            float sumWithoutPercent = startSum;

            for (int i = 0; i < time - 1; ++i) {
                fullSum += value;
                fullSum *= koeff;
                sumWithoutPercent += value;
            }
            float benefit = fullSum - sumWithoutPercent;
            benefitTView.setText(String.format("%.1f", benefit));
            fullSumTView.setText(String.format("%.1f", fullSum));
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(CALCULATION_ACTIVITY_CODE, intent);
                finish();
            }
        });
    }
}