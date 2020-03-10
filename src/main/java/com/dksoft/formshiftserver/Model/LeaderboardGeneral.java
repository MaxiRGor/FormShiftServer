package com.dksoft.formshiftserver.Model;

import javax.persistence.*;


@Entity
@Table(name = "formshift_leaderboard_general")
public class LeaderboardGeneral {

    public LeaderboardGeneral(){}

    public LeaderboardGeneral(int userId){
        this.userId = userId;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY )
    public Integer id;

    @Column(name = "user_id")
    public int userId;


    @Column(name = "place")
    public int place;


    public void setPlace(int place) {
        this.place = place;
    }

}
