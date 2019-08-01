package speller.functional_level;

import dto.YandexSpellerDto;
import org.testng.annotations.Test;
import service.YandexSpellerAssertions;
import service.YandexSpellerSteps;


public class CheckWordCorrection {

    @Test(description = "Check correction of word")
    void checkWordCorrectionAndSize() {
        String wordToCheck = "rainduw";
        String correctForm = "rainbow";

        YandexSpellerDto[] responseContents = new YandexSpellerSteps()
                .checkWord(wordToCheck);

        new YandexSpellerAssertions()
                .verifyWordAnswerSize(responseContents, 3);
        new YandexSpellerAssertions()
                .verifyErrorCode(responseContents, 1);
        new YandexSpellerAssertions()
                .verifyCorrectWord(responseContents, correctForm);
    }

    @Test(description = "Check correction of word")
    void checkMixedTranslationCorrection() {
        String wordToCheck = "ьфгыу ";
        String correctForm = "mouse";

        YandexSpellerDto[] responseContents = new YandexSpellerSteps()
                .checkWord(wordToCheck);
        new YandexSpellerAssertions()
                .verifyErrorCode(responseContents, 1);
        new YandexSpellerAssertions()
                .verifyCorrectWord(responseContents, correctForm);

    }
}