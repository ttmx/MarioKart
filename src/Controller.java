//Referred to as CObj

class Controller {
    Person[] accounts;

    public Controller() {
        accounts = new Person[0];
    }

    public Boolean repeatedEmail(String emailToCheck) {
        boolean isRepeated = false;
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].getEmail == emailToCheck) {
                isRepeated = true;
            }
        }
        return isRepeated;
    }

    public Boolean createAccount(String email, String name, String password) {
        accounts = increaseAccounts();
        accounts[accounts.length] = new Person(email, name, password);
        return true;
    }

    public Person[] increaseAccounts() {
        Person[] bigAccounts = new Person[accounts.length + 1];
        for (int i = 0; i < accounts.length; i++) {
            bigAccounts[i] = accounts[i];
        }
        return bigAccounts;
    }

    private int nameToIndex(String name) {

    }
}