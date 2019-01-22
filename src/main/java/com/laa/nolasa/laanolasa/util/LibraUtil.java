package com.laa.nolasa.laanolasa.util;

import com.laa.nolasa.laanolasa.entity.NolAutoSearchResults;

import java.util.Arrays;

public class LibraUtil {

    private static final String COLON = ":";
    private static final String EMPTY_STRING = "";

    public static void updateLibraDetails(NolAutoSearchResults autoSearchResult, Long[] libraIDs) {

        Arrays.sort(libraIDs);
        autoSearchResult.setLibrId1(libraIDs[0]);
        autoSearchResult.setLibrId2(libraIDs[1]);
        autoSearchResult.setLibrId3(libraIDs[2]);
        autoSearchResult.setLibrId4(libraIDs[3]);
        autoSearchResult.setLibrId5(libraIDs[4]);
        autoSearchResult.setLibrId6(libraIDs[5]);
        autoSearchResult.setLibrId7(libraIDs[6]);
        autoSearchResult.setLibrId8(libraIDs[7]);
        autoSearchResult.setLibrId9(libraIDs[8]);
        autoSearchResult.setLibrId10(libraIDs[9]);
        autoSearchResult.setLibrId11(libraIDs[10]);
        autoSearchResult.setLibrId12(libraIDs[11]);
        autoSearchResult.setLibrId13(libraIDs[12]);
        autoSearchResult.setLibrId14(libraIDs[13]);
        autoSearchResult.setLibrId15(libraIDs[14]);
    }

    public static boolean areLibraIDsEqual(NolAutoSearchResults nolAutoSearchResults, Long[] libraIDs) {
        return stringifyLibraIDs(nolAutoSearchResults).equals(stringifyLibraIDs(libraIDs));
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
