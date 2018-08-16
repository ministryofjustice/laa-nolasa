package com.laa.nolasa.laanolasa.util;

import com.laa.nolasa.laanolasa.entity.NolAutoSearchResults;

public class LibraUtil {

    private static final String COLON = ":";
    private static final String EMPTY_STRING = "";


    public static boolean isLibraIDsNotEqual(NolAutoSearchResults nolAutoSearchResults, Long[] libraIDs) {
        return !stringifyLibraIDs(nolAutoSearchResults).equals(stringifyLibraIDs(libraIDs));
    }

    private static String stringifyLibraIDs(NolAutoSearchResults nolAutoSearchResults) {
        StringBuilder libraIDs = new StringBuilder();
        appendNotNullValue(libraIDs, nolAutoSearchResults.getLibrId1(), COLON);
        appendNotNullValue(libraIDs, nolAutoSearchResults.getLibrId2(), COLON);
        appendNotNullValue(libraIDs, nolAutoSearchResults.getLibrId3(), COLON);
        appendNotNullValue(libraIDs, nolAutoSearchResults.getLibrId4(), COLON);
        appendNotNullValue(libraIDs, nolAutoSearchResults.getLibrId5(), COLON);
        appendNotNullValue(libraIDs, nolAutoSearchResults.getLibrId6(), COLON);
        appendNotNullValue(libraIDs, nolAutoSearchResults.getLibrId7(), COLON);
        appendNotNullValue(libraIDs, nolAutoSearchResults.getLibrId8(), COLON);
        appendNotNullValue(libraIDs, nolAutoSearchResults.getLibrId9(), COLON);
        appendNotNullValue(libraIDs, nolAutoSearchResults.getLibrId10(), COLON);
        appendNotNullValue(libraIDs, nolAutoSearchResults.getLibrId11(), COLON);
        appendNotNullValue(libraIDs, nolAutoSearchResults.getLibrId12(), COLON);
        appendNotNullValue(libraIDs, nolAutoSearchResults.getLibrId13(), COLON);
        appendNotNullValue(libraIDs, nolAutoSearchResults.getLibrId14(), COLON);
        appendNotNullValue(libraIDs, nolAutoSearchResults.getLibrId15(), EMPTY_STRING);
        return libraIDs.toString();
    }

    private static String stringifyLibraIDs(Long[] libraIDsArray) {
        StringBuilder libraIDs = new StringBuilder();
        appendNotNullValue(libraIDs, libraIDsArray[0], COLON);
        appendNotNullValue(libraIDs, libraIDsArray[1], COLON);
        appendNotNullValue(libraIDs, libraIDsArray[2], COLON);
        appendNotNullValue(libraIDs, libraIDsArray[3], COLON);
        appendNotNullValue(libraIDs, libraIDsArray[4], COLON);
        appendNotNullValue(libraIDs, libraIDsArray[5], COLON);
        appendNotNullValue(libraIDs, libraIDsArray[6], COLON);
        appendNotNullValue(libraIDs, libraIDsArray[7], COLON);
        appendNotNullValue(libraIDs, libraIDsArray[8], COLON);
        appendNotNullValue(libraIDs, libraIDsArray[9], COLON);
        appendNotNullValue(libraIDs, libraIDsArray[10], COLON);
        appendNotNullValue(libraIDs, libraIDsArray[11], COLON);
        appendNotNullValue(libraIDs, libraIDsArray[12], COLON);
        appendNotNullValue(libraIDs, libraIDsArray[13], COLON);
        appendNotNullValue(libraIDs, libraIDsArray[14], EMPTY_STRING);
        return libraIDs.toString();
    }

    private static void appendNotNullValue(StringBuilder builder, Long id, String delimiter) {
        if (null == id) {
            return;
        }
        builder.append(id).append(delimiter);
    }
}
