import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>();
    private static List<Parcel> trackableParcels = new ArrayList<>();
    private static ParcelBox<StandardParcel> standardParcelParcelBox = new ParcelBox<>();
    private static ParcelBox<FragileParcel> fragileParcelParcelBox = new ParcelBox<>();
    private static ParcelBox<PerishableParcel> perishableParcelParcelBox = new ParcelBox<>();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    trackParcel();
                    break;
                case 5:
                    getBoxes();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Отчет о посылке(доступны только хрупкие)");
        System.out.println("0 — Завершить");
    }


    private static void addParcel() {
        System.out.println("Выберите тип посылки: 1 - стандартную, 2 - хрупкую или 3 - скоропортящуюся.");
        int choice = Integer.parseInt(scanner.nextLine());
        int timeToLive = 0;

        switch (choice) {
            case 1, 2:
                break;
            case 3:
                System.out.println("Добавьте срок годности");
                timeToLive = Integer.parseInt(scanner.nextLine());
                break;
            default:
                System.out.println("Неверный выбор.");
        }
        System.out.println("Добавьте адрес назначения");
        String deliveryAddress = scanner.nextLine();
        System.out.println("Добавьте вес");
        int weight = Integer.parseInt(scanner.nextLine());
        System.out.println("Добавьте описание");
        String description = scanner.nextLine();
        System.out.println("Добавьте число отправки");
        int sendDay = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                StandardParcel standardParcel = new StandardParcel(deliveryAddress, description, sendDay, weight);
                standardParcelParcelBox.addParcel(standardParcel);
                if (!(standardParcelParcelBox.containsParcel(standardParcel))) {
                    allParcels.add(standardParcel);
                    System.out.println("Посылка " + description + " добавлена.");
                }
                break;
            case 2:
                FragileParcel fragileParcel = new FragileParcel(deliveryAddress, description, sendDay, weight);
                fragileParcelParcelBox.addParcel(fragileParcel);
                if (!(fragileParcelParcelBox.containsParcel(fragileParcel))) {
                    trackableParcels.add(fragileParcel);
                    allParcels.add(fragileParcel);
                    System.out.println("Посылка " + description + " добавлена.");
                }
                break;
            case 3:
                PerishableParcel perishableParcel = new PerishableParcel(deliveryAddress, description, sendDay, weight,
                        timeToLive);
                perishableParcelParcelBox.addParcel(perishableParcel);
                if (!(perishableParcelParcelBox.containsParcel(perishableParcel))) {
                    allParcels.add(perishableParcel);
                    System.out.println("Посылка " + description + " добавлена.");
                }
                break;
        }
    }

    private static void sendParcels() {
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }
    }

    private static void calculateCosts() {
        int deliveryCost = 0;
        for (Parcel parcel : allParcels) {
            deliveryCost += parcel.calculateDeliveryCost();
        }
        System.out.println("Стоимость доставки равна " + deliveryCost);
    }

    private static void trackParcel() {
        System.out.println("Введите название посылки");
        String description = scanner.nextLine();
        boolean foundParcel = false;
        Trackable necessaryParcel = null;
        for (Parcel parcel : trackableParcels) {
            if (description.equals(parcel.getDescription())) {
                foundParcel = true;
                necessaryParcel = (Trackable) parcel;
            }
        }
        if (foundParcel) {
            System.out.println("Введите новую локацию");
            String newLocation = scanner.nextLine();
            necessaryParcel.reportStatus(newLocation);
        } else {
            System.out.println("Посылка не найдена");
        }
    }

    private static void getBoxes() {
        perishableParcelParcelBox.getAllParcels();
        standardParcelParcelBox.getAllParcels();
        fragileParcelParcelBox.getAllParcels();
    }
}
