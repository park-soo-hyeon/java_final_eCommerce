package kr.co.javaex.sec23.repository;

import kr.co.javaex.sec23.domain.User;
import kr.co.javaex.sec23.util.FileUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserRepository {
    private static final String FILE_NAME = "use.json";
    private List<User> userList = new ArrayList<>();

    public UserRepository() {
        loadData();
    }

    private void loadData() {
        User[] users = FileUtil.load(FILE_NAME, User[].class);

        if(users != null) {
            userList = Arrays.asList(users);
        }
    }

    public void save(User user) {
        userList.add(user);
        FileUtil.save(FILE_NAME, userList);
    }
}
