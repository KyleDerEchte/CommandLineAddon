package de.kyleonaut.commandline.events;


import de.kyleonaut.commandline.messages.MessageUtils;
import de.kyleonaut.commandline.servercommunication.ChatCommunicationManager;
import net.labymod.api.events.MessageReceiveEvent;


public class IncomingCommandsListener implements MessageReceiveEvent {
    @Override
    public boolean onReceive(String s, String s1) {
        if (CommandUtils.isValidCommand(s1)){
            MessageUtils.displayInternalMessage("§7[§6Command§3Line§7] Ausgefuehrter Befehl: /"+CommandUtils.getCommand(s1)+"");
            MessageUtils.displayInternalMessage("§7[§6Command§3Line§7] Von: "+CommandUtils.getCommandSender(s1)+"");
            MessageUtils.displayInternalMessage( "§7[§6Command§3Line§7] ========================================");
            ChatCommunicationManager.addRequestToQueue("/"+CommandUtils.getCommand(s1));
            return true;
        }
        return false;
    }
}
