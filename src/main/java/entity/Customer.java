package entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@PrimaryKeyJoinColumn
	private int custId;
	private String name;
	
	@OneToOne 
	private CustomerDetail customerdetail;
	
	@ManyToMany(mappedBy="customer")
	private List<Product> product ;
	
	public List<Product> getProduct() {
		return product;
	}
	public void setProduct(List<Product> product) {
		this.product = product;
	}
	public CustomerDetail getCustomerdetail() {
		return customerdetail;
	}
	public void setCustomerdetail(CustomerDetail customerdetail) {
		this.customerdetail = customerdetail;
	}
	
	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", name=" + name + ", customerdetail=" + customerdetail + ", product="
				+ product + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
