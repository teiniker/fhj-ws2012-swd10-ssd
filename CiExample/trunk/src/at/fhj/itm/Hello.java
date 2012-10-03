package at.fhj.itm;

/**
 * Diese Klasse implementiert das Hello World Beispiel in Java.
 * 
 * @author Egon Teiniker
 * @version 1.0
 */
public class Hello
{
    /** String-Konstante die den ersten Teil der Ausgabe bildet. */
    public static final String HELLO = "Hello ";
 
    
    /**
     * Die main Methode instanziert die Klasse und ruft auf dem Objekt die
     * Methode sayHello() auf.
     *  
     * @param args Array der Command Line Parameter.
     */
    public static void main(String[] args)
    {
        System.out.println("Version    = " + Constants.VERSION);
        System.out.println("Build Date = " + Constants.BUILD_DATE);
        
        Hello h = new Hello();
        int number = h.sayHello("Kapfenberg");
        System.out.println("Anzahl der ausgegebenen Zeichen: " + number);
    }

    /**
     * Gibt den HELLO String gefolgt von einem Namen auf der Console aus.
     * 
     * @param name Name der ausgegeben werden soll.
     * 
     * @return Anzahl der ausgegebenen Zeichen.
     */
    public int sayHello(String name)
    {
        String msg = HELLO + name + "!";
        System.out.println(msg);
        return msg.length();
    }
}
