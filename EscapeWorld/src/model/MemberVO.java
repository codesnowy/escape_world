package model;

public class MemberVO {

	private String memId;
	private String memPw;
	private int memscore;
	private int memoney;
	private int memguard;
	
	
	public MemberVO() {
	}


	public MemberVO(String memId, String memPw, int memscore, int memoney, int memguard) {
		super();
		this.memId = memId;
		this.memPw = memPw;
		this.memscore = memscore;
		this.memoney = memoney;
		this.memguard = memguard;
	}


	@Override
	public String toString() {
		return "[ 아이디: " + memId + ", 점수: " + memscore + ", 소지금: " + memoney
				+ ", 생명수: " + memguard + "개 ]"; 	}


	public String getMemId() {
		return memId;
	}


	public void setMemId(String memId) {
		this.memId = memId;
	}


	public String getMemPw() {
		return memPw;
	}


	public void setMemPw(String memPw) {
		this.memPw = memPw;
	}


	public int getMemscore() {
		return memscore;
	}


	public void setMemscore(int memscore) {
		this.memscore = memscore;
	}


	public int getMemoney() {
		return memoney;
	}


	public void setMemoney(int memoney) {
		this.memoney = memoney;
	}

	public int getMemguard() {
		return memguard;
	}


	public void setMemguard(int memguard) {
		this.memguard = memguard;
	}

	
	
	
}
