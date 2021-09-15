package com.example.calculator;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String input="";
    private ArrayList<String> inputText=new ArrayList<String>();
    private ArrayList<String> outputText=new ArrayList<String>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen*/


        setContentView(R.layout.activity_main);
        ActionBar actionBar;
        actionBar=getSupportActionBar();
        ColorDrawable colorDrawable=new ColorDrawable(Color.parseColor("#FF000000"));
        actionBar.setBackgroundDrawable(colorDrawable);
        update();
        /*try {
            Intent intent3=getIntent();
            inputText=intent3.getStringArrayListExtra("InputText");
            outputText=intent3.getStringArrayListExtra("OutputText");

        }   //trying to back with values from history*/


    }

















    public void update(){
//This much func needed due to input string and real shown edittextview differs due to
// internal mobile calculator which can use scientif calc
        EditText editText=findViewById(R.id.display);
        String temp=editText.getText().toString();
        if(temp.equals("ERROR")){
            temp="";
        }
        String last="";
        if(input.length()!=0)
            last=input.substring(input.length() - 1);
        if(temp!=input) input=temp+last;
        editText.setText(input);
    }


    public void infofunc(View view) {
        Intent intent2=new Intent(this,info.class);
        intent2.putStringArrayListExtra("inputText",inputText);
        intent2.putStringArrayListExtra("outputText",outputText);
        startActivity(intent2);
    }

    public void lifefunc(View view) {
        Intent intent1=new Intent(this,life.class);
        startActivity(intent1);
    }

    public void clearfunc(View view) {

        input="";
        EditText editText=findViewById(R.id.display);
        editText.setText(input);

    }



    public void dividefunc(View view) {
        input+="/";
        update();
    }

    public void sevenfunc(View view) {
        input+="7";
        update();
    }

    public void eightfunc(View view) {
        input+="8";
        update();
    }

    public void ninefunc(View view) {
        input+="9";
        update();
    }

    public void multiplyfunc(View view) {
        input+="*";
        update();
    }

    public void fourfunc(View view) {
        input+="4";
        update();
    }

    public void fivefunc(View view) {
        input+="5";
        update();
    }

    public void sixfunc(View view) {
        input+="6";
        update();
    }

    public void minusfunc(View view) {
        input+="-";
        update();
    }

    public void onefunc(View view) {
        input+="1";
        update();
    }

    public void twofunc(View view) {
        input+="2";
        update();
    }

    public void threefunc(View view) {
        input+="3";
        update();
    }

    public void plusfunc(View view) {
        input+="+";
        update();
    }



    public void zerofunc(View view) {
        input+="0";
        update();
    }

    public void dotfunc(View view) {
        input+=".";
        update();
    }

    public void equalsfunc(View view) {
        double value;
        EditText editText=findViewById(R.id.display);
        input=editText.getText().toString();
        inputText.add(input);
        if(input.equals("")) return;
        try {//if error in syntax calculation
            value=calculate(input);
            input=String.valueOf(value);
        }
        catch (Exception e){
            input="ERROR";
        }
        if(input.substring(input.length() - 2).equals(".0")){//contains fake float value->int
            input=input.substring(0,input.length() - 2);
            outputText.add(input);
            editText.setText(input);
            return;
        }

        if(input!="ERROR"){
            value=Double.parseDouble(input);
            value=Math.round(value*1000.0)/1000.0;//value acting as temp val;;rounding upto three decimal
            input=String.valueOf(value);
        }
        outputText.add(input);
        editText.setText(input);

    }

    private static double calculate(String str) {

            return new Object() {
                int pos = -1, ch;

                void nextChar() {
                    ch = (++pos < str.length()) ? str.charAt(pos) : -1;
                }

                boolean eat(int charToEat) {
                    while (ch == ' ') nextChar();
                    if (ch == charToEat) {
                        nextChar();
                        return true;
                    }
                    return false;
                }

                double parse() {
                    nextChar();
                    double x = parseExpression();
                    if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                    return x;
                }

                // Grammar:
                // expression = term | expression `+` term | expression `-` term
                // term = factor | term `*` factor | term `/` factor
                // factor = `+` factor | `-` factor | `(` expression `)`
                //        | number | functionName factor | factor `^` factor

                double parseExpression() {
                    double x = parseTerm();
                    for (;;) {
                        if      (eat('+')) x += parseTerm(); // addition
                        else if (eat('-')) x -= parseTerm(); // subtraction
                        else return x;
                    }
                }

                double parseTerm() {
                    double x = parseFactor();
                    for (;;) {
                        if      (eat('*')) x *= parseFactor(); // multiplication
                        else if (eat('/')) x /= parseFactor(); // division
                        else return x;
                    }
                }

                double parseFactor() {
                    if (eat('+')) return parseFactor(); // unary plus
                    if (eat('-')) return -parseFactor(); // unary minus

                    double x;
                    int startPos = this.pos;
                    if (eat('(')) { // parentheses
                        x = parseExpression();
                        eat(')');
                    } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                        while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                        x = Double.parseDouble(str.substring(startPos, this.pos));
                    } else if (ch >= 'a' && ch <= 'z') { // functions
                        while (ch >= 'a' && ch <= 'z') nextChar();
                        String func = str.substring(startPos, this.pos);
                        x = parseFactor();
                        if (func.equals("sqrt")) x = Math.sqrt(x);
                        else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                        else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                        else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                        else throw new RuntimeException("Unknown function: " + func);
                    } else {
                        throw new RuntimeException("Unexpected: " + (char)ch);
                    }

                    if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                    return x;
                }
            }.parse();
        }




    public void leftbracketfunc(View view) {
        input+="(";
        update();
    }

    public void rightbracketfunc(View view) {
        input+=")";
        update();
    }

    public void deletefunc(View view) {
        EditText editText=findViewById(R.id.display);
        input=editText.getText().toString();
        if(input.length()!=0)  input=input.substring(0,input.length()-1);
        editText.setText(input);
    }
}
