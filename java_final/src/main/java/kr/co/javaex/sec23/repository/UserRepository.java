package kr.co.javaex.sec23.repository;

import kr.co.javaex.sec23.domain.User;
import kr.co.javaex.sec23.util.FileUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserRepository {
    private static final String FILE_NAME = "java_final/resource/use.json";
    private List<User> userList = new ArrayList<>();

    public UserRepository() {
        loadData();

        if (userList.isEmpty()) {
            userList.add(new User("admin1", "관리자", "admin123!", "010-0000-0000",
                            "admin@test.com", "정상", "관리자"));
            userList.add(new User("user1", "일반인1", "user123!", "010-1111-1111",
                    "user1@test.com", "정상", "일반회원"));
            userList.add(new User("user2", "일반인2", "user123!", "010-2222-2222",
                    "user2@test.com", "정상", "일반회원"));
            userList.add(new User("user3", "일반인3", "user123!", "010-3333-3333",
                    "user3@test.com", "정상", "일반회원"));
            userList.add(new User("user4", "일반인4", "user123!", "010-4444-4444",
                    "user4@test.com", "정상", "일반회원"));
            userList.add(new User("user5", "일반인5", "user123!", "010-5555-5555",
                    "user5@test.com", "정상", "일반회원"));

            update();
        }
    }

    private void loadData() {
        User[] users = FileUtil.load(FILE_NAME, User[].class);

        if(users != null)
            userList = new ArrayList<>(Arrays.asList(users));
    }

    public void save(User user) {
        userList.add(user);
        FileUtil.save(FILE_NAME, userList);
    }

    public void update() {
        FileUtil.save(FILE_NAME, userList);
    }

    public User findByEmail(String email) {
        for(User user : userList) {
            if (user.getUserEmail().equals(email))
                return user;
        }
        return null;
    }

    public User findByUserId(String userId) {
        for(User user : userList) {
            if (user.getUserId().equals(userId))
                return user;
        }
        return null;
    }

    public List<User> findAll() {
        return userList;
    }
}
