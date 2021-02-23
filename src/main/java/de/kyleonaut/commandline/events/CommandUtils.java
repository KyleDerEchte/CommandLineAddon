package de.kyleonaut.commandline.events;

import de.kyleonaut.commandline.CommandLine;
import de.kyleonaut.commandline.StartStopManager;
import de.kyleonaut.commandline.messages.InternalMessages;
import de.kyleonaut.commandline.messages.MessageUtils;

public class CommandUtils {

    public static boolean isValidCommand(String s1) {
        if (StartStopManager.isEnabled()) {
            if (!CommandLine.getWhitelistedUsers().contains(getCommandSender(s1))) {
                MessageUtils.displayInternalMessage(InternalMessages.userNotWhitelisted);
                return false;
            }

            if (CommandLine.getDeniedCommands().contains(getCommand(s1))) {
                MessageUtils.displayInternalMessage(InternalMessages.commandIsBlacklisted);
                return false;
            }

            return true;
        }
        return false;
    }

    public static String getCommandSender(String s1) {
        try {
            String[] parts = s1.split("->");
            String firstPart = parts[0];
            String[] rankAndName = firstPart.split(" ");
            return rankAndName[2];
        }catch (Throwable e){
            return null;
        }
    }

    public static String getCommand(String s1) {
        try {
            return s1.split("]")[1];
        }catch (Throwable e){
            return null;
        }
    }
}