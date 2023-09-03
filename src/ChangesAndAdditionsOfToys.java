import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class ChangesAndAdditionsOfToys {
    public static void createToy(Scanner iScanner) {
        System.out.printf("Введите имя игрушки: ");
        iScanner.nextLine();
        String name = iScanner.nextLine();
        int count = inputData("Введите количество игрушек: ", iScanner);
        int weight = inputData("Введите шанс выпадения игрушки при розыгрыше (от 1 до 100): ", iScanner);
        try {
            GameService.toysList.add(new Toys(name, count, weight));
            System.out.println("\n Список игрушек: ");
            System.out.println(GameService.toysList.get(0));
        } catch (ToysIsNotCreateException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getInfo());
        }
    }

    public static void changeWeight(Scanner iScanner) throws ToysIsNotCreateException{
        int id = inputData("Введите ID игрушки: ", iScanner);
        int weight = inputData("Введите шанс выпадения игрушки при розыгрыше (от 1 до 100): ", iScanner);
        if (GameService.toysList.get(id).setWeight(weight) == 4) {
            throw new ToysIsNotCreateException(4, "Ошибка изменения экземпляра класса!");
        }
        System.out.println("\n Список игрушек: ");
        System.out.println(GameService.toysList.get(id));
    }

    public static int inputData(String text, Scanner iScanner) {
        System.out.printf(text);
        int num = 0;
        if (iScanner.hasNextInt()) {
            num = iScanner.nextInt();
        } else {
            num = -1;
        }
        return num;
    }

    public static void deliveryToys(Scanner iScanner) throws ToysIsNotCreateException  {
        int n = -1;
        while (n == -1){
            n = inputData("Сколько игрушек добавить: ", iScanner);
            System.out.println("\n Список игрушек: ");
        }
        for (int i = 0; i < n; i++) {
            String name = String.format("%s", getName());
            int count = ThreadLocalRandom.current().nextInt(1, 10);
            int weight = ThreadLocalRandom.current().nextInt(1, 50);
            GameService.toysList.add(new Toys(name, count, weight));
            System.out.println(GameService.toysList.get(GameService.toysList.size() - 1));
        }
    }
    private static String getName() {
        return Names.values()[ new Random().nextInt( Names.values().length ) ].toString();
    }
    public static int checkToys(){
        if (GameService.toysList.size() == 0) {
            System.out.println("\n В магазине нет игрушек!");
            return -1;
        }
        return 0;
    }
    public static void printToys() {
        System.out.println("\n Остались игрушки: ");
        for (int i = 0; i < GameService.toysList.size(); i++) {
            System.out.println(GameService.toysList.get(i));
        }
        checkToys();
    }
    public static void getPrize() {
        if (GameService.prizeList.size() == 0) {
            System.out.println("\n Нет выйгрышей ");
        } else {
            Date date = new Date();
            try (FileWriter fw = new FileWriter("listOfToys.txt", StandardCharsets.UTF_8, true)) {
                fw.append(String.format("%s %s\n", GameService.prizeList.get(0), date));
                fw.flush();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println();
            System.out.printf("\n Выдан приз %s ", GameService.prizeList.get(0));
            System.out.println();
            GameService.prizeList.remove(0);
        }
    }
}
