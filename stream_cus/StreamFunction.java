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
  
}

