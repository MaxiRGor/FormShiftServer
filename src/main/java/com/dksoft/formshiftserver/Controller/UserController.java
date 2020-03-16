package com.dksoft.formshiftserver.Controller;

import com.dksoft.formshiftserver.LeaderboardGroupCounter;
import com.dksoft.formshiftserver.Model.*;
import com.dksoft.formshiftserver.Repository.LeaderboardGeneralRepository;
import com.dksoft.formshiftserver.Repository.LeaderboardGroupRepository;
import com.dksoft.formshiftserver.Repository.UserFacebookDataRepository;
import com.dksoft.formshiftserver.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

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

        LeaderboardGeneral leaderboardGeneral = new LeaderboardGeneral(userId,user.nickname,user.highScore);
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


    @GetMapping("/add_facebook_data/{user_id}/{device_id}/{facebook_id}/{email}/{name}/{icon_url}")
    @ResponseBody
    public ResponseEntity<HttpResponse> AddFacebookData(@PathVariable int user_id,
                                    @PathVariable String device_id,
                                    @PathVariable String facebook_id,
                                    @PathVariable String email,
                                    @PathVariable String name,
                                    @PathVariable String icon_url) {

        User user = userRepository.findById(user_id);
        if (!user.deviceId.equals(device_id))
            return new ResponseEntity<>(new HttpResponse( 1, "USER ID AND DEVICE ID MISMATCH"),HttpStatus.FORBIDDEN) ;

        UserFacebookData userFacebookData = new UserFacebookData(user_id, facebook_id, email, name, icon_url);
        userFacebookDataRepository.save(userFacebookData);

        user.facebookDataId = userFacebookData.id;
        userRepository.save(user);

        return  new ResponseEntity<>(new HttpResponse(0, "FACEBOOK DATA JOINED TO USER ACCOUNT"),HttpStatus.OK);
    }


    @GetMapping("/set_high_score/{user_id}/{device_id}/{score}")
    @ResponseBody
    public ResponseEntity<HttpResponse> SetHighScore(@PathVariable int user_id,
                                                     @PathVariable String device_id,
                                                     @PathVariable int score) {

        User user = userRepository.findById(user_id);
        if (!user.deviceId.equals(device_id))
            return new ResponseEntity<>(new HttpResponse(1, "USER ID AND DEVICE ID MISMATCH"),HttpStatus.FORBIDDEN) ;
        if (user.highScore < score) {
            user.highScore = score;
            userRepository.save(user);
            return new ResponseEntity<>(new HttpResponse( 0, "NEW HIGH SCORE ADDED"),HttpStatus.OK) ;
        } else return new ResponseEntity<>(new HttpResponse( 2, "SCORE IS LOWER THAN HIGH SCORE"),HttpStatus.OK) ;

    }

    private String getUserInfo(User user) {
        return user.id + " " + user.nickname;
    }

}
