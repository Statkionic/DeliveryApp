import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeliveryAppTest {

    StandardParcel standardParcel;
    FragileParcel fragileParcel;
    PerishableParcel perishableParcel;
    String deliveryAddress = "deliveryAddress";
    String description = "description";
    int sendDay;
    int weight;
    int timeToLive;
    ParcelBox<Parcel> box;

    @BeforeEach
    void beforeEach() {
        sendDay = 15;
        timeToLive = 1;
        weight = 1;
        box = new ParcelBox<>();
    }

    @Test
    public void standardParcelCost() {
        standardParcel = new StandardParcel(deliveryAddress, description, sendDay, weight);
        assertEquals(2, standardParcel.calculateDeliveryCost());
    }

    @Test
    public void fragileParcelCost() {
        fragileParcel = new FragileParcel(deliveryAddress, description, sendDay, weight);
        assertEquals(4, fragileParcel.calculateDeliveryCost());
    }

    @Test
    public void perishableParcelCost() {
        perishableParcel = new PerishableParcel(deliveryAddress, description, sendDay, weight, timeToLive);
        assertEquals(3, perishableParcel.calculateDeliveryCost());
    }

    @Test
    public void ParcelShouldExpired() {
        perishableParcel = new PerishableParcel(deliveryAddress, description, sendDay, weight, timeToLive);
        assertTrue(perishableParcel.isExpired(17));
    }

    @Test
    public void ParcelShouldNotExpired() {
        perishableParcel = new PerishableParcel(deliveryAddress, description, sendDay, weight, timeToLive);
        assertFalse(perishableParcel.isExpired(16));
    }

    @Test
    public void ShouldBeBoxLimit() {
        weight = 11;
        standardParcel = new StandardParcel(deliveryAddress, description, sendDay, weight);
        box.addParcel(standardParcel);
        assertFalse(box.containsParcel(standardParcel));
    }

    @Test
    public void ShouldBeNotBoxLimit() {
        weight = 10;
        standardParcel = new StandardParcel(deliveryAddress, description, sendDay, weight);
        box.addParcel(standardParcel);
        assertTrue(box.containsParcel(standardParcel));
    }

}
