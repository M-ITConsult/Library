package libraryBrussels;

import libraryBrussels.entities.AdresseClient;
import libraryBrussels.entities.Auteur;
import libraryBrussels.entities.Client;
import libraryBrussels.entities.Livre;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
static Scanner sc = new Scanner(System.in);
    public static void AjouterUnClient() {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx;

            System.out.println("Création du client");
            sc.nextLine();
            System.out.print("Entrez le numéro national: ");
            String addNN = sc.nextLine();
            System.out.print("Entrez le nom: ");
            String addName = sc.nextLine();
            System.out.print("Entrez le prénom: ");
            String addLastName = sc.nextLine();

            Client client = new Client();

            client.setNumeroNational(addNN);
            client.setNom(addName);
            client.setPrenom(addLastName);

            System.out.println("Entrez les cordonnées");
            System.out.print("Entrez la rue: ");
            String rueClient = sc.nextLine();
            System.out.print("Entrez le numéro de la rue: ");
            int numRue = sc.nextInt();
            System.out.print("Entrez le code postal: ");
            int codePost = sc.nextInt();
            sc.nextLine();
            System.out.print("Entrez la ville: ");
            String ville = sc.nextLine();

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
            System.out.println("Client ajoutés avec succès!");
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
    public static void DetailClient() {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction tx;

        // Affichage des clients
        List<Client> clients = session.createQuery("FROM Client", Client.class).getResultList();
        clients.forEach(c -> System.out.printf("""
                ID: %s
                Numéro National: %s
                Nom: %s
                Prénom: %s%n
                """, c.getId(), c.getNumeroNational(), c.getNom(), c.getPrenom()));

        // Affichage des adresses
        List<AdresseClient> adresseClients = session.createQuery("FROM AdresseClient", AdresseClient.class).getResultList();
        adresseClients.forEach(ad -> System.out.printf("""
                ID: %s
                Adresse: %s %s%n%s %s%n
                """, ad.getId(), ad.getRue(), ad.getNumero(), ad.getCp(), ad.getVille()));
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
    public static void ListesAuteurs() {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction tx;

        List<Auteur> auteurs = session.createQuery("FROM Auteur", Auteur.class).getResultList();

        System.out.println("Liste des auteurs: \n");
        auteurs.forEach(a -> System.out.printf("""
                ID: %s
                Nom: %s
                Prénom: %s
                Livres: %s%n
                """, a.getId(),a.getNom(),a.getPrenom(),a.getLivre()));

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
            livre.setAuteur(auteur);

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

        System.out.println("Suppression du livre");
        System.out.print("Entrez l'ID du livre: ");
        long idBook = sc.nextLong();

        // Récupère le livre
        Livre livre = session.get(Livre.class, idBook);

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
    public static void ListesLivres() {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction tx;

        List<Livre> livres = session.createQuery("FROM Livre", Livre.class).getResultList();


        System.out.println("Liste des livres: \n");
        livres.forEach(l -> System.out.printf("""
                ISBN: %s
                Titre: %s
                Date d'achat: %s
                Auteur: %s %s%n
                """, l.getISBN(), l.getTitre(), l.getDateAchat(), l.getAuteur().getNom(),l.getAuteur().getPrenom()));
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


    public static void main(String[] args) throws SQLException {

        while(true) {
            System.out.print("""
                    
                    Menu:
                    1. Ajouter un client
                    2. Supprimer un client
                    3. Listes des clients
                    4. Ajouter un auteur
                    5. Supprimer un auteur
                    6. Listes des auteurs
                    7. Ajouter un livre
                    8. Supprimer un livre
                    9. Listes des livres
                    10. Quitter
                    Enter your choice:
                    """ );
            String choice = sc.next();

            switch (choice) {
                case "1" -> AjouterUnClient();
                case "2" -> SupprimerUnClient();
                case "3" -> DetailClient();
                case "4" -> AjouterUnAuteur();
                case "5" -> SupprimerUnAuteur();
                case "6" -> ListesAuteurs();
                case "7" -> AjouterUnLivre();
                case "8" -> SupprimerUnLivre();
                case "9" -> ListesLivres();
                case "10" -> {
                    System.out.println("Merci et aurevoir :)");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
