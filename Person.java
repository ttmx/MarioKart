//Refered to as PObj

class Person {
    String email;
    String name;
    String pw;
    Ride[] rides;

    public Person(String email, String name, String pw) {
        this.email = email;
        this.name = name;
        this.pw = pw;
        rides = new Ride[0];
    }
    public String getName(){
        return name;
    }
    public String getEmail() {
        return email;
    }

    public String getPw() {
        return pw;
    }

    public Ride[] increaseRides() {
        Ride[] bigRides = new Ride[rides.length + 1];
        for (int i = 0; i < rides.length; i++) {
            bigRides[i] = rides[i];
        }
        return bigRides;
    }
    //return 0 if good,1 if invalid date,2 if already registered.... 
    public int newRide(String origin,String destination, int[] date,int hour, float duration, int seats) {
        int lErrorCode = 0;
        if(!isDateValid(date) || hour > 24 || hour < 0 || duration < 0 || seats < 0){
            lErrorCode = 1;
        }else if(isRideAlreadyRegistered(date)){
            lErrorCode = 2;
        }else{
            rides = increaseRides();
            rides[rides.length]= new Ride(origin,destination,date,hour,duration,seats);
        }
        return lErrorCode;
    }
    private boolean isDateValid(int[] date){
        return (date[0]<= 31 && date[0] >= 1 && date[1] <= 12 && date[1] >= 1);
    }
    private boolean isRideAlreadyRegistered(int[] date){
        boolean lCheck = false;
        for (int i = 0;i < rides.length; i++){
            if(rides[i].getDate()[0] == date[0] || rides[i].getDate()[1] == date[1] || rides[i].getDate()[2] == date[2]){
                lCheck = true;
            }
        }
        return lCheck;
    }
}