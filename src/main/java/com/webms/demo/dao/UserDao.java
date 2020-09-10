package com.webms.demo.dao;

import com.webms.demo.models.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.webms.demo.utils.Helper.newDate;

@Component
public class UserDao {
    
    private static User a = new User("Somjit", 1, newDate("1991 08 19"));
    private static User b = new User("CC1", 2, newDate("1990 07 07"));
    private static User c = new User("CC2", 3, newDate("1990 07 07"));
    
    private static int userCount = 3;
    private static List<User> userList = new ArrayList<>();
    
    static {
        userList.add(a);
        userList.add(b);
        userList.add(c);
    }
    
    public List<User> findAll() {
        return userList;
    }
    
    public User save(User u) {
        if (u.getId() == null) {
            u.setId(++userCount);
        }
        userList.add(u);
        return u;
    }
    
    public User findOne(int i) {
        for (User user : userList) {
            if (i == user.getId()) return user;
        }
        return null;
    }
    
}
