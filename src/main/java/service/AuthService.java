package service;

import container.ComponentContainer;
import controller.AdminController;
import controller.ProfileController;
import dto.Profile;
import enums.GeneralStatus;
import enums.ProfileRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ProfileRepository;
import util.MD5Util;

import java.time.LocalDateTime;
@Service
public class AuthService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private AdminController adminController;
    @Autowired
    private ProfileController profileController;
    public void login(String phone, String password) {
        Profile profile = profileRepository.getProfileByPhoneAndPassword(phone, MD5Util.encode(password));

        if (profile == null) {
            System.out.println("Phone or Password incorrect");
            return;
        }

        if (!profile.getStatus().equals(GeneralStatus.ACTIVE)) {
            System.out.println("You not allowed.MF");
            return;
        }
        ComponentContainer.currentProfile = profile;
        if (profile.getRole().equals(ProfileRole.ADMIN)) {
            adminController.start();
        } else if (profile.getRole().equals(ProfileRole.USER)) {

            profileController.start();
        } else {
            System.out.println("You don't have any role.");
        }

    }

    public void registration(Profile profile) {
        // check
        Boolean exist = profileRepository.isPhoneExist(profile.getPhone()); // unique
        if (exist) {
            System.out.println(" Phone already exist.");
            return;
        }
        profile.setStatus(GeneralStatus.ACTIVE);
        profile.setCreatedDate(LocalDateTime.now());
        profile.setRole(ProfileRole.USER);
        profile.setPassword(MD5Util.encode(profile.getPassword()));
        int result = profileRepository.saveProfile(profile);

        if (result != 0) {
            System.out.println("Profile created.");
        }

    }
}
