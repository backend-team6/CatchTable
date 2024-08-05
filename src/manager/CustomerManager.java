package manager;

import db.CustomerRepository;
import db.CustomerRepositoryImpl;
import db.RastaurantDTO;
import db.ReservationDTO;
import entity.Reservation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

public class CustomerManager {
    CustomerRepository customerRepository = CustomerRepositoryImpl.getInstance();

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public void run() {
        try {
            while (true) {
                System.out.println("1:예약하기 2:예약확인하기 9:종료");
                String command = br.readLine();
                if (command.equals("1")) {
                    getRestaurantList();
                    reservation();
                } else if (command.equals("2")) {
                    System.out.print("전화번호 입력 >> ");
                    String phoneNumber = br.readLine();
                    reservationConfirm(phoneNumber);
                } else if (command.equals("9")) {
                    System.exit(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //전체 식당 리스트 출력
    private void getRestaurantList() throws SQLException {
        List<RastaurantDTO> restaurants = customerRepository.selectAllRastaurant();

        for (RastaurantDTO rastaurantDTO: restaurants) {
            System.out.println(rastaurantDTO);
        }
    }

    //예약하기
    private void reservation() throws IOException {
        System.out.println("============================");
        System.out.print("식당 ID >> ");
        int restaurantId = Integer.parseInt(br.readLine());
        System.out.print("이름 >> ");
        String name = br.readLine();
        System.out.print("전화번호 >> ");
        String phoneNumber = br.readLine();
        System.out.print("인원수 >> ");
        int count = Integer.parseInt(br.readLine());

        if (customerRepository.insertReservation(new Reservation(restaurantId, count, name, phoneNumber)) == 1) {
            System.out.println("예약되었습니다.");
        }
    }

    private void reservationConfirm(String phoneNumber) {
        System.out.println("=========== 예약 내역 ===========");
        ReservationDTO reservation = customerRepository.selectReservation(phoneNumber);
        System.out.println(reservation);
    }
}
