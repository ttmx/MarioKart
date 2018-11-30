class Ride {

    private String destination;
    private String origin;
    private int[] date;
    private float duration;
    private int seats;
    private Person driver;
    private int emptySeats;
    

    public Ride(String origin,String destination, int[] date,int hour, float duration, int emptySeats) {
        this.origin = origin;
        this.destination = destination;
        this.date = date;
        this.duration = duration;
        this.emptySeats = emptySeats;
    }

    public String getDestination() {
        return destination;
    }

    public int[] getDate() {
        return date;
    }

    public float getDuration() {
        return duration;
    }

    public int getSeats() {
        return seats;
    }

    public Person getDriver() {
        return driver;
    }
}