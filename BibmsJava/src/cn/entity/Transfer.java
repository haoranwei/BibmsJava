package cn.entity;

public class Transfer {
	private int id;
	private String transOutAcc;
	private double transAmount;
	private String transInAcc;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTransOutAcc() {
		return transOutAcc;
	}
	public void setTransOutAcc(String transOutAcc) {
		this.transOutAcc = transOutAcc;
	}
	public double getTransAmount() {
		return transAmount;
	}
	public void setTransAmount(double transAmount) {
		this.transAmount = transAmount;
	}
	public String getTransInAcc() {
		return transInAcc;
	}
	public void setTransInAcc(String transInAcc) {
		this.transInAcc = transInAcc;
	}
}
