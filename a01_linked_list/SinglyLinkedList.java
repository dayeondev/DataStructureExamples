package a01_linked_list;

public class SinglyLinkedList {
	
	/*
	 * [Class] Node
	 *   1) 개요
	 *     - SinglyLinkedList의 data를 담는 노드
	 *     - 각 노드는 다음 노드를 가리키고 있어야 함
	 *     - data type은 정수라고 가정
	 *   2) 필수 operation
	 *     - 생성자
	 *     - getData(): 현재 노드가 가지고 있는 data를 반환하는 함수
	 *     - getNext(): 현재 노드가 가리키고 있는 Node를 반환하는 함수
	 *     - setNext(): 현재 노드의 가리키고 있는 Node를 설정하는 함수
	 */
	
	private static class Node {
		private int data;	// Node에서 저장하는 data
		private Node next;	// Node가 가리키고 있는 다음 노드
		
		/* [필수] 생성자 */
		public Node(int d) {
			// 구현
			data = d; // data 저장
		}
		
		/* [필수] getData() */
		public int getData() {
			// 구현
			return data; // data 반환
		}
		
		/* [필수] getNext() */
		public Node getNext() {
			// 구현
			return next; // 다음 node 반환
		}
		
		/* [필수] setNext() */
		public void setNext(Node n) {
			// 구현
			next = n; // 다음 node 설정
		}
	}
	
	/*
	 * [Class] SinglyLinkedList
	 *   1) 개요
	 *     - LinkedList의 첫 번째 노드를 가리키는 head와 마지막 노드를 가리키는 tail이 존재
	 *       -> head/tail을 dummy 노드로 만들어도 좋고, 데이터를 가진 노드로 만들어도 좋음
	 *     - data를 처음/마지막에 추가, 삭제하는 함수를 만들면 됨
	 *   2) 필수 operation
	 *     - getFirst(): linked list의 첫 번째 노드의 data를 반환하는 함수
	 *     - getLast(): linked list의 마지막 노드의 data를 반환하는 함수
	 *     - addFirst(): linked list의 맨 앞에 데이터를 추가
	 *     - addLast(): linked list의 맨 뒤에 데이터를 추가
	 *   3) 추가 operation
	 *     - removeFirst(): linked list의 맨 앞의 노드를 삭제
	 *     - printList(): linked list의 모든 데이터를 출력
	 */
	
	private Node head;	// data의 첫 부분을 가리키는 노드
	private Node tail;	// data의 마지막 부분을 가리키는 노드
	private int size;	// 리스트의 크기를 출력하는 노드 (필요시 사용)
	
	/* [필수] 생성자 */
	public SinglyLinkedList() {
		// 구현
		head = new Node(-1); // dummy
		tail = new Node(-1); // dummy
		head.setNext(tail); // 처음에는 아무것도 없으니 head 다음이 tail.
		size = 0; // 처음에는 아무것도 없으니 size를 0으로 초기화
	}
	
	/* 필요시 구현 */
	public int size() {
		// 구현
		return size; // 사이즈 반환
	}
	
	/* 필요시 구현 */
	public boolean isEmpty() {
		// 구현
		return (size == 0); // check the list is Empty
	}
	
	/* 
	 * [필수] getFirst()
	 *   - 노드가 아니라 데이터를 반환해야 함
	 *   - 만약 없을 경우 -1 리턴
	 */
	public int getFirst() {
		// 구현
		if(isEmpty()){
			return -1; // Empty 처리
		}
		else{
			Node firstNode =  head.getNext(); // 첫 노드는 head의 다음 노드
			return firstNode.getData(); // 첫 노드의 data 반환
		}
	}
	
	/* [필수] getLast() */
	public int getLast() {
		// 구현
		if(isEmpty()){
			return -1; // Empty 처리
		}
		else{
			Node lastNode = head.getNext(); // 끝 노드를 찾기 위한 객체에 firstNode 인스턴스로 초기화
			for(int i = 1; i < size; i++){ // 윗줄에서 i가 0일 때 할 일을 해주었으므로 1부터 시작 i가 size가 될 때 까지 반복
				lastNode = lastNode.getNext(); // 끝이 올 때 까지 다음 노드를 조회 
			}
			return lastNode.data; // 끝 노드의 data 반환
		}
	}
	
	/* 
	 * [필수] addFirst()
	 *   - 데이터를 입력받아서 추가
	 */
	public void addFirst(int data) {
		// 구현
		Node newNode = new Node(data); // 삽입할 노드 인스턴스 생성
		if(isEmpty()){ // 만약 비어있는 리스트라면
			newNode.setNext(tail); // 새 노드의 다음을 tail로 이어주고
			head.setNext(newNode); // 헤드의 다음을 새 노드로 연결
		}
		else{ 
			newNode.setNext(head.getNext()); // 새 노드의 다음을 기존의 first node로 설정
			head.setNext(newNode); // head의 다음을 새 노드로 연결
		}
		size++; // 노드가 추가됨에 따라 size++
		return;
	}
	
	/* [필수] addLast() */
	public void addLast(int data) {
		// 구현
		Node newNode = new Node(data); // 삽입할 노드 인스턴스 생성
		if(isEmpty()){ // 만약 비어있는 리스트라면
			addFirst(data); // 중복되는 기능이므로 addFirst에서 처리
			return; // 여기서 리턴하지 않으면 size++이 두 번 된다..
		}
		else{
			Node lastNode = head.getNext(); // 끝 노드를 찾기 위해 객체에 firstNode 인스턴스로 초기화
			for(int i = 1; i < size; i++){ // 윗줄에서 i가 0일 때 할 일을 해주었으므로 1부터 시작 i가 size가 될 때 까지 반복
				lastNode = lastNode.getNext(); // 끝이 올 때 까지 다음 노드를 조회
			}
			newNode.setNext(tail); // 새 노드의 다음 노드를 tail로 설정
			lastNode.setNext(newNode); // 탐색한 lastNode의 다음 노드를 새 노드로 설정
			size++; // 노드가 추가됨에 따라 size++
			return;
		}

	}
	
	/*
	 *  removeFirst()
	 *    - 첫 번째 노드를 삭제하고 data를 반환
	 *    - 만약 없을 경우 -1 리턴
	 */
	public int removeFirst() {
		// 구현
		if(isEmpty()){
			return -1;
		}
		else{
			int result = head.next.getData();
			Node tmpNode = head.getNext().getNext(); // 두번째 노드 인스턴스를 임시 저장
			head.getNext().setNext(null); // 포인터 끊
			head.setNext(tmpNode); // head의 다음 노드를 두번째 노드로 변경
			size--; // 노드 하나가 삭제되었으므로 size--;
			return result; // 삭제하기 전의 첫번째 노드의 data 반환
		}
	}
	
	/*
	 * printList() : 재귀함수나 반복문으로 구현
	 *   - 형식은 다음과 같이 구현 (마지막 개행 포함)
	 *   > 1 2 3 4 5 
	 */
	public void printList() {
		// 구현
		Node tmpNode = head.getNext();
		for(int i = 0; i < size; i++){
			System.out.print("" + tmpNode.getData() + " ");
			tmpNode = tmpNode.getNext();
		}
		System.out.println();
	}
}
