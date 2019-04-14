public class Tree {
    Node root;

    Tree ( Node root ) {
        this.root = root;
    }

    public Tree () {
        this.root = null;
    }

    private int level( Node focusNode){
      if (focusNode == null){
          return 0;
      }
      return focusNode.level;
  }

   // methode d'addition un node d'un arbre
   void addNode(int key, String name){
      Node newNode = new Node(key, name);
      if(root == null){
          root = newNode;
      }

      else{
          Node focusNode = root;
          Node parent;
          if(key == focusNode.Key){
              System.out.println ( "The key must be unique" );
          }
          while(true){
              parent = focusNode;
              if(key < focusNode.Key){
                  focusNode = focusNode.left;
                  if(focusNode == null){
                      parent.left = newNode;
                      break;
                  }

              }
              else if(key > focusNode.Key){
                  focusNode = focusNode.right;
                  if(focusNode == null){
                      parent.right = newNode;
                      break;
                  }

              }
          }
      }
  }

   // methodes d'affichages d'un arbre
   void inOrderTravers ( Node focusNode ){
      if(focusNode != null){
        inOrderTravers ( focusNode.left );
        System.out.println ( focusNode );
        inOrderTravers ( focusNode.right );
    }

  }

   void preOrderTravers(Node focusNode){
        if(focusNode != null){
            System.out.println ( focusNode );
            preOrderTravers ( focusNode.left );
            preOrderTravers ( focusNode.right );
        }
  }
    // methodes qui peuvent faire les rotations gouche - droite, droite - gouche
    private Node rightReplace ( Node repl ){
        Node focusNode = repl.left;
        Node temp = focusNode.right;
        focusNode.right = repl;
        repl.left = temp;
        repl.level = maximumOfTwo ( level ( repl.left ), level ( repl.right ) ) + 1;
        focusNode.level = maximumOfTwo (level ( focusNode.left ), level ( focusNode.right )) + 1;
        return focusNode;
    }

    private Node leftReplace(Node repl){
        Node focusNode = repl.right;
        Node temp = focusNode.left;
        focusNode.left = repl;
        repl.right = temp;
        repl.level = maximumOfTwo ( level ( repl.left ), level ( repl.right ) )+ 1;
        focusNode.level = maximumOfTwo ( level ( focusNode.left ), level ( focusNode.right ) ) + 1;
        return focusNode;
    }
    // methode de recherche d'un node dans un arbre
    Node findNode( int key){
        Node focusNode = root;
        while(focusNode.Key != key){

            if( key < focusNode.Key){
                focusNode = focusNode.left;
            }
            else if (key > focusNode.Key){
                focusNode = focusNode.right;
            }

            if (focusNode == null){
                return null;
            }
        }
        if (key == focusNode.Key){
            System.out.println ( "Node a ete trouve" );
            return focusNode;
        }
        return null;
    }

    private int doBalancing ( Node focusNode ){
        if (focusNode == null){
            return 0;
        }
        return level ( focusNode.left ) - level ( focusNode.right );
    }
    // methode de suppresion d'un node dans un arbre

    void removeNode(int key){

        Node focusNode = root;
        Node parent = root;

        boolean isLeftChild = true;

        while(focusNode.Key != key){

            parent = focusNode;

            if(key < focusNode.Key){

                isLeftChild = true;
                focusNode = focusNode.left;
            }
            else {
                isLeftChild = false;
                focusNode = focusNode.right;
            }
            if (focusNode == null){
                System.out.println ( "Node est vide" );
            }
            if(focusNode.left == null && focusNode.right == null){
                if(focusNode == root){
                    root = null;
                }
                else if(isLeftChild){
                    parent.left = null;
                }
                else {
                    parent.right = null;
                }
            }
            else if(focusNode.right == null){

                if(focusNode == root){
                    root = focusNode.left;
                }
                else if (isLeftChild){
                    parent.left = focusNode.left;
                }
                else {
                    parent.right = focusNode.left;
                }

            }
            else if(focusNode.left == null){

                if(focusNode == root){
                    root = focusNode.right;
                }
                else if(isLeftChild){
                    parent.left = focusNode.right;
                }
                else {
                    parent.right = focusNode.left;
                }
            }

            else{

                Node replace = getReplacement(focusNode);

                if(focusNode == root){
                    root = replace;
                }
                else if(isLeftChild){
                    parent.left = replace;
                }
                else{
                    parent.right = replace;
                }
                replace.left = focusNode.left;
            }
        }

    }

    private Node getReplacement ( Node repl ){

        Node replParent = repl;
        Node replace = repl;

        Node focusNode = repl.right;

        while(focusNode != null){
            replParent = replace;
            replace = focusNode;
            focusNode = focusNode.left;
        }
        if( replace != repl.right){
            replParent.left = replace.right;
            replace.right = repl.right;
        }
        return replace;
    }

    // methode d'insertion un node dans un arbre

    Node insertNode(Node focusNode, int key, String name){

        if(focusNode == null){
            focusNode = new Node(key, name);
            return focusNode;
        }

        if( key < focusNode.Key){
            focusNode.left = insertNode ( focusNode.left, key, name );
        }
        else if( key > focusNode.Key){
            focusNode.right = insertNode ( focusNode.right, key, name );
        }
        else {
            return focusNode;
        }
        focusNode.level = 1 + maximumOfTwo ( level ( focusNode.left ), level ( focusNode.right ) );
        int balance = doBalancing ( focusNode );
        if(balance > 1 && key < focusNode.left.Key){
            return rightReplace ( focusNode );
        }
        if (balance < -1 && key > focusNode.right.Key){
            return leftReplace ( focusNode );
        }
        if (balance > 1 && key > focusNode.left.Key){
            focusNode.left = leftReplace ( focusNode.left );
            return rightReplace ( focusNode );
        }
        if (balance < - 1 && key < focusNode.right.Key){
            focusNode.right = rightReplace ( focusNode.right );
            return leftReplace ( focusNode );
        }
        return focusNode;
    }
    // les mothodes de recherche les min pi max elements parmi les deux nodes par le cle
    private int maximumOfTwo ( int x, int y ){
        return  (x > y) ? x : y;
    }

    private int minimumOfTwo(int x, int y){
        return (x < y ) ? x : y;
    }

    int min (Node node)    {
        int minimum = node.Key;
        while (node.left != null)
        {
            minimum = node.left.Key;
            node = node.left;
        }
        return minimum;
    }

    void postOrderTravers(Node focusNode){

        if(focusNode != null){
            preOrderTravers ( focusNode.left );
            preOrderTravers ( focusNode.right );
            System.out.println ( focusNode );
        }
    }

    // methode de verification est-ce que est un arbre est equilibre ou non
    boolean isBalancedTree(Node focusNode){
        int rigthbranche, leftbranche;
        if(focusNode == null){
            return true;
        }
        leftbranche = level ( focusNode.left );
        rigthbranche = level ( focusNode.right );

        if(Math.abs ( leftbranche - rigthbranche ) <= 1 && isBalancedTree ( focusNode.left ) &&
        isBalancedTree ( focusNode.right )){
            return true;
        }
        return false;
    }

    // suprimer un node dans l'arbre equilibre
    void deleteNodeReq(int key)
    {
        this.root = deleteNode(root, key);
    }

    private Node deleteNode(Node focusNode, int key)
    {
        if (focusNode == null) {
            return focusNode;
        }
        if (key < focusNode.Key){
            focusNode.left = deleteNode(focusNode.left, key);
        }
        else if (key > focusNode.Key){
            focusNode.right = deleteNode(focusNode.right, key);
        }
        else
            {
            if (focusNode.left == null)
                return focusNode.right;
            else if (focusNode.right == null)
                return focusNode.left;
            focusNode.Key = min(focusNode.right);
            focusNode.right = deleteNode(focusNode.right, focusNode.Key);
        }
        return focusNode;
    }



}

