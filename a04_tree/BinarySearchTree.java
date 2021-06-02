package a04_tree;

public class BinarySearchTree {
    private static class Node {
        private int data;      // Node에서 저장하는 data
        private Node parent;    // 현재 Node의 부모를 가리킴
        private Node left;      // 현재 Node의 왼쪽 자식을 가리킴
        private Node right;     // 현재 Node의 오른쪽 자식을 가리킴
        
        /* [필수] 생성자 */
        public Node(int d) {
            data = d;
        }
        
        /* [필수] getData() */
        public int getData() {
            return data;
        }
        
        /* [필수] getParent() */
        public Node getParent() {
            return parent;
        }
        
        /* [필수] getLeft() */
        public Node getLeft() {
            return left;
        }
        
        /* [필수] getRight() */
        public Node getRight() {
            return right;
        }
        
        /* [필수] setParent() */
        public void setParent(Node n) {
            parent = n;
        }
        
        /* [필수] setLeft() */
        public void setLeft(Node n) {
            left = n;
        }
        
        /* [필수] setRight() */
        public void setRight(Node n) {
            right = n;
        }
    }
    
    /*
     * [Class] BinarySearchTree
     *   1) 개요
     *     - Tree의 맨 위를 가리키는 root가 있음
     *     - 데이터를 추가할 때는 작은값이 왼쪽, 큰값이 오른쪽으로 추가되게 해야 함
     *   2) 필수 operation
     *     - size(): BST의 크기를 나타내는 함수
     *     - getRoot(): BST의 root를 반환하는 함수
     *     - add(): BST에 data를 추가하는 함수
     *   3) 추가 operation
     *     - remove(): BST의 특정 data를 삭제하는 함수
     *     - inorder(): inorder 방식으로 tree를 순회하는 함수
     *     - preorder(): preorder 방식으로 tree를 순회하는 함수
     */
    
    private Node root;      // BST의 root
    private int size;       // BST의 size
    
    /* [필수] 생성자 */
    public BinarySearchTree() {
        root = new Node(-1);
        size = 0;
    }
    
    /*
     * [필수] size()
     *  - BST의 size를 리턴
     */
    public int size() {
        return size;
    }
    
    /*
     * [필수] getRoot()
     *  - BST의 root를 리턴
     */
    public Node getRoot() {
        return root;
    }
    
    /*
     * [필수] add()
     *  - BST에 값 추가
     *  - 다음의 세 가지 경우 중 하나
     *    1) root가 없으면 root로 설정
     *    2-1) 현재 보고 있는 노드의 값보다 작으면 왼쪽으로 이동
     *    2-2) 현재 보고 있는 노드의 값보다 크면 오른쪽으로 이동
     *    3) 만약 해당 위치가 비어있으면 거기에 추가
     */
    public void add(int data) {
        int root_data = getRoot().getData();

        if(size == 0){             //만약 빈 트리라면
            root = new Node(data);  //루트에 노드 대입
        }
        else{                       //아니라면
            add2SubTree(root, data);//add2SubTree에 root를 넘겨줌
        }
        size++;
    }
    public void add2SubTree(Node node, int data){
        if(node.getData() > data){              //인자로 받은 노드와 데이터 중 데이터가 작다면 왼쪽 자식 노드에 추가해야한다.
            if(node.getLeft() == null){         //왼쪽노드는 비어있는가? 비어있다면 추가
                Node newNode = new Node(data);  //새 노드 만들기
                newNode.setParent(node);        //새 노드의 부모 설정
                node.setLeft(newNode);          //새 노드를 인자로 받은 노드의 왼쪽 자식 노드로 지정
            }
            else{                               //만약 비어있지 않다면
                add2SubTree(node.getLeft(), data);  //왼쪽 노드를 재귀(빈 자식 노드를 찾을 때 까지)
            }
        }
        else{                                   //인자로 받은 노드와 데이터 중 데이터가 크다면 오른쪽 자식 노드에 추가해야한다.
            if(node.getRight() == null){        //오른쪽 노드는 비어있는가? 비어있다면 추가
                Node newNode = new Node(data);  //새 노드 만들기
                newNode.setParent(node);        //새 노드의 부모 설정
                node.setRight(newNode);         //새 노드를 인자로 받은 노드의 오른쪽 자식 노드로 지정
            }
            else{                               //만약 비어있지 않다면
                add2SubTree(node.getRight(), data); //오른쪽 노드를 재귀(빈 자식 노드를 찾을 때 까지)
            }
        }
    }
    /*
    * left sub-tree 중에 가장 큰 값을 가진 노드 반환
    * */
    public Node rightMost(Node n) {
        Node tmpNode;
        if(n.getLeft()==null){
            return n;
        }
        else{
            tmpNode = n.getLeft();
        }
        while(tmpNode.getRight()!=null){
            tmpNode = tmpNode.getRight();
        }
        return tmpNode;
    }

    /*
     * right sub-tree 중에 가장 큰 값을 가진 노드 반환
     * */
    public Node leftMost(Node n) {
        Node tmpNode;
        if(n.getRight()==null){
            return n;
        }
        else{
            tmpNode = n.getRight();
        }
        while(tmpNode.getLeft()!=null){
            tmpNode = tmpNode.getLeft();
        }
        return tmpNode;
    }
    
