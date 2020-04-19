package com.mapleleaf.profileservice.Controller;


import com.mapleleaf.profileservice.entity.Profile;
import com.mapleleaf.profileservice.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @RequestMapping(value = "/getUserProfile", method = RequestMethod.POST)
    public Profile getUserProfileByUserId(@RequestBody(required = true) String userId) {
        return profileService.getProfileByUserIdFromDao(userId);

    }

}
