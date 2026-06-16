public class FragileParcel extends Parcel implements Trackable{


    public FragileParcel(String deliveryAddress, String description, int sendDay, int weight) {
        super(deliveryAddress, description, sendDay, weight);
    }

    @Override
    public void packageItem() {
        System.out.println("Посылка " + getDescription() + " обёрнута в защитную плёнку");
        System.out.println("Посылка " + getDescription() + " упакована");
    }

    @Override
    public int calculateDeliveryCost() {
        return getWeight() * 4;
    }

    @Override
    public void reportStatus(String newLocation) {
        System.out.println("Хрупкая посылка " + getDescription() + " изменила местоположение на " + newLocation);
    }
}
