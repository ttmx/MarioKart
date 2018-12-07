import java.util.Locale;
import java.util.Scanner;

class Main {
    private static final String HELP = "ajuda";
    private static final String END = "termina";
    private static final String REGISTER = "regista";
    private static final String LOGIN = "entrada";
    private static final String LOGOFF = "sai";
    private static final String NEWRIDE = "nova";
    private static final String LISTRIDES = "lista";
    private static final String GETINFO = "consulta";
    private static final String TAKEARIDE = "boleia";
    private static final String REMOVERIDE = "remove";
    private static final String BYEBYE = "Obrigado. Ate a proxima.";

    public static void main(String[] args) {
        Locale.setDefault(new Locale("en", "US"));
        Controller CObj = new Controller();
        Scanner scan = new Scanner(System.in);
        mainMenu(scan, CObj);
    }

    public static void mainMenu(Scanner scan, Controller CObj) {

        String lCmd = "";
        while (!lCmd.equals(END)) {
            System.out.print("> ");
            lCmd = readCommand(scan);
            switch (lCmd) {
            case HELP:
                printMenuHelp();
                scan.nextLine();
                break;
            case REGISTER:
                register(scan, CObj);
                break;
            case LOGIN:
                login(scan, CObj);
                break;
            case END:
                printEnd();
                break;
            default:
                printInvalidCmd();
                scan.nextLine();
                break;
            }

        }
    }

    public static void loggedIn(Person personObj, Scanner scan, Controller CObj) {
        String lCmd = "";
        while (!lCmd.equals(LOGOFF)) {
            System.out.print(personObj.getEmail() + " > ");
            lCmd = readCommand(scan);
            switch (lCmd) {
            case LOGOFF:
                logoff();
                break;
            case NEWRIDE:
                newRide(personObj, scan, CObj);
                break;
            case LISTRIDES:
                listRides(scan, CObj, personObj);
                break;
            case GETINFO:
                getInfo(scan, CObj);
                break;
            case TAKEARIDE:
                takeARide(personObj, scan, CObj);
                break;
            case REMOVERIDE:
            	removeRide(scan, personObj,CObj);
                break;
            case HELP:
                printLoggedInHelp();
                break;
            default:
                printInvalidCmd();
                scan.nextLine();
                break;
            }

        }

    }

    public static String readCommand(Scanner scan) {
        String lRead = "";
        lRead = scan.next().toLowerCase();

        return lRead;
    }

    private static boolean isPwValid(String pw) {
        boolean lIsValid = true;

        for (int i = 0; i < pw.length(); i++) {
            // if (Character.isDigit(pw.charAt(i))) {
            // lHasDigits = true;
            // }
            // if (Character.isAlphabetic(pw.charAt(i))) {
            // lHasLetters = true;
            // }
            if (!Character.isLetterOrDigit(pw.charAt(i))) {
                lIsValid = false;
            }
        }
        return (pw.length() <= 5 && pw.length() >= 3 && lIsValid);
    }

    private static void printInvalidCmd() {
        System.out.println("Comando inexistente.");
    }

    private static void register(Scanner scan, Controller CObj) {
        boolean lHasCreated = false;
        int lFailCount = 0;
        String lEmail = scan.next();
        String lName = "";
        String lPass = "";
        scan.nextLine();
        boolean repeatedEmail = CObj.repeatedEmail(lEmail);
        if (!repeatedEmail) {

            System.out.print("nome (maximo 50 caracteres): ");
            lName = scan.nextLine();

            while (lFailCount < 3 && !lHasCreated) {
                System.out.print("password (entre 3 e 5 caracteres - digitos e letras): ");
                lPass = scan.next();
                scan.nextLine();
                if (isPwValid(lPass)) {
                    CObj.createAccount(lEmail, lName, lPass);
                    System.out.println("Registo efetuado.");
                    lHasCreated = true;
                } else {
                    lFailCount++;
                    System.out.println("Password incorrecta.");
                }
            }
        }
        if (repeatedEmail) {
            System.out.println("Utilizador ja existente.");
        }
        if (!lHasCreated) {
            System.out.println("Registo nao efetuado.");
        }

    }

