package com.laa.nolasa.laanolasa.util;

import com.laa.nolasa.laanolasa.entity.NolAutoSearchResults;

import java.util.Arrays;

public class LibraUtil {

    private static final String COLON = ":";
    private static final String EMPTY_STRING = "";

    public static void updateLibraDetails(NolAutoSearchResults autoSearchResult, Long[] libraIDs) {
        int numberOfResults = libraIDs.length;

        if(numberOfResults == 0) return;
        autoSearchResult.setLibrId1(libraIDs[0]);

        if(numberOfResults <= 1) return;
        autoSearchResult.setLibrId2(libraIDs[1]);

        if(numberOfResults == 2) return;
        autoSearchResult.setLibrId3(libraIDs[2]);

        if(numberOfResults == 3) return;
        autoSearchResult.setLibrId4(libraIDs[3]);

        if(numberOfResults == 4) return;
        autoSearchResult.setLibrId5(libraIDs[4]);

        if(numberOfResults == 5) return;
        autoSearchResult.setLibrId6(libraIDs[5]);

        if(numberOfResults == 6) return;
        autoSearchResult.setLibrId7(libraIDs[6]);

        if(numberOfResults == 7) return;
        autoSearchResult.setLibrId8(libraIDs[7]);

        if(numberOfResults == 8) return;
        autoSearchResult.setLibrId9(libraIDs[8]);

        if(numberOfResults == 9) return;
        autoSearchResult.setLibrId10(libraIDs[9]);

        if(numberOfResults == 10) return;
        autoSearchResult.setLibrId11(libraIDs[10]);

        if(numberOfResults == 11) return;
        autoSearchResult.setLibrId12(libraIDs[11]);

        if(numberOfResults == 12) return;
        autoSearchResult.setLibrId13(libraIDs[12]);

        if(numberOfResults == 13) return;
        autoSearchResult.setLibrId14(libraIDs[13]);

        if(numberOfResults == 14) return;
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
