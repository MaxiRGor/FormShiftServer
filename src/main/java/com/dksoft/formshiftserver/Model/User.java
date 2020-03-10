package com.dksoft.formshiftserver.Model;

import javax.persistence.*;

@Entity
@Table(name = "formshift_users")
public class User {

    public User() {    }

    public User(String nickname) {
        this.nickname = nickname;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "facebook_data_id")
    public int facebookDataId;


    @Column(name = "leaderboard_general_id")
    public int leaderboardGeneralId;

    @Column(name = "leaderboard_group_id")
    public int leaderboardGrouplId;

    @Column(name = "score")
    public int score;

    @Column(name = "nickname")
    public String nickname;
}