    public static void printMenuHelp() {
        System.out.println("ajuda - Mostra os comandos existentes");
        System.out.println("termina - Termina a execucao do programa");
        System.out.println("regista - Regista um novo utilizador no programa");
        System.out.println("entrada - Permite a entrada (\"login\") dum utilizador no programa");
    }

    public static void printLoggedInHelp() {
        System.out.println("ajuda - Mostra os comandos existentes");
        System.out.println("sai - Termina a sessao deste utilizador no programa");
        System.out.println("nova - Regista uma nova deslocacao");
        System.out.println("lista - Lista todas ou algumas deslocacoes registadas");
        System.out.println("boleia - Regista uma boleia para uma dada deslocacao");
        System.out.println("consulta - Lista a informacao de uma dada deslocacao");
        System.out.println("remove - Retira uma dada deslocacao");
    }

    private static void login(Scanner scan, Controller CObj) {
        String lEmail = scan.next();
        scan.nextLine();
        String lPass = "";
        Person lPerson = CObj.getPersonFromEmail(lEmail);
        boolean lLoggedIn = false;
        if (lPerson != null) {
            for (int i = 0; lLoggedIn == false && i < 3; i++) {
                System.out.print("password: ");
                lPass = scan.next();
                scan.nextLine();
                if (lPass.equals(lPerson.getPw())) {
                    lLoggedIn = true;
                    loggedIn(lPerson, scan, CObj);
                } else {
                    System.out.println("Password incorrecta.");
                }
            }
        } else {
            System.out.println("Utilizador nao existente.");
        }

    }

    private static void logoff() {
        System.out.println(BYEBYE);
    }

    private static void getInfo(Scanner scan, Controller CObj) {
        String lEmail = scan.next().trim();
        int[] lDate = CObj.dateFromString(scan.next().trim());
        Person lPerson = CObj.getPersonFromEmail(lEmail);
        boolean hasFound = false;
        if (lPerson == null) {
            System.out.println("Deslocacao nao existe.");
        } else {
            RideIterator lRI = lPerson.createRideIterator();
            Ride lRide = null;
            if (lRI.hasNext()) {
                do {
                    lRide = lRI.nextRide();
                    if (lRide.getDate()[0] == lDate[0] && lRide.getDate()[1] == lDate[1]&& lRide.getDate()[2] == lDate[2]) {
                        printRideInfo(lRide, lPerson, false, true);
                        hasFound = true;
                    }
                } while (lRI.hasNext());
            } if(!hasFound){
                System.out.println("Deslocacao nao existe.");
            }
        }
    }

    private static void listRides(Scanner scan, Controller CObj, Person personObj) {
        String lDate = scan.nextLine().trim();

        int[] laDate;
        CObj.sortAccounts();
        if (!lDate.equals("")) {
            laDate = CObj.dateFromString(lDate);
            listRidesWDate(laDate, CObj);
        } else {
            RideIterator lIterator = personObj.createRideIterator();
            lIterator.sort();
            if (lIterator.hasNext()) {
                do {
                    Ride lRide = lIterator.nextRide();
                    printRideInfo(lRide, personObj, false, false);
                    System.out.print("\n");
                } while (lIterator.hasNext());
            }
        }
    }

    private static void listRidesWDate(int[] date, Controller CObj) {
        int lUserCount = CObj.getUserCount();
        Person lPerson = new Person();
        boolean hasFound = false;
        if(!lPerson.isDateValid(date)) {
        	
        	System.out.println("Data invalida.");      	
        }else {
        	for (int i = 0; i < lUserCount; i++) {
                lPerson = CObj.getPersonFromIndex(i);
                RideIterator lIterator = lPerson.createRideIterator();
                lIterator.sort();
                while (lIterator.hasNext()) {

                    Ride lRide = lIterator.nextRide();
                    if (date[0] == lRide.getDate()[0] && date[1] == lRide.getDate()[1] && date[2] == lRide.getDate()[2]) {
                        printRideInfo(lRide, lPerson, true, false);
                        System.out.print("\n");
                        hasFound = true;
                    }
                    	
                    

                }
             
            }
        	if(!hasFound) {
            	System.out.println(lPerson.getName() + " nao existem deslocacoes registadas para " + date[0]+"-"+ date[1]+"-"+ date[2]+".");
            }
        }
        	
        	
        	
        
        
    }

