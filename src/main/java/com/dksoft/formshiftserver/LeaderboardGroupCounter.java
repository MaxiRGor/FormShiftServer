package com.dksoft.formshiftserver;

public class LeaderboardGroupCounter {
    private static LeaderboardGroupCounter ourInstance = new LeaderboardGroupCounter();

    public static LeaderboardGroupCounter getInstance() {
        return ourInstance;
    }

    private LeaderboardGroupCounter() {
    }

    private int groupCapacity = 100;
    private int currentGroupNumber = 0;
    private int amountInCurrentGroup = 0;

    public int GetCurrentGroupNumber(){
        if(amountInCurrentGroup>=groupCapacity)
        {
            currentGroupNumber++;
            amountInCurrentGroup = 0;
        }
        return  currentGroupNumber;
    }
}
