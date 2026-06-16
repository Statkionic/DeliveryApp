import java.util.ArrayList;

public class ParcelBox<T extends Parcel> {
    ArrayList<T> box = new ArrayList<>();
    int freeWeight = 10;

    public int getFreeWeight() {
        return freeWeight;
    }

    public void addParcel(T parcel) {
        if (!(box.contains(parcel))) {
            if ((freeWeight - parcel.getWeight()) < 0) {
                System.out.println("Коробка заполнена");
            } else {
                freeWeight = freeWeight - parcel.getWeight();
                box.add(parcel);
                System.out.println("Посылка добавлена в коробку");
            }
        } else {
            System.out.println("Посылка уже существует в коробке");
        }
    }

    public void getAllParcels() {
        if (box.size() > 0) {
            for (T parcel : box) {
                System.out.println(parcel.getDescription());
            }
        }
    }

    public boolean containsParcel(T parcel) {
        for (T parcel1 : box) {
            if (box.contains(parcel)) {
                return true;
            }
        }
        return false;
    }
}