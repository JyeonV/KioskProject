import java.util.*;


public class Kiosk {
    private List<Menu> menuList = new ArrayList<>();
    private List<MenuItem> cart = new ArrayList<>();
    private Map<String, Integer> cartMap = new HashMap<>();

    public Kiosk() {
        addMenuList();
    }

    Scanner in = new Scanner(System.in);
    public void start() {
        while(true) {
            System.out.println("[ MAIN MENU ]");

            for(int i = 0; i < menuList.size(); i++)
                System.out.printf("%d. %s\n",i + 1, menuList.get(i).getCategory());    // 메인 메뉴 출력
            System.out.println("0. 종료      | 종료");

            if(!cart.isEmpty()) {
                System.out.println("[ ORDER MENU ]");
                System.out.println("4. Orders\n5. Cancel");
            }

            int categoryNum = returnInput();

            if(categoryNum == 0)
                break;

            if(!cart.isEmpty() && (categoryNum == 4 || categoryNum == 5)) {
                if(categoryNum == 4) {
                    System.out.println("아래와 같이 주문하시겠습니까?");
                    int total = showShoppingCart();
                    discountApply(total);
                    continue;
                }
                if(categoryNum == 5) {
                    System.out.println("장바구니 비우기");
                    clearShoppingCart();
                    continue;
                }
            }

            if(categoryNum < 0 || categoryNum > menuList.size()) {
                System.out.println("잘못된 입력입니다. ");
                continue;
            }
            Menu selectMenu = menuList.get(categoryNum - 1);

            showMenuList(selectMenu);

            int menuNum = returnInput();
            if(menuNum == 0) {
                System.out.println("돌아가기. ");
                continue;
            }
            if(menuNum < 1 || menuNum > selectMenu.getMenuList().size()) {
                System.out.println("잘못된 입력입니다. ");
                continue;
            }

            MenuItem menuItemSelect = selectMenu.getMenuList().get(menuNum - 1);
            System.out.printf("선택한 메뉴: %s | %d | %s \n", menuItemSelect.getName(), menuItemSelect.getPrice(), menuItemSelect.getMenuDescription());

            addShoppingCart(new MenuItem(menuItemSelect.getName(), menuItemSelect.getPrice(), menuItemSelect.getMenuDescription()));
            }
        }

    public void addMenuList() {
        menuList.add(new Menu("Burgers"));
        menuList.add(new Menu("Drinks"));
        menuList.add(new Menu("Desserts"));
    }

    public void showMenuList(Menu menu) {
        System.out.printf("[ %s MENU ]\n", menu.getCategory());
        int x = 1;
        for (MenuItem i : menu.getMenuList()) {
            System.out.printf("%d. %-14s | %d | %s \n", x, i.getName(), i.getPrice(), i.getMenuDescription());
            x++;
        }
        System.out.println("0. 종료      | 종료");
    }

    public int returnInput() {
        while (true) {
            System.out.print("번호 입력 : ");
            try {
                int num = in.nextInt();
                in.nextLine();
                return num;
            } catch (RuntimeException e) {
                System.out.println("다시 입력해주세요.");
                in.nextLine();
            }
        }
    }

    public void addShoppingCart(MenuItem cartItem) {
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?\n1. 확인        2. 취소");
        int num = returnInput();
        int i = 0;
        switch(num) {
            case 1 -> {
                cart.add(cartItem);
                if(cartMap.containsKey(cartItem.getName())) {
                    cartMap.put(cartItem.getName(), cartMap.get(cartItem.getName()) + 1);
                } else {
                    cartMap.put(cartItem.getName(), 1);
                }
                System.out.println(cartItem.getName() + " 이 장바구니에 추가되었습니다.");
            }
            case 2 -> {
                System.out.println("취소");
            }
        }
    }
    //    - [ ]  기존에 생성한 Menu의 MenuItem을 조회 할 때 스트림을 사용하여 출력하도록 수정
    //    - [ ]  기존 장바구니에서 특정 메뉴 빼기 기능을 통한 스트림 활용
    //        - [ ]  예시 : 장바구니에 SmokeShack 가 들어 있다면, stream.filter를 활용하여 특정 메뉴 이름을 가진 메뉴 장바구니에서 제거

    public int showShoppingCart() {
        System.out.println("[ Orders ]");
        cart.stream().forEach(p -> System.out.printf("%-14s | %d | %s\n", p.getName(), p.getPrice(), p.getMenuDescription()));
        int total = cart.stream().mapToInt(MenuItem::getPrice).sum();
        // for (MenuItem p : cart) {
        //            System.out.printf("%-14s | %d | %s\n", p.getName(), p.getPrice(), p.getMenuDescription());
        //            total += p.getPrice();
        //        }
        System.out.println("[ TOTAL ]");
        System.out.println(total);
        System.out.println("1. 주문      2. 메뉴판");
        return total;
    }

    public void discountApply(int total) {
        int orderNum = returnInput();
        if(orderNum == 1) {
            System.out.println("할인 정보를 입력해주세요.");
            System.out.println("1. 국가유공자 : 10% \n2. 군인 : 5%\n3. 학생 : 3%\n4. 일반 : 0%");
            int num = returnInput();
            Discount discountType = switch(num) {
                case 1 -> Discount.NATIONALMERIT;
                case 2 -> Discount.MILITARY;
                case 3 -> Discount.STUDENT;
                default -> Discount.NONE;
            };
            total *= discountType.getDiscountRate();
            System.out.println("주문이 완료되었습니다. 금액은 " + total + "원 입니다.");
            clearShoppingCart();
        } else if (orderNum == 2) {
            System.out.println("메뉴판으로 돌아갑니다.");
        }
    }



    public void clearShoppingCart() {
        cartMap.forEach((key, value) -> System.out.printf("%s | %d개 \n", key, value));
        System.out.println("1. 전체 삭제\n2. 1개 삭제");
        int num = returnInput();
        if(num == 1)
            cart.clear();
        if(num == 2) {
            System.out.println("삭제할 메뉴 입력 :");
            String deleteMenu = in.nextLine();
            cart.stream().filter(p -> p.getName().equals(deleteMenu)).findFirst();
        }
    }

}

