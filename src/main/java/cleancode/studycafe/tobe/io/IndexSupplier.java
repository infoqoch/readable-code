package cleancode.studycafe.tobe.io;

public class IndexSupplier {
    private static final int OUTPUT_ADD = 1;
    private static final int INPUT_ADD = -1;

    public static int getOutputIndex(int input) {
        return input + OUTPUT_ADD;
    }

    public static int getInputIndex(int input) {
        return input + INPUT_ADD;
    }
}
