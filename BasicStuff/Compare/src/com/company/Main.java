package com.company;

public class Main
{

    public static void main(String[] args)
    {

        System.out.println("Your final score was "+calculateScore(false,10000,8,200));
        boolean gameOver=true;
        int score=800;
        int levelCompleted=5;
        int bonus=100;
        int finalScore=calculateScore(gameOver,score,levelCompleted,bonus);
        System.out.println("Your final score was "+finalScore);
        displayHighScorePosition("David",calculateHighScorePosition(1500));
        displayHighScorePosition("Kate",calculateHighScorePosition(900));
        displayHighScorePosition("Bob",calculateHighScorePosition(400));
        displayHighScorePosition("Sarah",calculateHighScorePosition(50));
    }

    public static int calculateScore(boolean gameOver,int score, int levelCompleted,int bonus)
    {
        int finalScore=0;
        if(gameOver)
        {
            finalScore =score +(levelCompleted*bonus);
        }
        return finalScore;
    }
    public static void displayHighScorePosition(String name,int position)
    {
        System.out.println(name+" managed to get into position "+position+" on the high score table.");
    }
    public static int calculateHighScorePosition(int score)
    {
        if(score>=1000)return 1;
        else if(score>=500)return 2;
        else if(score>=100)return 3;
        else return 4;
    }

}
