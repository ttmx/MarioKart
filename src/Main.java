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
            	scan.nextLine();
                listRides(scan, CObj, personObj);
                break;
            case GETINFO:
                getInfo();
                break;
            case TAKEARIDE:
                takeARide();
                break;
            case REMOVERIDE:
                removeRide();
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

    private static void getInfo() {

    }

    private static void listRides(Scanner scan, Controller CObj, Person personObj) {
        String lDate = "";
        int[] laDate;
        if (scan.hasNext()) {
            lDate = scan.next();
            laDate = CObj.dateFromString(lDate);
            listRides(laDate, CObj, personObj);
        } else {
        	System.out.println("NOPE");
        }
    }

    private static void listRides(int[] date, Controller CObj, Person personObj) {
    	int lUserCount = CObj.getUserCount();
    	for(int i = 0; i < lUserCount;i++ ) {
    		System.out.println(CObj.getPersonFromIndex(i).getEmail());
    	}
    }

    private static void removeRide() {

    }

    private static void newRide(Person personObj, Scanner scan, Controller CObj) {
        String lOrigin = scan.nextLine();
        scan.nextLine();
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

    private static void takeARide() {

    }

    private static void printEnd() {
        System.out.println(BYEBYE);
    }

}