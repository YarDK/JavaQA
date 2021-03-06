package mantis.appmanager;

import mantis.model.MailMessage;
import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;

import javax.mail.MessagingException;
import javax.mail.Message;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MailHelper {
    private ApplicationManager app;
    private final Wiser wiser;

    public MailHelper(ApplicationManager app) {
        this.app = app;
        wiser = new Wiser();
    }

    public List<MailMessage> waitForMail(int count, long timeout){
        System.out.println("Mail server run? - " + wiser.getServer().isRunning());
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() < start + timeout){
            System.out.println("Count message: " + wiser.getMessages().size());
            if(wiser.getMessages().size() >= count){
                return wiser.getMessages().stream().map(m -> toModelMail(m)).collect(Collectors.toList());
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        throw new Error("No mail :(");
    }


    private static MailMessage toModelMail(WiserMessage m) {
        try{
            MimeMessage mm = m.getMimeMessage();;
            return new MailMessage(mm.getAllRecipients()[0].toString(), (String) mm.getContent());
        } catch (MessagingException | IOException e){
            e.printStackTrace();
            return null;
        }
    }


    public void start(){
        wiser.start();

    }

    public void stop(){
        wiser.stop();
    }
}
