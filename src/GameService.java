import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class GameService {
    public static List<Toys> toysList;
    public static List<Toys> prizeList;

    public static void App() {
        toysList = new ArrayList<>();
        prizeList = new ArrayList<>();
        Scanner iScanner = new Scanner(System.in);
        try {
            Menu.headMenu(iScanner);
        } catch (ToysIsNotCreateException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getInfo());
        } finally {
            iScanner.close();
        }
    }
    public static void game() {
        int wt = 0;
        if (ChangesAndAdditionsOfToys.checkToys() == 0) {
            for (int i = 0; i < toysList.size(); i++) {
                wt += toysList.get(i).getWeight();
                toysList.get(i).setBound(wt);
            }

            int rnd = ThreadLocalRandom.current().nextInt(0, wt) + 1;
            for (int i = 0; i < toysList.size(); i++) {
                if(rnd > toysList.get(i).getBound() - toysList.get(i).getWeight() && rnd < toysList.get(i).getBound()) {
                    if(toysList.get(i).getCount() == 0) {
                        game();
                    } else {
                        prizeList.add(toysList.get(i));
                        toysList.get(i).setCount(toysList.get(i).getCount() - 1);
                        System.out.println();
                        System.out.printf("\n* Выпала игрушка: %s *",toysList.get(i));
                        System.out.println();
                    }
                }
            }
        }
    }
}