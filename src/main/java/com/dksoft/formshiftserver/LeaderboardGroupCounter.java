package com.dksoft.formshiftserver;

public class LeaderboardGroupCounter {
    private static LeaderboardGroupCounter ourInstance = new LeaderboardGroupCounter();

    public static LeaderboardGroupCounter getInstance() {
        return ourInstance;
    }

    private LeaderboardGroupCounter() {
        groupCapacity = 100;
        currentGroupNumber = 0;
        amountInCurrentGroup = 0;
    }

    private int groupCapacity;
    private int currentGroupNumber;
    private int amountInCurrentGroup;

    public int GetCurrentGroupNumber(){
        if(amountInCurrentGroup>=groupCapacity)
        {
            currentGroupNumber++;
            amountInCurrentGroup = 0;
        }
        return  currentGroupNumber;
    }
}
