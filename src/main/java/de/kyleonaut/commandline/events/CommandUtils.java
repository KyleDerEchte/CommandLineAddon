package de.kyleonaut.commandline.events;

import de.kyleonaut.commandline.CommandLine;
import de.kyleonaut.commandline.StartStopManager;
import de.kyleonaut.commandline.messages.MessageUtils;

public class CommandUtils {

    public static boolean isValidCommand(String s1) {
        if (StartStopManager.isEnabled()) {
            if(!(s1.contains("[") && s1.contains("->") && s1.contains("]"))){
                return false;
            }

            if (!CommandLine.getWhitelistedUsers().contains(getCommandSender(s1))) {
                return false;
            }

            for(String cmd : CommandLine.getDeniedCommands()){
                s1 = s1.toLowerCase();
                if (s1.contains(cmd)){
                    MessageUtils.displayInternalMessage("§7[§6Command§3Line§7]§c "+getCommandSender(s1)+" hat" +
                            " versucht den Befehl: /"+getCommand(s1)+" auszuführen");
                    return false;
                }
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
            return s1.split("] ")[1];
        }catch (Throwable e){
            return null;
        }
    }
}
