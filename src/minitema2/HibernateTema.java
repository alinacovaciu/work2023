package minitema2;

import java.util.List;
import java.util.Scanner;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.ssn.practica.work.hibernate.Course;

import util.WithSessionAndTransaction;

public class HibernateTema {
	public static void main(String[] args) {
		HibernateTema HibernateTema = new HibernateTema();
		HibernateTema.run();

	}

	private void run() {
		// TODO Auto-generated method stub
		while (true) {
			System.out.println("Meniu");
			System.out.println("0.Iesire");
			System.out.println("1.Adaugare articol");
			System.out.println("2.Adaugare magazin");
			System.out.println("3.Adaugare pret");
			System.out.println("4.Afisare articole");
			System.out.println("5.Afisare magazine");
			System.out.println("6.Afisare preturi");
			System.out.println("7.Afisare statistice(query)");
			System.out.println("Dati optiunea:");
			Scanner scan = new Scanner(System.in);
			String opt = scan.nextLine();
			switch (opt) {
			case "0":
				System.exit(1);
				break;
			case "1":

				System.out.println("Nume articol:");
				String name = scan.nextLine();

				saveArticle(name);

				break;
			case "2":
				System.out.println("Nume magazin:");
				String nume = scan.nextLine();

				saveStore(nume);

				break;
			case "3":
				System.out.println("Dati id articol:");
				int idA = scan.nextInt();
				System.out.println("Dati id magazin:");
				int idS = scan.nextInt();
				System.out.println("Pret:");
				int pr = scan.nextInt();
				savePrice(pr, idA, idS);

				break;
			case "4":
				showArticle();
				break;
			case "5":
				showStore();
				break;

			case "7":
				queryEntities();
				break;
			default:
				System.out.println("Optiune gresita!");
				break;

			}

		}

	}

	private void queryEntities() {
		// TODO Auto-generated method stub

		new WithSessionAndTransaction() {
			@Override
			public void doAction(Session session) {
				List<Course> result = session.createQuery("from Course", Course.class).getResultList();

				for (Course c : result) {
					System.out.println(c);
				}
			}
		}.run();
	}

	private void showStore() {

		new WithSessionAndTransaction() {
			@Override
			public void doAction(Session session) {
				List<Store> result = session.createQuery("from Store", Store.class).getResultList();

				for (Store c : result) {
					System.out.println(c);
				}
			}
		}.run();

	}

	private void showArticle() {
		new WithSessionAndTransaction() {
			@Override
			public void doAction(Session session) {
				List<Article> result = session.createQuery("from Article", Article.class).getResultList();

				for (Article c : result) {
					System.out.println(c);
				}
			}
		}.run();

	}

	private void saveArticle(String name) {

		new WithSessionAndTransaction() {
			@Override
			public void doAction(Session session) {
				Article articol = new Article(name);
				session.persist(articol);

			}
		}.run();
	}

	private void saveStore(String nume) {

		new WithSessionAndTransaction() {
			@Override
			public void doAction(Session session) {
				Store stores = new Store(nume);
				session.persist(stores);

			}
		}.run();
	}

	private void savePrice(int pricee, int idA, int idS) {

		new WithSessionAndTransaction() {
			@Override
			public void doAction(Session session) {
				TypedQuery<Article> query = session.createQuery("from Article where name = :nameParameter",
						Article.class);
				query.setParameter("nameParameter", idA);
				Article article = query.getSingleResult();
				if (article != null) {
					TypedQuery<Store> query2 = session.createQuery("from Store where name = :nameParameter",
							Store.class);
					query.setParameter("nameParameter", idS);
					Store store = query2.getSingleResult();

					if (store != null) {

						Price price = new Price(pricee, article, store);
						session.save(pricee);

					} else
						System.out.println("Store nu a fost gasit");
				}

				else
					System.out.println("Articol nu a fost gasit!");
			}
		}.run();
	}

}
