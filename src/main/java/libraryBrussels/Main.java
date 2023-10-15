package libraryBrussels;

import libraryBrussels.entities.AdresseClient;
import libraryBrussels.entities.Auteur;
import libraryBrussels.entities.Client;
import libraryBrussels.entities.Livre;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
static Scanner sc = new Scanner(System.in);
    public static void AjouterUnClient() {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx;

            System.out.println("Création du client");
            System.out.print("Entrez le numéro national: ");
            long addNN = sc.nextLong();
            System.out.print("Entrez le nom: ");
            String addName = sc.next();
            System.out.print("Entrez le prénom: ");
            String addLastName = sc.next();

            Client client = new Client();

            client.setNumeroNational(addNN);
            client.setNom(addName);
            client.setPrenom(addLastName);


            sc.nextLine(); // Add this line to clear the buffer

            System.out.println("Entrez les cordonnées");
            System.out.print("Entrez la rue: ");
            String rueClient = sc.nextLine();
            System.out.print("Entrez le numéro de la rue: ");
            int numRue = sc.nextInt();
            System.out.print("Entrez le code postal: ");
            int codePost = sc.nextInt();
            System.out.print("Entrez la ville: ");
            String ville = sc.next();

            AdresseClient adresseClient = new AdresseClient();

            adresseClient.setRue(rueClient);
            adresseClient.setNumero(numRue);
            adresseClient.setCp(codePost);
            adresseClient.setVille(ville);


        try {

            // Transaction Hibernate
            tx = session.beginTransaction();

            // Enregistrement des entités dans la BD
            session.persist(client);
            session.persist(adresseClient);

            // Validation de la transaction
            tx.commit();
            System.out.println("Enregistrements ajoutés avec succès!");
    } catch (Exception e) {
            // En cas d'erreurs, annuler la transaction
            if (session.getTransaction() !=null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
    public static void SupprimerUnClient() {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx;

        System.out.println("Suppression du client et de son adresse");
        System.out.print("Entrez l'ID du client à supprimer: ");
        long idRemove = sc.nextLong();

        // Suppression du client
        Client client = new Client();
        client.setId(idRemove);
        // Suppression de l'adresse
        AdresseClient adresseClient = new AdresseClient();
        adresseClient.setId(idRemove);

        try {

            // Transaction Hibernate
            tx = session.beginTransaction();

            // Enregistrement des entités dans la BD
            session.remove(client);
            session.remove(adresseClient);
            session.flush();

            // Validation de la transaction
            tx.commit();
            System.out.println("Client supprimé!");
        } catch (Exception e) {
            // En cas d'erreurs, annuler la transaction
            if (session.getTransaction() !=null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
    public static void ConsulterListeClients() {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction tx;

        List<Client> clients = session.createQuery("FROM Client", Client.class).getResultList();

        System.out.println("Liste des clients: \n");
        for (Client client : clients) {
            System.out.println("ID: " + client.getId());
            System.out.println("Numéro National: " + client.getNumeroNational());
            System.out.println("Nom: " + client.getNom());
            System.out.println("Prénom: " + client.getPrenom());
            System.out.println();
        }

        try {
            // Transaction Hibernate
            tx = session.beginTransaction();

            // Validation de la transaction
            tx.commit();
        } catch (Exception e) {
            // En cas d'erreurs, annuler la transaction
            if (session.getTransaction() !=null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
    public static void AjouterUnAuteur() {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx;

        System.out.println("Création de l'auteur");
        System.out.print("Entrez le nom: ");
        String addName = sc.next();
        System.out.print("Entrez le prénom: ");
        String addLastName = sc.next();

        Auteur auteur = new Auteur();

        auteur.setNom(addName);
        auteur.setPrenom(addLastName);

        try {

            // Transaction Hibernate
            tx = session.beginTransaction();

            // Enregistrement des entités dans la BD
            session.persist(auteur);

            // Validation de la transaction
            tx.commit();
            System.out.println("Enregistrements ajoutés avec succès!");
        } catch (Exception e) {
            // En cas d'erreurs, annuler la transaction
            if (session.getTransaction() !=null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
    public static void SupprimerUnAuteur() {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx;

        System.out.println("Suppression de l'auteur");
        System.out.print("Entrez l'ID de l'auteur à supprimer: ");
        long idRemove = sc.nextLong();

        // Suppression de l'auteur
        Auteur auteur = new Auteur();
        auteur.setId(idRemove);

        try {

            // Transaction Hibernate
            tx = session.beginTransaction();

            // Enregistrement des entités dans la BD
            session.remove(auteur);
            session.flush();

            // Validation de la transaction
            tx.commit();
            System.out.println("Auteur supprimé!");
        } catch (Exception e) {
            // En cas d'erreurs, annuler la transaction
            if (session.getTransaction() !=null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
    public static void ConsulterListeAuteur() {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction tx;

        List<Auteur> auteurs = session.createQuery("FROM Auteur", Auteur.class).getResultList();

        System.out.println("Liste des auteurs: \n");
        for (Auteur auteur : auteurs) {
            System.out.println("Nom: " + auteur.getNom());
            System.out.println("Prénom: " + auteur.getPrenom());
            System.out.println();
        }

        try {
            // Transaction Hibernate
            tx = session.beginTransaction();

            // Validation de la transaction
            tx.commit();
        } catch (Exception e) {
            // En cas d'erreurs, annuler la transaction
            if (session.getTransaction() !=null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
    public static void AjouterUnLivre() {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction tx;

        System.out.print("Entrez l'ID de l'auteur à ajouter au livre: ");
        int addAuthor = sc.nextInt();
        Auteur auteur = session.get(Auteur.class, addAuthor);

        if (auteur != null) {

            Livre livre = new Livre();
            System.out.print("Entrez l'ISBN du livre: ");
            long isbnBook = sc.nextLong();
            sc.nextLine();
            System.out.print("Entrez le titre du livre: ");
            String titleBook = sc.nextLine();
            System.out.print("Entrez la date d'achat: (AAAA-MM-JJ)");
            LocalDate date = LocalDate.parse(sc.nextLine());

            livre.setISBN(isbnBook);
            livre.setTitre(titleBook);
            livre.setDateAchat(date);
            livre.setAuteur_id(addAuthor);

            session.persist(auteur);
            session.persist(livre);

            System.out.println("Livre ajouté avec succès!");
        } else {
            System.out.println("Auteur non trouvé.");
        }

        try {
            // Transaction Hibernate
            tx = session.beginTransaction();

            // Validation de la transaction
            tx.commit();
        } catch (Exception e) {
            // En cas d'erreurs, annuler la transaction
            if (session.getTransaction() !=null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
    public static void SupprimerUnLivre() {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx;

        System.out.println("Suppression du livre lié à un auteur");
        System.out.print("Entrez l'ID du livre: ");
        long idBook = sc.nextLong();

        // Récupère le livre
        Livre livre = session.get(Livre.class, idBook);
        // Récupère l'auteur
        if (livre != null) {
            for (Auteur auteur : livre.getAuteurList()) {
                auteur.setLivre(null); // Dissocier l'auteur du livre
            }
        }

        try {

            // Transaction Hibernate
            tx = session.beginTransaction();

            // Enregistrement des entités dans la BD
            session.remove(livre);
            session.flush();

            // Validation de la transaction
            tx.commit();
            System.out.println("Livre supprimé!");
        } catch (Exception e) {
            // En cas d'erreurs, annuler la transaction
            if (session.getTransaction() !=null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
    public static void ConsulterListeLivres() {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction tx;

        List<Livre> livres = session.createQuery("FROM Livre", Livre.class).getResultList();
        List<Auteur> auteurs = session.createQuery("FROM Auteur", Auteur.class).getResultList();

        System.out.println("Liste des livres: \n");
        for (Livre livre : livres) {
            System.out.printf("ISBN: %s%n",livre.getISBN());
            for (Auteur auteur : auteurs) {
                System.out.printf("Auteur: %s %s%n",auteur.getNom(),auteur.getPrenom());
            }
            System.out.printf("Titre: %s%n", livre.getTitre());
            System.out.printf("Date d'achat: %s%n\n", livre.getDateAchat());
        }

        try {
            // Transaction Hibernate
            tx = session.beginTransaction();

            // Validation de la transaction
            tx.commit();
        } catch (Exception e) {
            // En cas d'erreurs, annuler la transaction
            if (session.getTransaction() !=null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }


    public static void main(String[] args) {

        while(true) {
            System.out.print("""
                    
                    Menu:
                    1. Ajouter un client
                    2. Supprimer un client
                    3. Consulter la liste des clients
                    4. Ajouter un auteur
                    5. Supprimer un auteur
                    6. Liste des auteurs
                    7. Ajouter un livre
                    8. Supprimer un livre
                    9. Liste des livres
                    10. Quitter
                    Enter your choice:""" );
            String choice = sc.next();

            switch (choice) {
                case "1" -> AjouterUnClient();
                case "2" -> SupprimerUnClient();
                case "3" -> ConsulterListeClients();
                case "4" -> AjouterUnAuteur();
                case "5" -> SupprimerUnAuteur();
                case "6" -> ConsulterListeAuteur();
                case "7" -> AjouterUnLivre();
                case "8" -> SupprimerUnLivre();
                case "9" -> ConsulterListeLivres();
                case "10" -> {
                    System.out.println("Merci et aurevoir :)");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
