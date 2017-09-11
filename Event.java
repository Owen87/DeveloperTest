/**
 * Created by oweno_000 on 06-Sep-17.
 */
public class Event {

    // Event object Fields
    private int eventID; // This is so each event has a unique numerical identifier
    private String eventName;
    private float ticketPrice; // I used float for currency because the values won't be big enough to require using the data type "double"
    private int xLocation;
    private int yLocation;
    private int distance;
    
	// Getters and Setters / Accessors & Mutators
    public int getEventID() {
        return eventID;
    }
    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public String getEventName() {
        return eventName;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public float getTicketPrice() {
        return ticketPrice;
    }
    public void setTicketPrice(float ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
    
    public int getXLocation() {
        return xLocation;
    }
    public void setXLocation(int xLocation) {
        this.xLocation = xLocation;
    }
    
    public int getYLocation() {
        return yLocation;
    }
    public void setYLocation(int yLocation) {
        this.yLocation = yLocation;
    }
    
    public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
    
    // Constructors for the Event objects
    public Event(int eventID, String eventName, float ticketPrice) {
        this.eventID = eventID;
        this.eventName = eventName;
        this.ticketPrice = ticketPrice;
        this.toString();
    }

    public Event(){
        this.eventID = 0;
        this.eventName = "Non-event";
        this.ticketPrice = 0.00f;
        this.toString();
    }

    // I need an overridden toString method to return all the information
    @Override
    public String toString() {
        return  "Event ID Number: " + eventID + "\n" +
                "Event Name: " + eventName +  "\n" +
                "Standard ticket price: " + ticketPrice + "\n" +
        		"Distance from user location: " + distance + "\n";
    }
}