package meow.meowfx.internals.Http;

import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import meow.meowfx.internals.BasicAPIs.Console;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpManager {

    /**
     * You can use this method to send a JSON-RPC request to a given URL.
     * Make sure the URL exists and is reachable
     * @param url url of the JSON_RPC server
     * @param json the JSON string to send
     * @return the response from the server as a String or null if an error happened.
     * example for method usage:
        * <pre>
     * sendRpcRequest(<a href="https://playground.oresat.org/json-rpc">...</a>,
     * """
     *     {
     *       "jsonrpc": "2.0",
     *       "method": "subtract",
     *       "params": [42, 23],
     *       "id": 1
     *     }
     * """)
     */
  public static String sendRpcRequest(String url, String json) {
    if (isValidJson(json)) {
      throw new IllegalArgumentException("Your JSON is not valid, buddy");
    }
    try {
      URL url_internal = new URL(url);
      HttpURLConnection conn = (HttpURLConnection) url_internal.openConnection();
      conn.setDoOutput(true);
      conn.setRequestMethod("POST");
      conn.setRequestProperty("Content-Type", "application/json");
      try (OutputStream os = conn.getOutputStream()) {
        os.write(json.getBytes());
      }
      return getResult(conn);
    } catch (IOException e) {
      Console.error("An error occurred while sending the request: " + e.getMessage());
    } catch (IllegalArgumentException e) {
      Console.error("Invalid JSON: " + e.getMessage());
    }
    return null;
  }

  /**
     * You can use this method to send a Rest request to a given URL.
     * Make sure the URL exists and is reachable
     * @param url url of the JSON_RPC server
     * @param params the params that are required for POST or PUT requests, in json format please.
     * @return the response from the server as a String or null if an error happened.
     * example for method usage:
     * <pre>
     * sendRpcRequest(<a href="https://playground.oresat.org/json-rpc">...</a>,
     * "POST", """
   *         {
   *           "title": "foo",
   *           "body": "bar",
   *           "userId": 1
   *         }
   *         """)
   */
  public static String sendRESTRequest(String url, RestEndPoints endPoint, String params) {
    try {
      HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
      if (endPoint == RestEndPoints.GET) {
        con.setRequestMethod("GET");
      } else {
        if (isValidJson(params)) {
          throw new IllegalArgumentException("Your JSON is not valid, buddy");
        }
        con.setRequestMethod(endPoint.toString());
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setDoOutput(true);
        try (OutputStream os = con.getOutputStream()) {
          byte[] input = params.getBytes(StandardCharsets.UTF_8);
          os.write(input, 0, input.length);
        }
      }
      return getResult(con);
    } catch (IOException e) {
        Console.error("An error occurred while sending the REST request: " + e.getMessage());
    }
    return null;
  }
  private static String getResult(HttpURLConnection con) throws IOException {
    StringBuilder responseBuilder = new StringBuilder();
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
      String line;
      while ((line = reader.readLine()) != null) {
        responseBuilder.append(line);
      }
    }
    return responseBuilder.toString();
  }

  public static boolean isValidJson(String json) {
    try {
      JsonParser.parseString(json);
      return false;
    } catch (JsonSyntaxException ex) {
      return true;
    }
  }
}