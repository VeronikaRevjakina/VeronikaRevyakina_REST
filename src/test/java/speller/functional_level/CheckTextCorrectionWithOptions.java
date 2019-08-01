package speller.functional_level;

import dto.YandexSpellerDto;
import enums.Languages;
import enums.Options;
import org.testng.annotations.Test;
import service.YandexSpellerSteps;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckTextCorrectionWithOptions {
    @Test(description = "Check text with different options parameters")
    void checkTextWithOptionsIgnoreURLParameters() {
        String wordToCheck = "https://vk.com";

        YandexSpellerDto[][] responseContents = new YandexSpellerSteps()
                .checkTextWithParams(
                        wordToCheck, Arrays.asList(
                                Languages.EN,
                                Languages.RU
                        ),
                        Arrays.asList(
                                Options.IGNORE_URLS
                        )
                );

        assertThat(responseContents[0]).hasSize(0);
    }

    @Test(description = "Check text with different options parameters")
    void checkTextWithOptionsIgnoreCapitalLetters() {
        String wordToCheck = "wOrD";

        YandexSpellerDto[][] responseContents = new YandexSpellerSteps()
                .checkTextWithParams(
                        wordToCheck, Arrays.asList(
                                Languages.EN,
                                Languages.RU
                        ),
                        Arrays.asList(
                                Options.IGNORE_CAPITALIZATION
                        )
                );

        assertThat(responseContents[0]).hasSize(0);
    }

    @Test(description = "Check text with different options parameters")
    void checkTextWithOptionsFindRepeatWordsParameters() {
        String wordToCheck = "homehomehome";

        YandexSpellerDto[][] responseContents = new YandexSpellerSteps()
                .checkTextWithParams(
                        wordToCheck, Arrays.asList(
                                Languages.EN,
                                Languages.RU
                        ),
                        Arrays.asList(
                                Options.FIND_REPEAT_WORDS
                        )
                );

        assertThat(responseContents[0]).hasSize(1);
        assertThat(responseContents[0][0].getS().get(0)).contains("home");
    }

    @Test(description = "Check text with different options parameters")
    void checkTextWithOptionsParametersAtSameTime() {
        String wordToCheck = "thethe+12345+https://vk.com";

        YandexSpellerDto[][] responseContents = new YandexSpellerSteps()
                .checkTextWithParams(
                        wordToCheck, Arrays.asList(
                                Languages.EN,
                                Languages.RU
                        ),
                        Arrays.asList(
                                Options.IGNORE_DIGITS,
                                Options.IGNORE_URLS,
                                Options.FIND_REPEAT_WORDS
                        )
                );

        assertThat(responseContents[0]).hasSize(1);
        assertThat(responseContents[0][0].getS().get(0)).isEqualTo("the");
    }
}
