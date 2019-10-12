import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

public class Main {
    public static void main(String[] args) {

        // Set up key listener
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }

        // Add key listener
        GlobalScreen.addNativeKeyListener(new Logger());
    }
}
