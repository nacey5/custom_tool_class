



private static void test1() {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900,1 ,"male", "New York"));
        personList.add(new Person("Jack", 7000,2, "male", "Washington"));
        personList.add(new Person("Lily", 7800,3, "female", "Washington"));
        personList.add(new Person("Anni", 8200,4, "female", "New York"));
        personList.add(new Person("Owen", 9500, 5,"male", "New York"));
        personList.add(new Person("Alisa", 7900, 6,"female", "New York"));
        List<String> collect = personList.stream().filter(x -> x.getSalary() > 8000).map((person -> person.getName())).collect(Collectors.toList());
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
