package com.example.moviesapp.enums;

public enum Language {
    ENGLISH("en"),
    SPANISH("es"),
    FRENCH("fr"),
    GERMAN("de"),
    ITALIAN("it"),
    PORTUGUESE("pt"),
    DUTCH("nl"),
    SWEDISH("sv"),
    DANISH("da"),
    NORWEGIAN("no"),
    FINNISH("fi"),
    POLISH("pl"),
    CZECH("cs"),
    SLOVAK("sk"),
    RUSSIAN("ru"),
    UKRAINIAN("uk"),
    GREEK("el"),
    TURKISH("tr"),
    ARABIC("ar"),
    HEBREW("he"),
    HINDI("hi"),
    BENGALI("bn"),
    JAPANESE("ja"),
    CHINESE("zh"),
    KOREAN("ko"),
    THAI("th"),
    VIETNAMESE("vi"),
    INDONESIAN("id"),
    MALAY("ms"),
    TAGALOG("tl"),
    SWAHILI("sw"),
    CATALAN("ca"),
    CROATIAN("hr"),
    SERBIAN("sr"),
    ROMANIAN("ro"),
    BULGARIAN("bg"),
    HUNGARIAN("hu"),
    SLOVENIAN("sl"),
    MACEDONIAN("mk"),
    ALBANIAN("sq"),
    ESTONIAN("et"),
    LATVIAN("lv"),
    LITHUANIAN("lt"),
    BELARUSIAN("be"),
    GEORGIAN("ka"),
    ARMENIAN("hy"),
    AZERBAIJANI("az");

    private final String code;

    Language(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

