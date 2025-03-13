package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;
import cleancode.studycafe.tobe.model.StudyCafePassesSupplier;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudyCafePassesFileSupplier implements StudyCafePassesSupplier {
    public final List<StudyCafeLockerPass> STUDY_CAFE_LOCKER_PASSES_BY_FILE = getStudyCafeLockerPassesByFile();
    public final List<StudyCafePass> STUDY_CAFE_PASSES_BY_FILE = getStudyCafePassesByFile();

    @Override
    public List<StudyCafePass> getStudyCafePasses(StudyCafePassType studyCafePassType) {
        return STUDY_CAFE_PASSES_BY_FILE.stream()
                .filter(studyCafePass -> studyCafePass.isTypeOf(studyCafePassType))
                .toList();
    }

    @Override
    public Optional<StudyCafeLockerPass> getLockerPass(StudyCafePass selectedPass) {
        return STUDY_CAFE_LOCKER_PASSES_BY_FILE.stream()
                .filter(option ->
                        option.isTypeOf(selectedPass.getPassType())
                                && option.isTheSameDurationOf(selectedPass.getDuration()) // 두 객체 비교를 위한 별도의 로직이 없을까?
                )
                .findFirst();
    }

    private static List<StudyCafePass> getStudyCafePassesByFile() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/resources/cleancode/studycafe/pass-list.csv"));
            List<StudyCafePass> studyCafePasses = new ArrayList<>();
            for (String line : lines) {
                String[] values = line.split(",");
                StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[0]);
                int duration = Integer.parseInt(values[1]);
                int price = Integer.parseInt(values[2]);
                double discountRate = Double.parseDouble(values[3]);

                StudyCafePass studyCafePass = StudyCafePass.of(studyCafePassType, duration, price, discountRate);
                studyCafePasses.add(studyCafePass);
            }

            return studyCafePasses;
        } catch (IOException e) {
            throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
        }
    }

    private static List<StudyCafeLockerPass> getStudyCafeLockerPassesByFile() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/resources/cleancode/studycafe/locker.csv"));
            List<StudyCafeLockerPass> lockerPasses = new ArrayList<>();
            for (String line : lines) {
                String[] values = line.split(",");
                StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[0]);
                int duration = Integer.parseInt(values[1]);
                int price = Integer.parseInt(values[2]);

                StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(studyCafePassType, duration, price);
                lockerPasses.add(lockerPass);
            }

            return lockerPasses;
        } catch (IOException e) {
            throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
        }
    }

}