    private static void removeRide(Scanner scan, Person pObj,Controller CObj) {
        switch(pObj.removeRide(CObj.dateFromString(scan.next().trim()))){
            case 0:
                System.out.println("Deslocacao removida.");
                break;
            case 1:
                System.out.println("Data invalida.");
                break;
            case 2:
                System.out.println("Deslocacao nao existe.");
                break;
            case 3:
                System.out.println(pObj.getName() + " ja nao pode eliminar esta deslocacao.");
                break;

        }
    }

    private static void newRide(Person personObj, Scanner scan, Controller CObj) {
        scan.nextLine();
        String lOrigin = scan.nextLine();

        String lDestination = scan.nextLine();

        String lDate = scan.next();
        int[] laDate = CObj.dateFromString(lDate);

        int lHour = scan.nextInt();

        float lDuration = scan.nextFloat();
        int lSeats = scan.nextInt();

        // 0 if good, 1 if invalid data, 2 if already registered
        switch (personObj.newRide(lOrigin, lDestination, laDate, lHour, lDuration, lSeats)) {
        case 0:
            System.out.println("Deslocacao registada. Obrigado " + personObj.getName() + ".");
            break;
        case 1:
            System.out.println("Dados invalidos.");
            System.out.println("Deslocacao nao registada.");
            break;
        case 2:
            System.out.println(personObj.getName() + " ja tem uma deslocacao registada nesta data");
            System.out.println("Deslocacao nao registada.");
            break;
        default:
            System.out.println("I have absolutely no idea how you got here");
            break;
        }

    }

    private static void takeARide(Person pObj, Scanner scan, Controller CObj) {
        String lEmail = scan.next().trim();
        int[] lDate = CObj.dateFromString(scan.next().trim());
        Person lPerson = CObj.getPersonFromEmail(lEmail);

        if (!pObj.isDateValid(lDate)) {
            System.out.println("Data invalida.");
        } else if (lPerson == null) {
            System.out.println("Utilizador inexistente.");
        } else if (!lPerson.isRideAlreadyRegistered(lDate)) {
            System.out.println("Deslocacao nao existe.");
        } else if (lPerson.getEmail().equals(pObj.getEmail())) {
            System.out.println(pObj.getName() + " nao pode dar boleia a si propria. Boleia nao registada.");
        } else {
            //System.out.println("Empty Seats: " + lPerson.getRideFromDate(lDate).getEmptySeats() + "; date: " + lPerson.getRideFromDate(lDate).getDateNumber()+ "-" + lDate[1] + "-" + lDate[2]);
            if (lPerson.getRideFromDate(lDate).getEmptySeats() == 0) {
                System.out.println(pObj.getName() + " nao existe lugar. Boleia nao registada.");
            } else {
                lPerson.getRideFromDate(lDate).incPerson();
                System.out.println("Boleia registada.");
            }
        }
    }

    private static void printEnd() {
        System.out.println(BYEBYE);
    }

    private static RideIterator checkPerson(Person personObj) {
        if (personObj != null) {
            return personObj.createRideIterator();
        } else {
            return null;
        }
    }

    private static void printRideInfo(Ride ride, Person person, boolean needDriver, boolean freeSpots) {
        if (needDriver) {
            System.out.println(person.getEmail());
        }
        System.out.println(ride.getOrigin());
        System.out.println(ride.getDestination());
        System.out.print(ride.getDate()[0] + "-" + ride.getDate()[1] + "-" + ride.getDate()[2]);
        System.out.print(" " + ride.getHour());
        System.out.print(" " + ride.getDuration());
        System.out.println(" " + ride.getSeats());
        if (!freeSpots) {
            System.out.println("Boleias registadas: " + (ride.getSeats() - ride.getEmptySeats()));
        } else {
            System.out.println("Lugares vagos: " + ride.getEmptySeats());
        }
    }

}