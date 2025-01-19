// Module & Provides
@Module
public class MyModule {
    @Provides
    String provideHelloWorld() {
        return "Hello World";
    }
}

// Component, Inject
// Provision method
@Component(modules = MyModule.class)
public interface MyComponent {
    String getString(); // Provision method
}
public class ExampleUnitTest {
    @Test
    public void testProvision() {
        MyComponent myComponent = DaggerMyComponent.create();
        System.out.println("result = " + myComponent.getString()); // result = Hello Wolrd
    }
}
// Member-injection method
@Component(modules = MyModule.class)
public interface MyComponent {
    void inject(MyClass myClass); // Member-injection method
}
public class MyClass {
    @Inject
    String str;

    public String getStr() {
        return str;
    }
}


public class ExampleUnitTest {
    @Test
    public void testMemberInjection() {
        MyClass myClass = new MyClass();

        MyComponent myComponent = DaggerMyComponent.create();
        myComponent.inject(myClass);
        System.out.println("result = " + myClass.getStr()); // result = Hello World
    }
}