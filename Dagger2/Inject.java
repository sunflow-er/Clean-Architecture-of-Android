// Module
@Module
public class PersonModule {
    @Provides
    String provideName() {
        return "Charles"
    }

    @Provides
    int provideAge() {
        return 100;
    }
}

// Component
@Component(modules = PersonModule.class)
public interface PersonComponent {
    PersonA getPersonA(); // Provision method

    void inject(PersonB personB); // member-injection method
}

// PersonA
public class PersonA {
    private String name;
    private int age;

    @Inject // 생성자 주입
    public PersonA(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

// PersonB
public class PersonB {
    @Inject // 필드 주입
    String name;

    private int age;

    @Inject // 메서드 주입
    public void setAge(int age) {
        this.age = age;
    }

    public String getNmae() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

public class ExampleUnitTest {
    @Test
    public void testInjection() {
        personComponent personComponent = DaggerPersonComponent.create();

        // personA
        PersonA personA = personComponent.getPersonA();
        System.out.println(personA.getName() + " : " + personA.getAge()); // Charles : 100

        // personB
        PersonB personB = new PersonB();
        personComponent.inject(personB);
        System.out.println(personB.getName() + " : " + personB.getAge()); // Charles : 100
    }
}