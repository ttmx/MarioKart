class Ride {

    private String destination;
    private String origin;
    private int[] date;
    private int hour;
    private float duration;
    private int seats;
    private Person driver;
    private int emptySeats;
    private int dateAsNumber;

    public Ride(String origin,String destination, int[] date,int hour, float duration, int emptySeats) {
        this.origin = origin;
        this.destination = destination;
        this.date = date;
        this.hour = hour;
        this.duration = duration;
        this.emptySeats = emptySeats;
        dateAsNumber = date[0]+date[1]*100+date[2]*10000;
    }
    public int getHour() {
    	return hour;
    }
    public String getOrigin() {
    	return origin;
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
    public int getEmptySeats() {
    	return emptySeats;
    }

    public Person getDriver() {
        return driver;
    }
    public int getDateNumber(){
        return dateAsNumber;
    }
}