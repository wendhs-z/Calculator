package com.wndynjtt.calculator;

import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class MainActivityPresenter {

    private static final String AC= "AC";
    private static final float ZERO = 0;

    private boolean isFirstNumber = true;

    Calculator calculator;

    TextView tvOutput;

    Button btnEquals, btnPlus, btnMinus, btnDivide, btnMultiply;

    public MainActivityPresenter(TextView tvOutput, Button btnEquals,
                                 Button btnPlus, Button btnMinus, Button btnDivide,
                                 Button btnMultiply)
    {
        this.btnEquals = btnEquals;
        this.btnPlus = btnPlus;
        this.btnMinus = btnMinus;
        this.btnDivide = btnDivide;
        this.btnMultiply = btnMultiply;
        this.tvOutput = tvOutput;
        calculator = new Calculator();

        btnEquals.setEnabled(false);
    }


    public void updateMethod(String method)
    {
        disableButtons();
        // calling this means firstequation has started and
        // entering number has ended
        calculator.enteredNumberIsComplete();
        // but we should still check the last number entered
        switch(method)
        {
            case "+":
                Log.d("wendy", "method: add");
                calculator.setMethod(Calculator.Method.ADDITION);
                btnPlus.setSelected(true);
                break;
            case "-":
                Log.d("wendy", "method: minus");
                calculator.setMethod(Calculator.Method.SUBTRACTION);
                btnMinus.setSelected(true);
                break; 
            case "x":
                Log.d("wendy", "method: multiply");
                calculator.setMethod(Calculator.Method.MULTIPLICATION);
                btnMultiply.setSelected(true);
                break;
            case "÷":
                Log.d("wendy", "method: divide");
                calculator.setMethod(Calculator.Method.DIVISION);
                btnDivide.setSelected(true);
                break;
        }

        tvOutput.setText("");
        isFirstNumber = true;
        disableButtons();
    }

    public void showOutput()
    {
        calculator.enteredNumberIsComplete();
        calculator.calculate();
        String output = calculator.getOutput();
        tvOutput.setText(output);
        resetButtons();
    }

    private void resetButtons()
    {
        btnDivide.setEnabled(true);
        btnMultiply.setEnabled(true);
        btnMinus.setEnabled(true);
        btnPlus.setEnabled(true);

        btnDivide.setSelected(false);
        btnMultiply.setSelected(false);
        btnMinus.setSelected(false);
        btnPlus.setSelected(false);
    }

    private void disableButtons()
    {
        btnDivide.setEnabled(false);
        btnMultiply.setEnabled(false);
        btnMinus.setEnabled(false);
        btnPlus.setEnabled(false);
    }

    public void setText(String n)
    {
        if(n.equals(AC))
        {
            tvOutput.setText("0");
            isFirstNumber = true;
            calculator.resetEquation();
            resetButtons();
            return;
        }

        // if it's first equation we set the textview's text.
        if(isFirstNumber)
        {
            tvOutput.setText(n);
            //then we toggle off the isFirstEquation so next
            isFirstNumber = false;
        }
        // if isFirstEquation is off then we assume that we'll
        //be appending.
        else
        {
            tvOutput.append(n);
        }

        calculator.updateEquation(n);

        btnEquals.setEnabled(true);
    }

}
