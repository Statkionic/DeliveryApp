abstract class Parcel {

    private String description;
    private int weight;
    private String deliveryAddress;
    private int sendDay;
    private int standardPrice;

    public int getSendDay() {
        return sendDay;
    }


    public int getWeight() {
        return weight;
    }


    public Parcel(String deliveryAddress, String description, int sendDay, int weight) {
        this.deliveryAddress = deliveryAddress;
        this.description = description;
        this.sendDay = sendDay;
        this.weight = weight;
    }

    public void packageItem() {
        System.out.println("Посылка " + getDescription() + " упакована");
    }

    public void deliver() {
        System.out.println("Посылка " + getDescription() + " доставлена по адресу " + deliveryAddress);
    }

    public abstract int calculateDeliveryCost();

    public String getDescription() {
        return description;
    }
}
