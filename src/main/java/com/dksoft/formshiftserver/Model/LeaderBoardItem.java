package com.dksoft.formshiftserver.Model;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Bean;

public class LeaderBoardItem {
    public LeaderBoardItem(){}
    public int place;
    public String name;
    public int high_score;

    public LeaderBoardItem(int place, String name, int high_score) {
        this.place = place;
        this.name = name;
        this.high_score = high_score;
    }
}
