import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<MenuItem> menuItem = new ArrayList<>();
    private String category;

    public Menu(String category) {
        this.category = category;
        switch(category) {
            case "Burgers" -> {
                menuItem.add(new MenuItem("ShackBurger", 6900, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
                menuItem.add(new MenuItem("SmokeShack", 8900, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
                menuItem.add(new MenuItem("Cheeseburger", 6900, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
                menuItem.add(new MenuItem("Hamburger", 5400, "비프패티를 기반으로 야채가 들어간 기본버거"));
            }
            case "Drinks" -> {
                menuItem.add(new MenuItem("Coke", 2000, "콜라 입니다."));
                menuItem.add(new MenuItem("Cider", 2000, "사이다 입니다."));
            }
            case "Desserts" -> {
                menuItem.add(new MenuItem("FrenchFries", 2500, "감자튀김 입니다."));
                menuItem.add(new MenuItem("IceCream", 1500, "아이스 크림 입니다."));
            }
        }
    }

    public List<MenuItem> getMenuList() {
        return menuItem;
    }

    public String getCategory() {
        return category;
    }
}