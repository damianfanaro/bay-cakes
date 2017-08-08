package client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.UrlEscapers;
import configuration.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * TODO: complete with description
 *
 * @author dfanaro
 */
public class ConsumerClient {

    private static final String TEST_REQ_HEADER = "test_req_header";
    private static final String TEST_REQ_HEADER_VALUE = "test_req_header_value";

    private String url;

    public ConsumerClient(String url) {
        this.url = url;
    }

    public Map getAsMap(String path, String queryString) throws IOException {
        URIBuilder uriBuilder;
        try {
            uriBuilder = new URIBuilder(url).setPath(path);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        if (StringUtils.isNotEmpty(queryString)) {
            uriBuilder.setParameters(parseQueryString(queryString));
        }
        return jsonToMap(Request.Get(uriBuilder.toString())
                .addHeader(TEST_REQ_HEADER, TEST_REQ_HEADER_VALUE)
                .execute().returnContent().asString());
    }

    private List<NameValuePair> parseQueryString(String queryString) {
        return Arrays.stream(queryString.split("&")).map(s -> s.split("="))
                .map(p -> new BasicNameValuePair(p[0], UrlEscapers.urlFormParameterEscaper().escape(p[1])))
                .collect(Collectors.toList());
    }

    private String encodePath(String path) {
        return Arrays.asList(path.split("/"))
                .stream().map(UrlEscapers.urlPathSegmentEscaper()::escape).collect(Collectors.joining("/"));
    }

    public List getAsList(String path) throws IOException {
        return jsonToList(Request.Get(url + encodePath(path))
                .addHeader(TEST_REQ_HEADER, TEST_REQ_HEADER_VALUE)
                .execute().returnContent().asString());
    }

    public String get(String path) throws IOException {
        return Request.Get(url + encodePath(path))
                .addHeader(Constants.JWT_HEADER_NAME, Constants.JWT_HEADER_VALUE)
                .execute().returnContent().asString();
    }

    private String prettifyJson(String json) throws JsonProcessingException {
        return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(json);
    }

    public Map post(String path, String body, ContentType mimeType) throws IOException {
        String respBody = Request.Post(url + encodePath(path))
                .addHeader(TEST_REQ_HEADER, TEST_REQ_HEADER_VALUE)
                .bodyString(body, mimeType)
                .execute().returnContent().asString();
        return jsonToMap(respBody);
    }

    private HashMap jsonToMap(String respBody) throws IOException {
        if (respBody.isEmpty()) {
            return new HashMap();
        }
        return new ObjectMapper().readValue(respBody, HashMap.class);
    }

    private List jsonToList(String respBody) throws IOException {
        return new ObjectMapper().readValue(respBody, ArrayList.class);
    }

    public int options(String path) throws IOException {
        return Request.Options(url + encodePath(path))
                .addHeader(TEST_REQ_HEADER, TEST_REQ_HEADER_VALUE)
                .execute().returnResponse().getStatusLine().getStatusCode();
    }

    public String postBody(String path, String body, ContentType mimeType) throws IOException {
        return Request.Post(url + encodePath(path))
                .bodyString(body, mimeType)
                .execute().returnContent().asString();
    }

    public Map putAsMap(String path, String body) throws IOException {
        String respBody = Request.Put(url + encodePath(path))
                .addHeader(TEST_REQ_HEADER, TEST_REQ_HEADER_VALUE)
                .bodyString(body, ContentType.APPLICATION_JSON)
                .execute().returnContent().asString();
        return jsonToMap(respBody);
    }

}
