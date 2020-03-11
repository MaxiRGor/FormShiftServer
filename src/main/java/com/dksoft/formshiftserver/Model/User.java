package com.dksoft.formshiftserver.Model;

import javax.persistence.*;

@Entity
@Table(name = "formshift_users")
public class User {

    public User() {    }

    public User(String nickname, String deviceId) {
        this.nickname = nickname;
        this.deviceId = deviceId;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "device_id")
    public String deviceId;

    @Column(name = "facebook_data_id")
    public int facebookDataId;

    @Column(name = "leaderboard_general_id")
    public int leaderboardGeneralId;

    @Column(name = "leaderboard_group_id")
    public int leaderboardGrouplId;

    @Column(name = "nickname")
    public String nickname;

    @Column(name = "high_score")
    public int highScore;

}
