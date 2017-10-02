package com.example.robert.calculator2;

import android.app.ActivityManager;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView output;

    boolean equalsIsActive = false;
    boolean operatorlsActive = false;
    char previousOperator = '+';
    char currentOperator;
    double accumulator = 0;
    double currentValue;
    StringBuffer input = new StringBuffer();
    CharSequence currentOutput;

    public void toast(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output = (TextView) findViewById(R.id.display);
    }

    public void ifDigit(char b) {
        if (equalsIsActive) {
            accumulator = 0;
            equalsIsActive = false;
            output.setText("");
        }
        input.append(b);
        operatorlsActive = false;
    }

    public void ifBinary(char b) {
        equalsIsActive = false;
        if (!operatorlsActive) {
            operatorlsActive = true;
            if (currentOperator == 0) {
                previousOperator = '+';
            } else {
                previousOperator = currentOperator;
            }
            currentOperator = b;
            if (input.length() != 0) {
                currentValue = Double.parseDouble(input.toString());

                switch (previousOperator) {
                    case '+':
                        accumulator += currentValue;
                        break;
                    case '-':
                        accumulator -= currentValue;
                        break;
                    case '*':
                        accumulator *= currentValue;
                        break;
                    case '÷':
                        accumulator /= currentValue;
                        break;
                }
                input.setLength(0);
            }
        output.append(String.valueOf(b));
        }
        operatorlsActive = true;
    }

    public void ifUnary(char b) {
        if (!operatorlsActive) {
            operatorlsActive = true;
            //if (currentOperator == 0) {
            //    previousOperator = '+';
            //} else {
                previousOperator = currentOperator;
            //}
            currentOperator = b;
            if (input.length() != 0) {
                currentValue = Double.parseDouble(input.toString());

                switch (currentOperator) {
                    case 'S': //squared
                        currentOutput = output.getText();

                        //currentOutput = // remove as many characters as the length of currentValue [input.length()];
                        output.setText(currentOutput);
                        currentValue = currentValue * currentValue;
                        ifBinary(previousOperator);
                        break;
                    case 'R': //square root
                        //
                        accumulator -= currentValue;
                        break;
                    case 'I': //inverse
                        //
                        accumulator *= currentValue;
                        break;
                    case '%': //percent
                        //
                        accumulator /= currentValue;
                        break;
                    case 'A': //absolute value
                        //
                        accumulator *= currentValue;
                        break;
                    case 'V': //invert sign
                        //
                        accumulator /= currentValue;
                        break;
                }


                //output.append(String.valueOf(currentValue));
                currentOperator = 0;
                input.setLength(0);
                operatorlsActive = false;
            }
            //output.append(String.valueOf(b));
        }

    }

    public void ifEquals() {
        if (input.length() != 0) {
            currentValue = Double.parseDouble(input.toString());
            switch (currentOperator) {
                case '+':
                    accumulator += currentValue;
                    break;
                case '-':
                    accumulator -= currentValue;
                    break;
                case '*':
                    accumulator *= currentValue;
                    break;
                case '÷':
                    accumulator /= currentValue;
                    break;
            }
                input.setLength(0);
        }
        output.setText(Double.toString(accumulator));
        input.setLength(0);
        previousOperator = '+';
        currentOperator = 0;
        operatorlsActive = false;
        equalsIsActive = true;
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

            case R.id.decimal: //uses single char "." for decimal
                ifDigit('.');
                output.append(".");
                break;

            //binary operators
            case R.id.plus: //uses single char "+" for add
                ifBinary('+');
                break;

            case R.id.minus: //uses single char "-" for subtract
                ifBinary('-');
                break;

            case R.id.times: //uses single char "*" for multiply
                ifBinary('*');
                break;

            case R.id.divide: //uses single char "÷" for divide
                ifBinary('÷');
                break;

            //unary operators
            case R.id.xsquared: //uses single char "S" for Squared
                ifUnary('S');
                //output.append("x²");
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

            case R.id.absolute: //uses single char "A" for Absolute
                output.append("|x|");
                break;

            case R.id.invertsign: //uses single char "V" for inVert sign (didn't want to use C because of Clear)
                output.append("+/-");
                break;

            //equals
            case R.id.equals: //uses single char "=" for equals
                ifEquals();
                break;

            //clear all
            case R.id.clear:
                operatorlsActive = false;
                previousOperator = '+';
                currentOperator = 0;
                accumulator = 0;
                currentValue = 0;
                input.setLength(0);
                output.setText("");
                break;

            default:
                break;
        }
    }
}