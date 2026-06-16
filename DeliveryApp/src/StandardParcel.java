public class StandardParcel extends Parcel {

    public StandardParcel(String deliveryAddress, String description, int sendDay, int weight) {
        super(deliveryAddress, description, sendDay, weight);
    }

    @Override
    public int calculateDeliveryCost() {
        return getWeight() * 2;
    }
}
