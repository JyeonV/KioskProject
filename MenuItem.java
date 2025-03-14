public class MenuItem {
    private String name;
    private int price;
    private String menuDescription;

    public MenuItem(String name, int price, String menuDescription) {
        this.name = name;
        this.price = price;
        this.menuDescription = menuDescription;
    }

    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }
    public String getMenuDescription() {
        return menuDescription;
    }
}