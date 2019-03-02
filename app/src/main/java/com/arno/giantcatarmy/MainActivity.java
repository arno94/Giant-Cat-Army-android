package com.arno.giantcatarmy;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.arno.giantcatarmy.entity.Fails;
import com.arno.giantcatarmy.entity.PanelData;
import com.arno.giantcatarmy.entity.Quest;

public class MainActivity extends AppCompatActivity {

    private PanelData panelData;

    private TextView tvNumbers;
    private TextView tvCurrentNumber;
    private TextView tvFinishLine;

    private Button[] btOptions;
    private TextView[] btQuests;
    private Button btRestart;
    private int currentQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btOptions = new Button[3];
        btQuests = new TextView[3];
        currentQuestion = 0;
        init();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void reset(){
        setButtonsClickable(true);
        tvCurrentNumber.setTextColor(Color.WHITE);
        panelData = new PanelData();
        tvFinishLine.setVisibility(View.INVISIBLE);
        updateUI();
    }

    private void init(){
        panelData = new PanelData();

        tvNumbers = (TextView)findViewById(R.id.tv_numbers);
        tvCurrentNumber = (TextView)findViewById(R.id.tv_current_number);
        tvFinishLine = (TextView)findViewById(R.id.tv_finish_line);

        btOptions[0] = (Button)findViewById(R.id.bt_option_1);
        btOptions[1]= (Button)findViewById(R.id.bt_option_2);
        btOptions[2] = (Button)findViewById(R.id.bt_option_3);

        btRestart = (Button)findViewById(R.id.bt_restart);

        setButtonsClickable(true);

        btQuests[0] = (TextView)findViewById(R.id.tv_quest_1);
        btQuests[1]= (TextView)findViewById(R.id.tv_quest_2);
        btQuests[2] = (TextView)findViewById(R.id.tv_quest_3);

        btOptions[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvCurrentNumber.setTextColor(Color.WHITE);
                int number = panelData.getLastNumber() + 5;

                // check if bigger than 60
                if(panelData.isBiggerThan60(number)){
                    showFail(Fails.BIGGER_THAN_60);
                }

                // check if repeating number
                if(panelData.containsNumber(number)){
                    showFail(Fails.REPEATING_NUMBER);
                }

                if(!panelData.isGoodOrder(number)){
                    showFail(Fails.WRONG_ORDER);
                }
                else{
                    if(panelData.isQuestCompleted(number)){
                        btQuests[currentQuestion].setTextColor(Color.GREEN);
                        tvCurrentNumber.setTextColor(Color.GREEN);

                        Animation a = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.questcompleted);
                        a.reset();
                        btQuests[currentQuestion].clearAnimation();
                        btQuests[currentQuestion].startAnimation(a);

                        currentQuestion++;
                        if(currentQuestion < Quest.QUESTS.length){
                            btQuests[currentQuestion].setEnabled(true);
                        }
                        else{
                            // WIN
                            showWin();
                            setButtonsClickable(false);
                        }
                        panelData.addQuest();
                    }
                }
                panelData.addNumber(number);
                updateUI();
            }
        });

        btOptions[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvCurrentNumber.setTextColor(Color.WHITE);
                int number = panelData.getLastNumber() + 7;

                // check if bigger than 60
                if(panelData.isBiggerThan60(number)){
                    showFail(Fails.BIGGER_THAN_60);
                }

                // check if repeating number
                if(panelData.containsNumber(number)){
                    showFail(Fails.REPEATING_NUMBER);
                }

                if(!panelData.isGoodOrder(number)){
                    showFail(Fails.WRONG_ORDER);
                }
                else{
                    if(panelData.isQuestCompleted(number)){
                        btQuests[currentQuestion].setTextColor(Color.GREEN);
                        tvCurrentNumber.setTextColor(Color.GREEN);

                        Animation a = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.questcompleted);
                        a.reset();
                        btQuests[currentQuestion].clearAnimation();
                        btQuests[currentQuestion].startAnimation(a);

                        currentQuestion++;
                        if(currentQuestion < Quest.QUESTS.length){
                            btQuests[currentQuestion].setEnabled(true);
                        }
                        else{
                            // WIN
                            showWin();
                            setButtonsClickable(false);
                        }
                        panelData.addQuest();
                    }
                }
                panelData.addNumber(number);
                updateUI();
            }
        });

        btOptions[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvCurrentNumber.setTextColor(Color.WHITE);
                int number = (int)Math.sqrt(panelData.getLastNumber());
                if(number * number != panelData.getLastNumber()){
                    setButtonsClickable(false);
                    showFail();
                    showFail(Fails.NON_WHOLE_NUMBER);
                    return;
                }

                // check if repeating number
                if(panelData.containsNumber(number)){
                    showFail(Fails.REPEATING_NUMBER);
                }

                if(!panelData.isGoodOrder(number)){
                    showFail(Fails.WRONG_ORDER);
                }
                else{
                    if(panelData.isQuestCompleted(number)){
                        btQuests[currentQuestion].setTextColor(Color.GREEN);
                        tvCurrentNumber.setTextColor(Color.GREEN);

                        Animation a = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.questcompleted);
                        a.reset();
                        btQuests[currentQuestion].clearAnimation();
                        btQuests[currentQuestion].startAnimation(a);

                        currentQuestion++;
                        if(currentQuestion < Quest.QUESTS.length){
                            btQuests[currentQuestion].setEnabled(true);
                        }
                        else{
                            // WIN
                            showWin();
                            setButtonsClickable(false);
                        }
                        panelData.addQuest();
                    }
                }
                panelData.addNumber(number);
                updateUI();
            }
        });

        btRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });
    }

    private void setButtonsClickable(boolean b){
        for(int i = 0;i < 3; i++){
            btOptions[i].setClickable(b);
        }
    }

    private void showWin(){
        tvFinishLine.setText(getString(R.string.win));
        tvFinishLine.setTextColor(Color.GREEN);
        tvFinishLine.setVisibility(View.VISIBLE);
    }

    private void showFail(final Fails fail){
        setButtonsClickable(false);
        tvCurrentNumber.setTextColor(Color.RED);
        tvFinishLine.setTextColor(Color.RED);
        tvFinishLine.setVisibility(View.VISIBLE);

        switch(fail){
            case WRONG_ORDER:
                //System.out.println("WRONG ORDER ------");
                tvFinishLine.setText(getString(R.string.lose_wrong_order));
                break;
            case REPEATING_NUMBER:
                //System.out.println("REPEATED NUMBER ------");
                tvFinishLine.setText(getString(R.string.lose_repeating_number));
                break;
            case NON_WHOLE_NUMBER:
                //System.out.println("NON WHOLE NUMBER ------");
                tvFinishLine.setText(getString(R.string.lose_non_whole_number));
                break;
            case BIGGER_THAN_60:
                //System.out.println("BIGGER THAN 60 ------");
                tvFinishLine.setText(getString(R.string.lose_bigger_than_60));
        }
    }

    private void showFail(){
        tvCurrentNumber.setTextColor(Color.RED);
        //btQuests[currentQuestion].getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
    }

    private void updateUI(){
        tvNumbers.setText("" + panelData);
        tvCurrentNumber.setText("" + panelData.getLastNumber());
    }
}
