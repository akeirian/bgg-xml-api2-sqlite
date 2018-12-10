package pw.akeirian.bgg_xml_api2_sqlite;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * BGG XML API2 consumer
 */
public abstract class ApiConsumer {
    private static final int READ_TIMEOUT_MS = 20000;
    private static final int CONNECT_TIMEOUT_MS = 20000;
    private static final int REQUEST_DELAY_MS = 5000;
    private static final int MAX_RETRIES = 10;

    protected InputStream getInputStream(URL url) throws IOException, InterruptedException {
        int retries = 0;
        do {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(READ_TIMEOUT_MS);
            conn.setConnectTimeout(CONNECT_TIMEOUT_MS);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            switch (conn.getResponseCode()) {
                case HttpURLConnection.HTTP_OK:
                    // Requests will result in an HTTP response of 200 along with the XML data
                    // once the server has the data available.
                    return conn.getInputStream();
                case HttpURLConnection.HTTP_ACCEPTED:
                    // The XMLAPI will place collection requests that are received into a queue
                    // to be processed by backend servers. When this happens, the request will
                    // result in an HTTP response of 202 to indicate the request has been queued
                    // and is pending processing.
                    break;
                case HttpURLConnection.HTTP_INTERNAL_ERROR:
                case HttpURLConnection.HTTP_UNAVAILABLE:
                    // BGG throttles the requests now, which is to say that if you send requests
                    // too frequently, the server will give you 500 or 503 return codes,
                    // reporting that it is too busy. Currently, a 5-second wait between
                    // requests seems to suffice.
                    break;
                default:
                    break;
            }
            conn.disconnect();
            Thread.sleep(REQUEST_DELAY_MS);
            retries++;
        }
        while (retries < MAX_RETRIES);
        throw new IOException();
    }
}
