public class PerishableParcel extends Parcel{

    private int timeToLive;

    public PerishableParcel(String deliveryAddress, String description, int sendDay, int weight, int timeToLive) {
        super(deliveryAddress, description, sendDay, weight);
        this.timeToLive = timeToLive;
    }

    public boolean isExpired(int currentDay) {
        return (getSendDay() + timeToLive) < currentDay;
    }

    @Override
    public int calculateDeliveryCost() {
        return getWeight() * 3;
    }
}
