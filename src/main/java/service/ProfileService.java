package service;

import dto.Profile;
import enums.GeneralStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import repository.ProfileRepository;

import java.util.List;
@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    @Lazy
    private CardService cardService;

    public void profileList() {
        List<Profile> profileList = profileRepository.getProfileList();

        for (Profile profile : profileList) {
            System.out.println(profile);
        }
    }

    public void changeProfileStatus(String phone) {
        Profile profile = profileRepository.getProfileByPhone(phone);
        if (profile == null) {
            System.out.println("Profile not found");
            return;
        }

        if (profile.getStatus().equals(GeneralStatus.ACTIVE)) {
            profileRepository.changeProfileStatus(phone, GeneralStatus.BLOCK);
        } else {
            profileRepository.changeProfileStatus(phone, GeneralStatus.ACTIVE);
        }
    }

}
