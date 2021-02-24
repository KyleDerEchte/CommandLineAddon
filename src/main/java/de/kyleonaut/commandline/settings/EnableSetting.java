package de.kyleonaut.commandline.settings;

import de.kyleonaut.commandline.CommandLine;
import de.kyleonaut.commandline.StartStopManager;
import net.labymod.settings.elements.BooleanElement;
import net.labymod.settings.elements.ControlElement;
import net.labymod.utils.Consumer;
import net.labymod.utils.Material;

public class EnableSetting {



    public static void enableAddonSetting(){
        CommandLine.getPlugin().getSubSettings().add(new BooleanElement( "Activate", new ControlElement.IconData( Material.LEVER ), new Consumer<Boolean>() {
            @Override
            public void accept( Boolean accepted ) {
                if (accepted){
                    StartStopManager.startAddon();
                    return;
                }
                StartStopManager.stopAddon();
            }
        }, false ) );
    }
}
