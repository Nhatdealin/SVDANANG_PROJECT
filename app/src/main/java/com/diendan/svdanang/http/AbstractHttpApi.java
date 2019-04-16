package com.diendan.svdanang.http;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.diendan.svdanang.api.exception.ApiException;
import com.diendan.svdanang.utils.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Logger;



/**
 * Created by dcmen on 08/31/16.
 */
public abstract class AbstractHttpApi implements HttpApi {
    public static final String CHARSET = "UTF-8";
    public static final int CONNECT_TIME_OUT = 25000;
    public static final int READ_TIME_OUT = 25000;
    protected static final Logger LOG = Logger.getLogger(AbstractHttpApi.class.getCanonicalName());
    private static final String LINE_FEED = "\r\n";
    private final String BOUNDARY = "*****";
    private final String TWO_HYPHENS = "--";

    protected String executeHttpPost(@NonNull String requestUrl, @Nullable Map<String, String> headers,
                                     String jsonObject)
            throws JSONException, ApiException, IOException {
        HttpURLConnection connection = prepareConnection(requestUrl);
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestProperty("Content-Type", "application/json; charset=" + CHARSET);
        connection.setRequestProperty("Content-Length", "" + Integer.toString(jsonObject.getBytes().length));
        if (headers != null) addHeaderFields(connection, headers);
        if (jsonObject != null) {
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(connection.getOutputStream(), CHARSET), true);
            writer.append(jsonObject);
            writer.flush();
            writer.close();
        }
        String response = executeHttpRequest(connection);
        return response;
    }
    protected String executeHttpPut(@NonNull String requestUrl, @Nullable Map<String, String> headers,
                                     String jsonObject)
            throws JSONException, ApiException, IOException {
        HttpURLConnection connection = prepareConnection(requestUrl);
        connection.setRequestMethod("PUT");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestProperty("Content-Type", "application/json; charset=" + CHARSET);
        connection.setRequestProperty("Content-Length", "" + Integer.toString(jsonObject.getBytes().length));
        if (headers != null) addHeaderFields(connection, headers);
        if (jsonObject != null) {
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(connection.getOutputStream(), CHARSET), true);
            writer.append(jsonObject);
            writer.flush();
            writer.close();
        }
            String response = executeHttpRequest(connection);
        return response;
    }

    protected String executeHttpPost(@NonNull String requestUrl, @Nullable Map<String, String> headers,
                                     JSONObject jsonObject)
            throws JSONException, ApiException, IOException {
        return executeHttpPost(requestUrl, headers, jsonObject.toString());
    }

    protected String executeHttpPost(@NonNull String requestUrl, @Nullable Map<String, String> headers,
                                     Map<String, String> params)
            throws JSONException, ApiException, IOException {
        String urlParams = StringUtils.getParamsRequest(params);
        byte[] postDataBytes = urlParams.getBytes();

        HttpURLConnection connection = prepareConnection(requestUrl);
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestProperty("Accept", "*/*");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=" + CHARSET);
        connection.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        if (headers != null) addHeaderFields(connection, headers);
        if (!TextUtils.isEmpty(urlParams)) {
            connection.getOutputStream().write(postDataBytes);
//            PrintWriter writer = new PrintWriter(new OutputStreamWriter(connection.getOutputStream(), CHARSET), true);
//            writer.append(urlParams);
//            writer.flush();
//            writer.close();
        }
        String response = executeHttpRequest(connection);
        return response;
    }

    protected String executeHttpGet(@NonNull String requestUrl, @Nullable Map<String, String> headers,
                                    @Nullable Map<String, String> params)
            throws JSONException, ApiException, IOException {
        String urlParams = null;
        if (params != null) urlParams = StringUtils.getParamsRequest(params);
        String queryUrl = TextUtils.isEmpty(urlParams) ? requestUrl : (requestUrl + "?" + urlParams);
        HttpURLConnection connection = prepareConnection(queryUrl);
        connection.setRequestMethod("GET");
        if (headers != null) addHeaderFields(connection, headers);
        String response = executeHttpRequest(connection);
        return response;
    }

    protected String executeHttpGet(@NonNull String requestUrl, @Nullable Map<String, String> headers)
            throws JSONException, ApiException, IOException {
        HttpURLConnection connection = prepareConnection(requestUrl);
        connection.setRequestMethod("GET");
        if (headers != null) addHeaderFields(connection, headers);
        String response = executeHttpRequest(connection);
        return response;
    }

    protected String executeHttpMultipart2(@NonNull String requestUrl, @Nullable Map<String, String> headers,
                                           @Nullable Map<String, String> params, Map<String, File> files)
            throws JSONException, ApiException, IOException {

        HttpURLConnection connection = prepareConnection(requestUrl);
        connection.setRequestMethod("POST");
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.setRequestProperty("Cache-Control", "no-cache");
        connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + BOUNDARY);
        if (headers != null) addHeaderFields(connection, headers);
        OutputStream outputStream = connection.getOutputStream();
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, CHARSET), true);
        if (params != null) addFormFields(writer, params);
        if (files != null) addFileParts(writer, outputStream, files);
        writer.append(TWO_HYPHENS + BOUNDARY + TWO_HYPHENS).append(LINE_FEED);
        writer.flush();
        writer.close();
        String response = executeHttpRequest(connection);
        return response;
    }

    protected String executeHttpMultipart(@NonNull String requestUrl, @Nullable Map<String, String> headers,
                                          Map<String, String> params, Map<String, File> files)
            throws JSONException, ApiException, JSONException, IOException {

        HttpURLConnection connection = prepareConnection(requestUrl);
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.setRequestProperty("Cache-Control", "no-cache");
        connection.setRequestProperty("Accept", "*/*");
        connection.setRequestProperty("Content-Type", "multipart/form-data; charset=" + CHARSET + "; boundary=" + BOUNDARY);
        if (headers != null) addHeaderFields(connection, headers);
        OutputStream outputStream = connection.getOutputStream();
        DataOutputStream dataStream = new DataOutputStream(outputStream);
        if (params != null) addFormFields(dataStream, params);
        if (files != null && files.size() > 0) addFileParts(dataStream, outputStream, files);
        dataStream.writeBytes(TWO_HYPHENS + BOUNDARY +
                TWO_HYPHENS + LINE_FEED);
        dataStream.flush();
        dataStream.close();
        String response = executeHttpRequest(connection);
        return response;
    }

    protected String executeHttpMultipartImages(@NonNull String requestUrl, @Nullable Map<String, String> headers,
                                          Map<String, String> params, ArrayList<File> files)
            throws JSONException, ApiException, JSONException, IOException {

        HttpURLConnection connection = prepareConnection(requestUrl);
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.setRequestProperty("Cache-Control", "no-cache");
        connection.setRequestProperty("Accept", "*/*");
        connection.setRequestProperty("Content-Type", "multipart/form-data; charset=" + CHARSET + "; boundary=" + BOUNDARY);
        if (headers != null) addHeaderFields(connection, headers);
        OutputStream outputStream = connection.getOutputStream();
        DataOutputStream dataStream = new DataOutputStream(outputStream);
        if (params != null) addFormFields(dataStream, params);
        if (files != null && files.size() > 0) addFileImagesParts(dataStream, outputStream, files);
        dataStream.writeBytes(TWO_HYPHENS + BOUNDARY +
                TWO_HYPHENS + LINE_FEED);
        dataStream.flush();
        dataStream.close();
        String response = executeHttpRequest(connection);
        return response;
    }
    /**
     * Prepare the connection with basic requirement from our application
     *
     * @param requestUrl
     * @return the new http connection
     * @throws IOException
     */
    private HttpURLConnection prepareConnection(String requestUrl) throws IOException {
        URL url = new URL(requestUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("connection", "close");
        connection.setConnectTimeout(CONNECT_TIME_OUT);
        connection.setReadTimeout(READ_TIME_OUT);
        connection.setUseCaches(false);
        return connection;
    }

    /**
     * Completes the request and receives response from the server.
     *
     * @return a list of Strings as response in case the server returned status
     * OK, otherwise an exception is thrown.
     * @throws IOException
     */
    private String executeHttpRequest(HttpURLConnection connection)
            throws JSONException, ApiException, IOException {
        StringBuilder response = new StringBuilder();
        // checks server's status code first
        int status = connection.getResponseCode();
//        if (status == HttpURLConnection.HTTP_OK) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    status == HttpURLConnection.HTTP_OK || status == HttpURLConnection.HTTP_CREATED ? connection.getInputStream() : connection.getErrorStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            connection.disconnect();
        } catch (Exception ex) {
            throw new ApiException(ApiException.NETWORK_ERROR, "Server returned non-OK status: " + status);
        }
//        } else {
//            throw new ApiException(ApiException.NETWORK_ERROR, "Server returned non-OK status: " + status);
//        }
        return response.toString();
    }

    /**
     * Adds set of header field to the request.
     *
     * @param connection
     * @param headers
     */
    public void addHeaderFields(HttpURLConnection connection, Map<String, String> headers) {
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            connection.setRequestProperty(entry.getKey(), entry.getValue());
        }
    }

    public void addFormFields(PrintWriter writer, Map<String, String> params) {
        for (Map.Entry<String, String> entry : params.entrySet()) {
            addFormField(writer, entry.getKey(), entry.getValue());
        }
    }

    public void addFormFields(DataOutputStream stream, Map<String, String> params) throws IOException {
        for (Map.Entry<String, String> entry : params.entrySet()) {
            addFormField(stream, entry.getKey(), entry.getValue());
        }
    }


    private void addFileParts(PrintWriter writer, OutputStream outputStream, Map<String, File> files) throws IOException {
        for (Map.Entry<String, File> entry : files.entrySet()) {
            if (entry.getValue() != null)// && entry.getValue().exists()
                addFilePart(writer, outputStream, entry.getKey(), entry.getValue());
        }
    }


    private void addFileParts(DataOutputStream stream, OutputStream outputStream, Map<String, File> files) throws IOException {
        for (Map.Entry<String, File> entry : files.entrySet()) {
            if (entry.getValue() != null)// && entry.getValue().exists()
                addFilePart(stream, outputStream, entry.getKey(), entry.getValue());
        }
    }

    private void addFileImagesParts(DataOutputStream stream, OutputStream outputStream, ArrayList<File> images) throws IOException {
        for (File item : images) {
                addFilePart(stream, outputStream, "ImageFiles", item);
        }
    }

    /**
     * Adds a form field to the request
     *
     * @param name  field name
     * @param value field value
     */
    public void addFormField(PrintWriter writer, String name, String value) {
        writer.append(TWO_HYPHENS + BOUNDARY).append(LINE_FEED);
        writer.append("Content-Disposition: form-data; name=\"" + name + "\"")
                .append(LINE_FEED);
        writer.append("Content-Type: text/plain; charset=" + CHARSET).append(
                LINE_FEED);
        writer.append(LINE_FEED);
        writer.append(value).append(LINE_FEED);
        writer.flush();
    }


    /**
     * Adds a form field to the request
     *
     * @param name  field name
     * @param value field value
     */
    public void addFormField(DataOutputStream dataStream, String name, String value) throws IOException {
        if (value != null && !value.equals("null")) {
            dataStream.writeBytes(TWO_HYPHENS + BOUNDARY + LINE_FEED);
            dataStream.writeBytes("Content-Disposition: form-data; name=\"" + name + "\"" + LINE_FEED);
            dataStream.writeBytes(LINE_FEED);
            dataStream.write(value.getBytes(CHARSET));
            dataStream.writeBytes(LINE_FEED);
            dataStream.flush();
        }
    }

    /**
     * Adds a upload file section to the request
     *
     * @param fieldName  name attribute in <input type="file" name="..." />
     * @param uploadFile a File to be uploaded
     * @throws IOException
     */
    public void addFilePart(DataOutputStream stream, OutputStream outputStream, String fieldName, File uploadFile)
            throws IOException {
        String fileName = uploadFile.getName();
        stream.writeBytes(TWO_HYPHENS + BOUNDARY + LINE_FEED);
        stream.writeBytes(
                "Content-Disposition: form-data; name=\"" + fieldName
                        + "\"; filename=\"" + fileName + "\"" + LINE_FEED);
        stream.writeBytes(
                "Content-Type: "
                        + URLConnection.guessContentTypeFromName(fileName));
        stream.writeBytes(LINE_FEED);
        stream.writeBytes("Content-Transfer-Encoding: binary" + LINE_FEED);
        stream.writeBytes(LINE_FEED);
        stream.flush();

        FileInputStream inputStream = new FileInputStream(uploadFile);
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        outputStream.flush();
        inputStream.close();

        stream.writeBytes(LINE_FEED);
        stream.flush();
    }

    /**
     * Adds a upload file section to the request
     *
     * @param fieldName  name attribute in <input type="file" name="..." />
     * @param uploadFile a File to be uploaded
     * @throws IOException
     */
    public void addFilePart(PrintWriter writer, OutputStream outputStream, String fieldName, File uploadFile)
            throws IOException {
        String fileName = uploadFile.getName();
        writer.append("--" + BOUNDARY).append(LINE_FEED);
        writer.append(
                "Content-Disposition: form-data; name=\"" + fieldName
                        + "\"; filename=\"" + fileName + "\"")
                .append(LINE_FEED);
        writer.append(
                "Content-Type: "
                        + URLConnection.guessContentTypeFromName(fileName))
                .append(LINE_FEED);
        writer.append("Content-Transfer-Encoding: binary").append(LINE_FEED);
        writer.append(LINE_FEED);
        writer.flush();

        FileInputStream inputStream = new FileInputStream(uploadFile);
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        outputStream.flush();
        inputStream.close();

        writer.append(LINE_FEED);
        writer.flush();
    }

    public void addFilePart(PrintWriter writer, OutputStream outputStream, String fieldName, File uploadFile,
                            String contentType) throws IOException {
        String fileName = uploadFile.getName();
        writer.append("--" + BOUNDARY).append(LINE_FEED);
        writer.append(
                "Content-Disposition: form-data; name=\"" + fieldName
                        + "\"; filename=\"" + fileName + "\"")
                .append(LINE_FEED);
        writer.append("Content-Type: " + contentType).append(LINE_FEED);
//		writer.append("Content-Transfer-Encoding: binary").append(LINE_FEED);
        writer.append(LINE_FEED);
        writer.flush();

        FileInputStream inputStream = new FileInputStream(uploadFile);
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        outputStream.flush();
        inputStream.close();

        writer.append(LINE_FEED);
        writer.flush();
    }


}
