package com.dksoft.formshiftserver;


import com.dksoft.formshiftserver.Model.LeaderboardGeneral;
import com.dksoft.formshiftserver.Model.User;
import com.dksoft.formshiftserver.Repository.LeaderboardGeneralRepository;
import com.dksoft.formshiftserver.Repository.LeaderboardGroupRepository;
import com.dksoft.formshiftserver.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class ScheduleUpdatingLeaderBoard {

    private UserRepository userRepository;
    private LeaderboardGroupRepository leaderboardGroupRepository;
    private LeaderboardGeneralRepository leaderboardGeneralRepository;

    @Autowired
    ScheduleUpdatingLeaderBoard(UserRepository userRepository,
                                LeaderboardGroupRepository leaderboardGroupRepository,
                                LeaderboardGeneralRepository leaderboardGeneralRepository) {
        this.userRepository = userRepository;
        this.leaderboardGeneralRepository = leaderboardGeneralRepository;
        this.leaderboardGroupRepository = leaderboardGroupRepository;
    }

    @Scheduled(fixedRate = 10000)       //3600000 = 60 min // 60000 = 1 min // 10000 = 10 sec
    public void updateLeaderBoard() {

        System.out.println("updatingLeaderBoard");


        List<User> users = userRepository.findAll();
        List<LeaderboardGeneral> leaderBoardUsers = leaderboardGeneralRepository.findAll();

        users.sort(Comparator.comparing(User::getId));
        leaderBoardUsers.sort(Comparator.comparing(LeaderboardGeneral::getUserId));

        for (int i = 0; i < leaderBoardUsers.size(); i++) {
            if (users.get(i).getId() == leaderBoardUsers.get(i).getUserId()) {

                if(!users.get(i).getName().equals(leaderBoardUsers.get(i).getNickname())){
                    leaderBoardUsers.get(i).setNickname(users.get(i).getName());
                }

                leaderBoardUsers.get(i).setHighScore(users.get(i).getHighScore());

            } else System.out.println("shit happened in ScheduleUpdatingLeaderBoard");
        }
        leaderBoardUsers.sort(Comparator.comparing(LeaderboardGeneral::getHighScore).reversed());
        for (int i = 0; i < leaderBoardUsers.size(); i++) {
            leaderBoardUsers.get(i).setPlace(i + 1);
        }
        leaderboardGeneralRepository.saveAll(leaderBoardUsers);
        System.out.println("updated");
    }
}