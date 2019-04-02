package com.diendan.svdanang.http;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.diendan.svdanang.api.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;


/**
 * Created by dcmen on 08/31/16.
 */
public interface HttpApi {

    /**
     * Execute http request with post method and body is json string (raw)
     *
     * @param requestUrl
     * @param jsonObject
     * @return The response string
     * @throws IOException
     * @throws ApiException
     */
    Object doHttpPost(@NonNull String requestUrl, String jsonObject)
            throws ApiException, JSONException, IOException, ApiException;

    /**
     * Execute http request with post method and body is json object (raw)
     *
     * @param requestUrl
     * @param jsonObject
     * @return The response string
     * @throws IOException
     * @throws ApiException
     */
    Object doHttpPost(@NonNull String requestUrl, JSONObject jsonObject)
            throws ApiException, JSONException, IOException;

    /**
     * Execute http request with post method and body is param pairs (x-www-form-urlencoded)
     *
     * @param requestUrl
     * @param params
     * @return The response string
     * @throws IOException
     * @throws ApiException
     */
    Object doHttpPost(@NonNull String requestUrl, Map<String, String> params)
            throws ApiException, JSONException, IOException;

    /**
     * Execute http request with get method
     *
     * @param requestUrl
     * @param params
     * @return The response string
     * @throws IOException
     */
    Object doHttpGet(@NonNull String requestUrl, @Nullable Map<String, String> params)
            throws ApiException, JSONException, IOException;

    /**
     * Execute http request with get method
     *
     * @param requestUrl
     * @return The response string
     * @throws IOException
     * @throws ApiException
     */
    Object doHttpGet(@NonNull String requestUrl)
            throws ApiException, JSONException, IOException;

    /**
     * Execute http request with post method and multipart content-type
     *
     * @param requestUrl
     * @param params
     * @param files
     * @return The response string
     * @throws IOException
     */
    Object doHttpMultipart(@NonNull String requestUrl, Map<String, String> params, Map<String, File> files)
            throws ApiException, JSONException, IOException;

    Object doHttpMultipartImages(@NonNull String requestUrl, Map<String, String> params, ArrayList<File> files)
            throws ApiException, JSONException, IOException;
}
