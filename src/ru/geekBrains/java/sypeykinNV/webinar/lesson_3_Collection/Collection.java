package ru.geekBrains.java.sypeykinNV.webinar.lesson_3_Collection;

import java.util.*;


public class Collection {
    public static class Box implements Comparable<Box>{
        private int width;
        private int height;
        private int marker;

        public Box(int width, int height, int marker) {
            this.width = width;
            this.height = height;
            this.marker = marker;
        }

        public Box(int width, int height ) {
            this.width = width;
            this.height = height;
        }

        @Override
        public String toString() {
            return String.format("Box(%d, %d) %d", width, height, marker);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Box)) return false;
            Box box = (Box) obj;
            return this.width == box.width && this.height == box.height;
        }

        @Override
        public int hashCode() {
            return Objects.hash(width, height);
        }

        private int square(){
            return width * height;
        }
        @Override
        public int compareTo(Box box) {
//            int sq = square();
//            int boxSq = box.square();
//
//            if(sq < boxSq){
//                return  -1;
//            }else if (sq > boxSq){
//                return 1;
//            }else {
//                return 0;
//            }
            return (box == this) ? 0 : square() - box.square();
        }
    }

    public static void main(String[] args) {

    }

    private static void mapExample() {
        Map<String, Integer> map = new HashMap<>();
        map.put("January", 1);
        map.put("February", 2);
        map.put("March", 3);
        map.put("January", 4);
        map.put("May", 5);

        System.out.println(map);
        System.out.println(map.get("January"));

        //как бегать по мапе
        //1#
        for (String s : map.keySet()){
            System.out.println(s + " : " + map.get(s));
        }

        //2#
        Set<Map.Entry<String, Integer>> set = map.entrySet();
        Iterator<Map.Entry<String, Integer>> iter = set.iterator();
        while (iter.hasNext()){
            Map.Entry<String, Integer> entry = iter.next();
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    private static void treeSetExample() {
        TreeSet<Box> tree = new TreeSet<>();


        Box b0 = new Box(1,1 );
        Box b1 = new Box(2,2 );
        Box b2 = new Box(3,3 );
        Box b3 = new Box(4,4 );
        Box b4 = new Box(1,1 );

        tree.addAll(Arrays.asList(b0, b1, b2, b3, b4));
        System.out.println(tree);
    }

    private static void linkedHashSetExample() {
        Set<String> set = new LinkedHashSet<>();
        set.add("January");
        set.add("February");
        set.add("March");
        System.out.println(set);
        set.add("March");
        System.out.println(set);


        Box b0 = new Box(1,1, 1);
        Box b1 = new Box(2,2, 2);
        Box b2 = new Box(3,3, 3);
        Box b3 = new Box(4,4, 4);
        Box b4 = new Box(1,1, 5);

        Set<Box> set1 = new HashSet<>();

        set1.add(b0);
        set1.add(b1);
        set1.add(b2);
        set1.add(b3);
        set1.add(b4);

        System.out.println(set1);
    }

    private static void hashSetExample() {
        HashSet<String> set = new HashSet<>();
        set.add("January");
        set.add("February");
        set.add("March");
        System.out.println(set);
        set.add("March");
        System.out.println(set);


        Box b0 = new Box(1,1, 1);
        Box b1 = new Box(2,2, 2);
        Box b2 = new Box(3,3, 3);
        Box b3 = new Box(4,4, 4);
        Box b4 = new Box(1,1, 5);

        Set<Box> set1 = new HashSet<>();

        set1.add(b0);
        set1.add(b1);
        set1.add(b2);
        set1.add(b3);
        set1.add(b4);

        System.out.println(set1);
    }

    private static void arrayListExamples() {
        ArrayList<Box> list0 = getBoxArrayList();

        LinkedList<String> list = new LinkedList<>();
        list.add("January");
        list.add("February");
        list.add("March");
        System.out.println(list);

        Box b0 = new Box(1,1);
        Box b1 = new Box(2,2);
        Box b2 = new Box(3,3);
        Box b3 = new Box(4,4);
        Box b4 = new Box(1,1);

        ArrayList<Box> list1 = new ArrayList<>();

        list1.add(b0);
        list1.add(b1);
        list1.add(b2);
        list1.add(b3);

        System.out.println(list1);

        runOnStrings(list1);
    }

    private static ArrayList<Box> getBoxArrayList() {
        ArrayList<String> list = new ArrayList<>();
        list.add("January");
        list.add("February");
        list.add("March");
        System.out.println(list);

        Box b0 = new Box(1,1);
        Box b1 = new Box(2,2);
        Box b2 = new Box(3,3);
        Box b3 = new Box(4,4);
        Box b4 = new Box(1,1);

        ArrayList<Box> list1 = new ArrayList<>();

        list1.add(b0);
        list1.add(b1);
        list1.add(b2);
        list1.add(b3);

        System.out.println(list1);
        return list1;
    }

    private static void runOnStrings(ArrayList<Box> list1) {
        //Как бегать по строкам
        //#1
        for (int i = 0; i < list1.size(); i++) {
            System.out.print(list1.get(i));
        }
        System.out.println();

        //#2 \/\/\/\/
        for ( Box b : list1){
            System.out.print(b + " ");
        }
        System.out.println();

        //#3  for each работает вот так
        Iterator<Box> iter = list1.iterator();
        while (iter.hasNext()){
            System.out.print(iter.next() + " ");
        }
        System.out.println();
    }
}
