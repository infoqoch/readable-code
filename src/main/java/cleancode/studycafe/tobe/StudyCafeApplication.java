package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.io.StudyCafePassesFileSupplier;

public class StudyCafeApplication {

    public static void main(String[] args) {
        StudyCafePassMachineConfig config = new StudyCafePassMachineConfig(
                new InputHandler(),
                new OutputHandler(),
                new StudyCafePassesFileSupplier()
        );
        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(config);
        studyCafePassMachine.run();
    }

}
