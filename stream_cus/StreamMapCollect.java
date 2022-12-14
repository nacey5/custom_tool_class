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
        //不改变原来集合的方式进行数据修改
        List<Person> personListNew = personList.stream().map(person -> {
            Person personNew = new Person(person.getName(), 0, 0, null, null);
            personNew.setSalary(person.getSalary() + 10000);
            return personNew;
        }).collect(Collectors.toList());
        //改变原来集合的方式进行数据修改
        List<Person> personListNew2 = personList.stream().map(person -> {
            person.setSalary(person.getSalary() + 10000);
            return person;
        }).collect(Collectors.toList());
         // 求工资之和方式1：
        Optional<Integer> sumSalary = personList.stream().map(Person::getSalary).reduce(Integer::sum);
        // 求工资之和方式2：
        Integer sumSalary2 = personList.stream().reduce(0, (sum, p) -> sum += p.getSalary(),
                (sum1, sum2) -> sum1 + sum2);
        // 求工资之和方式3：
        Integer sumSalary3 = personList.stream().reduce(0, (sum, p) -> sum += p.getSalary(), Integer::sum);

        // 求最高工资方式1：
        Integer maxSalary = personList.stream().reduce(0, (max, p) -> max > p.getSalary() ? max : p.getSalary(),
                Integer::max);
        // 求最高工资方式2：
        Integer maxSalary2 = personList.stream().reduce(0, (max, p) -> max > p.getSalary() ? max : p.getSalary(),
                (max1, max2) -> max1 > max2 ? max1 : max2);
        //转成map
        Map<?, Person> map = personList.stream().filter(p -> p.getSalary() > 8000)
                .collect(Collectors.toMap(Person::getName, p -> p));
        
        // 求总数
	Long count = personList.stream().collect(Collectors.counting());
	// 求平均工资
	Double average = personList.stream().collect(Collectors.averagingDouble(Person::getSalary));
	// 求最高工资
	Optional<Integer> max = personList.stream().map(Person::getSalary).collect(Collectors.maxBy(Integer::compare));
	// 求工资之和
	Integer sum = personList.stream().collect(Collectors.summingInt(Person::getSalary));
	// 一次性统计所有信息
	DoubleSummaryStatistics collect = personList.stream().collect(Collectors.summarizingDouble(Person::getSalary));
	// 将员工按薪资是否高于8000分组
        Map<Boolean, List<Person>> part = personList.stream().collect(Collectors.partitioningBy(x -> x.getSalary() > 8000));
        // 将员工按性别分组
        Map<String, List<Person>> group = personList.stream().collect(Collectors.groupingBy(Person::getSex));
        // 将员工先按性别分组，再按地区分组
        Map<String, Map<String, List<Person>>> group2 = personList.stream().collect(Collectors.groupingBy(Person::getSex, Collectors.groupingBy(Person::getArea)));
	
	// 按工资升序排序（自然排序）
	List<String> newList = personList.stream().sorted(Comparator.comparing(Person::getSalary)).map(Person::getName)
			.collect(Collectors.toList());
	// 按工资倒序排序
	List<String> newList2 = personList.stream().sorted(Comparator.comparing(Person::getSalary).reversed())
			.map(Person::getName).collect(Collectors.toList());
	// 先按工资再按年龄升序排序
	List<String> newList3 = personList.stream()
			.sorted(Comparator.comparing(Person::getSalary).thenComparing(Person::getAge)).map(Person::getName)
			.collect(Collectors.toList());
	// 先按工资再按年龄自定义排序（降序）
	List<String> newList4 = personList.stream().sorted((p1, p2) -> {
		if (p1.getSalary() == p2.getSalary()) {
			return p2.getAge() - p1.getAge();
		} else {
			return p2.getSalary() - p1.getSalary();
		}
	}).map(Person::getName).collect(Collectors.toList());

	System.out.println("按工资升序排序：" + newList);
	System.out.println("按工资降序排序：" + newList2);
	System.out.println("先按工资再按年龄升序排序：" + newList3);
	System.out.println("先按工资再按年龄自定义降序排序：" + newList4);

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
