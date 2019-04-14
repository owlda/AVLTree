public class Executing {
    public static void main ( String[] args ) {
        //------------------- Denis Andrienko ----------------------------------------//

        //----------------------- Testing -------------------------------------------//
        // creation le presedent  du company comme le root d'arbre
        Node president = new Node(30,"President of company" );


        /* creation d'un arbre equilibre
        creation un arbre qui represent l'hierarchie de l'enterprise*/
        Tree company = new Tree (president);

         /* addition d'un arbre les employes commes les node d'arbre
        methode qui teste d'arbre automatique avec l'insertion*/

        company.root = company.insertNode (company.root, 20, "Vice president 1" );
        company.root = company.insertNode (company.root, 15, "Superviser 1" );
        company.root = company.insertNode (company.root, 10, "Manager" );
        company.root = company.insertNode (company.root, 5, "Salesman" );
        company.root = company.insertNode (company.root, 35, "Vice president 2" );
        company.root = company.insertNode (company.root, 1, "Delivierman" );
        company.root = company.insertNode (company.root, 45, "Superviser 2" );
        company.root = company.insertNode (company.root, 55, "Senior manager" );
        company.root = company.insertNode (company.root, 60, "Manager" );
        company.root = company.insertNode (company.root, 65, "Salesman" );
        company.root = company.insertNode (company.root, 70, "Delivierman" );


        // affichage d'arbre avec la methode prefix
        company.preOrderTravers ( company.root );

        /*Node{Key=20, name='Vice president 1}
          Node{Key=10, name='Manager}
          Node{Key=5, name='Salesman}
          Node{Key=1, name='Delivierman}
          Node{Key=15, name='Superviser 1}
          Node{Key=55, name='Senior manager}
          Node{Key=35, name='Vice president 2}
          Node{Key=30, name='President of company}
          Node{Key=45, name='Superviser 2}
          Node{Key=65, name='Salesman}
          Node{Key=60, name='Manager}
          Node{Key=70, name='Delivierman}*/

        // l'arbre est equilibre
        System.out.println ( company.isBalancedTree ( company.root ) );

        /*true
        */

        // recherche un node d'un arbre equilibre
        System.out.println ( company.findNode ( 10) );

        /*Node a ete trouve
        Node{Key=10, name='Manager}*/

        // suppesion un node d'un arbre equilibre
        company.deleteNodeReq ( 70 );

        // affichage d'arbre avec la methode prefix
        company.preOrderTravers ( company.root );
        /*Node{Key=20, name='Vice president 1}
          Node{Key=10, name='Manager}
          Node{Key=5, name='Salesman}
          Node{Key=1, name='Delivierman}
          Node{Key=15, name='Superviser 1}
          Node{Key=55, name='Senior manager}
          Node{Key=35, name='Vice president 2}
          Node{Key=30, name='President of company}
          Node{Key=45, name='Superviser 2}
          Node{Key=65, name='Salesman}
          Node{Key=60, name='Manager}
          */

        // l'arbre est equilibre
        System.out.println ( company.isBalancedTree ( company.root ) );

        /*true
        */
    }
}
