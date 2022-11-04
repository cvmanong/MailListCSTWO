package client.startup;

import client.controller.MailController;
import client.view.Welcome;

public class MailStart extends Welcome {
    @Override
    public void showMailAdmin() {
        new MailController().setVisible(true);
    }

    public static void main(String[] args) {
        new MailStart().setVisible(true);
    }
}
