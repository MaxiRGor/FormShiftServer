package com.dksoft.formshiftserver.Model;


import javax.persistence.*;

@Entity
@Table(name = "formshift_leaderboard_general")
public class LeaderboardGeneral {

    public LeaderboardGeneral(){}

    public LeaderboardGeneral(int userId,String nickname, int highScore){
        this.userId = userId;
        this.nickname = nickname;
        this.highScore = highScore;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY )
    public Integer id;

    @Column(name = "user_id")
    public int userId;


    @Column(name = "place")
    public int place;

    @Column(name = "nickname")
    public String nickname;

    @Column(name = "high_score")
    public int highScore;

    public int getUserId(){
        return userId;
    }

    public String getNickname(){
        return nickname;
    }
    public int getHighScore(){
        return highScore;
    }

    public void setNickname(String nickname){
        this. nickname = nickname;
    }


    public void setHighScore(int score){
        this.highScore = score;
    }
    public void setPlace(int place) {
        this.place = place;
    }

}
