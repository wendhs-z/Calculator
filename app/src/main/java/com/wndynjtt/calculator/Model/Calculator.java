package com.wndynjtt.calculator.Model;


import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class Calculator {

    private float output = 0;

    private boolean hasAnswer = false;

    List<Object> equation;

    String number = "0";

    /* this identifies which method should be used */
    public enum Method
    {
        ADDITION, SUBTRACTION, DIVISION, MULTIPLICATION, NULL
    }

    /* initialization of this model */
    public Calculator() {
        equation = new ArrayList<>();
    }

    /* sets which method is needed */
    public void setMethod(Method method)
    {
        if(equation.size() >= 3)
        {
            calculate();
        }
        equation.add(method);

    }

    /* calculates the given numbers */
    public void calculate()
    {
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
                        break;
                    case DIVISION:
                        answer = n1 / n2;
                        break;
                    case SUBTRACTION:
                        answer = n1 - n2;
                        break;
                    case MULTIPLICATION:
                        answer = n1 * n2;
                        break;
                    case NULL:
                        answer = 0;
                    default:
                        break;
                }
            }

            output = answer;
            equation.clear();
            equation.add(String.valueOf(answer));

        }
    }

    /* controls the equation's continuability */
    public void enteredNumberIsComplete()
    {
        if(equation.size() == 1) return;

        equation.add(number);
        number = "0";
    }

    /* updates the values of the numbers */
    public void updateEquation(String n)
    {
        number += n;
    }

    /* returns the output as string */
    public String getOutput()
    {
        return NumberFormat.getInstance().format(output);
    }

    /* resets the equation */
    public void resetEquation()
    {
        equation.clear();
    }
}
