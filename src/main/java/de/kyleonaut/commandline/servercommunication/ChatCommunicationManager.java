package de.kyleonaut.commandline.servercommunication;

import de.kyleonaut.commandline.StartStopManager;
import de.kyleonaut.commandline.messages.InternalMessages;
import de.kyleonaut.commandline.messages.MessageUtils;

import java.util.ArrayList;
import java.util.List;

public class ChatCommunicationManager implements Runnable{

    private static final List<String> requestList = new ArrayList<String>();

    public static void addRequestToQueue(String request) {
        if (!StartStopManager.isEnabled()) {
            MessageUtils.displayInternalMessage(InternalMessages.addonIsNotOnline);
            return;
        }
        requestList.add(request);
    }

    private void executeNextRequest() {
        if (requestList.isEmpty()) return;
        String toSendToServer = requestList.remove(0);
        MessageUtils.sendMessageToServer(toSendToServer);
    }

    @Override
    public void run() {
        executeNextRequest();
    }
}
