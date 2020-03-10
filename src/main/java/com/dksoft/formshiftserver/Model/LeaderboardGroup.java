package com.dksoft.formshiftserver.Model;

import javax.persistence.*;

@Entity
@Table(name = "formshift_leaderboard_group")
public class LeaderboardGroup {

    public LeaderboardGroup() {
    }

    public LeaderboardGroup(int userId, int groupNumber) {
        this.userId = userId;
        this.groupNumber = groupNumber;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "user_id")
    public int userId;


    @Column(name = "place")
    public int place;

    @Column(name = "group_number")
    public int groupNumber;


    public void setPlace(int place) {
        this.place = place;
    }

}
