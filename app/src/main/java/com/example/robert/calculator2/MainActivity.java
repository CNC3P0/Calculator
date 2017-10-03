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

    boolean equalsIsActive = false;     // Flag for whether previous button was equals
    boolean operatorlsActive = false;   // Flag for whether previous button was an operator
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

    public void processBinary(char operator) {
        switch (operator) {
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

    public void ifDecimal(char b) {
        if (equalsIsActive) {
            accumulator = 0;
            equalsIsActive = false;
            output.setText("");
        }
        if (input.indexOf(".") == -1) {
            input.append(b);
            output.append(".");
            operatorlsActive = false;
        }
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
                processBinary(previousOperator);
                input.setLength(0);
            }
        output.append(String.valueOf(b));
        }
        operatorlsActive = true;
    }

    public void ifUnary(char b) {
        if (!operatorlsActive) {
            //operatorlsActive = true;
            if (currentOperator == 0) {
                previousOperator = '+';
            } else {
                previousOperator = currentOperator;
            }
            currentOperator = b;
            if (input.length() != 0) {
                currentValue = Double.parseDouble(input.toString());

                switch (currentOperator) {
                    case 'S': //Squared
                        currentOutput = output.getText();
                        currentOutput = currentOutput.subSequence(0,currentOutput.length()-input.length());
                        currentValue = currentValue * currentValue;
                        currentOutput = currentOutput + Double.toString(currentValue);
                        output.setText(currentOutput);
                        processBinary(previousOperator); //apply previous function to this result
                        break;
                    case 'R': //square Root
                        currentOutput = output.getText();
                        currentOutput = currentOutput.subSequence(0,currentOutput.length()-input.length());
                        currentValue = Math.sqrt(currentValue);
                        currentOutput = currentOutput + Double.toString(currentValue);
                        output.setText(currentOutput);
                        processBinary(previousOperator);
                        break;
                    case 'I': //Inverse
                        currentOutput = output.getText();
                        currentOutput = currentOutput.subSequence(0,currentOutput.length()-input.length());
                        currentValue = 1 / currentValue;
                        currentOutput = currentOutput + Double.toString(currentValue);
                        output.setText(currentOutput);
                        processBinary(previousOperator);
                        break;
                    case '%': //percent
                        currentOutput = output.getText();
                        currentOutput = currentOutput.subSequence(0,currentOutput.length()-input.length());
                        currentValue = accumulator * currentValue / 100;
                        currentOutput = currentOutput + Double.toString(currentValue);
                        output.setText(currentOutput);
                        processBinary(previousOperator);
                        break;
                    case 'A': //Absolute value
                        currentOutput = output.getText();
                        currentOutput = currentOutput.subSequence(0,currentOutput.length()-input.length());
                        currentValue = Math.abs(currentValue);
                        currentOutput = currentOutput + Double.toString(currentValue);
                        output.setText(currentOutput);
                        processBinary(previousOperator);
                        break;
                    case 'V': //inVert sign
                        currentOutput = output.getText();
                        currentOutput = currentOutput.subSequence(0,currentOutput.length()-input.length());
                        currentValue = 0 - currentValue;
                        currentOutput = currentOutput + Double.toString(currentValue);
                        output.setText(currentOutput);
                        processBinary(previousOperator);
                        break;
                }
                currentOperator = 0;
                input.setLength(0);
                operatorlsActive = false;
            }
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
                default:
                    accumulator = currentValue;
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
    public void onClick(View v) { //Monitor button presses and trigger events
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
                ifDecimal('.');

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
                ifUnary('R');
                break;

            case R.id.inverse: //uses single char "I" for Inverse
                ifUnary('I');
                break;

            case R.id.percent: //uses single char "%" for percent
                ifUnary('%');
                break;

            case R.id.absolute: //uses single char "A" for Absolute
                ifUnary('A');
                break;

            case R.id.invertsign: //uses single char "V" for inVert sign (didn't want to use C because of Clear)
                ifUnary('V');
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