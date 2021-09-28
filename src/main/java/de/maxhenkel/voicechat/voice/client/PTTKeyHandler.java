package de.maxhenkel.voicechat.voice.client;

import com.mojang.blaze3d.platform.InputConstants;
import de.maxhenkel.voicechat.events.InputEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

public class PTTKeyHandler implements InputEvents.KeyboardEvent, InputEvents.MouseEvent {

    private boolean pttKeyDown;

    public PTTKeyHandler() {
        InputEvents.KEYBOARD_KEY.register(this);
        InputEvents.MOUSE_KEY.register(this);
    }

    @Override
    public void onKeyboardEvent(long window, int key, int scancode) {
        InputConstants.Key boundKey = KeyBindingHelper.getBoundKeyOf(KeyEvents.KEY_PTT);
        if (boundKey.getValue() == -1 || boundKey.getType().equals(InputConstants.Type.MOUSE)) {
            return;
        }
        pttKeyDown = InputConstants.isKeyDown(window, boundKey.getValue());
    }

    @Override
    public void onMouseEvent(long window, int button, int action, int mods) {
        InputConstants.Key boundKey = KeyBindingHelper.getBoundKeyOf(KeyEvents.KEY_PTT);
        if (boundKey.getValue() == -1 || !boundKey.getType().equals(InputConstants.Type.MOUSE)) {
            return;
        }
        if (boundKey.getValue() != button) {
            return;
        }
        pttKeyDown = action != 0;
    }

    public boolean isPTTDown() {
        return pttKeyDown;
    }

}
