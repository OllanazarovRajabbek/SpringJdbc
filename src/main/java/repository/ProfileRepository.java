package repository;

import db.DataBase;
import dto.Profile;
import enums.GeneralStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class ProfileRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Profile getProfileByPhoneAndPassword(String phone, String password) {
        String sql = "select  * from profile where phone= '%s' and password = '%s'";
        sql = String.format(sql, phone, password);
        Profile profile = (Profile) jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Profile.class));
        return profile;
    }

    public Profile getProfileByPhone(String phone) {
        String sql = "select  * from profile where phone= '%s'";
        sql = String.format(sql, phone);
        Profile profile = (Profile) jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Profile.class));
        return profile;
    }

    public Boolean isPhoneExist(String phone) {
        String sql = "select  id from profile where phone= '%s'";
        sql = String.format(sql, phone);
        jdbcTemplate.execute(sql);
        return true;
    }

    public Integer saveProfile(Profile profile) {
        String sql = "insert into profile(name,surname,phone,password,role,status,created_date) " +
                "values ('%s','%s','%s','%s','%s','%s','%s')";
        sql = String.format(sql, profile.getName(), profile.getSurname(), profile.getPhone(), profile.getPassword(),
                profile.getRole().name(), profile.getStatus().name(), profile.getCreatedDate());
        jdbcTemplate.execute(sql);
        return 1;
    }


    public List<Profile> getProfileList() {
        String sql = "select * from student";
        List<Profile> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Profile.class));
        return list;
    }

    public Integer changeProfileStatus(String phone, GeneralStatus status) {
        return jdbcTemplate.update("update profile set status = '%s' where phone = '%s'", phone, status);
    }
}
