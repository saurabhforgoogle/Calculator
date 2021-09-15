package com.example.calculator;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class life extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life);
        ActionBar actionBar;
        actionBar=getSupportActionBar();
        ColorDrawable colorDrawable=new ColorDrawable(Color.parseColor("#FF000000"));
        actionBar.setBackgroundDrawable(colorDrawable);
        buttonfunc();
    }


    public void calcfunc(View view) {
        Intent intent5=new Intent(this,MainActivity.class);
        startActivity(intent5);
    }

    private void buttonfunc() {
        Button button1=findViewById(R.id.bmi);
        Button button2=findViewById(R.id.discount);
        Button button3=findViewById(R.id.date);
        Button button4=findViewById(R.id.temp);
        Button button5=findViewById(R.id.interestcalc);
        Button button6=findViewById(R.id.length);
        button1.setOnClickListener(buttonexecution);
        button2.setOnClickListener(buttonexecution);
        button3.setOnClickListener(buttonexecution);
        button4.setOnClickListener(buttonexecution);
        button5.setOnClickListener(buttonexecution);
        button6.setOnClickListener(buttonexecution);
    }

    private void backfunc() {
        Button back=findViewById(R.id.backbutton);
        back.setOnClickListener(backbuttonexecution);
    }


    private View.OnClickListener buttonexecution= v -> {

        //Toast.makeText(life.this, "Button Pressed", Toast.LENGTH_SHORT).show();
        switch (v.getId()){
            case R.id.bmi:
                setContentView(R.layout.bmi);
                bmifunc();
                break;
            case  R.id.date:
                setContentView(R.layout.date);
                break;
            case R.id.discount:
                setContentView(R.layout.discount);
                discountfunc();
                break;
            case R.id.temp:
                setContentView(R.layout.temperature);
                tempfunc();
                break;
            case R.id.interestcalc:
                setContentView(R.layout.interestcalculator);
                break;
            case R.id.length:
                setContentView(R.layout.length);
                lengthfunc();
                break;

        }
        backfunc();

    };

    private void bmifunc() {
        Button submit=findViewById(R.id.bmisubmit);
        submit.setOnClickListener(calculatebmiclicked);
    }
    private  View.OnClickListener calculatebmiclicked=v->{
        EditText heightbmi=findViewById(R.id.editHeightbmi);
        EditText weightbmi=findViewById(R.id.editWeightbmi);
        TextView answerbmi=findViewById(R.id.answerbmi);
        try{
            int Height=Integer.parseInt(heightbmi.getText().toString());
            int Weight=Integer.parseInt(weightbmi.getText().toString());
            int bmi=(Weight*10000)/(Height*Height);
            String outputText="Bmi:"+bmi;
            answerbmi.setText(outputText);
        }
        catch (NumberFormatException e) {
            Toast.makeText(this, "Incorrect inputs", Toast.LENGTH_SHORT).show();
        }
    };

    private void lengthfunc() {
        EditText Feetlength=findViewById(R.id.editFeetLength);
        EditText MetreLength=findViewById(R.id.editMetreLength);
        EditText MileLength=findViewById(R.id.editMileLength);
        final boolean[] disableFeet = {false};
        final boolean[] disableMetre = {false};
        final boolean[] disableMile = {false};
        Feetlength.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {

                if(!disableFeet[0]){

                    String input=Feetlength.getText().toString();
                    if(input.equals("")){
                        disableMetre[0]=true;
                        MetreLength.setText(input);
                        disableMile[0]=true;
                        MileLength.setText(input);
                        return;
                    }

                    double Feet=Double.parseDouble(input);
                    if(Feet>=0){
                        String Metre=""+(0.3048*Feet);
                        String Mile=""+(0.000189394*Feet);
                        disableMetre[0]=true;
                        MetreLength.setText(Metre);
                        disableMile[0]=true;
                        MileLength.setText(Mile);
                    }
                    else{
                        TextView suggestionLength=findViewById(R.id.suggestionLength);
                        String ErrorText="Feet Value Non-Negative";
                        suggestionLength.setText(ErrorText);
                    }
                }
                else {
                    disableFeet[0]=false;
                }

            }
        });


        MetreLength.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(!disableMetre[0]){

                    String input=MetreLength.getText().toString();
                    if(input.equals("")){
                        disableFeet[0]=true;
                        Feetlength.setText(input);
                        disableMile[0]=true;
                        MileLength.setText(input);
                        return;
                    }
                    double Metre=Double.parseDouble(input);

                    if(Metre>=0){
                        String Feet=""+(3.28084*Metre);
                        String Mile=""+(0.000621371*Metre);
                        disableFeet[0]=true;
                        Feetlength.setText(Feet);
                        disableMile[0]=true;
                        MileLength.setText(Mile);
                    }
                    else{
                        TextView suggestionLength=findViewById(R.id.suggestionLength);
                        String ErrorText="Metre value Non-Negative";
                        suggestionLength.setText(ErrorText);
                    }
                }

                else{
                    disableMetre[0]=false;
                }

            }
        });
        MileLength.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(!disableMile[0]){

                    String input=MileLength.getText().toString();
                    if(input.equals("")){
                        disableFeet[0]=true;
                        Feetlength.setText(input);
                        disableMetre[0]=true;
                        MetreLength.setText(input);
                        return;
                    }
                    double Mile=Double.parseDouble(input);
                    if(Mile>=0){
                        String Feet=""+(5280*Mile);
                        String Metre=""+(1609.34*Mile);
                        disableFeet[0]=true;
                        Feetlength.setText(Feet);
                        disableMetre[0]=true;
                        MetreLength.setText(Metre);
                    }
                    else{
                        TextView suggestionLength=findViewById(R.id.suggestionLength);
                        String ErrorText="Mile Value Non-Negative";
                        suggestionLength.setText(ErrorText);
                    }
                }

                else{
                    disableMile[0]=false;
                }

            }
        });
    }





    private void tempfunc() {
        EditText KelvinTemperature=findViewById(R.id.editKelvinTemperature);
        EditText CelsiusTemperature=findViewById(R.id.editCelsiusTemperature);
        EditText FahrenheitTemperature=findViewById(R.id.editFahrenheitTemperature);
        final boolean[] disableKelvin = {false};
        final boolean[] disableCelsius = {false};
        final boolean[] disableFahrenheit = {false};
        KelvinTemperature.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {

                if(!disableKelvin[0]){

                    String input=KelvinTemperature.getText().toString();
                    if(input.equals("")){
                        disableCelsius[0]=true;
                        CelsiusTemperature.setText(input);
                        disableFahrenheit[0]=true;
                        FahrenheitTemperature.setText(input);
                        return;
                    }

                    double Kelvin=Double.parseDouble(input);
                    if(Kelvin>=0){
                        String Celsius=""+(Kelvin-273.15);
                        String Fahrenheit=""+((Kelvin-273.15)*9/5 +32);
                        disableCelsius[0]=true;
                        CelsiusTemperature.setText(Celsius);
                        disableFahrenheit[0]=true;
                        FahrenheitTemperature.setText(Fahrenheit);
                    }
                    else{
                        TextView suggestionTemperature=findViewById(R.id.suggestionTemperature);
                        String ErrorText="Kelvin Value Non-Negative";
                        suggestionTemperature.setText(ErrorText);
                    }
                }
                else {
                    disableKelvin[0]=false;
                }

            }
        });


        CelsiusTemperature.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(!disableCelsius[0]){

                    String input=CelsiusTemperature.getText().toString();
                    if(input.equals("")){
                        disableKelvin[0]=true;
                        KelvinTemperature.setText(input);
                        disableFahrenheit[0]=true;
                        FahrenheitTemperature.setText(input);
                        return;
                    }
                    double Celsius=Double.parseDouble(input);
                    if(Celsius>=-273.15 ){
                        String Kelvin=""+(Celsius+273.15);
                        String Fahrenheit=""+(Celsius*9/5 +32);
                        disableKelvin[0]=true;
                        KelvinTemperature.setText(Kelvin);
                        disableFahrenheit[0]=true;
                        FahrenheitTemperature.setText(Fahrenheit);
                    }
                    else{
                        TextView suggestionTemperature=findViewById(R.id.suggestionTemperature);
                        String ErrorText="Incorrect Celsius Value";
                        suggestionTemperature.setText(ErrorText);
                    }
                }

                else{
                    disableCelsius[0]=false;
                }

            }
        });
        FahrenheitTemperature.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(!disableFahrenheit[0]){

                    String input=FahrenheitTemperature.getText().toString();
                    if(input.equals("")){
                        disableKelvin[0]=true;
                        KelvinTemperature.setText(input);
                        disableCelsius[0]=true;
                        CelsiusTemperature.setText(input);
                        return;
                    }
                    double Fahrenheit=Double.parseDouble(input);
                    double Celsiusref=(Fahrenheit-32)*5/9;
                    if(Celsiusref>=-273.15){
                        String Kelvin=""+(Celsiusref+273.15);
                        String Celsius=""+Celsiusref;
                        disableKelvin[0]=true;
                        KelvinTemperature.setText(Kelvin);
                        disableCelsius[0]=true;
                        CelsiusTemperature.setText(Celsius);
                    }
                    else{
                        TextView suggestionTemperature=findViewById(R.id.suggestionTemperature);
                        String ErrorText="Incorrect Fahrenheit Value";
                        suggestionTemperature.setText(ErrorText);
                    }
                }

                else{
                    disableFahrenheit[0]=false;
                }

            }
        });


    }






    private  View.OnClickListener calculatediscountclicked=v ->{
        EditText pricediscount=findViewById(R.id.editpricediscount);
        EditText discountdiscount=findViewById(R.id.editdiscountdiscount);
        TextView answerdiscount=findViewById(R.id.answerdiscount);
        try {
            Double price = Double.parseDouble(pricediscount.getText().toString());
            price=Math.round(price*10.0)/10.0;
            Double discount = Double.parseDouble((discountdiscount.getText().toString()));
            Double TotalDiscount = price * discount / 100.0;
            TotalDiscount=Math.round(TotalDiscount*10.0)/10.0;

            String toPrint = "Total price After Discount is " + (price - TotalDiscount) + ". You saved " +
                    TotalDiscount;
            answerdiscount.setText(toPrint);
        }
        catch (NumberFormatException e) {
            Toast.makeText(this, "First Enter Some values", Toast.LENGTH_SHORT).show();
        }


    };

    private void discountfunc() {
        Button calculatediscount=findViewById(R.id.discountsubmit);
        calculatediscount.setOnClickListener(calculatediscountclicked);
    }

    private View.OnClickListener backbuttonexecution=v ->{
        setContentView(R.layout.activity_life);
        buttonfunc();
    };

}