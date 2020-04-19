package com.mapleleaf.profileservice.service;

import com.mapleleaf.profileservice.dao.ProfileDao;
import com.mapleleaf.profileservice.entity.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    ProfileDao profileDao;

    public Profile getProfileByUserIdFromDao(String userId) {
        return profileDao.fetchProfileData(userId);
    }
}
