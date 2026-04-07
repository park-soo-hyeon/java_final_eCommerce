package kr.co.javaex.sec23.service;

import kr.co.javaex.sec23.domain.User;
import kr.co.javaex.sec23.repository.UserRepository;

public class UserService {
    private final UserRepository userRepository = new UserRepository();

    public void register(User newUser) throws IllegalArgumentException {
        String userId = newUser.getUserId();
        String userEmail = newUser.getUserEmail();
        String userPw = newUser.getUserPw();

        if (!userId.matches("^[a-zA-Z0-9]{5,15}$"))
            throw new IllegalArgumentException("회원 ID는 영문자와 숫자 사용하여 5~15자리로 입력!");

        if (userRepository.findByUserId(userId) != null)
            throw new IllegalArgumentException("이미 존재하는 회원 ID!");

        if (!userPw.matches("^(?=.*[a-zA-Z])(?=.*[0-9]).{5,15}$"))
            throw new IllegalArgumentException("비밀번호는 영문자 1자 이상, 숫자 1자 이상을 포함하여 5~15자리로 입력!");

        if (userRepository.findByEmail(userEmail) != null)
            throw new IllegalArgumentException("이미 존재하는 이메일!");

        userRepository.save(newUser);
    }

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null)
            return null;
        if (user.getUserPw().equals(password))
            return user;
        else
            return null;
    }

    public void updateUserInfo(String userId, String newName, String newPhone, String newEmail) throws IllegalArgumentException {
        User user = userRepository.findByUserId(userId);

        if (user == null)
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");

        if (!user.getUserEmail().equals(newEmail)) {
            if (userRepository.findByEmail(newEmail) != null)
                throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        user.setName(newName);
        user.setUserNum(newPhone);
        user.setUserEmail(newEmail);

        userRepository.update();
    }

    public void changePassword(String userId, String oldPw, String newPw) throws IllegalArgumentException {
        User user = userRepository.findByUserId(userId);

        if (user == null)
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");

        if (!user.getUserPw().equals(oldPw))
            throw new IllegalArgumentException("비밀번호가 일치 하지 않습니다.");

        if (!newPw.matches("^(?=.*[a-zA-Z])(?=.*[0-9]).{5,15}$"))
            throw new IllegalArgumentException("비밀번호는 영문자 1자 이상, 숫자 1자 이상을 포함하여 5~15자리로 입력!");

        user.setUserPw(newPw);
        userRepository.update();
    }
}