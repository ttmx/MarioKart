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
    private static Controller CObj;
    private static Scanner scan;

    public static void main(String[] args) {
        Controller CObj = new Controller();
        Scanner scan = new Scanner(in);
    }

    public static mainMenu(){
        System.out.print("> ");
        String lCmd="";                
        while(lCmd.equals(END)){
            lCmd = readCommand(scan);
            switch(lCmd){
                case HELP:
                    printMenuHelp();
                    break;
                case REGISTER:
                    register();
                    break;
                case LOGIN:
                    login();
                    break;
                case END:
                    break;
                default:
                    printInvalidCmd();
                    break;
            }                            
        }                
    }

    public static loggedIn(String username){
        String lCmd="";
        while(!lCmd.equals(LOGOFF)){
            lCmd = readCommand(scan);
            switch(lCmd){
                case LOGOFF:
                    logoff();
                    break;
                case NEWRIDE:
                    newRide(p1,p2,p3);
                    break;
                case LISTRIDES:
                    listRides();
                    break;
                case GETINFO:
                    getInfo();
                    break;
                case TAKEARIDE:
                    takeARide(p1,p2,p3);
                    break;
                case REMOVERIDE:
                    removeRide(p1,p2,p3);
                    break;
                case HELP:
                    printLoggedInHelp();
                    break;
                default:
                    printInvalidCmd();
                    break;
            }   
        

        }
               

    }

    public static String readCommand(Scanner scan) {
        String lRead = scan.next().toLowerCase;
        return lRead;
    }

    public static void printMenuHelp() {
        System.out.println("ajuda - Mostra os comandos existentes");
        System.out.println("termina - Termina a execucao do programa");
        System.out.println("regista - Regista um novo utilizador no programa");
        System.out.println("entrada - Permite a entrada (”login”) dum utilizador no programa");
    }

    public static void printLoggedInHelp() {
        System.out.println("ajuda - Mostra os comandos existentes");
        System.out.println("sai - Termina a sessao deste utilizador no programa");
        System.out.println("nova - Regista uma nova deslocacao");
        System.out.println("lista - Lista todas ou algumas deslocacoes registadas");
        System.out.println("boleia - Regista uma boleia para uma dada deslocacao");
        System.out.println("consulta - Lista a informacao de uma dada deslocacao");
    }

    private static void printInvalidCmd() {
        System.out.print("Comando invalido");
    }

    private static void register() {
        String lEmail = scan.next();
        String lName = "";
        String lPass = "";
        if (CObj.repeatedEmail(lEmail)) {
            lName = scan.nextLine();
            lPass = scan.next();
                if (lPass.length <= 5 && lPass.length >= 3) {
                    CObj.createAccount(lEmail, lName, lPass);
                }
        }
                
            
        
                                                                                       
        

    }

    private static void login() {

    }
}