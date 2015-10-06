package jpabook.model.entity;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by holyeye on 2014. 3. 11..
 */

@Entity
public class Member {

	//@Id @GeneratedValue(generator="system-uuid")
	//@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Id
	@GenericGenerator(name="seq_id", strategy="jpabook.codes.StockCodeGenerator")
	@GeneratedValue(generator="seq_id")
    @Column(name = "MEMBER_ID")
    private String id;

    private String name;

    private String city;
    private String street;
    private String zipcode;

    @OneToMany(mappedBy="member")
    private List<Order> orders = new ArrayList<Order>();
    //Getter, Setter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
