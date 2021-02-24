package de.kyleonaut.commandline;


import de.kyleonaut.commandline.messages.InternalMessages;
import de.kyleonaut.commandline.messages.MessageUtils;
import de.kyleonaut.commandline.servercommunication.ChatCommunicationManager;
import de.kyleonaut.commandline.servercommunication.Scheduler;

public class StartStopManager {
    private static int communicatorId;
    private static boolean isEnabled = false;

    public static void setIsEnabled(boolean isEnabled) {
        StartStopManager.isEnabled = isEnabled;
    }

    public static boolean isEnabled() { return isEnabled; }


    public static void startAddon(){
        MessageUtils.displayInternalMessage(InternalMessages.addonWasEnabled);
        setIsEnabled(true);
        communicatorId = Scheduler.scheduleRepeatingTask(new ChatCommunicationManager(),3000,0);

    }

    public static void stopAddon(){
        Scheduler.killTask(communicatorId);
        setIsEnabled(false);
        MessageUtils.displayInternalMessage(InternalMessages.addonWasDisabled);
    }

}
