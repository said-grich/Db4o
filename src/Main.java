
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

class Main {

    public static void main(String[] args) {
        ObjectContainer bd = null;
        try {
            bd = Db4o.openFile("bd.data");
            Personne personne1 = new Personne("Dupond", "Pierre", 35);
            Personne personne2 = new Personne("Durand", "Julien", 40);
            Personne personne3 = new Personne("Breton", "Ludovic", 25);
            Personne personne4 = new Personne("Vian", "Bernard", 40);
            // Ajoute les personnes dans la base de données
            bd.store(personne1);
            bd.store(personne2);
            bd.store(personne3);
            bd.store(personne4);
            bd.commit();
            // Passe un objet, exemple de ce que l'on cherche.
            ObjectSet<Personne> personnes = bd.queryByExample(new Personne(null, null, 40));
            for (Personne personne : personnes) {
                System.out.println(personne.getNom());
            }
        } finally {
            if (bd != null) {
                // close() fait un commit automatique.
                bd.close();
                // Si on oublie de fermer (ne pas le faire !), la base sera fermée quand même
                // (avec un commit automatique) par ShutdownHook !!
            }
        }
    }

}
