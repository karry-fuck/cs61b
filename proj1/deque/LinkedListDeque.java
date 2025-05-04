package deque;

public class LinkedListDeque<T> implements Deque<T>{
    private int size;
    private Node<T> sentinel ;
    LinkedListDeque(){
        size=0;
        sentinel =new Node<>();
        sentinel.setSentinel();
    }

    private  static class  Node<T>{
        private Node<T> pre;
        private T item;
        private Node<T> next;
        Node(){
            pre=null;
            next=null;
        }
        Node(Node<T> pre, T item ,Node<T> next){
            this.pre=pre;
            this.item=item;
            this.next=next;
        }
        public  void clean(){
            this.pre=this.next=null;
        }
        public  void  setSentinel(){
            this.pre=this.next=this;
        }
    }
@Override
    public void addFirst(T item){
        Node<T> temp=new Node<>(sentinel,item,sentinel.next);
        sentinel.next.pre=temp;
        sentinel.next=temp;
        size ++;
}
@Override
public void addLast(T item){
        Node<T> temp=new Node<>(sentinel.pre,item,sentinel);
        sentinel.pre.next=temp;
        sentinel.pre=temp;
        size ++;
}
@Override
public int size(){
        return size;
}

@Override
public void printDeque(){
        if (size==0) return;
        Node<T> temp =sentinel.next;
        for(int i =1;i<=size;i++){
            System.out.print(temp.item +" ");
            temp=temp.next;
        }
        System.out.println( );
}
@Override
public T removeFirst(){
         if(isEmpty())  return  null;
        Node<T> first =sentinel.next;
        first.next.pre=sentinel;
        sentinel.next = first.next;
        size --;
        first.clean();
        return first.item;
}
@Override
public T removeLast(){
        if(isEmpty()) return  null;
        Node<T> last =sentinel.pre;
        last.pre.next=sentinel;
        sentinel.pre=last.pre;
        size --;
        last.clean();
        return last.item;
}
@Override
public T get(int index){
        if (index < 0 || index >= size || isEmpty()) {
        return null;
    }
        Node<T> temp =sentinel.next;
        int times =0;
        while(times<index){
            temp=temp.next;
            times ++;
        }
        return temp.item;
}


public T getRecursive(int index) {
    // 边界检查：索引无效或链表为空时返回null
    if (index < 0 || index >= size || isEmpty()) {
        return null;
    }
    // 调用辅助方法，从哨兵的下一个节点（第一个有效节点）开始递归
    return getRecursiveHelper(sentinel.next, index);
}

// 辅助递归方法：传入当前节点和剩余索引值
private T getRecursiveHelper(Node<T> currentNode, int remainingIndex) {
    // 终止条件1：遍历到哨兵节点，说明索引越界
    if (currentNode == sentinel) {
        return null;
    }
    // 终止条件2：剩余索引为0，返回当前节点的元素
    if (remainingIndex == 0) {
        return currentNode.item;
    }
    // 递归步骤：移动到下一个节点，剩余索引减1
    return getRecursiveHelper(currentNode.next, remainingIndex - 1);
}

}
