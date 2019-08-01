package service;

import dto.YandexSpellerDto;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class YandexSpellerAssertions {

    public void verifyCorrectWord(YandexSpellerDto[] responseContents,
                                  String expectedWord) {
        assertTrue(responseContents[0].getS().get(0).contains(expectedWord));
    }

    public void verifyWordAnswerSize(YandexSpellerDto[] responseContents,
                                     int expectedSize) {
        assertEquals(responseContents[0].getS().size(), expectedSize);
    }

    public void verifyCorrectText(YandexSpellerDto[][] responseContents,
                                  String expectedWord) {
        assertTrue(responseContents[0][0].getS().get(0).contains(expectedWord));
    }

    public void verifyTextAnswerSize(YandexSpellerDto[][] responseContents,
                                     int expectedSize) {
        assertEquals(responseContents[0].length, expectedSize);
    }

    public void verifyErrorCode(YandexSpellerDto[] responseContents,
                                int errorCode) {
        assertEquals(responseContents[0].getCode().intValue(), errorCode);
    }
}
