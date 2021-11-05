package Logger;

public class Logger {
    private static boolean isEnabled = true;

    public Logger() {
    }

    public static void enableLogger(){
        isEnabled = true;
    } 
    
    public static void disableLogger(){
        isEnabled = false;
    }
    
    public static void log(Object toPrint){
        if (isEnabled) {
            System.out.println(toPrint);
        }
    }

    public static void logNewLine(){
        if (isEnabled) {
            System.out.println();
        }
    }
    
    public static void logInLine(Object toPrint){
        if (isEnabled) {
            System.out.print(toPrint);
        }
    }
}
