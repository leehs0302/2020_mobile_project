package com.example.excalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

public class CalculatorActivity extends Activity {
    Button btnChange1,btnChange2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        btnChange1 = findViewById(R.id.btnChangeMoney1);
        btnChange2 = findViewById(R.id.btnChangeMoney2);

        btnChange1.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalculatorActivity.this,MoneyListActivity.class);
                startActivityForResult(intent,1);
            }
        });

        btnChange2.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalculatorActivity.this,MoneyListActivity.class);
                startActivityForResult(intent,2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                btnChange1.setText(data.getStringExtra("result"));
            }
        }
        else{
            if(resultCode == RESULT_OK){
                btnChange2.setText(data.getStringExtra("result"));
            }
        }
    }
}
