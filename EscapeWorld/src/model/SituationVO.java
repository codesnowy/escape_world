package model;

public class SituationVO {
	
	private int situ_no;
	private String situ_txt;
	private int live;
	
	public SituationVO() {
	}
	
	public SituationVO(int situ_no, String situ_txt, int live) {
		super();
		this.situ_no = situ_no;
		this.situ_txt = situ_txt;
		this.live = live;
	}

	@Override
	public String toString() {
		return situ_txt;
	}

	public int getSitu_no() {
		return situ_no;
	}

	public void setSitu_no(int situ_no) {
		this.situ_no = situ_no;
	}

	public String getSitu_txt() {
		return situ_txt;
	}

	public void setSitu_txt(String situ_txt) {
		this.situ_txt = situ_txt;
	}

	public int getLive() {
		return live;
	}

	public void setLive(int live) {
		this.live = live;
	}
	
	
}
