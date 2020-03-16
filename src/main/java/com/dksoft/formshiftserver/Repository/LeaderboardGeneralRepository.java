package com.dksoft.formshiftserver.Repository;

import com.dksoft.formshiftserver.Model.LeaderBoardItem;
import com.dksoft.formshiftserver.Model.LeaderboardGeneral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface LeaderboardGeneralRepository extends JpaRepository<LeaderboardGeneral, Integer> {

    //@Query(value = "SELECT lg.place, u.nickname, u.high_score FROM `formshift_leaderboard_general` lg LEFT JOIN `formshift_users` u ON lg.user_id = u.id LIMIT 100", nativeQuery = true)
   // @Query(value = "SELECT new com.dksoft.formshiftserver.Model.LeaderBoardItem(lg.place, u.nickname, u.high_score) FROM LeaderboardGeneral lg LEFT JOIN User u ON lg.user_id = u.id LIMIT 100", nativeQuery = true)


    @Query(value = "SELECT * FROM `formshift_leaderboard_general` ORDER by formshift_leaderboard_general.place LIMIT ?1", nativeQuery = true)
    ArrayList<LeaderboardGeneral> getLeaders(Integer limit);

}
