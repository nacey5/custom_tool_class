/**
 * 使用stream流通过filter生成符合条件的列表
 * @author DAHUANG
 * @date 2022/8/16
 */
private static void test1() {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900,1 ,"male", "New York"));
        personList.add(new Person("Jack", 7000,2, "male", "Washington"));
        personList.add(new Person("Lily", 7800,3, "female", "Washington"));
        personList.add(new Person("Anni", 8200,4, "female", "New York"));
        personList.add(new Person("Owen", 9500, 5,"male", "New York"));
        personList.add(new Person("Alisa", 7900, 6,"female", "New York"));
        List<String> collect = personList.stream().filter(x -> x.getSalary() > 8000).map((person -> person.getName())).collect(Collectors.toList());
        //获取员工最大值
        Optional<Person> max = personList.stream().max(Comparator.comparingInt(Person::getSalary));
        Integer maxSalary=max.get().getSalary());
    }

 private static void test6() {
        String[] strArr = { "abcd", "bcdd", "defde", "fTr" };
        //每个元素大写
        List<String> strList = Arrays.stream(strArr).map(String::toUpperCase).collect(Collectors.toList());

        List<Integer> intList = Arrays.asList(1, 3, 5, 7, 9, 11);
        //每个元素+3
        List<Integer> intListNew = intList.stream().map(x -> x + 3).collect(Collectors.toList());

        System.out.println("每个元素大写：" + strList);
        System.out.println("每个元素+3：" + intListNew);
    }

/**
* additional class
*/
@Data
@Getter
@Setter
class Person {
    private String name;  // 姓名
    private int salary; // 薪资
    private int age; // 年龄
    private String sex; //性别
    private String area;  // 地区

    // 构造方法
    public Person(String name, int salary, int age,String sex,String area) {
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.sex = sex;
        this.area = area;
    }

}
