# escape_world
간단한 자바 콘솔 게임입니다.

DB와 연동하여 회원가입 및 로그인을 할 수 있으며 사용자의 아이디, 비밀번호, 점수, 돈, 생명수를 저장하도록 만들었습니다.

게임을 시작하면 특정 상황에서 두 가지 선택지 중에서 하나를 선택하여 사망하거나 생존하여 계속 게임을 이어나가게 됩니다.
상황과 선택지는 각각의 DB에서 출력하게 만들어져 있습니다.

3번 생존에 성공하였을 경우 점수 1점과 소지금 500원이 늘어납니다. 

상점에서는 생명수를 구매할 수 있는데 생명수의 금액은 1,000원이며 사망하였을 때 만약 생명수를 가지고 있다면 자동으로 부활합니다.
생명수를 구매하면 자동으로 사용자의 돈이 줄어들게 되는데 만약 소지금이 부족한 경우 구매하지 못하고 상점에서 쫓겨납니다. 

10%의 확률로 복권에 당첨되면 소지금이 500원 늘어나며 또한, 10%의 확률로 홀짝 게임이 실행됩니다. 
홀짝게임은 랜덤으로 숫자를 부여하여 사용자의 선택에 따라 50%의 확률로 사망하거나 소지금이 500원 늘어나게 되어있습니다. 

각 유저의 등수와 점수는 유저 랭킹에서 확인할 수 있습니다. 
