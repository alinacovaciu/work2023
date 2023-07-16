package minitema2;

public class Application {
	private KeyboardUtils kb = new KeyboardUtils();
	private DatabaseOperations db = new DatabaseOperations();

	public static void main(String[] args) {
		Application app = new Application();
		app.run();

	}

	private void run() {
		while (true) {
			showMenu();
			String option = kb.getString("Option: ");
			switch (option) {
			case "1":
				addArticle();
				break;
			case "2":
				addStore();
				break;
			case "3":
				addPrice();
				break;
			}
		}

	}

	private void showMenu() {

	}

	private void addPrice() {
		String storeName = kb.getString("Store name: ");
		String articleName = kb.getString("Article name: ");
		int price = kb.getInt("Price: ");
		try {
			db.addPrice(storeName, articleName, price);
		} catch (RuntimeException e) {

			System.out.println(e.getMessage());
		}

	}

	private void addStore() {
		String name = kb.getString("Name: ");
		db.addStore(name);

	}

	private void addArticle() {
		String name = kb.getString("Name: ");
		db.addArticle(name);

	}

}
