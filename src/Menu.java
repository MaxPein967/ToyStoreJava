import java.util.Scanner;

public class Menu {
    public static void headMenu(Scanner iScanner) throws ToysIsNotCreateException {
        int run = -1;
        String menu = """
                ______________________________________
                Добро пожаловать в наш магазин игрушек
                ______________________________________
                Комманды:
                1 - Добавить новую игрушку
                2 - Изменить шанс выпадения игрушки
                3 - Разыграть приз
                4 - Забрать приз
                5 - Доставка игрушек со склада
                6 - Вывести список доступных игрушек
                7 - Выход
                """;
        System.out.print(menu);
        run = ChangesAndAdditionsOfToys.inputData("", iScanner);
        switch (run) {
            case 1:
                ChangesAndAdditionsOfToys.createToy(iScanner);
                headMenu(iScanner);
                break;
            case 2:
                ChangesAndAdditionsOfToys.changeWeight(iScanner);
                headMenu(iScanner);
                break;
            case 3:
                GameService.game();
                headMenu(iScanner);
                break;
            case 4:
                ChangesAndAdditionsOfToys.getPrize();
                headMenu(iScanner);
                break;
            case 5:
                ChangesAndAdditionsOfToys.deliveryToys(iScanner);
                headMenu(iScanner);
                break;
            case 6:
                ChangesAndAdditionsOfToys.printToys();
                headMenu(iScanner);
                break;
            case 7:
                System.out.println();
                System.out.println("До новых встреч!");
                System.out.println();
                System.exit(0);
                break;
            default:
                headMenu(iScanner);
                break;
        }
    }
}
