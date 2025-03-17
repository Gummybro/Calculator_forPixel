package edu.gannon.calculator;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView result;
    private String input = "";
    private String operator = "";
    private double num1 = 0, num2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);
        setNumberClickListeners();
        setOperationClickListeners();
    }

    private void setNumberClickListeners() {
        int[] numberButtons = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btnDot};
        for (int id : numberButtons) {
            findViewById(id).setOnClickListener(v -> {
                input += ((Button) v).getText();
                result.setText(input);
            });
        }
    }

    private void setOperationClickListeners() {
        int[] operationButtons = {R.id.btnAdd, R.id.btnSubtract, R.id.btnMultiply, R.id.btnDivide, R.id.btnPercent, R.id.btnEquals};
        for (int id : operationButtons) {
            findViewById(id).setOnClickListener(v -> {
                if (!input.isEmpty()) {
                    num1 = Double.parseDouble(input);
                    operator = ((Button) v).getText().toString();
                    input = "";
                }
            });
        }

        findViewById(R.id.btnEquals).setOnClickListener(v -> {
            if (!input.isEmpty()) {
                num2 = Double.parseDouble(input);
                double output = 0;
                switch (operator) {
                    case "+": output = num1 + num2; break;
                    case "-": output = num1 - num2; break;
                    case "×": output = num1 * num2; break;
                    case "÷": output = num2 != 0 ? num1 / num2 : 0; break;
                    case "%": output = num1 % num2; break;
                }
                result.setText(String.valueOf(output));
                input = String.valueOf(output);
            }
        });

        findViewById(R.id.btnClear).setOnClickListener(v -> {
            input = "";
            result.setText("0");
        });

        findViewById(R.id.btnBack).setOnClickListener(v -> {
            if (!input.isEmpty()) {
                input = input.substring(0, input.length() - 1);
                result.setText(input.isEmpty() ? "0" : input);
            }
        });
    }
}
