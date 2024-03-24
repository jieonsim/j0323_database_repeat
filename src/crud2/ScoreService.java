package crud2;

import java.util.ArrayList;
import java.util.Scanner;

public class ScoreService {
	Scanner scanner = new Scanner(System.in);
	ScoreDAO dao = new ScoreDAO();
	ScoreVO vo;

	public void setInput() {
		while (true) {
			System.out.println("\n성적 등록을 위해 아래 정보를 입력해 주세요.");
			System.out.print("학생 이름 : ");
			String name = scanner.next();

			while (true) {
				vo = dao.getSearch(name);
				if (vo == null) {
					break;
				} else {
					System.out.println("동일한 이름의 학생이 존재합니다. 다시 입력해 주세요.");
				}
			}

			System.out.print("국어 점수 : ");
			int korean = scanner.nextInt();

			System.out.print("영어 점수 : ");
			int english = scanner.nextInt();

			System.out.print("수학 점수 : ");
			int math = scanner.nextInt();

			vo = new ScoreVO();
			vo.setName(name);
			vo.setKorean(korean);
			vo.setEnglish(english);
			vo.setMath(math);

			int result = dao.setInput(vo);
			if (result != 0) {
				System.out.println(name + " 학생의 성적 등록이 완료되었습니다.");
			} else {
				System.out.println(name + " 학생의 성적 등록이 실패하였습니다.");
			}

			System.out.print("다른 학생의 성적 등록을 계속 진행하시겠습니까? (Y/N) : ");
			String answer = scanner.next();
			if (!answer.toUpperCase().equals("Y")) {
				break;
			}
		}
	}

	public void getList() {
		ArrayList<ScoreVO> vos = dao.getList();
		System.out.println("\n\t성 적 리 스 트");
		System.out.println("====================================================");
		System.out.println("번호\t이름\t\t국어\t\t영어\t\t수학\t\t총점\t\t평균\t\t\t학점");
		System.out
				.println("------------------------------------------------------------------------------------------");

		for (int i = 0; i < vos.size(); i++) {
			vo = vos.get(i);
			calculator(vo);

			System.out.print(" " + (i + 1) + "\t");
			System.out.print(vo.getName() + "\t\t");
			System.out.print(vo.getKorean() + "\t\t");
			System.out.print(vo.getEnglish() + "\t\t");
			System.out.print(vo.getMath() + "\t\t");
			System.out.print(vo.getTotal() + "\t\t");
			System.out.print(String.format("%.1f", vo.getAverage()) + "\t\t\t");
			System.out.print(vo.getGrade() + "\n");
		}
		System.out
				.println("------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t총 인원 수 : " + vos.size() + "명");
		System.out.println("====================================================");
	}

	private void calculator(ScoreVO vo) {
		vo.setTotal(vo.getKorean() + vo.getEnglish() + vo.getMath());
		vo.setAverage(vo.getTotal() / 3.0);

		if (vo.getAverage() >= 90) {
			vo.setGrade('A');
		} else if (vo.getAverage() >= 80) {
			vo.setGrade('B');
		} else if (vo.getAverage() >= 70) {
			vo.setGrade('C');
		} else if (vo.getAverage() >= 60) {
			vo.setGrade('D');
		} else {
			vo.setGrade('F');
		}
	}

	public void getSearch() {
		while (true) {
			BasicSearch();

			System.out.print("조회를 계속 진행하시겠습니까 ? (Y/N) : ");
			String answer = scanner.next();

			if (!answer.toUpperCase().equals("Y")) {
				break;
			}
		}
	}

	private void BasicSearch() {
		System.out.print("\n성적을 조회할 학생의 이름을 입력하세요 : ");
		String name = scanner.next();

		vo = dao.getSearch(name);

		if (vo != null) {
			calculator(vo);

			System.out.println("\n번호 : " + vo.getIdx());
			System.out.println("이름 : " + vo.getName());
			System.out.println("국어 : " + vo.getKorean());
			System.out.println("영어 : " + vo.getEnglish());
			System.out.println("수학 : " + vo.getMath());
			System.out.println("총점 : " + vo.getTotal());
			System.out.println("평균 : " + vo.getAverage());
			System.out.println("학점 : " + vo.getGrade());
		} else {
			System.out.println(name + " 학생은 조회되지 않습니다.");
		}

	}

	public void setUpdate() {
		BasicSearch();

		boolean isModified = false; // 수정 여부를 추적하는 플래그

		while (true) {
			System.out.print("\n수정할 항목을 선택하세요. | 1. 이름   2. 국어 점수  3. 영어 점수   4. 수학 점수  0. 수정 종료 : ");
			int option = scanner.nextInt();

			if (option == 0) {
				if (!isModified) { // 수정된 내역이 없으면 메시지를 출력하지 않음
					System.out.println("수정된 내역이 없습니다.");
				}
				break; // 수정 종료
			}

			System.out.print("수정할 내용을 입력하세요 : ");

			switch (option) {
			case 1:
				vo.setName(scanner.next());
				System.out.println("학생의 이름 수정이 완료되었습니다.");
				break;
			case 2:
				vo.setKorean(scanner.nextInt());
				System.out.println("국어 점수의 수정이 완료되었습니다.");
				break;
			case 3:
				vo.setEnglish(scanner.nextInt());
				System.out.println("영어 점수의 수정이 완료되었습니다.");
				break;
			case 4:
				vo.setMath(scanner.nextInt());
				System.out.println("수학 점수의 수정이 완료되었습니다.");
				break;
			default:
				System.out.println("올바른 번호를 입력하세요.");
				continue;
			}

			System.out.print("수정을 계속 진행하시겠습니까? (Y/N) : ");
			String answer = scanner.next();
			if (answer.toUpperCase().equals("N")) {
				System.out.println("수정 처리가 종료되었습니다.");
				break; // 루프 종료
			}
		}

		if (isModified) { // 수정된 내역이 있으면 결과를 저장 및 처리
			int result = dao.setUpdate(vo);
			if (result != 0) {
				System.out.println("성적 수정이 완료되었습니다.");
			} else {
				System.out.println("수정된 내역이 없습니다."); // 실제로는 이 경우가 발생되지 않을 것임
			}
		}
	}

	public void setDelete() {
		BasicSearch();
		if(vo != null) {
			System.out.print("삭제하시겠습니까 ? (Y/N) ==> ");
			String answer = scanner.next();
			if(answer.toUpperCase().equals("Y")) {
				int result = dao.setDelete(vo.getIdx());
				if(result != 0) {
					System.out.println(vo.getName() + " 학생의 성적 자료가 삭제되었습니다.");
				} else {
					System.out.println(vo.getName() + " 학생의 성적 자료가 없습니다.");
					System.out.println();
				}
			} else {
				System.out.println("성적 삭제 처리가 취소되었습니다.");
			}
		}
	}
}
