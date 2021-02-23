package de.kyleonaut.commandline.commands;

import de.kyleonaut.commandline.StartStopManager;
import net.labymod.api.events.MessageReceiveEvent;

public class DisableCommand implements MessageReceiveEvent {
    @Override
    public boolean onReceive(String s, String s1) {
        try {
            String command = s1.split(" ")[1];
            if (command.equals("#disable")){
                StartStopManager.stopAddon();
                return true;
            }
        }catch (IndexOutOfBoundsException e){

        }
        return false;
    }
}
