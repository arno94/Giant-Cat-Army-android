package com.arno.giantcatarmy.entity;

import java.util.ArrayList;

public class PanelData {

    private ArrayList<Integer> numbers;
    private int questIndex;

    public PanelData(){
        numbers = new ArrayList<>();
        numbers.add(0);
        questIndex = 0;
    }

    public void addNumber(int number){
        numbers.add(number);
    }

    public ArrayList<Integer> getNumbers(){
        return numbers;
    }

    public boolean isQuestCompleted(int number){
        return number == Quest.QUESTS[questIndex];
    }

    public void addQuest(){
        questIndex++;
    }

    /**
     * BIGGER THAN 60
     * @param number
     * @return
     */
    public boolean isBiggerThan60(int number){
        return number > 60;
    }

    /**
     * REPEATING NUMBER
     * @param number
     * @return
     */
    public boolean containsNumber(int number){
        for(Integer i : numbers){
            if(number == i){
                return true;
            }
        }
        return false;
    }

    public boolean isGoodOrder(int number){
        for(int i = 0;i < Quest.QUESTS.length; i++){
            if(Quest.QUESTS[i] == number){
                if(i != questIndex) return false;
            }
        }
        return true;
    }

    public boolean isNumberOkay(int number){
        if(number > 60) return false;

        for(Integer i : numbers){
            if(number == i){
                return false;
            }
        }

        for(int i = 0;i < Quest.QUESTS.length; i++){
            if(Quest.QUESTS[i] == number){
                if(i != questIndex) return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        if(numbers.isEmpty()) return "";
        String s = numbers.get(0) + "";
        for(int i = 1;i < numbers.size(); i++){
            s += ", " + numbers.get(i);
        }
        return s;
    }

    public int getLastNumber(){
        return numbers.get(numbers.size()-1);
    }

}
