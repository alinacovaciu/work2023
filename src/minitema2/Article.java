package minitema2;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Article {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private int id;
	private String name;

	/*
	 * @ManyToMany(cascade = { CascadeType.ALL })
	 * 
	 * @JoinTable(name = "Article_Store", // joinColumns = { @JoinColumn(name =
	 * "article_id") }, // inverseJoinColumns = { @JoinColumn(name = "store_id") })
	 * 
	 * private List<Store> stores = new ArrayList<>();
	 * 
	 * @OneToMany(mappedBy = "article") private List<Price> prices = new
	 * ArrayList<>();
	 */

	@OneToMany(mappedBy = "article")
	private List<Price> prices = new ArrayList<>();

	public Article() {

	}

	public Article(String name) {
		super();
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*
	 * public List<Store> getStores() { return stores; }
	 * 
	 * public void setStores(List<Store> stores) { this.stores = stores; }
	 */
	public List<Price> getPrices() {
		return prices;
	}

	public void setPrices(List<Price> prices) {
		this.prices = prices;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", name=" + name + "]";
	}

}
