package crud2;

import java.util.Scanner;

public class ScoreMain {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		ScoreService service = new ScoreService();

		while (true) {
			System.out.println("\n - - - - - - - - - - - - - - - -  성적 관리 시스템   - - - - - - - - - - - - - - - - - -");
			System.out.print("옵션 선택 | 1. 성적 등록   2. 전체 조회   3. 개별 조회   4. 수정   5. 삭제   0. 종료 --> ");
			int option = scanner.nextInt();

			switch (option) {
			case 1:
				service.setInput();
				break;
			case 2:
				service.getList();
				break;
			case 3:
				service.getSearch();
				break;
			case 4:
				service.setUpdate();
				break;
			case 5:
				service.setDelete();
				break;
			default:
				System.out.println("시스템을 종료합니다. 이용해 주셔서 감사합니다.");
				scanner.close();
				return;
			}
		}
	}
}