    /*
     *  remove()
     *    - BST의 특정 data를 삭제하는 함수
     *      1) leaf node일 경우 그냥 삭제
     *      2) internal node일 경우 삭제하고 아래쪽 node를 올림
     *         - 왼쪽 sub-tree(작은 값들) 중에 가장 큰 값 or 오른쪽 sub-tree(큰 값들) 중에 가장 작은 값 중 하나를 올림
     *         - 왼쪽 sub-tree 중에 가장 큰 값을 우선적으로 올림
     *    - data가 없으면 -1을 return
     */
    public int remove(int data) {
        Node findNode = getRoot();

        // 노드 찾기
        while(findNode.getData()!=data){
            if(findNode.getData()>data){
                if(findNode.getLeft() == null){
                    return -1;
                }
                findNode = findNode.getLeft();
            }
            else{
                if(findNode.getRight() == null){
                    return -1;
                }
                findNode = findNode.getRight();
            }
        }

        if(findNode.getLeft() == null && findNode.getRight() == null){  //leaf node 처리
            if(getRoot().getData() == findNode.getData()){  //만약 트리를 지우는 경우
                int result = findNode.getData();
                root = new Node(-1);

                size--;
                return result;
            }
            else{
                int result = findNode.getData();
                
                if(findNode.getParent().getData() > findNode.getData()){
                    findNode.getParent().setLeft(null); //왼쪽이면 왼쪽 끊어주기
                }
                else{           
                    findNode.getParent().setRight(null);//아니면 오른쪽 끊어주기
                }
                
                findNode.setParent(null); //부모 끊어주기

                size--;
                return result;
            }
        }

        else{   //internal node 처리
            int result = findNode.getData();
            Node mostNode;

            if(findNode.getLeft() != null){     //왼쪽 노드가 있을 때의 처리
                mostNode = rightMost(findNode); //most 노드 지정
                System.out.println(mostNode.getData());

                // mostNode가 있던 자리에 오게될 노드와 mostNode의 부모를 연결
                if(mostNode.getLeft() != null){     //혹시 왼쪽이 없을지도
                    mostNode.getParent().setRight(mostNode.getLeft());
                    mostNode.getLeft().setParent(mostNode.getParent());
                }
                else{
                    mostNode.getParent().setRight(null);
                }
                mostNode.setParent(null);
                mostNode.setLeft(null);
                mostNode.setRight(null);

                // mostNode를 삭제할 노드의 위치에 연결
                if(findNode.getParent() != null){   //혹시 부모가 없을지도
                    mostNode.setParent(findNode.getParent());
                }
                mostNode.setLeft(findNode.getLeft());
                if(findNode.getRight() != null){    //혹시 오른쪽이 없을지도
                    mostNode.setRight(findNode.getRight());
                }

                // 삭제하게 될 노드와 연결된 노드를 mostNode와 연결
                if(findNode.getParent() != null){   //혹시 부모가 없을지도
                    if (findNode.getParent().getData() > mostNode.getData()) {
                        findNode.getParent().setLeft(mostNode);
                    }
                    else{
                        findNode.getParent().setRight(mostNode);
                    }
                }
                else{
                    root = mostNode;
                }
                findNode.getLeft().setParent(mostNode);
                if(findNode.getRight() != null){    //혹시 오른쪽이 없을지도
                    findNode.getRight().setParent(mostNode);
                }

                findNode.setParent(null);
                findNode.setLeft(null);
                findNode.setRight(null);

                size--;
                return result;

            }
            else{
                mostNode = leftMost(findNode); //most 노드 지정

                // mostNode가 있던 자리에 오게될 노드와 mostNode의 부모를 연결
                if(mostNode.getRight() != null){
                    mostNode.getParent().setLeft(mostNode.getRight());
                    mostNode.getRight().setParent(mostNode.getParent());
                }
                else{
                    mostNode.getParent().setLeft(null);
                }

                mostNode.setParent(null);
                mostNode.setLeft(null);
                mostNode.setRight(null);

                // mostNode를 삭제할 노드의 위치에 연결
                if(findNode.getParent() != null){   //혹시 부모가 없을지도
                    mostNode.setParent(findNode.getParent());
                }
                mostNode.setRight(findNode.getRight());


                // 삭제하게 될 노드와 연결된 노드를 mostNode와 연결
                if(findNode.getParent() != null){   //혹시 부모가 없을지도
                    if (findNode.getParent().getData() > mostNode.getData()) {
                        findNode.getParent().setLeft(mostNode);
                    }
                    else{
                        findNode.getParent().setRight(mostNode);
                    }
                }
                else{
                    root = mostNode;
                }
                findNode.getRight().setParent(mostNode);

                findNode.setParent(null);
                findNode.setLeft(null);
                findNode.setRight(null);

                size--;
                return result;
            }

        }


    }
    
    /*
     *  preorder()
     *    - BST의 모든 데이터를 preorder 방식으로 출력
     *    - 출력 예시로 제공
     */
    public void preorder(Node n) {
        if(size() == 0){
            return;
        }
        System.out.print(n.data + " ");
        if(n.getLeft() != null){
            preorder(n.getLeft());
        }
        if(n.getRight() != null){
            preorder(n.getRight());
        }
    }
    
    /*
     *  inorder()
     *    - BST의 모든 데이터를 inorder 방식으로 출력
     *    - 출력 예시로 제공 (숫자가 정렬되어서 출력되는게 정상입니다)
     */
    public void inorder(Node n) {
        if(size() == 0){
            return;
        }
        if(n.getLeft() != null){
            inorder(n.getLeft());
        }
        System.out.print(n.data + " ");
        if(n.getRight() != null){
            inorder(n.getRight());
        }
    }
    
    /*
     *  inorder()
     *    - BST의 모든 데이터를 postorder 방식으로 출력
     *    - 형식은 preorder 참조
     *      예시) 1 2 3 4 5 
     */
    public void postorder(Node n) {
        if(size() == 0){
            return;
        }
        if(n.getLeft() != null){
            postorder(n.getLeft());
        }
        if(n.getRight() != null){
            postorder(n.getRight());
        }
        System.out.print(n.data + " ");
    }
}
