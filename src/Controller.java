//Referred to as CObj

class Controller {
    private Person[] accounts;
    private int personCount;

    public Controller() {
        accounts = new Person[50];
        personCount = 0;
    }

    public Boolean repeatedEmail(String emailToCheck) {
        boolean isRepeated = false;
        if(personCount != 0) {
        	 for (int i = 0; i < personCount; i++) {
                 if (accounts[i].getEmail() == emailToCheck) {
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
    	Person lPerson=null;
    	for(int i= 0;i < accounts.length;i++) {
    		if(accounts[i].getEmail()==emailToCheck) {
    			lPerson = accounts[i];
    		}
    	}
    	return lPerson;
    }

    public Person[] increaseAccounts() {
        Person[] bigAccounts = new Person[accounts.length + 1];
        for (int i = 0; i < accounts.length; i++) {
            bigAccounts[i] = accounts[i];
        }
        return bigAccounts;
    }

    private int emailToIndex(String email) {
        int res = -1;
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].getEmail() == email) {
                res = i;
            }
        }
        return res;
    }
}