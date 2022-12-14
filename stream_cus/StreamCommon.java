public class StreamTest {
        List<String> listForExample = Arrays.asList("a", "b", "c");
        int[] arrayForExample={1,3,5,6,8};
        List<Integer> listForExampleInteger = Arrays.asList(7, 6, 9, 3, 8, 2, 1);
  
        // 创建一个顺序流
        Stream<String> stream = listForExample.stream();
        // 创建一个并行流
        Stream<String> parallelStream = listForExample.parallelStream();
        //使用数组创建流
        IntStream stream1 = Arrays.stream(arrayForExample);
        //使用Stream的静态方法
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4);
        //使用lambda表达式
        Stream<Integer> stream2 = Stream.iterate(0, (x) -> x + 3).limit(4);
        //生成顺序的无序流
        Stream<Double> stream3 = Stream.generate(Math::random).limit(3);
        //除了直接创建并行流，还可以通过parallel()把顺序流转换成并行流
        listForExample.stream().parallel().filter(x-> x.equals("tt")).findFirst();
        
       // 遍历输出符合条件的元素
        listForExampleInteger.stream().filter(x -> x > 6).forEach(System.out::println);
        // 匹配第一个
        Optional<Integer> findFirst = listForExampleInteger.stream().filter(x -> x > 6).findFirst();
        // 匹配任意（适用于并行流）
        Optional<Integer> findAny = listForExampleInteger.parallelStream().filter(x -> x > 6).findAny();
        // 是否包含符合特定条件的元素
        boolean anyMatch = listForExampleInteger.stream().anyMatch(x -> x > 6);
        
}
