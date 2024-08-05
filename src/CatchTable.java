import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import manager.AdminManager;

public class CatchTable {
    BufferedReader br;
    AdminManager adminManager;
    // CustomerManager


    public CatchTable() {
        this.br = new BufferedReader(new InputStreamReader(System.in));
        this.adminManager = new AdminManager();
    }

    public void run() {
        System.out.println("CatchTable 실행");
        System.out.println("1 : 관리자 로그인, 2 : 예약 유저 관련, 9 : 종료");
        while (true) {
            try {
                int command = Integer.parseInt(br.readLine());
                if (command == 1) {
                    AdminManager adminManager=AdminManager.getInstance();
                } else if (command == 2) {
                    // CustomerManager 실행
                } else if (command == 9) {
                    System.out.println("종료합니다.");
                    break;
                } else {
                    System.out.println("잘못된 번호입니다.");
                }
            } catch (IOException e) {
                System.out.println("IOException 발생");
            }
        }
    }

    public static void main(String[] args) {
        CatchTable catchTable = new CatchTable();
        catchTable.run();
    }
}