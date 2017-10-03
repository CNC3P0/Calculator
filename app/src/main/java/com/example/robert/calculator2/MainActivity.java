/*
 * App Name:        Rainbow Calculator
 * Package Name:    calculator2
 * Developer:       Robert Spanell
 * Student ID:      T00597013
 * Class:           COMP 2160
 * Date:            2017/10/03
 *
 * Comments:        Great exercise for starting out with Android Studio and also reacquainting
 *                  myself with Java. Admittedly the code ended up a bit of a Frankenstein's
 *                  monster and it needs better encapsulation.
 *                  If I could start over I probably would have tried a different approach,
 *                  perhaps Reverse Polish notation, instead of coming up with my own algorithm.
 *
 * Dedication:      As one of my first real apps this is for my 8-year-old daughter Khoen. Her
 *                  point of reference is, of course, games on her Android tablet. Of course she
 *                  wants me to learn how to design them. The name of my calculator, as well as
 *                  some of the colour scheme, is her input.
 */

package com.example.robert.calculator2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/*
 * This class implements a simple calculator that does addition, subtraction, multiplication
 * and division. It supports the decimal point and the negative sign. It also has a selection
 * of additional feature buttons. These are:  x² (squared), √ (square root), 1/x (inverse),
 * % (percent), +/- (change sign) and |x| (absolute value).
 * It supports accumulative processing only, not standard mathematical order of preference,
 * except as a side effect for the additional features as these are done inline when pressed.
 * The limit to the number you can type in is somewhere close to 1.8*10^308, so probably your
 * fingers will tire first. More important considerations are that the screen will only display
 * 51 characters at a time (3 screen lines, additional characters you may enter are off screen),
 * and that precision is lost when the answer is converted to scientific notation and displayed
 * on one line (17 characters).
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView output;

    boolean equalsIsActive = false;     // Flag for whether previous button was equals.
    boolean operatorlsActive = false;   // Flag for whether previous button was an operator.
    char previousOperator = '+';
    char currentOperator;
    double accumulator = 0;
    double currentValue;
    StringBuffer input = new StringBuffer();    // Someplace to hold what's been entered.
    CharSequence currentOutput;         // Someplace to retrieve what's on the display into.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output = (TextView) findViewById(R.id.display);
    }

    public void processBinary(char operator) {

        /*
         * A subroutine that is called from ifBinary and ifUnary methods.
         */

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

        /*
         * Executes if any digit is pressed.
         * First checks if last thing pressed was the equals button. If it was then it
         * resets things since this means a new operation has begun.
         */

        if (equalsIsActive) {
            accumulator = 0;
            equalsIsActive = false;
            output.setText("");
        }
        input.append(b);
        operatorlsActive = false;
    }

    public void ifDecimal(char b) {

        /*
         * Executes if the decimal is pressed.
         * First checks if last thing pressed was the equals button. If it was then it
         * resets things since this means a new operation has begun.
         * Checks if more than one decimal in a row has been pressed and suppresses them.
         */

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

        /*
         * Executes if any binary operator is pressed.
         * First checks if there is no active operation. If there is, then just outputs operator to
         * display and sets flag.
         */

        equalsIsActive = false;
        if (!operatorlsActive) {
            operatorlsActive = true;    // I think this is redundant but didn't want to risk
                                        // breakage so close to deadline.
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

        /*
         * Executes if any unary operator is pressed.
         * First checks if there is no active operation. If there is, then does nothing.
         * Performs required operation on the last entered value and replaces it on the screen so
         * that the next operation can proceed.
         */

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
                //Cases for use on values used within equations
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
                equalsIsActive = true;
            }
            else { //Cases for use on single values, primarily after pressing equals operator

                switch (currentOperator) {
                    case 'S':
                        accumulator = accumulator * accumulator;
                        break;
                    case 'R':
                        accumulator = Math.sqrt(accumulator);
                        break;
                    case 'I': //Inverse
                        accumulator = 1 / accumulator;
                        break;
                    case 'A': //Absolute value
                        accumulator = Math.abs(accumulator);
                        break;
                    case 'V': //inVert sign
                        accumulator = 0 - accumulator;
                        break;
                }
                output.setText(Double.toString(accumulator));
            }
            currentOperator = 0;
            input.setLength(0);
            operatorlsActive = false;
        }
    }

    public void ifEquals() {

        /*
         * Executes if equals is pressed.
         */

        if (input.length() != 0) {
            currentValue = Double.parseDouble(input.toString());
            switch (currentOperator) {  // I think I can use modify processBinary method here but
                                        // didn't want to risk breakage so close to deadline.
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
    public void onClick(View v) {

        /*
         * Monitors button presses and trigger events
         */

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