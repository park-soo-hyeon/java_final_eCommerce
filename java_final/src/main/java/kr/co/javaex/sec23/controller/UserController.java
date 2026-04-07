package kr.co.javaex.sec23.controller;

import kr.co.javaex.sec23.domain.User;
import kr.co.javaex.sec23.service.UserService;

import java.util.Scanner;

public class UserController {
    private final UserService userService = new UserService();
    private final Scanner scanner = new Scanner(System.in);

    private User loggedInUser = null;

    public void userMenu() {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n===== [ 회원 관리 메뉴 ] =====");
            System.out.println("1. 회원가입");
            System.out.println("2. 로그인");
            System.out.println("3. 로그아웃");
            System.out.println("4. 회원 정보 수정");
            System.out.println("5. 비밀번호 변경");
            System.out.println("0. 뒤로 가기 (메인 메뉴로)");
            System.out.print("메뉴를 선택하세요: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    registerMenu();
                    break;
                case "2":
                    loginMenu();
                    break;
                case "3":
                    logoutMenu();
                    break;
                case "4":
                    updateInfoMenu();
                    break;
                case "5":
                    changePwMenu();
                    break;
                case "0":
                    System.out.println("메인 메뉴로 돌아갑니다.");
                    isRunning = false;
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 0~5 사이의 숫자를 입력해 주세요.");
            }
        }
    }

    public void registerMenu() {
        System.out.println("\n=== 회원가입 ===");
        System.out.print("아이디 (영문, 숫자 5~15자): ");
        String userId = scanner.nextLine();

        System.out.print("이름: ");
        String name = scanner.nextLine();

        System.out.print("비밀번호 (영문, 숫자 포함 5~15자): ");
        String pw = scanner.nextLine();

        System.out.print("전화번호: ");
        String phone = scanner.nextLine();

        System.out.print("이메일: ");
        String email = scanner.nextLine();

        System.out.print("일반회원(1) 관리자(2) 입력: ");
        int check = Integer.parseInt(scanner.nextLine());

        String cate = "일반회원";
        if (check == 1)
            cate = "일반회원";
        else if (check == 2)
            cate = "관리자";

        User newUser = new User(userId, name, pw, phone, email, cate);

        try {
            userService.register(newUser);
            System.out.println("회원가입이 완료되었습니다!");
        } catch (IllegalArgumentException e) {
            System.out.println("가입 실패: " + e.getMessage());
        }
    }

    public void loginMenu() {
        if (loggedInUser != null) {
            System.out.println("이미 로그인되어 있습니다.");
            return;
        }

        System.out.println("\n=== 로그인 ===");
        System.out.print("이메일: ");
        String email = scanner.nextLine();
        System.out.print("비밀번호: ");
        String pw = scanner.nextLine();

        User user = userService.login(email, pw);

        if (user != null) {
            loggedInUser = user;
            System.out.println("로그인 성공! 환영합니다, " + user.getName() + "님.");
        } else {
            System.out.println("로그인 실패: 이메일 또는 비밀번호가 일치하지 않습니다.");
        }
    }

    public void logoutMenu() {
        if (loggedInUser != null) {
            System.out.println("로그아웃 되었습니다. 안녕히 가세요, " + loggedInUser.getName() + "님!");
            loggedInUser = null;
        } else {
            System.out.println("로그인 상태가 아닙니다.");
        }
    }

    public void updateInfoMenu() {
        if (loggedInUser == null) {
            System.out.println("로그인이 되어 있지 않아 수정이 불가능합니다.");
            return;
        }

        System.out.println("\n=== 회원 정보 수정 ===");
        System.out.print("이름: ");
        String name = scanner.nextLine();

        System.out.print("전화번호: ");
        String phone = scanner.nextLine();

        System.out.print("이메일: ");
        String email = scanner.nextLine();

        try {
            userService.updateUserInfo(loggedInUser.getUserId(), name, phone, email);
            System.out.println("회원정보 수정이 완료되었습니다!");
        } catch (IllegalArgumentException e) {
            System.out.println("수정 실패: " + e.getMessage());
        }
    }

    public void changePwMenu() {
        if (loggedInUser == null) {
            System.out.println("로그인이 되어 있지 않아 비밀번호 변경이 불가능합니다.");
            return;
        }

        System.out.println("\n=== 비밀번호 변경 ===");
        System.out.print("현재 비밀번호 입력: ");
        String oldPw = scanner.nextLine();

        System.out.print("변경할 비밀번호 입력: ");
        String newPw = scanner.nextLine();

        try {
            userService.changePassword(loggedInUser.getUserId(), oldPw, newPw);
            System.out.println("비밀번호 변경이 완료되었습니다!");
        } catch (IllegalArgumentException e) {
            System.out.println("비밀번호 변경 실패: " + e.getMessage());
        }
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
}