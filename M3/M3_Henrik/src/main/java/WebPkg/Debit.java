package WebPkg;

public class Debit {
	String name, cpf, email, address, description, justiceId, receiptId;
	boolean status;
	double value;
	
	public Debit(String name, String cpf, String email, String address, String description, boolean status, double value) {
		this.name = name;
		this.cpf = cpf;
		this.email = email;
		this.address = address;
		this.description = description;
		this.status = status;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getJusticeId() {
		return justiceId;
	}
	public void setJusticeId(String justiceId) {
		this.justiceId = justiceId;
	}
	public String getReceiptId() {
		return receiptId;
	}
	public void setReceiptId(String receiptId) {
		this.receiptId = receiptId;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	
}
