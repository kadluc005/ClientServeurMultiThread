import java.io.*;
import java.net.Socket;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        final String SERVER_ADDRESS = "localhost";
        final int SERVER_PORT = 3001;

        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connexion au serveur...");
            String serverMessage;
            while ((serverMessage = in.readLine()) != null) {
                System.out.println("Serveur : " + serverMessage);


                if (serverMessage.toLowerCase().contains("jeu est terminé")) {
                    break;
                }

                // Demander à l'utilisateur de saisir un nombre
                System.out.print("Votre tentative (ou 'exit' pour quitter) : ");
                String userGuess = scanner.nextLine();

                // Envoyer la tentative au serveur
                out.println(userGuess);

                // Quitter si l'utilisateur a saisi "exit"
                if ("exit".equalsIgnoreCase(userGuess)) {
                    System.out.println("Déconnexion...");
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la communication avec le serveur : " + e.getMessage());
        }
    }
}