package com.projet.BackendPfe;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.projet.BackendPfe.Controller.UserController;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.core.io.FileSystemResource;

@Service
public class EmailSenderService {
	public static String reserveBody;

  
	private final JavaMailSender javaMailSender;
    
    @Autowired
    public EmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmailWithImagesAndButtonMedecin(String to, String me,String subject, String body ) throws UnsupportedEncodingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true,"UTF-8");
			helper.setTo(to);
			helper.setSubject(subject);
			 // Set HTML content with background image
            String htmlContent = "<html><body><center><img  width=450;height=450;margin:0 auto; src='cid:backgroundImage'></center>"+
            		" <center><h1 >Cher(e) : "+UserController.getPrenom() +"  " +UserController.getNom() +"</h1></center>\n"
            		+"<h3>Bienvenue à notre application de gestion de <u>clinique NAR</u> ! Nous sommes ravis de vous avoir parmi nous. Vous êtes maintenant officiellement inscrit(e) en tant que membre du personnel.</h3>"
            		+"<h3> Voici vos informations de connexion : \n </h3>"
            		+"<h3><u>Nom d'utilisateur :</u> "+"   "+UserController.getUsername()
            		+"<h3><u>Mot de passe :</u>"+UserController.getPassword()
            		+"\n"
            		+"<h3>Veuillez conserver ces informations en lieu sûr et ne les partager avec personne. Vous pouvez utiliser ces identifiants pour accéder à notre application et commencer à gérer vos patients et leurs dossiers médicaux.</h3>"
            		+"<h3>Si vous avez des questions ou rencontrez des problèmes lors de votre utilisation de l'application, n'hésitez pas à contacter notre équipe d'assistance à <i>CliniqueNAR@gmail.com</i> ou par téléphone au <i>73 123 456</i>.</h3>"
            		+"<h3> Encore une fois, bienvenue à bord ! Nous sommes impatients de travailler avec vous pour offrir les meilleurs soins à nos patients.</h3>"
            		+"<h3>Cordialement.</h3>"
            		+"\n\n"
            		+"<hr>"
            		+"\n"
                    +"<center><a href='https://www.google.com' style=\"display: inline-block; margin: 0 auto; background-color: #007bff; color: white; padding: 10px 20px; text-align: center; text-decoration: none; font-size: 16px; border: none; border-radius: 5px; cursor: pointer;\">Accéder à Site Web Clinique NAR</a></center>"
            		+"</body></html>";

            helper.setText(htmlContent, true);

            FileSystemResource image = new FileSystemResource("C:\\Users\\tashtiba\\Desktop\\images projet web\\bien3.jpg");
            helper.addInline("backgroundImage", image);
            helper.setFrom(new InternetAddress("rahmaromdhani08@gmail.com", "Clinique NAR"));
            
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public void sendEmailWithImagesAndButtonPatient(String to, String me,String subject, String body ) throws UnsupportedEncodingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true,"UTF-8");
			helper.setTo(to);
			helper.setSubject(subject);
			 // Set HTML content with background image
            String htmlContent = "<html><body><center><img  width=450;height=450;margin:0 auto; src='cid:backgroundImage'></center>"+
            		" <center><h1 >Cher/Chère : "+UserController.getPrenom() +"  " +UserController.getNom() +"</h1></center>\n"
            		+"<h3>Nous sommes ravis de vous accueillir à la <u>clinique NAR</u> ! Nous vous remercions d'avoir choisi notre clinique pour vos besoins de santé.</h3>"
            		+"<h3> Votre compte patient a été créé avec succès. Veuillez trouver ci-dessous vos informations de connexion : \n </h3>"
            		+"<h3><u>Nom d'utilisateur :</u> "+"   "+UserController.getUsername()
            		+"<h3><u>Mot de passe :</u>"+UserController.getPassword()
            		+"\n"
            		+"<h3>Veuillez utiliser ces informations pour accéder à votre compte patient sur notre site web. Une fois connecté, vous pourrez :</h3>"
            		+"<h3>Prendre rendez-vous en ligne.\r\n"
            		+ "Consulter vos rendez-vous à venir.\r\n"
            		+ "Accéder à vos résultats de laboratoire.\r\n"
            		+ "Mettre à jour vos informations personnelles..</h3>"
            		+"<h3> Encore une fois, bienvenue à la clinique <u>clinique NAR</u> ! Nous sommes impatients de vous voir lors de votre prochaine visite.</h3>"
            		+"<h3>Cordialement.</h3>"
            		+"\n\n"
            		+"<hr>"
            		+"\n"
                    +"<center><a href='https://www.google.com' style=\"display: inline-block; margin: 0 auto; background-color: #007bff; color: white; padding: 10px 20px; text-align: center; text-decoration: none; font-size: 16px; border: none; border-radius: 5px; cursor: pointer;\">Accéder à Site Web Clinique NAR</a></center>"
            		+"</body></html>";

            helper.setText(htmlContent, true);

            FileSystemResource image = new FileSystemResource("C:\\Users\\tashtiba\\Desktop\\images projet web\\welcomePatient.png");
            helper.addInline("backgroundImage", image);
            helper.setFrom(new InternetAddress("rahmaromdhani08@gmail.com", "Clinique NAR"));
            
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public static String getReserveBody() {
  		return reserveBody;
  	}

  	public static void setReserveBody(String reserveBody) {
  		EmailSenderService.reserveBody = reserveBody;
  	}
//" <h1 text-align: right; float: right;>\"Cher(e) : \"+</h1>\" +\r\n"
//+UserController.getPrenom() +"  " +UserController.getNom() +"\n" 
}