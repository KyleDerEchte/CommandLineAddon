package de.kyleonaut.commandline.messages;

import net.labymod.main.LabyMod;
import net.minecraft.client.Minecraft;

public class MessageUtils {

    public static void sendMessageToServer(String str){
        if (!str.isEmpty()){
            Minecraft.getMinecraft().thePlayer.sendChatMessage(str);
        }
    }

    public static void displayInternalMessage(String str){
        LabyMod.getInstance().displayMessageInChat(str);
    }


}
