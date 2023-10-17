package libraryBrussels;

import libraryBrussels.entities.AdresseClient;
import libraryBrussels.entities.Auteur;
import libraryBrussels.entities.Client;
import libraryBrussels.entities.Livre;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.sql.SQLException;
import java.sql.SQLOutput;
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

        String hql = "FROM Client";
        String hql1 = "FROM AdresseClient";
        List<Client> clients = session.createQuery(hql, Client.class).getResultList();
        List<AdresseClient> adresseClients = session.createQuery(hql1, AdresseClient.class).getResultList();

            // Vérifier que le client et l'adresse ont la même ID
            for (Client client : clients) {
                for (AdresseClient adresseClient : adresseClients) {
                    if (client.getId() == adresseClient.getId()) {
                        System.out.printf("""
                                        ID Client: %d
                                        Numéro National: %s
                                        Nom: %s
                                        Prénom: %s
                                        Adresse: %s %d, %d %s%n
                                        """, client.getId(), client.getNumeroNational(), client.getNom(), client.getPrenom(),
                                adresseClient.getRue(), adresseClient.getNumero(), adresseClient.getCp(), adresseClient.getVille());
                    }
                }
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
    public static void ListesAuteurs() {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction tx;

        List<Auteur> auteurs = session.createQuery("FROM Auteur", Auteur.class).getResultList();

        System.out.println("Liste des auteurs: \n");
        auteurs.forEach(a -> System.out.printf("""
                ID: %s
                Nom: %s
                Prénom: %s%n
                """, a.getId(),a.getNom(),a.getPrenom()));

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
                ID: %s
                ISBN: %s
                Titre: %s
                Date d'achat: %s
                Auteur: %s %s%n
                """, l.getId(), l.getISBN(), l.getTitre(), l.getDateAchat(), l.getAuteur().getNom(),l.getAuteur().getPrenom()));
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
    public static void EmprunteUnLivre() {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction tx;

        System.out.print("Sélectionné l'ID du client qui emprunte le livre: ");
        long idCust = sc.nextLong();

        Client client = session.get(Client.class, idCust);

        System.out.print("Entrez l'ID du livre à emprunter: ");
        long idBook = sc.nextLong();
        sc.nextLine();

        Livre livre = session.get(Livre.class, idBook);

        if (client != null && livre != null) {

            livre.getClients().add(client);

            System.out.printf("Livre emprunté par %s %s%n",client.getPrenom(),client.getNom());
        } else {
            System.out.println("Client ou Livre non trouvé.");
        }

        try {
            // Transaction Hibernate
            tx = session.beginTransaction();

            session.persist(livre);

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
    public static void LivresEmprunte() {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction tx;

        List<Client> emprunt = session.createQuery("SELECT DISTINCT c FROM Client c JOIN c.livres l", Client.class).getResultList();

        System.out.println("Liste des livres empruntés: \n");
        for (Client client : emprunt) {
                System.out.printf("""
                Emprunteur: %s %s%n
                """, client.getPrenom(), client.getNom());

                for (Livre livre : client.getLivres()) {
                    System.out.printf("Livre: %s, ISBN - %d%n\n", livre.getTitre(), livre.getISBN());
                }
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
    public static void EmpruntRendu() {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction tx;

        System.out.print("Sélectionné l'ID du client qui à emprunté le livre: ");
        long idCust = sc.nextLong();

        Client client = session.get(Client.class, idCust);

        System.out.print("Entrez l'ID du livre emprunté: ");
        long idBook = sc.nextLong();
        sc.nextLine();

        Livre livre = session.get(Livre.class, idBook);

        if (client != null && livre != null) {

            livre.getClients().remove(client);

            System.out.printf("Livre rendu par %s %s%n",client.getPrenom(),client.getNom());
        } else {
            System.out.println("Client ou Livre non trouvé.");
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
                    10. Emprunt d'un livre
                    11. Liste livres empruntés
                    12. Emprunt à remettre
                    13. Quitter
                    Enter your choice:  """ );
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
                case "10" -> EmprunteUnLivre();
                case "11" -> LivresEmprunte();
                case "12" -> EmpruntRendu();
                case "13" -> {
                    System.out.println("Merci et au revoir :)");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
