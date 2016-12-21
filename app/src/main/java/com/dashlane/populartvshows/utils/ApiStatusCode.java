package com.dashlane.populartvshows.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author diego.galico
 *
 * Class that maps status code with status message
 *
 * ApiStatusCode is not used, is preserved in order to use it in the future
 */
@SuppressWarnings("unused")
public class ApiStatusCode {

    private static final Map<Integer, String> hashMapApiStatusCode;

    static {
        Map<Integer, String> aMap = new HashMap<>();
        aMap.put(501, "Invalid service: this service does not exist");
        aMap.put(401, "Authentication failed: You do not have permissions to access the service");
        aMap.put(405, "Invalid format: This service doesn't exist in that format");
        aMap.put(422, "Invalid parameters: Your request parameters are incorrect");
        aMap.put(404, "Invalid id: The pre-requisite id is invalid or not found");
        aMap.put(401, "Invalid API key: You must be granted a valid key");
        aMap.put(403, "Duplicate entry: The data you tried to submit already exists");
        aMap.put(503, "Service offline: This service is temporarily offline, try again later");
        aMap.put(500, "Internal error: Something went wrong, contact TMDb");
        aMap.put(504, "Time out:Your request to the backend server timed out");
        hashMapApiStatusCode = Collections.unmodifiableMap(aMap);
    }

    public static String getApiStatusByCode(int code){
        return hashMapApiStatusCode.get(code);
    }
}
