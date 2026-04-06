package kr.co.javaex.sec23.repository;

public class User {
    private String userId;
    private String name;
    private String userPw;
    private String userNum;
    private String userEmail;
    private String userStatus;
    private String userCate;

    public User(String userId, String name, String userPw, String userNum, String userEmail, String userStatus, String userCate) {
        this.userId = userId;
        this.name = name;
        this.userPw = userPw;
        this.userNum = userNum;
        this.userEmail = userEmail;
        this.userStatus = userStatus;
        this.userCate = userCate;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getUserPw() {
        return userPw;
    }

    public String getUserNum() {
        return userNum;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public String getUserCate() {
        return userCate;
    }
}
