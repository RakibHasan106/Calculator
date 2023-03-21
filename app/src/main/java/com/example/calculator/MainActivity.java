package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    MaterialButton bt_c,bt_rightbrac,bt_leftbrac,bt_div;
    MaterialButton bt_7,bt_8,bt_9,bt_multiply;
    MaterialButton bt_4,bt_5,bt_6,bt_plus;
    MaterialButton bt_1,bt_2,bt_3,bt_minus;
    MaterialButton bt_ac,bt_0,bt_dot,bt_equal;

    TextView resultview;
    TextView solutionview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        find_button_id(bt_c,R.id.bt_c);
        find_button_id(bt_leftbrac,(R.id.bt_leftbrac));
        find_button_id(bt_rightbrac,(R.id.bt_righbrac));
        find_button_id(bt_div,(R.id.bt_div));
        find_button_id(bt_7,(R.id.bt_7));
        find_button_id(bt_8,(R.id.bt_8));
        find_button_id(bt_9,(R.id.bt_9));
        find_button_id(bt_multiply,(R.id.bt_multiply));
        find_button_id(bt_4,(R.id.bt_4));
        find_button_id(bt_5,(R.id.bt_5));
        find_button_id(bt_6,(R.id.bt_6));
        find_button_id(bt_plus,(R.id.bt_plus));
        find_button_id(bt_1,(R.id.bt_1));
        find_button_id(bt_2,(R.id.bt_2));
        find_button_id(bt_3,(R.id.bt_3));
        find_button_id(bt_minus,(R.id.bt_min));
        find_button_id(bt_ac,(R.id.bt_ac));
        find_button_id(bt_0,(R.id.bt_0));
        find_button_id(bt_dot,(R.id.bt_dot));
        find_button_id(bt_equal,(R.id.bt_equal));

        resultview=(TextView) findViewById(R.id.result);
        solutionview=(TextView) findViewById(R.id.solution);

    }

    void find_button_id(MaterialButton btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        MaterialButton btn = (MaterialButton)view;
        String text=btn.getText().toString();
        String solutionText=solutionview.getText().toString();
        String Result;

        if(text.equals("ac")){
            resultview.setText("0");
            solutionview.setText("0");
        }
        else if(text.equals("C")){
            if(!solutionText.equals("0")&&solutionText.length()>1) {
                solutionText = solutionText.substring(0, solutionText.length() - 1);
                solutionview.setText(solutionText);
            }
            else if(solutionText.length()==1){
                solutionview.setText("0");
            }

        }
        else if(text.equals("=")){
            try{
                Context context  = Context.enter();
                context.setOptimizationLevel(-1);
                Scriptable scriptable = context.initStandardObjects();
                Result =  context.evaluateString(scriptable,solutionText,"Javascript",1,null).toString();
                if(Result.endsWith(".0")){
                    Result = Result.replace(".0","");
                }
            }catch (Exception e){
                Result="error";
            }
            resultview.setText(Result);
        }
        else{
            if(solutionText.equals("0")){
                solutionview.setText(text);

            }
            else{
                solutionText+=text;
                solutionview.setText(solutionText);
            }
        }
    }
}