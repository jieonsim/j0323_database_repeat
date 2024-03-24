package crud1;

import java.util.Scanner;

public class UserMain {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		UserService service = new UserService();

		while (true) {
			System.out.print("1. 회원 등록   2. 전체 조회   3. 개별 조회   4. 수정   5. 삭제   6. 종료 => ");
			int choice = scanner.nextInt();

			switch (choice) {
			case 1:
				service.setSingUp();
				break;
			case 2:
				service.getFullList();
				break;
			case 3:
				System.out.print("개별 조회가 필요한 회원의 이름을 입력하세요 : ");
				String name = scanner.next();
				service.getSearch(name);
				break;
			case 4:
				System.out.print("수정이 필요한 회원의 이름을 입력하세요 : ");
				name = scanner.next();
				service.setUpdate(name);
				break;
			case 5:
				System.out.print("삭제할 회원의 이름을 입력하세요 : ");
				name = scanner.next();
				service.setDelete(name);
				break;
			default:
				System.out.println("〓 〓 〓 종료되었습니다. 〓 〓 〓");
				scanner.close();
				return;
			}
		}
	}
}
