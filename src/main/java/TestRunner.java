import annotations.AfterSuite;
import annotations.BeforeSuite;
import exceptions.NumberOfAnnotationException;
import annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TestRunner {

    public static void start(Object obj) throws InvocationTargetException, IllegalAccessException {

        //запись всех методов в массив
        Method[] methodsArray = obj.getClass().getDeclaredMethods();

        int numOfBeforeAnnotation = 0;
        int numOfAfterAnnotation = 0;

        Method beforeMethod = null;
        Method afterMethod = null;

        boolean isDoubledAnnotation = false;

        // список для записи метододов @Test
        List<Method> testMethods = new ArrayList<>();

        // перебор списка с методами и подсчет аннотаций типа BeforeSuite и AfterSuite
        for (Method m : methodsArray) {
            try {
                if (m.isAnnotationPresent(Test.class)) {
                    testMethods.add(m);
                }
                if (m.isAnnotationPresent(BeforeSuite.class)) {
                    numOfBeforeAnnotation++;
                    if (numOfBeforeAnnotation > 1) {
                        throw new NumberOfAnnotationException();
                    }
                    beforeMethod = m;
                }
                if (m.isAnnotationPresent(AfterSuite.class)) {
                    numOfAfterAnnotation++;
                    if (numOfAfterAnnotation > 1) {
                        throw new NumberOfAnnotationException();
                    }
                    afterMethod = m;
                }
            } catch (NumberOfAnnotationException e) {
                e.printStackTrace();
                isDoubledAnnotation = true;
                break;
            }
        }

        // проверка, не было ли двойных аннотаций типа @BeforeAnnotation и @AfterAnnotation
        if (!isDoubledAnnotation) {

            // запуск метода с аннотацией @BeforeSuite
            startBefAndAftMethods(beforeMethod, obj);

            // сортировка методов из списка с аннотацией @Test по параметру в аннотации
            testMethods = sortTestMethods(testMethods);

            // запуск методов с аннотацией @Test
            startTestMethods(testMethods, obj);

            // запуск метода с аннотацией @AfterSuite
            startBefAndAftMethods(afterMethod, obj);

            System.out.println();
        }
    }

    // запуск методов с аннотациями @BeforeSuite и @AfterSuite
    private static void startBefAndAftMethods(Method method, Object obj) throws InvocationTargetException, IllegalAccessException {
        if (method != null){
            method.invoke(obj);
        }
    }

    // сортировка методов из списка с аннотацией @Test по параметру в аннотации
    private static List<Method> sortTestMethods(List<Method> testMethods){
        testMethods.sort(new Comparator<>() {
            @Override
            public int compare(Method m1, Method m2) {
                return m1.getAnnotation(Test.class).numberInQueue() - m2.getAnnotation(Test.class).numberInQueue();
            }
        });
        return testMethods;
    }

    // запуск методов из списка с аннотацией @Test
    private static void startTestMethods(List<Method> testMethods, Object obj) throws InvocationTargetException, IllegalAccessException {
        for (Method m : testMethods) {
            if (m != null) {
                m.invoke(obj);
            }
        }
    }
}
