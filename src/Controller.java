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
    	if(accounts.length -2 <= personCount) {
    		accounts = increaseAccounts();
    	}
        
        accounts[personCount] = new Person(email, name, password);
        personCount++;
        return true;
    }

    public Person getPersonFromEmail(String emailToCheck) {
        Person lPerson = null;
        for (int i = 0; i < personCount; i++) {
            if (accounts[i].getEmail().equals(emailToCheck)) {
                lPerson = accounts[i];
            }
        }
        return lPerson;
    }

    private Person[] increaseAccounts() {
        Person[] bigAccounts = new Person[accounts.length + 20];
        for (int i = 0; i < personCount; i++) {
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

    /*public Ride[] getRides(int[] date) {
    	
        Ride[] lRides = new Ride[0];
        for (int i = 0; i < personCount; i++) {
            for (int a = 0; i < accounts[i].createRideIterator(lRides).nextR; i++) {
                if (date.equals(accounts[i].getRides()[a])) {
                    lRides = increaseRides(lRides);
                    lRides[lRides.length - 1] = accounts[i].getRides()[a];
                }
            }
        }
        return lRides;
    }*/
    public void sortAccounts() {
    	char lCompareOne;
    	char lCompareTwo;
    	boolean hasSorted = false;
    	for (int i = 1; i < personCount; i++) {
    		for(int a = 0; a <= 10 && !hasSorted;a++) {
    			lCompareOne = new Character(accounts[i-1].getEmail().charAt(a));
        		lCompareTwo = new Character(accounts[i].getEmail().charAt(a));
        		if(Character.compare(lCompareOne, lCompareTwo) > 0) {
        			Person temp = accounts[i-1];
        			accounts[i-1] = accounts[i]; 
        			accounts[i] = temp;
        		} else if(Character.compare(lCompareOne, lCompareTwo) < 0) {
        			hasSorted = true;
        		}
    		}
    		
    		
    		
    	}
    	    }

    private Ride[] increaseRides(Ride[] rideyboy) {
        Ride[] bigRides = new Ride[rideyboy.length + 20];
        for (int i = 0; i < personCount; i++) {
            bigRides[i] = rideyboy[i];
        }
        return bigRides;
    }
    // I think this one is really similar to insertion sort
    private Ride[] sortRides(Ride[] rideyboy){ 
        int len = personCount; 
        for (int i=1; i<len; ++i) { 
            Ride key = rideyboy[i]; 
            int j = i-1;
            while (j>=0 && rideyboy[j].getDateNumber() > key.getDateNumber()){ 
                rideyboy[j+1] = rideyboy[j]; 
                j = j-1; 
            } 
            rideyboy[j+1] = key; 
        }
        return rideyboy;    
    }
}