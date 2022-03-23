//import java.util.Scanner;
//
//// @author: Touhid
//public class cmsc4011 {
//    //private static final Scanner scanner = new Scanner(System.in);
//
//   // private static Integer readInt() {
//     //   return scanner.nextInt();
//    //}
//
////    public static void main(String[] args) {
////        // int size = readInt();
////        // MinHeap myMinHeap = new MinHeap(size);
////
////       /* int actionCode;
////        do {
////            actionCode = readInt();
////            if (actionCode == 1) {
////                int x = readInt();
////                int y = readInt();
////                myMinHeap.insert(x, y, cost);
////            } else if (actionCode == 2) {
////                int x = readInt();
////                int z = readInt();
////                myMinHeap.decreaseKey(x, z);
////            } else if (actionCode == 3) {
////                HeapItem minItem = myMinHeap.extractMin();
////                System.out.printf("%d %d\n", minItem.getId(), minItem.getPriority());
////            }
////        } while (actionCode != 4);*/
////    }
//
//    private static class MinHeap {
//        private final int[] idToPositionMap;
//        private int lastId = 0;
//        private final HeapItem[] myMinHeapArray;
//        private int currentSize;
//
//        private MinHeap(int size) {
//            this.idToPositionMap = new int[size];
//            this.myMinHeapArray = new HeapItem[size];
//            this.currentSize = 0;
//        }
//
//        public boolean isEmpty() {
//            return currentSize <= 0;
//        }
//
//        public void insert(int source, int destination, int priority) {
//            myMinHeapArray[currentSize] = new HeapItem(source, destination, priority);
//            idToPositionMap[lastId] = currentSize++;
//            decreaseKey(lastId++, priority);
//        }
//
//        public void decreaseKey(int id, int newPriority) {
//            int i = idToPositionMap[id];
//            myMinHeapArray[i].setPriority(newPriority);
//
//            int parentI = parent(i);
//            while (i > 0 && myMinHeapArray[parentI].getPriority() > myMinHeapArray[i].getPriority()) {
//                swap(i, parentI);
//                i = parentI;
//                parentI = parent(i);
//            }
//        }
//
//        public HeapItem extractMin() {
//            HeapItem minItem = myMinHeapArray[0];
//
//            myMinHeapArray[0] = myMinHeapArray[--currentSize];
//            minHeapify(0); // to restore heap property for the recently updated root element
//
//            return minItem;
//        }
//
//        private int parent(int childPosition) {
//            return (int) Math.floor((childPosition - 1) / 2.0);
//        }
//
//        private int left(int parentPosition) {
//            // For 1-indexed array implementation, the formula would be (2 * parentPosition).
//            // But, here +1 is needed as we're using 0-indexed array system.
//            return 2 * parentPosition + 1;
//        }
//
//        private int right(int parentPosition) {
//            // For 1-indexed array implementation, the formula would be (2 * parentPosition + 1).
//            // But, here +2 is needed as we're using 0-indexed array system.
//            return 2 * parentPosition + 2;
//        }
//
//        private void minHeapify(int i) {
//            int left = left(i);
//            int right = right(i);
//
//            // We're to identify min. item among the i'th element & its 2 children (left & right).
//            // Firstly, we're looking at left:
//            int minPos = (left <= currentSize && myMinHeapArray[left].getPriority() < myMinHeapArray[i].getPriority()) ?
//                    left : i;
//            // Then, we're looking at right:
//            if (right <= currentSize && myMinHeapArray[right].getPriority() < myMinHeapArray[minPos].getPriority())
//                minPos = right;
//
//            // If any of the left or right child has lower priority than the i'th element, we're to swap those two &
//            // then we should min-heapify the lower subtree.
//            if (minPos != i) {
//                swap(i, minPos);
//                minHeapify(minPos);
//            }
//        }
//
//        // TODO : Provide source & dest. for node-1 & node-2
//        private void swap(int pos1, int pos2) {
//            HeapItem node1 = myMinHeapArray[pos1];
//            HeapItem node2 = myMinHeapArray[pos2];
//
//            myMinHeapArray[pos1] = node2;
//            myMinHeapArray[pos2] = node1;
//            idToPositionMap[node1.getSource()] = node2.getSource();
//            idToPositionMap[node2.getSource()] = node1.getSource();
//
//            idToPositionMap[node1.getDest()] = node2.getDest();
//            idToPositionMap[node2.getDest()] = node1.getDest();
//        }
//    }
//
//    private static class HeapItem {
//        private int id, source, dest, priority;
//
//        public HeapItem(int source, int dest, int priority) {
//            this.priority = priority;
//            this.source = source;
//            this.dest = dest;
//        }
//
//        public int getPriority() {
//            return priority;
//        }
//
//        public void setPriority(int priority) {
//            this.priority = priority;
//        }
//
//
//        public int getSource() {
//            return source;
//        }
//
//        public void setSource(int source) {
//            this.source = source;
//        }
//
//        public int getDest() {
//            return dest;
//        }
//
//        public void setDest(int dest) {
//            this.dest = dest;
//        }
//    }
//}