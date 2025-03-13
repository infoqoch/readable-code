package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.io.StudyCafePassesFileSupplier;
import cleancode.studycafe.tobe.model.StudyCafePassesSupplier;

public class StudyCafePassMachineConfig {
    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final StudyCafePassesSupplier studyCafePassesSupplier;

    public StudyCafePassMachineConfig(InputHandler inputHandler, OutputHandler outputHandler, StudyCafePassesFileSupplier studyCafePassesFileSupplier) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
        studyCafePassesSupplier = studyCafePassesFileSupplier;
    }

    public InputHandler getInputHandler() {
        return inputHandler;
    }

    public OutputHandler getOutputHandler() {
        return outputHandler;
    }

    public StudyCafePassesSupplier getStudyCafePassesSupplier() {
        return studyCafePassesSupplier;
    }
}
