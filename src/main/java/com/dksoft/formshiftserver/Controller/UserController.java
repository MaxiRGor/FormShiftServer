package com.dksoft.formshiftserver.Controller;

import com.dksoft.formshiftserver.LeaderboardGroupCounter;
import com.dksoft.formshiftserver.Model.LeaderboardGeneral;
import com.dksoft.formshiftserver.Model.LeaderboardGroup;
import com.dksoft.formshiftserver.Model.User;
import com.dksoft.formshiftserver.Model.UserFacebookData;
import com.dksoft.formshiftserver.Repository.LeaderboardGeneralRepository;
import com.dksoft.formshiftserver.Repository.LeaderboardGroupRepository;
import com.dksoft.formshiftserver.Repository.UserFacebookDataRepository;
import com.dksoft.formshiftserver.Repository.UserRepository;
import jdk.nashorn.internal.runtime.Debug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserFacebookDataRepository userFacebookDataRepository;
    private final LeaderboardGeneralRepository leaderboardGeneralRepository;
    private final    LeaderboardGroupRepository leaderboardGroupRepository;

    @Autowired
    public UserController(UserRepository userRepository
            , UserFacebookDataRepository userFacebookDataRepository
            , LeaderboardGeneralRepository leaderboardGeneralRepository
            , LeaderboardGroupRepository leaderboardGroupRepository) {
        this.userRepository = userRepository;
        this.userFacebookDataRepository = userFacebookDataRepository;
        this.leaderboardGeneralRepository = leaderboardGeneralRepository;
        this.leaderboardGroupRepository = leaderboardGroupRepository;
    }

    @GetMapping("/get/{id}")
    public @ResponseBody
    String getUser(@PathVariable("id") int id) {
        User user = userRepository.findById(id);
        String userInfo = getUserInfo(user);
        System.out.println(userInfo);
        return userInfo;
    }

    @GetMapping("/create/{nickname}/{facebook_id}/{email}/{name}/{icon_url}")
    @ResponseBody
    public User CreateUser (@PathVariable String nickname,@PathVariable String facebook_id, @PathVariable String email, @PathVariable String name, @PathVariable String icon_url) {
        User user = new User(nickname);
        userRepository.save(user);
        int userId = user.id;

        UserFacebookData userFacebookData = new UserFacebookData(userId,facebook_id,email,name,icon_url);
        userFacebookDataRepository.save(userFacebookData);
        int facebookDataId = userFacebookData.id;

        LeaderboardGeneral leaderboardGeneral = new LeaderboardGeneral(userId);
        leaderboardGeneralRepository.save(leaderboardGeneral);
        int leaderboardGeneralId = leaderboardGeneral.id;
        leaderboardGeneral.setPlace(leaderboardGeneralId);

        LeaderboardGroup leaderboardGroup = new LeaderboardGroup(userId, LeaderboardGroupCounter.getInstance().GetCurrentGroupNumber());
        leaderboardGroupRepository.save(leaderboardGroup);
        int leaderboardGroupId = leaderboardGroup.id;
        leaderboardGroup.setPlace(leaderboardGroupId);

        user.facebookDataId = (facebookDataId);
        user.leaderboardGeneralId = (leaderboardGeneralId);
        user.leaderboardGrouplId = (leaderboardGroupId);
        return user;
    }

    private String getUserInfo(User user){
        return  user.id + " " + user.nickname;
    }

}
