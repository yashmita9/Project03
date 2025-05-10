package in.co.rays.project_3.dto;

import java.util.Date;

public class PositionDTO extends BaseDTO {

	private String designation;
	private Date openDate;
	private int requireExp;
	private int condition;

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public int getRequireExp() {
		return requireExp;
	}

	public void setRequireExp(int requireExp) {
		this.requireExp = requireExp;
	}

	public int getCondition() {
		return condition;
	}

	public void setCondition(int condition) {
		this.condition = condition;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
