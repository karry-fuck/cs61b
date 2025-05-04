package deque;

public class ArrayDeque<T> implements Deque<T>{
    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] deque;

    public ArrayDeque() {
        size = 0;
        deque = (T[]) new Object[8];
        nextFirst = 3;
        nextLast = 4;
    }
@Override
    public void addFirst(T item) {
        if (size == deque.length) {
            resize(deque.length * 2);
        }
        deque[nextFirst] = item;
        nextFirst = (nextFirst - 1 + deque.length) % deque.length;
        size++;
    }
@Override
    public void addLast(T item) {
        if (size == deque.length) {
            resize(deque.length * 2);
        }
        deque[nextLast] = item;
        nextLast = (nextLast + 1) % deque.length;
        size++;
    }
    public int size(){
        return size;
    }


@Override
public void printDeque() {
    if (isEmpty()) {
        System.out.println();
        return;
    }

    StringBuilder sb = new StringBuilder();
    int current = (nextFirst + 1) % deque.length;

    for (int i = 0; i < size; i++) {
        sb.append(deque[current]).append(" ");
        current = (current + 1) % deque.length;
    }

    // 删除最后一个多余的空格并换行
    if (sb.length() > 0) {
        sb.deleteCharAt(sb.length() - 1);
    }
    System.out.println(sb.toString());
}
@Override
    public T removeFirst() {
        if (isEmpty()) return null;
        int firstIndex = (nextFirst + 1) % deque.length;
        T item = deque[firstIndex];
        deque[firstIndex] = null;
        nextFirst = firstIndex;
        size--;
        checkAndShrink();
        return item;
    }
@Override
    public T removeLast() {
        if (isEmpty()) return null;
        int lastIndex = (nextLast - 1 + deque.length) % deque.length;
        T item = deque[lastIndex];
        deque[lastIndex] = null;
        nextLast = lastIndex;
        size--;
        checkAndShrink();
        return item;
    }


        private void checkAndShrink() {
        double usageRatio = (double) size / deque.length;
        // 当容量≥16且使用率<25%时缩容到50%，但最小容量为8
        if (deque.length >= 16 && usageRatio < 0.25) {
            resize(Math.max(deque.length / 2, 8));
        }
    }

    @Override
    public T get(int index){
        if(index<0 ||index>deque.length-1){return null;}
        return deque[index];
    }

    private void resize(int newCapacity) {
        T[] newDeque = (T[]) new Object[newCapacity];
        int current = (nextFirst + 1) % deque.length;
        int numElements = size; // 需要复制的元素总数


        if (current + numElements <= deque.length) {
            // 情况1：元素连续不跨末尾
            System.arraycopy(deque, current, newDeque, 0, numElements);
        } else {
            // 情况2：元素分两段
            int firstPart = deque.length - current;
            System.arraycopy(deque, current, newDeque, 0, firstPart);
            System.arraycopy(deque, 0, newDeque, firstPart, numElements - firstPart);
        }
        nextFirst = newDeque.length - 1;
        nextLast = numElements;
        deque = newDeque;
    }
}