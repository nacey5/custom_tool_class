/**
 * @author DAHUANG
 * @date 2022/8/16
 */
public class StreamFunction{
    
  //max
    private static void test3() {
        List<String> list = Arrays.asList("adnm", "admmt", "pot", "xbangd", "weoujgsd");
        Optional<String> max = list.stream().max(Comparator.comparing(String::length));
        System.out.println("最长的字符串：" + max.get());
    }
  //自定义排序收集
    private static void test4() {
        List<Integer> list = Arrays.asList(7, 6, 9, 4, 11, 6);
        // 自然排序
        Optional<Integer> max = list.stream().max(Integer::compareTo);
        // 自定义排序
        Optional<Integer> max2 = list.stream().max(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        //自然排序的最大值
        Integer maxOne = max.get();
        //自定义排序的最大值
        Integer maxTwo = max2.get();
    }
    
     private static void test6() {
        String[] strArr = { "abcd", "bcdd", "defde", "fTr" };
        //每个元素大写
        List<String> strList = Arrays.stream(strArr).map(String::toUpperCase).collect(Collectors.toList());

        List<Integer> intList = Arrays.asList(1, 3, 5, 7, 9, 11);
        //每个元素+3
        List<Integer> intListNew = intList.stream().map(x -> x + 3).collect(Collectors.toList());
    }
    
    private static void test8() {
        //处理前的集合
        List<String> list = Arrays.asList("m,k,l,a", "1,3,5,7");
        //通过内部处理得到的处理后的集合
        List<String> listNew = list.stream().flatMap(s -> {
            // 将每个元素转换成一个stream
            String[] split = s.split(",");
            Stream<String> s2 = Arrays.stream(split);
            return s2;
        }).collect(Collectors.toList());

    }
  
    
}

