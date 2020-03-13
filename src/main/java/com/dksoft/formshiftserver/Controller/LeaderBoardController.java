package com.dksoft.formshiftserver.Controller;

import com.dksoft.formshiftserver.Model.LeaderBoardItem;
import com.dksoft.formshiftserver.Model.LeaderboardGeneral;
import com.dksoft.formshiftserver.Model.User;
import com.dksoft.formshiftserver.Repository.LeaderboardGeneralRepository;
import com.dksoft.formshiftserver.Repository.LeaderboardGroupRepository;
import com.dksoft.formshiftserver.Repository.UserFacebookDataRepository;
import com.dksoft.formshiftserver.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/leaderboard")
public class LeaderBoardController {
    private final UserRepository userRepository;
    private final UserFacebookDataRepository userFacebookDataRepository;
    private final LeaderboardGeneralRepository leaderboardGeneralRepository;
    private final LeaderboardGroupRepository leaderboardGroupRepository;

    @Autowired
    public LeaderBoardController(UserRepository userRepository
            , UserFacebookDataRepository userFacebookDataRepository
            , LeaderboardGeneralRepository leaderboardGeneralRepository
            , LeaderboardGroupRepository leaderboardGroupRepository) {
        this.userRepository = userRepository;
        this.userFacebookDataRepository = userFacebookDataRepository;
        this.leaderboardGeneralRepository = leaderboardGeneralRepository;
        this.leaderboardGroupRepository = leaderboardGroupRepository;
    }

    @GetMapping("/get/general")
    public @ResponseBody
    List<LeaderBoardItem> GetLeaders() {
        int limit = 100;
        List<LeaderboardGeneral> LeaderboardGeneralLeaders = leaderboardGeneralRepository.getLeaders(limit);
        List<LeaderBoardItem> leaders = new ArrayList<>();
        for (LeaderboardGeneral lead:LeaderboardGeneralLeaders) {
            leaders.add(new LeaderBoardItem(lead.place,lead.nickname,lead.highScore));
        }
        return leaders;
    }
}
