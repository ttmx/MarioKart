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
        if(personCount != 0) {
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
    	Person lPerson=null;
    	for(int i= 0;i < accounts.length;i++) {
    		if(accounts[i].getEmail().equals(emailToCheck)) {
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
    public int[] dateFromString(String sDate){
        int[] iaDate = new int[3];
        String[] saDate = sDate.split("-");
        for(int i = 0; i <3;i++){
            iaDate[i] = Integer.parseInt(saDate[i].trim());
        }
        return iaDate;
    }
    public boolean dateMakesSense(int[] date) {
        boolean niceDate = true;
        int febDays = 28;
        if ((2000 - date[2]) % 4 == 0)
            febDays += 1;
        int[] daysPerMonth = { 31, 28, 31, 30, febDays, 30, 31, 31, 30, 31, 30, 31 };
        if(date[1]<=12){
        if(date[0]>daysPerMonth[date[1]-1])
            niceDate = false;
        }else{
            niceDate = false;
        }
        return niceDate;
    }
    
}