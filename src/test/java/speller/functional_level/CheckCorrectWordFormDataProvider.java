package speller.functional_level;

import dto.YandexSpellerDto;
import enums.Languages;
import enums.Options;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import service.YandexSpellerAssertions;
import service.YandexSpellerSteps;

import java.util.Arrays;

public class CheckCorrectWordFormDataProvider {
    //-----------------------------------Data Providers

    @DataProvider
    public Object[][] enWordsData() {
        return new Object[][]{
                {"4858","ovocado 2", "avocado"},
                {"145","tamato", "tomato"},
                {"6869","cacamber", "cucumber"}
        };
    }

    @Test(description = "Check correction of word", dataProvider = "enWordsData")
    void checkCorrectEnWord(String testId, String actualWord, String expectedWord) {

        /*YandexSpellerDto[] responseContents = new YandexSpellerSteps()
                .checkWord(actualWord);*/
        YandexSpellerDto[][] responseContents = new YandexSpellerSteps()
                .checkTextWithParams(
                        actualWord, Arrays.asList(
                                Languages.EN
                        ),
                        Arrays.asList(
                                Options.IGNORE_DIGITS,
                                Options.IGNORE_URLS
                        )
                );
        new YandexSpellerAssertions()
                .verifyCorrectText(responseContents,expectedWord);
    }


    @DataProvider
    public Object[][] ruWordsData() {
        return new Object[][]{
                {"123","вуниль 2", "ваниль"},
                {"145","периц", "перец"},
                {"999","карица", "корица"}
        };
    }

    @Test(description = "Check correction of word", dataProvider = "ruWordsData")
    void checkCorrectRuWord(String testId, String actualWord, String expectedWord) {

        YandexSpellerDto[][] responseContents = new YandexSpellerSteps()
                .checkTextWithParams(
                        actualWord, Arrays.asList(
                                Languages.RU
                        ),
                        Arrays.asList(
                                Options.IGNORE_DIGITS,
                                Options.IGNORE_URLS
                        )
                );
        new YandexSpellerAssertions()
                .verifyCorrectText(responseContents,expectedWord);
    }
}
