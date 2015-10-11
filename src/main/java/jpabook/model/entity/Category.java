package jpabook.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Category {
	
	
	//@Id @GeneratedValue(generator="system-uuid")
	//@GenericGenerator(name="system-uuid", strategy = "uuid")
    @Id
    @GenericGenerator(name="category_seq_id", strategy="jpabook.codes.CategoryCodeGenerator")
    @GeneratedValue(generator="category_seq_id")
	@Column(name="CATEGORY_ID")
	private String id;
	private String name;
	
	@ManyToOne
	@JoinColumn(name ="PARENT_ID")
	private Category parent;
	
	@OneToMany(mappedBy="parent")
	private List<Category> child = new ArrayList<Category>();

	@OneToMany(mappedBy="category")
	private List<CategoryItem> cateogryItems = new ArrayList<CategoryItem>();
	
	
	public void addChildCategory(Category child){
		this.child.add(child);
		child.setParent(this);
	}
	
	public void addItem(CategoryItem cateogryItem){
		cateogryItems.add(cateogryItem);
	}
	
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

   
    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public List<Category> getChild() {
        return child;
    }

    public void setChild(List<Category> child) {
        this.child = child;
    }
    
    public String toString() {
    	return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
