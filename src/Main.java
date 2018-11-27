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
    
    

    public static void main(String[] args) {
        Controller CObj = new Controller();
        Scanner scan = new Scanner(System.in);
        mainMenu(scan,CObj);
    }

    public static void mainMenu(Scanner scan,Controller CObj){
        
        String lCmd="";                
        while(!lCmd.equals(END)){
        	System.out.print("> ");
            lCmd = readCommand(scan);
            switch(lCmd){
                case HELP:
                    printMenuHelp();
                    break;
                case REGISTER:
                    register(scan,CObj);
                    
                    break;
                case LOGIN:
                    login(scan,CObj);
                    
                    break;
                case END:
                	System.out.println("Obrigado. Ate a proxima.");
                    break;
                default:
                    printInvalidCmd();
                    break;
            }
            
            
        }                
    }

    public static void loggedIn(String username,Scanner scan,Controller CObj){
        String lCmd="";
        while(!lCmd.equals(LOGOFF)){
            lCmd = readCommand(scan);
            switch(lCmd){
                case LOGOFF:
                    logoff();
                    break;
                case NEWRIDE:
                    newRide();
                    break;
                case LISTRIDES:
                    listRides();
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
                    
                    break;
            }
            
            
        

        }
               

    }



	public static String readCommand(Scanner scan) {
        String lRead = ""; 
        lRead= scan.next().toLowerCase();
        
        return lRead;
    }
	
	private static boolean isPwValid(String pw){
        return (pw.length() <= 5 && pw.length() >= 3);
    }

    private static void printInvalidCmd() {
        System.out.println("Comando inexistente.");
    }

    private static void register(Scanner scan,Controller CObj) {
        boolean lHasCreated=false;
        int lFailCount = 0;
        String lEmail = scan.next();
        String lName = "";
        String lPass = "";
        scan.nextLine();
        
        if (!CObj.repeatedEmail(lEmail)) {
        	
        	System.out.print("nome (maximo 50 caracteres): ");
            lName = scan.nextLine();
            
            while(lFailCount != 3 && !lHasCreated){
            	System.out.print("password (entre 3 e 5 caracteres - digitos e letras): ");
                lPass = scan.next();
                if (isPwValid(lPass)) {
                    CObj.createAccount(lEmail, lName, lPass);
                    System.out.println("Registo efetuado.");
                    lHasCreated = true;
                }else{
                    lFailCount++;
                    System.out.println("Password incorrecta.");
                }
            }
            if(lFailCount == 3){
                System.out.println("Registo nao efetuado");
                }
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
    }
    

    private static void login(Scanner scan,Controller CObj) {
    	String lEmail = scan.next();
    	String lPass = "";
    	Person lPerson = CObj.getPersonFromEmail(lEmail);
    	if(lPerson != null) {
    		lPass = scan.next();
    		if(lPass == lPerson.getPw()) {
    			loggedIn(lEmail,scan,CObj);
    		}
    		
    	}

    }
    private static void logoff() {
		
		
	}

	private static void getInfo() {
		
		
	}

	private static void listRides() {
		
		
	}

	private static void removeRide() {
	
		
	}

	private static void newRide() {
	
		
	}

	private static void takeARide() {
	
		
	}


}