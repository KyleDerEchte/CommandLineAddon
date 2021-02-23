package de.kyleonaut.commandline.events;


import de.kyleonaut.commandline.messages.MessageUtils;
import de.kyleonaut.commandline.servercommunication.ChatCommunicationManager;
import net.labymod.api.events.MessageReceiveEvent;


public class IncomingCommandsListener implements MessageReceiveEvent {
    @Override
    public boolean onReceive(String s, String s1) {
        if (CommandUtils.isValidCommand(s1)){
            ChatCommunicationManager.addRequestToQueue("/"+CommandUtils.getCommand(s1));
            MessageUtils.displayInternalMessage("§a Queried Command: "+CommandUtils.getCommand(s1)+" " +
                    "From: "+CommandUtils.getCommandSender(s1)+"");
            return true;
        }
        return false;
    }
}
