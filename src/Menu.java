import java.awt.List;
import java.util.ArrayList;

public class Menu {
	private ArrayList<Item> menu;
	public Menu() {
		this.menu = new ArrayList<>();
	}
	public ArrayList<Item> getMenu() {
		return menu;
	}
	public void addItem(Item i) {
		menu.add(i);
	}
	
	
}
