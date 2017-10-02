package com.example.robert.calculator2;

import android.app.ActivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView output;
    private boolean equalsIsActive = false;
    private boolean operatorlsActive = false;
    // private String input;
    private char previousOperator = '+';
    private char currentOperator;
    private float accumulator = 0;
    private float currentValue;

    StringBuffer input = new StringBuffer();

    public void toast(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        output = (TextView) findViewById(R.id.display);
        //output.setText("numbers go here");
    }

    public void ifDigit(char b) {
        if (equalsIsActive) {
            accumulator = 0;
            equalsIsActive = false;
        }
        input.append(b);
        operatorlsActive = false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            //digits
            case R.id.button0:
                ifDigit('0');
                output.append("0");
                break;

            case R.id.button1:
                ifDigit('1');
                output.append("1");
                break;

            case R.id.button2:
                ifDigit('2');
                output.append("2");
                break;

            case R.id.button3:
                ifDigit('3');
                output.append("3");
                break;

            case R.id.button4:
                ifDigit('4');
                output.append("4");
                break;

            case R.id.button5:
                ifDigit('5');
                output.append("5");
                break;

            case R.id.button6:
                ifDigit('6');
                output.append("6");
                break;

            case R.id.button7:
                ifDigit('7');
                output.append("7");
                break;

            case R.id.button8:
                ifDigit('8');
                output.append("8");
                break;

            case R.id.button9:
                ifDigit('9');
                output.append("9");
                break;

            //clear all
            case R.id.clear:
                output.setText("");
                break;

            //operators
            case R.id.xsquared: //uses single char "S" for Squared
                output.append("x²");
                break;

            case R.id.squareroot: //uses single char "R" for Root
                output.append("√");
                break;

            case R.id.inverse: //uses single char "I" for Inverse
                output.append("1/x");
                break;

            case R.id.percent: //uses single char "%" for percent
                output.append("%");
                break;

            case R.id.plus: //uses single char "+" for add
                output.append("+");
                break;

            case R.id.minus: //uses single char "-" for subtract
                output.append("-");
                break;

            case R.id.times: //uses single char "*" for multiply
                output.append("*");
                break;

            case R.id.divide: //uses single char "/" for divide
                output.append("/");
                break;

            case R.id.absolute: //uses single char "A" for Absolute
                output.append("|x|");
                break;

            case R.id.invertsign: //uses single char "V" for inVert sign (didn't want to use C because of Clear)
                output.append("+/-");
                break;

            case R.id.decimal: //uses single char "." for decimal
                output.append(".");
                break;

            case R.id.equals: //uses single char "=" for equals
                output.append("=");
                break;

            default:
                break;
        }

    }

}
