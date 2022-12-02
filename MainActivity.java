package com.onivv.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.TextView;
import android.text.Editable;
import android.text.TextWatcher;

import android.view.View;
import android.widget.EditText;
import java.util.Random;
import android.os.Bundle;
import android.widget.ImageView;




public class MainActivity extends AppCompatActivity {
    ImageView iv;
    ImageView chadwin;
    ImageView losetate;
    int n;
    TextView wrong;
    TextView description;
    TextView reveal;
    EditText letter;
    int imageKey;
    Button restart;
    Random rand=new Random();
    TextView words,error;
    String[] LIST=new String[]{"israel","germany","argentina","belgium","france","egypt","england","poland","qatar","netherlands","denmark","peru","albania","venezuela","brazil","ghana","switzerland","italy","austria","russia","ukraine","cyprus","greece","bulgaria","hungary"};
    String X="",txt="",input,showtext="",word=PickRandom(LIST),empty=HideWord(word,X),show_wronglet="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        words=findViewById(R.id.correct);
        words.setText(empty);
        error=findViewById(R.id.wrong);
        restart=findViewById(R.id.button);
        restart.setVisibility(View.GONE);
        iv = findViewById(R.id.l1);
        iv.setImageResource(R.drawable.hangman0);
        chadwin=findViewById(R.id.chadwin);
        chadwin.setVisibility(View.GONE);
        losetate=findViewById(R.id.losetate);
        losetate.setVisibility(View.GONE);
        n = 0;
        wrong=findViewById(R.id.TheWrongLetter);
        description=findViewById(R.id.description);
        reveal=findViewById(R.id.reveal);
        reveal.setVisibility(View.GONE);
        loadpics1(n);
        letter = findViewById(R.id.letter);
        letter.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                input=letter.getText().toString();
                txt=txt+input;
                if(n>=6){
                    iv.setVisibility(View.GONE);
                    losetate.setVisibility(View.VISIBLE);
                    restart.setVisibility(View.VISIBLE);
                    n=0;
                    X="";
                    input="";
                    txt="";
                    showtext="";
                    show_wronglet="";
                    words.setVisibility(View.GONE);
                    error.setVisibility(View.GONE);
                    letter.setVisibility(View.GONE);
                    wrong.setVisibility(View.GONE);
                    description.setVisibility(View.GONE);
                    reveal.setText("The word was:"+word);
                    word=PickRandom(LIST);
                    empty=HideWord(word,X);
                    reveal.setVisibility(View.VISIBLE);
                }
                if(n<6&&FirstLetter(txt)==false&&Duplicate(show_wronglet)==false){
                    n++;
                    loadpics1(n);
                    if(Duplicate(word)==false){
                        show_wronglet+=input;
                        error.setText(show_wronglet);
                    }

                    letter.getText().clear();

                }
                if(n<6&&FirstLetter(txt)==false&&Duplicate(show_wronglet)==true){
                    letter.getText().clear();
                }
                if(FirstLetter(txt)){
                    showtext+=input;
                    words.setText(Print(word,empty,showtext.charAt(showtext.length()-1)));
                    empty=Print(word,empty,showtext.charAt(showtext.length()-1));
                    if(empty.equals(word)){
                        iv.setVisibility(View.GONE);
                        chadwin.setVisibility(View.VISIBLE);
                        restart.setVisibility(View.VISIBLE);
                        n=0;
                        X="";
                        input="";
                        txt="";
                        showtext="";
                        show_wronglet="";
                        word=PickRandom(LIST);
                        empty=HideWord(word,X);
                        words.setVisibility(View.GONE);
                        error.setVisibility(View.GONE);
                        letter.setVisibility(View.GONE);
                        wrong.setVisibility(View.GONE);
                        description.setVisibility(View.GONE);
                    }

                    letter.getText().clear();
                }

            }
        });
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                words.setText(empty);
                error.setText(show_wronglet);
                words.setVisibility(View.VISIBLE);
                error.setVisibility(View.VISIBLE);
                letter.setVisibility(View.VISIBLE);
                wrong.setVisibility(View.VISIBLE);
                description.setVisibility(View.VISIBLE);
                chadwin.setVisibility(View.GONE);
                losetate.setVisibility(View.GONE);
                iv.setVisibility(View.VISIBLE);
                restart.setVisibility(View.GONE);
                reveal.setVisibility(View.GONE);
                n=0;
                loadpics1(0);
            }
        });
    }
    public String PickRandom(String[] list){
        int place;
        place = rand.nextInt(list.length);
        return list[place];
    }
    boolean FirstLetter(String let){
        String answer = word;
        if(let.length()>0){
            for(int i=0;i<answer.length();i++){
                if(let.charAt(let.length()-1)==answer.charAt(i))return true;
            }
            return false;

        }
        return false;
    }
    public boolean Duplicate(String str) {
        String str2 = txt;
        if (str2.length() > 0) {
            for (int i = 0; i < str.length(); i++) {
                if (str2.charAt(str2.length() - 1) == str.charAt(i)) return true;

            }
            return false;
        }
        return false;
    }
    public String HideWord(String str,String Hide){
        for (int i = 0; i < str.length(); i++) {
            Hide+="X";
        }
        return Hide;
    }
    public String Print(String str,String str2, char let){
        for(int i=0;i<str.length();i++){
            if(let==str.charAt(i)){
                str2=str2.substring(0,i)+str.charAt(i)+str2.substring(i+1);
            }
        }
        return str2;

    }

    public void loadpics1(int n){
        imageKey=getResources().getIdentifier("hangman"+n,"drawable",getPackageName());
        iv.setImageResource(imageKey);



    }
}