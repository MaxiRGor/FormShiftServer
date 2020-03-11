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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserFacebookDataRepository userFacebookDataRepository;
    private final LeaderboardGeneralRepository leaderboardGeneralRepository;
    private final LeaderboardGroupRepository leaderboardGroupRepository;

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
    String GetUser(@PathVariable("id") int id) {
        User user = userRepository.findById(id);
        return getUserInfo(user);
    }


    @GetMapping("/register/{nickname}/{device_id}")
    @ResponseBody
    public User RegisterUser(@PathVariable String nickname,
                                  @PathVariable String device_id) {

        User user = new User(nickname, device_id);
        userRepository.save(user);
        int userId = user.id;

        LeaderboardGeneral leaderboardGeneral = new LeaderboardGeneral(userId);
        leaderboardGeneralRepository.save(leaderboardGeneral);
        int leaderboardGeneralId = leaderboardGeneral.id;
        leaderboardGeneral.setPlace(leaderboardGeneralId);

        LeaderboardGroup leaderboardGroup = new LeaderboardGroup(userId, LeaderboardGroupCounter.getInstance().GetCurrentGroupNumber());
        leaderboardGroupRepository.save(leaderboardGroup);
        int leaderboardGroupId = leaderboardGroup.id;
        leaderboardGroup.setPlace(leaderboardGroupId);

        user.leaderboardGeneralId = (leaderboardGeneralId);
        user.leaderboardGrouplId = (leaderboardGroupId);
        userRepository.save(user);
        return user;
    }


    @GetMapping("/join_facebook/{user_id}/{device_id}/{facebook_id}/{email}/{name}/{icon_url}")
    @ResponseBody
    public boolean JoinFacebookData(@PathVariable int user_id,
                                    @PathVariable String device_id,
                                    @PathVariable String facebook_id,
                                    @PathVariable String email,
                                    @PathVariable String name,
                                    @PathVariable String icon_url) {

        User user = userRepository.findById(user_id);
        if (!user.deviceId.equals(device_id))
            return false;

        UserFacebookData userFacebookData = new UserFacebookData(user_id, facebook_id, email, name, icon_url);
        userFacebookDataRepository.save(userFacebookData);

        user.facebookDataId = userFacebookData.id;
        userRepository.save(user);

        return true;
    }


    @GetMapping("/set_high_score/{user_id}/{device_id}/{score}")
    @ResponseBody
    public boolean SetHighScore(@PathVariable int user_id,
                                @PathVariable String device_id,
                                @PathVariable int score) {

        User user = userRepository.findById(user_id);
        if (!user.deviceId.equals(device_id))
            return false;

        if (user.highScore < score) {
            user.highScore = score;
            userRepository.save(user);
        }

        return true;
    }

    private String getUserInfo(User user) {
        return user.id + " " + user.nickname;
    }

}
