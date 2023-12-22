package it.cnr.ilc.maia.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.stream.Collectors;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

abstract class ExternController {

    @Autowired
    protected HttpServletRequest httpServletRequest;

    @Autowired
    protected Environment environment;

    private RestTemplate restTemplate;

    protected RestTemplate restTemplate() throws Exception {
        if (restTemplate == null) {
            final SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] x509Certificates, String s) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] x509Certificates, String s) {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }}, null);
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier((String hostname, SSLSession session) -> true);
            restTemplate = new RestTemplate();
            String baseUrl = getClass().getAnnotation(RequestMapping.class).value()[0].substring(1);
            baseUrl = environment.getProperty(baseUrl + ".url");
            if (baseUrl.endsWith("/")) {
                baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
            }
            DefaultUriBuilderFactory uriBuilderFactory = new DefaultUriBuilderFactory(baseUrl);
            restTemplate.setUriTemplateHandler(uriBuilderFactory);
        }
        return restTemplate;
    }

    @GetMapping("/**")
    public ResponseEntity<byte[]> get() throws Exception {
        HttpEntity<Void> entity = new HttpEntity<>(getHeaders(httpServletRequest));
        UrlAndParams urlAndParams = getUrlAndPArams(httpServletRequest);
        ResponseEntity<byte[]> response = restTemplate().exchange(urlAndParams.url, HttpMethod.GET, entity, byte[].class, urlAndParams.params);
        return ResponseEntity.status(response.getStatusCode()).headers(getHeaders(response)).body(response.getBody());
    }

    @PostMapping("/**")
    public ResponseEntity<byte[]> post(@RequestBody @NotNull byte[] body) throws Exception {
        HttpEntity<byte[]> entity = new HttpEntity<>(body, getHeaders(httpServletRequest));
        UrlAndParams urlAndParams = getUrlAndPArams(httpServletRequest);
        ResponseEntity<byte[]> response = restTemplate().exchange(urlAndParams.url, HttpMethod.POST, entity, byte[].class, urlAndParams.params);
        return ResponseEntity.status(response.getStatusCode()).headers(getHeaders(response)).body(response.getBody());
    }

    @PutMapping("/**")
    public ResponseEntity<byte[]> put(@RequestBody @NotNull byte[] body) throws Exception {
        HttpEntity<byte[]> entity = new HttpEntity<>(body, getHeaders(httpServletRequest));
        UrlAndParams urlAndParams = getUrlAndPArams(httpServletRequest);
        ResponseEntity<byte[]> response = restTemplate().exchange(urlAndParams.url, HttpMethod.PUT, entity, byte[].class, urlAndParams.params);
        return ResponseEntity.status(response.getStatusCode()).headers(getHeaders(response)).body(response.getBody());
    }

    @DeleteMapping("/**")
    public ResponseEntity<byte[]> delete() throws Exception {
        HttpEntity<Void> entity = new HttpEntity<>(getHeaders(httpServletRequest));
        UrlAndParams urlAndParams = getUrlAndPArams(httpServletRequest);
        ResponseEntity<byte[]> response = restTemplate().exchange(urlAndParams.url, HttpMethod.POST, entity, byte[].class, urlAndParams.params);
        return ResponseEntity.status(response.getStatusCode()).headers(getHeaders(response)).body(response.getBody());
    }

    protected UrlAndParams getUrlAndPArams(HttpServletRequest request) {
        UrlAndParams urlAndParams = new UrlAndParams();
        urlAndParams.url = request.getServletPath().substring(getClass().getAnnotation(RequestMapping.class).value()[0].length());
        if (!request.getParameterMap().isEmpty()) {
            urlAndParams.url += request.getParameterMap().entrySet().stream()
                    .map(e -> e.getKey().concat("={").concat(e.getKey()).concat("}"))
                    .collect(Collectors.joining("&", "?", ""));
            urlAndParams.params = request.getParameterMap().entrySet().stream()
                    .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()[0]));
        }
        return urlAndParams;
    }

    protected HttpHeaders getHeaders(HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        String next;
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            next = enumeration.nextElement();
            headers.add(next, request.getHeader(next));
        }
        return headers;
    }

    protected HttpHeaders getHeaders(ResponseEntity response) {
        HttpHeaders headers = new HttpHeaders();
        response.getHeaders().keySet().stream().forEach(k -> {
            if (!k.toLowerCase().startsWith("access-control-") && !k.equalsIgnoreCase("vary") && !k.equalsIgnoreCase("transfer-encoding")) {
                headers.put(k, response.getHeaders().get(k));
            }
        });
        return headers;
    }

    protected class UrlAndParams {

        protected String url;
        protected Map<String, String> params = Collections.emptyMap();

    }
}
