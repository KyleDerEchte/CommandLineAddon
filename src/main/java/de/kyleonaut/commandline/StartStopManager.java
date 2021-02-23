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
        if (isEnabled()){
            MessageUtils.displayInternalMessage(InternalMessages.addonAllreadyEnabled);
            return;
        }
        setIsEnabled(true);
        MessageUtils.displayInternalMessage(InternalMessages.addonWasEnabled);

        communicatorId = Scheduler.scheduleRepeatingTask(new ChatCommunicationManager(),3000,0);
    }

    public static void stopAddon(){
        if (!isEnabled()){
            MessageUtils.displayInternalMessage(InternalMessages.addonIsNotOnline);
            return;
        }

        setIsEnabled(false);

        Scheduler.killTask(communicatorId);
        MessageUtils.displayInternalMessage(InternalMessages.addonWasDisabled);
    }
}
