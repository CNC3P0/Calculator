package com.example.robert.calculator2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public void toast(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button one = (Button) findViewById(R.id.button1);
        one.setOnClickListener(this); // calling onClick() method
        Button two = (Button) findViewById(R.id.button2);
        two.setOnClickListener(this);
        Button three = (Button) findViewById(R.id.button3);
        three.setOnClickListener(this);
        Button four = (Button) findViewById(R.id.button4);
        four.setOnClickListener(this); // calling onClick() method
        Button five = (Button) findViewById(R.id.button5);
        five.setOnClickListener(this);
        Button six = (Button) findViewById(R.id.button6);
        six.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button0:
                toast("0");
                break;

            case R.id.button1:
                toast("1");
                break;

            case R.id.button2:
                toast("2");
                break;

            case R.id.button3:
                toast("3");
                break;

            case R.id.button4:
                toast("4");
                break;

            case R.id.button5:
                toast("5");
                break;

            case R.id.button6:
                toast("6");
                break;

            case R.id.button7:
                toast("7");
                break;

            case R.id.button8:
                toast("8");
                break;

            case R.id.button9:
                toast("9");
                break;

            case R.id.xsquared:
                toast("xsquared");
                break;

            case R.id.squareroot:
                toast("squareroot");
                break;

            case R.id.inverse:
                toast("inverse");
                break;

            case R.id.percent:
                toast("percent");
                break;

            case R.id.plus:
                toast("plus");
                break;

            case R.id.minus:
                toast("minus");
                break;

            case R.id.times:
                toast("times");
                break;

            case R.id.divide:
                toast("divide");
                break;

            case R.id.clear:
                toast("clear");
                break;

            case R.id.absolute:
                toast("absolute");
                break;

            case R.id.changesign:
                toast("changesign");
                break;

            case R.id.decimal:
                toast("decimal");
                break;

            case R.id.equals:
                toast("equals");
                break;


            default:
                break;
        }
    }

}
