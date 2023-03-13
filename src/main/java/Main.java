import classes.*;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        // @BeforeSuite, @AfterSuite и @Test расположены в классах с случайном порядке
        //Class01 - "номальный класс" BeforeSuite - 1 шт, AfterSuite - 1 шт Test - 3 шт
        //Class02 - @BeforeSuite - 0 шт, @AfterSuite - 1 шт @Test - 3 шт
        //Class03 - @BeforeSuite - 1 шт, @AfterSuite - 0 шт @Test - 3 шт
        //Class04 - @BeforeSuite - 0 шт, @AfterSuite - 0 шт @Test - 3 шт
        //Class05 - @BeforeSuite - 2 шт, @AfterSuite - 1 шт @Test - 3 шт
        //Class06 - @BeforeSuite - 1 шт, @AfterSuite - 2 шт @Test - 3 шт
        //Class07 - @BeforeSuite - 2 шт, @AfterSuite - 2 шт @Test - 3 шт

        Class01 class01 = new Class01();
        Class02 class02 = new Class02();
        Class03 class03 = new Class03();
        Class04 class04 = new Class04();
        Class05 class05 = new Class05();
        Class06 class06 = new Class06();
        Class07 class07 = new Class07();

        TestRunner.start(class01);
        TestRunner.start(class02);
        TestRunner.start(class03);
        TestRunner.start(class04);

        // классы с NumberOfAnnotationException
//        TestRunner.start(class05);
//        TestRunner.start(class06);
//        TestRunner.start(class07);
    }
}