package model;

public class DestinationVO {
	
	private int choice;
	private int situ_no;
	private String dest_txt;
	private String dest_result;
	
	public DestinationVO() {
	}
	
	public DestinationVO(int choice, int situ_no, String dest_txt, String dest_result) {
		super();
		this.choice = choice;
		this.situ_no = situ_no;
		this.dest_txt = dest_txt;
		this.dest_result = dest_result;
	}

	@Override
	public String toString() {
		return dest_txt + " " ;
	}

	public int getChoice() {
		return choice;
	}

	public void setChoice(int choice) {
		this.choice = choice;
	}

	public int getSitu_no() {
		return situ_no;
	}

	public void setSitu_no(int situ_no) {
		this.situ_no = situ_no;
	}

	public String getDest_txt() {
		return dest_txt;
	}

	public void setDest_txt(String dest_txt) {
		this.dest_txt = dest_txt;
	}

	public String getDest_result() {
		return dest_result;
	}

	public void setDest_result(String dest_result) {
		this.dest_result = dest_result;
	}
	
	

}
