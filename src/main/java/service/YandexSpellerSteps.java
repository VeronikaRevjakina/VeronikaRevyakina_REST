package service;

import com.google.gson.Gson;
import dto.YandexSpellerDto;
import enums.Languages;
import enums.Options;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class YandexSpellerSteps {

    public YandexSpellerDto[] checkWord(String word) {
        return new Gson().fromJson(
                        new YandexSpellerService()
                                .checkWord(YandexSpellerConstants.CHECK_TEXT_URI,word)
                                .getBody().asString(), YandexSpellerDto[].class);
    }

    public YandexSpellerDto[][] checkTextWithParams(String text,
                                                    List<Languages> languages,
                                                    List<Options> options) {
        HashMap<String, Object> params = new HashMap<>();

        params.put(YandexSpellerConstants.PARAM_TEXTS, text);

        modifyParamLanguages(languages, params);

        modifyParamOptions(options, params);

        return new Gson().fromJson(
                new YandexSpellerService()
                        .checkTextWithParams(YandexSpellerConstants.CHECK_TEXTS_URI,
                                params)
                        .getBody().asString(), YandexSpellerDto[][].class);
    }

    private void modifyParamLanguages(List<Languages> languages,
                                      HashMap<String, Object> params) {
        List<String> languagesList = new ArrayList<>();
        for (Languages language : languages) {
            languagesList.add(language.getLang());
        }
        String newLanguageList = String.join(", ", languagesList);
        params.put(YandexSpellerConstants.PARAM_LANGUAGES,newLanguageList);
    }

    private void modifyParamOptions(List<Options> options,
                                    HashMap<String, Object> params) {
        int sum=0;
        for(Options option:options){
            sum+=option.getNumber();
        }
        params.put(YandexSpellerConstants.PARAM_OPTIONS,Integer.toString(sum));
    }
}
