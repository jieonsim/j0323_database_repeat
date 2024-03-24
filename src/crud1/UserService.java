package crud1;

import java.util.ArrayList;
import java.util.Scanner;

public class UserService {
	Scanner scanner = new Scanner(System.in);

	public void setSingUp() {
		UserDAO dao = new UserDAO();
		System.out.println("〓 〓 〓 회원 정보 등록하기 〓 〓 〓");

		System.out.print("이름 : ");
		String name = scanner.next();

		System.out.print("나이 : ");
		int age = scanner.nextInt();

		System.out.print("성별 : ");
		String gender = scanner.next();

		System.out.print("주소 : ");
		String address = scanner.next();

		UserVO vo = new UserVO();
		vo.setName(name);
		vo.setAge(age);
		vo.setGender(gender);
		vo.setAddress(address);

		dao.setSignUp(vo);
		System.out.println("〓 〓 〓 회원 등록이 완료되었습니다.〓 〓 〓");

		dao.connClose();
	}

	public void getFullList() {
		UserDAO dao = new UserDAO();

		ArrayList<UserVO> vos = dao.getFullList();
		UserVO vo = new UserVO();

		System.out.println("------------------------------------------------");
		System.out.println("번호\t이름\t\t나이\t성별\t주소");
		System.out.println("------------------------------------------------");

		for (int i = 0; i < vos.size(); i++) {
			vo = vos.get(i);
			System.out.print(" " + (i + 1) + "\t");
			System.out.print(vo.getName() + "\t\t");
			System.out.print(vo.getAge() + "\t");
			System.out.print(vo.getGender() + "\t");
			System.out.print(vo.getAddress() + "\n");
		}
		System.out.println("------------------------------------------------");
		System.out.println("\t\t총 : " + vos.size() + "건");

		dao.connClose();
	}

	public void getSearch(String name) {
		UserDAO dao = new UserDAO();

		UserVO vo = dao.getSearch(name);

		System.out.println(name + "님(으)로 검색된 자료");
		if (vo.getName() != null) {
			System.out.println("번호 : " + vo.getIdx() + "\t");
			System.out.println("이름 : " + vo.getName() + "\t");
			System.out.println("나이 : " + vo.getAge() + "\t");
			System.out.println("성별 : " + vo.getGender() + "\t");
			System.out.println("주소 : " + vo.getAddress() + "\t");
		} else {
			System.out.println("존재하지 않는 회원입니다.");
		}
		dao.connClose();
	}

	public void setUpdate(String name) {
		UserDAO dao = new UserDAO();
		UserVO vo = dao.getSearch(name);

		System.out.println(name + "님(으)로 검색된 자료");
		if (vo.getName() != null) {
			System.out.println("번호 : " + vo.getIdx() + "\t");
			System.out.println("이름 : " + vo.getName() + "\t");
			System.out.println("나이 : " + vo.getAge() + "\t");
			System.out.println("성별 : " + vo.getGender() + "\t");
			System.out.println("주소 : " + vo.getAddress() + "\t");

			System.out.print("수정할 항목 : 1. 이름   2. 나이   3. 성별   4. 주소 => ");
			int choice = scanner.nextInt();

			System.out.print("수정할 내용 : ");
			String content = scanner.next();

			switch (choice) {
			case 1:
				vo.setName(content);
				break;
			case 2:
				vo.setAge(Integer.parseInt(content));
				break;
			case 3:
				vo.setGender(content);
				break;
			case 4:
				vo.setAddress(content);
				break;
			default:
				System.out.println("==> 수정 항목에 존재하지 않는 항목을 선택하여 처리가 불가합니다.");
				return;
			}

			int result = dao.setUpdate(vo);
			if (result != 0) {
				System.out.println("==> 자료 수정이 완료되었습니다.");
			}
		} else {
			System.out.println("==> 존재하지 않는 회원입니다.");
		}
		dao.connClose();
	}

	public void setDelete(String name) {
		UserDAO dao = new UserDAO();
		UserVO vo = dao.getSearch(name);

		System.out.println(name + "님(으)로 검색된 자료");
		if (vo.getName() != null) {
			System.out.println("번호 : " + vo.getIdx() + "\t");
			System.out.println("이름 : " + vo.getName() + "\t");
			System.out.println("나이 : " + vo.getAge() + "\t");
			System.out.println("성별 : " + vo.getGender() + "\t");
			System.out.println("주소 : " + vo.getAddress() + "\t");

			System.out.println("------------------------------------------------");
			System.out.print("==> 삭제하시겠습니까 ? (Y/N) : ");
			String answer = scanner.next();

			if (answer.toUpperCase().equals("Y")) {
				dao.setDelete(name);
				System.out.println("==> 삭제가 완료되었습니다.");
			} else {
				System.out.println("==> 삭제 취소가 완료되었습니다.");
			}
		} else {
			System.out.println("==> 존재하지 않는 회원으로 삭제가 불가합니다.");
		}
		dao.connClose();
	}
}
