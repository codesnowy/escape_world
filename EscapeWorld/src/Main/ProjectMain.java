package Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import model.DestinationVO;
import model.MemberVO;
import model.SituationVO;
import service.DestiService;
import service.MemberService;
import service.SituService;

public class ProjectMain {

	public static void main(String[] args) {

		MemberService memService = MemberService.getInstance();
		SituService situ = SituService.getInstance();
		DestiService desti = DestiService.getInstance();

		ArrayList<SituationVO> situList = situ.getSituList();

		Scanner sc = new Scanner(System.in);

		System.out.println("  ＿人人人人人人人人人人＿\n" 
						 + "  ＞                    ＜\n" 
						 + "  ＞  이승 탈출 넘버원  ＜\n"
						 + "  ＞                    ＜\n" 
						 + "  ￣Y^Y^Y^Y^Y^Y^Y^Y^Y^Y^￣");

		while (true) {
			System.out.println("\n행동을 선택해 주세요.");
			System.out.println("1. 회원가입 | 2. 로그인 | 3. 종료");
			System.out.print(">>> ");

			int command = 0;

			try {
				command = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("숫자로 입력해주세요.");
				continue;
			}

			if (command == 1) {

				// 회원가입
				System.out.println("아이디를 입력해주세요.");
				System.out.print(">>> ");
				String id = sc.nextLine();
				System.out.println("비밀번호를 입력해주세요.");
				System.out.print(">>> ");
				String pw = sc.nextLine();

				boolean isDuple = memService.dupleCheck(id);

				if (isDuple) {
					System.out.println("중복된 아이디 입니다.");
				} else {

					int cnt = memService.insertMem(new MemberVO(id, pw, 0, 0, 0));

					if (cnt > 0) {
						System.out.println("회원가입에 성공하였습니다.");
					} else {
						System.out.println("회원가입에 실패하였습니다. 관리자에게 문의하세요.");
					}
				}

			} else if (command == 2) {
				System.out.println("아이디를 입력해주세요.");
				System.out.print(">>> ");
				String id = sc.nextLine();
				System.out.println("비밀번호를 입력해주세요.");
				System.out.print(">>> ");
				String pw = sc.nextLine();

				MemberVO login = memService.loginMem(id);

				if (login != null) {

					if (pw.equals(login.getMemPw())) {

						System.out.print("\n✨" + login.getMemId() + "님 환영합니다✨");

						while (true) {
							System.out.println("행동을 선택해주세요.");
							System.out.println("1. 게임 시작 | 2. 상점 | 3. 랭킹 | 4. 로그아웃");
							System.out.print(">>> ");

							int select = 0;

							try {
								select = Integer.parseInt(sc.nextLine());
							} catch (NumberFormatException e) {
								System.out.println("숫자를 입력해주세요");
								continue;
							}
							if (select == 1) {

								int situNo = 0;
								while (true) {
									// 0. 랜덤 복권과 게임 
									Random random = new Random();
									int randInt = random.nextInt(100) + 1;
									if (randInt <= 10) {
										System.out.println("\n🎁10%의 확률로 복권에 당첨되었다!(소지금 +500원)🎁\n");
										login.setMemoney(login.getMemoney() + 500);
										memService.win(id, login.getMemscore(), login.getMemoney());
										continue;
									} else if (randInt >= 90) {
										System.out.println("10%의 확률로 게임이 시작됩니다.");
										int gameNo = 0;
										if (randInt % 2 == 0) {
											gameNo = 2;
										} else if (randInt % 2 == 1) {
											gameNo = 1;
										}
										while (true) {
											System.out.println("당신의 눈 앞에 두 개의 문이 있습니다. 선택해 주세요");
											System.out.print("1. 오른쪽 문 | 2. 왼쪽 문  >>> ");
											int gameAnswer = 0;
											try {
												gameAnswer = Integer.parseInt(sc.nextLine());
											} catch (NumberFormatException e) {
												System.out.println("숫자를 입력해주세요");
												continue;
											}
											if (gameNo == gameAnswer) {
												System.out.println("돈이 있는 문이었습니다! (소지금 +500)");
												login.setMemoney(login.getMemoney() + 500);
												memService.win(id, login.getMemscore(), login.getMemoney());
												break;
											} else if (gameNo != gameAnswer) {
												System.out.println("\n밖으로 떨어지는 문을 골라 사망하였습니다");
												System.out.println("━━━━━┓\n" + 
														"┓┓┓┓┓┃/ . `\n" + 
														"┓┓┓┓┓ : .＼○ノ (으아악!)\n" + 
														"┓┓┓┓┓┃ ` / 　　\n" + 
														"┓┓┓┓┓┃ `ノ) . `\n" + 
														"┓┓┓┓┓┃ ,\n" + 
														"┓┓┓┓┓┃　　　 　\n" + 
														"┓┓┓┓┓┃　　　 \n" + 
														"┓┓┓┓┓┃　　 　　\n" + 
														"┓┓┓┓┓┃　");
												System.out.println("게임에 실패하여 처음으로 돌아갑니다.");
												break;
											}
											break;
										}
									} else {
										// 이승 탈출 넘버원 시작
										Collections.shuffle(situList);
										for (int i = 0; i < 3; i++) {

											// 1. 상황 출력하기
											System.out.println("\n" + (i + 1) + "번 상황 : " + situList.get(i));
											situNo = situList.get(i).getSitu_no();
											// 2. destination에서 출력한 SITU_TXT의 SITU_NO와 같은 SITU_NO와 연결된 DEST_TXT 가져오기
											ArrayList<DestinationVO> destiList = desti.getDestiList(situNo);
											System.out.println(destiList);
											// 3. 사용자에게서 대답을 입력 받고 그걸로 choice번호 생성
											System.out.print("\n숫자로 대답을 입력해 주세요 >>> ");
											int anwser = 0;
											try {
												anwser = Integer.parseInt(sc.nextLine());
											} catch (NumberFormatException e) {
												System.out.println("숫자를 입력해주세요");
												i--;
												continue;
											}
											int destiNo = 0;
											String result = "";
											if (anwser == 1) {
												destiNo = desti.getDestiList(situNo).get(anwser - 1).getChoice();
												result = desti.getDestiList(situNo).get(anwser - 1).getDest_result();
											} else if (anwser == 2) {
												destiNo = desti.getDestiList(situNo).get(anwser - 1).getChoice();
												result = desti.getDestiList(situNo).get(anwser - 1).getDest_result();
											} else {
												System.out.println("제시된 숫자 중에서 입력해 주세요");
												i--;
												continue;
											}
											System.out.println("\n>> " + result + " <<");
											// 4. DESTI와 LIVE를 비교하여 게임 진행 여부 확인 + RESULT 출력
											if (destiNo != situList.get(i).getLive()) {

												if (login.getMemguard() > 0) {
													// 4-1. 만약 방어막이 1개 이상 있다면 부활 시키기
													login.setMemguard(login.getMemguard() - 1);
													memService.revive(id, login.getMemguard());

													System.out.println(
															"생명수💧를 사용하여 부활하였다! 남은 생명수: " + login.getMemguard() + "개");
													i--;
													continue;

												} else {
													// 4-2. 방어막이 없다면 다시 처음으로 돌아가기
													System.out.println("   ▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄\n" + "   ████▌▄▌▄▐▐▌█████\n"
															+ "   ████▌▄▌▄▐▐▌▀████\n" + "   ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀");
													System.out.println("게임에 실패하여 처음으로 돌아갑니다.\n");
													break;
												}
											} else {
												if (i == 2) {
													// 5. 3번 맞췄을 경우 점수 1점과 돈 500원 획득
													System.out.println("\n▶3번 생존에 성공하였다!(점수 + 1 , 돈 + 500원)◀");
													login.setMemscore(login.getMemscore() + 1);
													login.setMemoney(login.getMemoney() + 500);

													memService.win(id, login.getMemscore(), login.getMemoney());
													break;
												} else {
													continue;
												}
											}
										}

									}
									// 6. 게임에서 지거나 3번 이상 맞췄을 경우 진행여부 확인하기
									System.out.println("\n다시 시작하시겠습니까?");
									System.out.println("1. 진행한다 | 2. 선택 화면으로 돌아간다");
									System.out.print(">>> ");

									int gameCQ = 0;

									try {
										gameCQ = Integer.parseInt(sc.nextLine());
									} catch (NumberFormatException e) {
										System.out.println("숫자로 입력해주세요.");
										continue;
									}

									if (gameCQ == 1) {
										continue;
									} else if (gameCQ != 1) {
										System.out.print("선택 화면으로 돌아갑니다. ");
										break;
									}
								}

							} else if (select == 2) {

								while (true) {
									// 상점에서 방어막 아이템 구매
									// 1. 아이디 + 현재 소지금 출력
									System.out.println("[아이디: " + id + " | 현재 소지금: " + login.getMemoney() + "원 "
											+ "| 생명수 : " + login.getMemguard() + "개]\n ");

									// 2. 구매할지 여부 물어보기
									System.out.println("==========저세상 상점===========\n\n" + "         아이템 : 생명수💧\n"
											+ "         가  격 : 1,000원\n" + "    효 능 : 죽음에서 소생한다\n\n"
											+ "================================\n" + "\n1. 구매한다 | 2. 상점을 나간다");

									System.out.print(">>> ");

									int ItemWant = 0;
									try {
										ItemWant = Integer.parseInt(sc.nextLine());
									} catch (NumberFormatException e) {
										System.out.println("숫자로 입력해주세요.");
										continue;
									}

									// 3-1. 돈이 1,000원 이상이면 1개 구매
									if (ItemWant == 1 && login.getMemoney() >= 1000) {
										System.out.println("생명수를 1개 구매하였습니다.");
										login.setMemoney(login.getMemoney() - 1000);
										login.setMemguard(login.getMemguard() + 1);

										memService.buyItem(id, login.getMemoney(), login.getMemguard());
									} else if (ItemWant == 1 && login.getMemoney() < 1000) {
										System.out.println("돈 없는 손님은 사절입니다💢 돈을 벌어 오세요. \n");
										break;
									} else if (ItemWant == 2) {
										System.out.println("다음에 다시 찾아 주세요.\n");
										break;
									}
								}

							} else if (select == 3) {
								// 랭킹보기
								ArrayList<MemberVO> memList = memService.getMemList();

								System.out.println("=====================유저 랭킹=====================\n");
								for (int i = 0; i < memList.size(); i++) {
									System.out.println((i + 1) + "등 " + memList.get(i));
								}
								System.out.println("\n====================================================\n");

							} else if (select == 4) {
								// 로그아웃
								break;
							} else {
								System.out.println("잘못 입력하셨습니다.");
							}
						}
					} else {
						// 로그인 실패
						System.out.println("아이디와 비밀번호를 다시 확인해주세요");
					}
				} else {
					System.out.println("존재하지 않는 아이디입니다.");
				}
			} else if (command == 3) {
				System.out.println("종료합니다.");
				break;
			} else {
				System.out.println("잘못 입력하셨습니다.");
			}
		}
	}
}