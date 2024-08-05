package manager;

import db.AdminRepository;
import db.AdminRepositoryImpl;
import db.RestaurantRepository;
import db.RestaurantRepositoryImpl;
import entity.Restaurant;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class AdminManager {
    private AdminManager(){ }
    public static AdminManager instance=new AdminManager();
    public static AdminManager getInstance(){
        return instance;
    }

    RestaurantRepository restaurantRepository= RestaurantRepositoryImpl.getInstance();
    AdminRepository adminRepository = AdminRepositoryImpl.getInstance();
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

    public void run() {
        try {
            while (true) {
                System.out.println("1:회원가입, 2:로그인 : 8:이전 상태로 돌아기기, 9:종료");
                String command = br.readLine();
                if (command.equals("1")) {
                    // 회원가입
                    signUp();
                } else if (command.equals("2")) {
                    /**
                     * 로그인 수행
                     * 로그인 성공 시 관리자 화면 메소드 진입? => managerView(adminId);
                     */
                    int adminId = logIn();
                    if (adminId > 0) {
                        managerView(adminId);
                    }
                    else {
                        System.out.println("잘못된 아이디 또는 패스워드입니다.");
                    }
                } else if (command.equals("8")) {
                    return;
                } else if (command.equals("9")) {
                    System.exit(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void signUp() {
        try {
            System.out.print("아이디를 입력하세요: ");
            String username = br.readLine();
            System.out.print("비밀번호를 입력하세요: ");
            String password = br.readLine();

            boolean success = adminRepository.registerUser(username, password);
            if (success) {
                System.out.println("회원가입 성공");
            } else {
                System.out.println("회원가입 실패.");
            }
        } catch (IOException e) {
            System.out.println("입력 중 오류가 발생했습니다.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("회원가입 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
    }


    public int logIn() {
        int adminId = -1;
        try {
            System.out.print("아이디를 입력하세요: ");
            String username = br.readLine();
            System.out.print("비밀번호를 입력하세요: ");
            String password = br.readLine();

            adminId = adminRepository.loginUser(username, password);
            if (adminId > 0) {
                System.out.println("로그인 성공!");
            } else {
                System.out.println("로그인 실패. 아이디나 비밀번호가 잘못되었습니다.");
            }
        } catch (IOException e) {
            System.out.println("입력 중 오류가 발생했습니다.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("로그인 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
        return adminId;
    }

    void managerView(int adminId) throws IOException, SQLException {
        while (true) {
            System.out.println("1:식당목록 호출, 2:식당 등록, 8:이전 상태로 돌아가기, 9:종료");
            String command = br.readLine();
            if (command.equals("1")) {
                // 식당 목록 보여주기
                showRestaurant(adminId);
            } else if (command.equals("2")) {
                // 식당 등록
                addRestaurant(adminId);
            } else if (command.equals("8")) {
                return;
            } else if (command.equals("9")) {
                System.exit(0);
            }
        }
    }

    void showRestaurant(int id) throws SQLException, IOException {
        List<Restaurant> restaurantList=restaurantRepository.showAllRestaurant(id);
        for(Restaurant restaurant:restaurantList){
            System.out.println(restaurant.toString());
        }
        System.out.println("원하는 식당의 id를 선택해주세요");
        int selectId=0;
        try {
            selectId=Integer.parseInt(br.readLine());
        } catch (IOException e) {
            System.out.println("잘못 선택하셨습니다.");
            return;
        }

        int finalSelectId = selectId;
        boolean exists = restaurantList.stream()
                .anyMatch(restaurant -> restaurant.getId() == finalSelectId);

        if (!exists) {
            System.out.println("선택하신 식당 ID가 리스트에 없습니다.");
            return;
        }

        Restaurant selectedRestaurant=null;
        for (Restaurant restaurant : restaurantList) {
            if (restaurant.getId() == selectId) {
                selectedRestaurant=restaurant;
            }
        }

        System.out.println("--------------");
        System.out.println("1. 식당 정보 수정");
        System.out.println("2. 식당 상태 수정");
        System.out.println("3. 대기명단 확인");
        System.out.println("4. 손님 호출 출력");
        System.out.println("5. 이전 메뉴로 돌아가기");
        System.out.println("9. 종료");
        System.out.println("--------------");
        System.out.print("번호를 입력해주세요 >> ");
        int selectNum;
        try {
            selectNum=Integer.parseInt(br.readLine());
        } catch (IOException e) {
            System.out.println("잘못선택하셨습니다.");
            return;
        }
        switch (selectNum){
            case 1: updateRestaurant(selectedRestaurant); break;
            case 2: updateRestaurantState(selectedRestaurant); break;
//            case 3: printWaitList(selectedRestaurant); break;
//            case 4: receiveCustomer(selectedRestaurant); break;
            case 5: return ;
            case 9: System.exit(0);
            default:
                System.out.println("잘못 선택하셨습니다.");
                return ;
        }

    }

    void updateRestaurantState(Restaurant restaurant) throws SQLException { //로그인할 때 id받기
        String nowState=restaurant.getState();
        System.out.println("--- 식당 정보를 수정합니다. ---");
        System.out.println("현재 식당 상태 : "+nowState);
        System.out.println("--------------------------");
        System.out.println("1. 영업중");
        System.out.println("2. 준비중");
        System.out.println("3. 마감");
        System.out.println("--------------------------");
        int select= 0;
        try {
            select = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            System.out.println("잘못 선택하셨습니다.");
            return ;
        }
        String setState="";
        switch (select){
            case 1:
                setState="영업중"; break;
            case 2:
                setState="준비중"; break;
            case 3:
                setState="마감"; break;
            default:
                System.out.println("잘못 선택하셨습니다. 종료하겠습니다.");
                return ;
        }
        int result=restaurantRepository.updateRestaurantState(restaurant.getId(), setState);
        if(result==1){
            System.out.println("상태 변경에 성공하였습니다.");
            restaurant.setState(setState);
        }else{
            System.out.println("상태 변경에 실패하였습니다.");
        }
    }

    public void updateRestaurant(Restaurant restaurant) throws IOException {
        System.out.println("수정할 정보 선택");
        System.out.println("1:상태, 2:업체명, 3:전화번호, 4:주소, 5:카테고리, 6:수용인원 수 (공백으로 구분)");

        String[] input = br.readLine().split(" ");

        for (String key : input) {
            if (key.equals("1")) {
                System.out.print("상태 입력 : ");
                restaurant.setState(br.readLine());
            }
            else if (key.equals("2")) {
                System.out.print("업체명 입력 : ");
                restaurant.setRname(br.readLine());
            }
            else if (key.equals("3")) {
                System.out.print("전화번호 입력 : ");
                restaurant.setNumber(br.readLine());
            }
            else if (key.equals("4")) {
                System.out.print("주소 입력 : ");
                restaurant.setAddress(br.readLine());
            }
            else if (key.equals("5")) {
                System.out.println("카테고리 입력 : ");
                restaurant.setCategory(br.readLine());
            }
            else if (key.equals("6")) {
                System.out.print("수용인원 수 입력 : ");
                restaurant.setAccept(Integer.parseInt(br.readLine()));
            }

        }
        try {
            restaurantRepository.updateRestaurant(restaurant);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQLException 발생");
            throw new RuntimeException(e);
        }
    }

    public void addRestaurant(int adminId) {
        try {

            Restaurant restaurant = new Restaurant();
            restaurant.setAdminId(adminId);
            restaurant.setState("준비중");
            System.out.print("업체명 : ");
            restaurant.setRname(br.readLine());
            System.out.print("전화번호 : ");
            restaurant.setNumber(br.readLine());
            System.out.print("주소 : ");
            restaurant.setAddress(br.readLine());
            System.out.print("카테고리 : ");
            restaurant.setAddress(br.readLine());
            System.out.println("수용 가능 인원 수");
            restaurant.setAccept(Integer.parseInt(br.readLine()));

            restaurantRepository.saveRestaurant(restaurant);
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해주세요.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException 발생");
        }
    }
}
