package classes;

import annotations.BeforeSuite;
import annotations.Test;

public class Class03 {

    @Test(numberInQueue = 2)
    public void met02() {
        int numberInQueue = 2; // переменная оставлена для проверки работы программы
        System.out.println("print from Class01.met02() @Test numberInQueue = " + numberInQueue);
    }

    @BeforeSuite
    public void met01() {
        System.out.println("print from Class01.met01() @BeforeSuite");
    }

    @Test(numberInQueue = 3)
    public void met04() {
        int numberInQueue = 3; // переменная оставлена для проверки работы программы
        System.out.println("print from Class01.met04() @Test numberInQueue = " + numberInQueue);
    }

    @Test(numberInQueue = 1)
    public void met07() {
        int numberInQueue = 1; // переменная оставлена для проверки работы программы
        System.out.println("print from Class01.met07() @Test numberInQueue = " + numberInQueue);
    }
}
