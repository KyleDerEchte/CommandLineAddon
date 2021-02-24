package de.kyleonaut.commandline;

import de.kyleonaut.commandline.events.IncomingCommandsListener;
import de.kyleonaut.commandline.settings.EnableSetting;
import net.labymod.api.LabyModAddon;
import net.labymod.settings.elements.SettingsElement;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CommandLine extends LabyModAddon {

    private static final String commandUsersFilePath = "./LabyMod/addons-1.8/config/whitelisted_users.txt";
    private static final String deniedCommandsFilePath = "./LabyMod/addons-1.8/config/denied_commands.txt";
    public static CommandLine getPlugin() {
        return plugin;
    }
    private static CommandLine plugin;
    private static final ArrayList<String> whitelistedUsers = new ArrayList<String>();
    private static final ArrayList<String> deniedCommands = new ArrayList<String>();
    @Override
    public List<SettingsElement> getSubSettings() {
        return super.getSubSettings();
    }

    @Override
    public void onEnable() {
        plugin = this;
        registerListener();
        EnableSetting.enableAddonSetting();
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


    private static void createWhitelistedUsersFileIfNotExists() throws FileNotFoundException {
        File file = new File(commandUsersFilePath);
        if (!file.exists()){
            PrintWriter writer = new PrintWriter(commandUsersFilePath);
            writer.println(" ");
            writer.close();
        }
    }

    public static void loadWhitelistedUsers() throws IOException {
        createWhitelistedUsersFileIfNotExists();
        BufferedReader br = new BufferedReader(new FileReader(commandUsersFilePath));
        String line;
        while ((line = br.readLine()) != null) {
            whitelistedUsers.add(line);
        }
    }

    private static void createDeniedCommandsIfNotExists() throws FileNotFoundException {
        File file = new File(deniedCommandsFilePath);
        if (!file.exists()){
            PrintWriter writer = new PrintWriter(deniedCommandsFilePath);
            writer.println(" ");
            writer.close();
        }
    }

    public static void loadDeniedCommands() throws IOException {
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
