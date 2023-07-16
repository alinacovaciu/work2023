package minitema2;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import util.WithSessionAndTransaction;

public class DatabaseOperations {
	public void addArticle(String name) {
		new WithSessionAndTransaction() {

			@Override
			public void doAction(Session session) {
				Article a = new Article(name);
				session.save(a);
			}
		}.run();

	}

	public void addStore(String name) {
		new WithSessionAndTransaction() {

			@Override
			public void doAction(Session session) {
				Store a = new Store(name);
				session.save(a);
			}
		}.run();

	}

	public void addPrice(String storeName, String articleName, int price) {
		new WithSessionAndTransaction() {

			@Override
			public void doAction(Session session) {
				Store store = getStoreByName(storeName, session);

				if (store == null) {
					throw new RuntimeException("Magazin inexistent: " + articleName);

				}
				Article article = getArticleByName(articleName, session);
				if (article == null) {
					throw new RuntimeException("Articol inexistent: " + articleName);

				}

				Price p = new Price(price, article, store);

				session.save(p);
			}
		}.run();
	}

	private Store getStoreByName(String name, Session session) {
		Query<Store> query = session.createQuery("from Store where name= :name", Store.class);
		query.setParameter("name", name);
		Store store = query.uniqueResult();
		return store;
	}

	private Article getArticleByName(String articleName, Session session) {
		Query<Article> query = session.createQuery("from Article where name= :name", Article.class);
		query.setParameter("name", articleName);
		Article article = query.uniqueResult();
		return article;
	}

	private List<Article> getArticles(Session session) {
		Query<Article> query = session.createQuery("from Article", Article.class);

		return query.list();
	}

	public List<Article> getAllArticles() {

		return new WithSessionAndTransaction<List<Article>>() {

			@Override
			public void doAction(Session session) {

				setReturnValue(getArticles(session));

			}
		}.run();

	}

	private List<Store> getStore(Session session) {

		Query<Store> query = session.createQuery("from Store", Store.class);

		return query.list();

	}

	public List<Store> getAllStores() {

		return new WithSessionAndTransaction<List<Store>>() {

			@Override
			public void doAction(Session session) {

				setReturnValue(getStore(session));

			}
		}.run();

	}

	private List<Price> getAllPrice(Session session) {

		Query<Price> query = session.createQuery("from Price", Price.class);

		return query.list();
	}

	public List<Price> getAllPrices() {

		return new WithSessionAndTransaction<List<Price>>() {

			@Override
			public void doAction(Session session) {

				setReturnValue(getAllPrice(session));

			}
		}.run();

	}
}
