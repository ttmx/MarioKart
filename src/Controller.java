//Referred to as CObj

class Controller {
    private Person[] accounts;
    private int personCount;

    public Controller() {
        accounts = new Person[0];
        personCount = 0;
    }

    public Boolean repeatedEmail(String emailToCheck) {
        boolean isRepeated = false;
        if (personCount != 0) {
            for (int i = 0; i < personCount; i++) {
                if (accounts[i].getEmail().equals(emailToCheck)) {
                    isRepeated = true;
                }
            }
        }
        return isRepeated;
    }

    public Boolean createAccount(String email, String name, String password) {
        accounts = increaseAccounts();
        accounts[personCount] = new Person(email, name, password);
        personCount++;
        return true;
    }
    
    public Person getPersonFromEmail(String emailToCheck) {
        Person lPerson = null;
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].getEmail().equals(emailToCheck)) {
                lPerson = accounts[i];
            }
        }
        return lPerson;
    }

    private Person[] increaseAccounts() {
        Person[] bigAccounts = new Person[accounts.length + 1];
        for (int i = 0; i < accounts.length; i++) {
            bigAccounts[i] = accounts[i];
        }
        return bigAccounts;
    }

    public int[] dateFromString(String sDate) {
        int[] iaDate = new int[3];
        String[] saDate = sDate.split("-");
        for (int i = 0; i < 3; i++) {
            iaDate[i] = Integer.parseInt(saDate[i].trim());
        }
        return iaDate;
    }
    public int getUserCount() {
    	return personCount;
    }
    public Person getPersonFromIndex(int index) {
    	return accounts[index];
    }

   /* public Ride[] getRides(int[] date) {
        Ride[] lRides = new Ride[0];
        for (int i = 0; i < accounts.length; i++) {
            for (int a = 0; i < accounts[i].getRides().length; i++) {
                if (date.equals(accounts[i].getRides()[a])) {
                    lRides = increaseRides(lRides);
                    lRides[lRides.length - 1] = accounts[i].getRides()[a];
                }
            }
        }
        return lRides;
    }

    private Ride[] increaseRides(Ride[] rideyboy) {
        Ride[] bigRides = new Ride[rideyboy.length + 1];
        for (int i = 0; i < rideyboy.length; i++) {
            bigRides[i] = rideyboy[i];
        }
        return bigRides;
    }*/
}