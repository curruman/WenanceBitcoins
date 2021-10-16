package com.wenance.bitcoins.utils;

public class ConstantsUtils {
    private ConstantsUtils(){
    }

    /*Message for declaration's exceptions*/
    public static final String END_DATE_LESS_THAT_START_DATE_ERROR="The end date cannot be less than the start date.";
    public static final String FORMAT_INVALIDATE="Parameters of date invalidate.";
    public static final String ILEGAL_PARAMETER_DATE_ERROR="Illegal method signature.";
    public static final String FAILED_QUERY="Failed price query.";
    public static final String WENANCE_SERVICE_PATH_TIMEOUT = "Timeout";

    /*Controller's paths*/
    public static final String PATH_SEPARATOR="/";
    public static final String CURRENCIES="currencies";
    public static final String API_VERSION="api/v1";
    public static final String FIND_CURRENCY="findCurrency";
    public static final String FIND_CURRENCIES="findCurrencies";
    public static final String START_DATE="{startDate}";
    public static final String END_DATE="{endDate}";
    public static final String CURRENCY_ID="{id}";
}
