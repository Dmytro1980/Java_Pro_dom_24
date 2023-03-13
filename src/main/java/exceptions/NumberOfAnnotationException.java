package exceptions;

public class NumberOfAnnotationException extends Exception {
    public NumberOfAnnotationException(){
        super("Wrong number of @BeforeSuite or @AfterSuite annotations.");
    }
}
