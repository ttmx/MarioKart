class Ride {

    private String destination;
    private int[] date;
    private float duration;
    private int seats;
    private Person driver;
    private int spots;

    public Ride(String destination, int[] date, float duration, int spots) {
        this.destination = destination;
        this.date = date;
        this.duration = duration;
        this.spots = spots;
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