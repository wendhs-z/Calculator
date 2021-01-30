package com.wndynjtt.calculator;

import android.util.Log;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class Calculator {

    private float output = 0;

    private boolean hasAnswer = false;

    List<Object> equation;

    String number = "0";

    public enum Method
    {
        ADDITION, SUBTRACTION, DIVISION, MULTIPLICATION, NULL
    }

    public Calculator() {
        equation = new ArrayList<>();
    }


    public void setMethod(Method method)
    {
        if(equation.size() >= 3)
        {
            calculate();
        }
        equation.add(method);
        Log.d("wendy", "equation on setMethod: " + equation.toString());
    }

    public void calculate()
    {
        Log.d("wendy", "equation" + equation.toString());
        if(equation.size() >=3)
        {
            float n1 = 0, n2 = 0, answer = 0;

            if(equation.get(0) instanceof String)
            {
                n1 = Float.parseFloat((String) equation.get(0));
            }

            if(equation.get(2) instanceof String)
            {
                n2 = Float.parseFloat((String) equation.get(2));
            }

            if(equation.get(1) instanceof Method)
            {
                Method currentMethod = (Method) equation.get(1);
                switch (currentMethod)
                {
                    case ADDITION:
                        answer = n1 + n2;
                        Log.d("wendy", "equation n1: " + n1);
                        Log.d("wendy", "equation n2: " + n2);
                        Log.d("wendy", "equation add: " + answer);
                        break;
                    case DIVISION:
                        answer = n1 / n2;
                        Log.d("wendy", "equation n1: " + n1);
                        Log.d("wendy", "equation n2: " + n2);
                        Log.d("wendy", "equation divide: " + answer);
                        break;
                    case SUBTRACTION:
                        answer = n1 - n2;
                        Log.d("wendy", "equation n1: " + n1);
                        Log.d("wendy", "equation n2: " + n2);
                        Log.d("wendy", "equation subtract: " + answer);
                        break;
                    case MULTIPLICATION:
                        answer = n1 * n2;
                        Log.d("wendy", "equation n1: " + n1);
                        Log.d("wendy", "equation n2: " + n2);
                        Log.d("wendy", "equation multiply: " + answer);
                        break;
                    case NULL:
                        answer = 0;
                        Log.d("wendy", "equation n1: " + n1);
                        Log.d("wendy", "equation n2: " + n2);
                        Log.d("wendy", "equation null: " + answer);
                    default:
                        break;
                }
            }

            output = answer;
            equation.clear();
            equation.add(String.valueOf(answer));

            Log.d("wendy", "equation end: " + equation.toString());


        }
    }

    public void enteredNumberIsComplete()
    {
        if(equation.size() == 1) return;

        equation.add(number);
        number = "0";
        Log.d("wendy", "equation complete: " + equation.toString());
    }

    public void updateEquation(String n)
    {
        number += n;
    }

    public String getOutput()
    {
        return NumberFormat.getInstance().format(output);
    }

    public void resetEquation()
    {
        equation.clear();
    }
}
