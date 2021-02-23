package de.kyleonaut.commandline;
import de.kyleonaut.commandline.commands.DisableCommand;
import de.kyleonaut.commandline.commands.EnableCommand;
import de.kyleonaut.commandline.events.IncomingCommandsListener;
import net.labymod.api.LabyModAddon;
import net.labymod.settings.elements.SettingsElement;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CommandLine extends LabyModAddon {

    private static final String commandUsersFilePath = "./LabyMod/addons-1.8/config/whitelisted_users.txt";
    private static final String deniedCommandsFilePath = "./LabyMod/addons-1.8/config/denied_commands.txt";

    private static final ArrayList<String> whitelistedUsers = new ArrayList<String>();

    private static final ArrayList<String> deniedCommands = new ArrayList<String>();


    @Override
    public void onEnable() {
        registerCommands();
        registerListener();
    }

    @Override
    public void loadConfig() {
        try {
            loadWhitelistedUsers();
            loadDeniedCommands();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void fillSettings(List<SettingsElement> list) {

    }

    private void registerListener(){
        getApi().getEventManager().register(new IncomingCommandsListener());
    }

    private void registerCommands(){
        getApi().getEventManager().register(new EnableCommand());
        getApi().getEventManager().register(new DisableCommand());
    }

    private void createWhitelistedUsersFileIfNotExists() throws FileNotFoundException {
        File file = new File(commandUsersFilePath);
        if (!file.exists()){
            PrintWriter writer = new PrintWriter(commandUsersFilePath);
            writer.println(" ");
            writer.close();
        }
    }

    private void loadWhitelistedUsers() throws IOException {
        createWhitelistedUsersFileIfNotExists();
        BufferedReader br = new BufferedReader(new FileReader(commandUsersFilePath));
        String line;
        while ((line = br.readLine()) != null) {
            whitelistedUsers.add(line);
        }
    }

    private void createDeniedCommandsIfNotExists() throws FileNotFoundException {
        File file = new File(deniedCommandsFilePath);
        if (!file.exists()){
            PrintWriter writer = new PrintWriter(deniedCommandsFilePath);
            writer.println(" ");
            writer.close();
        }
    }

    private void loadDeniedCommands() throws IOException {
        createDeniedCommandsIfNotExists();
        BufferedReader br = new BufferedReader(new FileReader(deniedCommandsFilePath));
        String line;
        while ((line = br.readLine()) != null) {
            deniedCommands.add(line);
        }
    }


    public static ArrayList<String> getWhitelistedUsers() {
        return whitelistedUsers;
    }

    public static ArrayList<String> getDeniedCommands() {
        return deniedCommands;
    }

}
