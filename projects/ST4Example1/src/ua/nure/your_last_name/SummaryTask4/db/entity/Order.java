package ua.nure.your_last_name.SummaryTask4.db.entity;

/**
 * Order entity.
 * 
 * @author D.Kolesnikov
 * 
 */
public class Order extends Entity {

	private static final long serialVersionUID = 5692708766041889396L;

	private Integer bill;

	private Long userId;

	private int statusId;

	public Integer getBill() {
		return bill;
	}

	public void setBill(Integer bill) {
		this.bill = bill;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	@Override
	public String toString() {
		return "Order [bill=" + bill + ", userId=" + userId + ", statusId="
				+ statusId + ", getId()=" + getId() + "]";
	}

}
