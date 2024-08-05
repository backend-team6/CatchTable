package manager;

import db.RestaurantRepository;
import db.RestaurantRepositoryImpl;
import entity.Restaurant;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class AdminManager {

    RestaurantRepository restaurantRepository= RestaurantRepositoryImpl.getInstance();

    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

    public void run() {
        try {
            while (true) {
                System.out.println("1:회원가입, 2:로그인 : 8:이전 상태로 돌아기기, 9:종료");
                String command = br.readLine();
                if (command.equals("1")) {
                    // 회원가입
                } else if (command.equals("2")) {
                    /**
                     * 로그인 수행
                     * 로그인 성공 시 관리자 화면 메소드 진입? => managerView(adminId);
                     */
                } else if (command.equals("8")) {
                    return;
                } else if (command.equals("9")) {
                    System.exit(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    void managerView(int adminId) throws IOException {
        while (true) {
            System.out.println("1:식당목록 호출, 2:식당 등록, 8:이전 상태로 돌아가기, 9:종료");
            String command = br.readLine();
            if (command.equals("1")) {
                // 식당 목록 보여주기
            } else if (command.equals("2")) {
                // 식당 등록
            } else if (command.equals("8")) {
                return;
            } else if (command.equals("9")) {
                System.exit(0);
            }
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
}
