package it.cnr.ilc.maia.controller;

import it.cnr.ilc.maia.dto.texto.AicRequest;
import it.cnr.ilc.maia.dto.texto.AicResponse;
import it.cnr.ilc.maia.dto.texto.AisRequest;
import it.cnr.ilc.maia.dto.texto.AisResponse;
import it.cnr.ilc.maia.dto.texto.AnnotationFeature;
import it.cnr.ilc.maia.dto.texto.AnnotationOffset;
import it.cnr.ilc.maia.dto.texto.AnnotationMultipleRequest;
import it.cnr.ilc.maia.dto.texto.TextoKwicRequest;
import it.cnr.ilc.maia.dto.texto.KwicResponse;
import it.cnr.ilc.maia.dto.texto.KwicRequest;
import it.cnr.ilc.maia.dto.texto.TextoAicRequest;
import it.cnr.ilc.maia.dto.texto.TextoAisRequest;
import it.cnr.ilc.maia.dto.texto.TextoAnnotationCreateRequest;
import it.cnr.ilc.maia.dto.texto.TextoAnnotationFeatureCreateRequest;
import it.cnr.ilc.maia.dto.texto.TextoWordAnnotationsRequest;
import it.cnr.ilc.maia.dto.texto.WordAnnotationsRequest;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.DefaultUriBuilderFactory;

@RestController
@RequestMapping("/texto")
public class TextoController extends ExternController {

    private RestTemplate lexoRestTemplate;

    protected RestTemplate lexoRestTemplate() throws Exception {
        if (lexoRestTemplate == null) {
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
            lexoRestTemplate = new RestTemplate();
            String baseUrl = LexoController.class.getAnnotation(RequestMapping.class).value()[0].substring(1);
            baseUrl = environment.getProperty(baseUrl + ".url");
            if (baseUrl.endsWith("/")) {
                baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
            }
            DefaultUriBuilderFactory uriBuilderFactory = new DefaultUriBuilderFactory(baseUrl);
            lexoRestTemplate.setUriTemplateHandler(uriBuilderFactory);
        }
        return lexoRestTemplate;
    }

    @PostMapping("resource/{id}/upload")
    public void upload(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file) throws Exception {
        HttpHeaders headers = new HttpHeaders(getHeaders(httpServletRequest));
        headers.remove("content-length");
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", file.getResource());
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);
        UrlAndParams urlAndParams = getUrlAndPArams(httpServletRequest);
        ResponseEntity<Void> response = restTemplate().exchange(urlAndParams.url, HttpMethod.POST, entity, Void.class, urlAndParams.params);
        if (!response.getStatusCode().is2xxSuccessful()) {
            try {
                textoRemoveResource(id);
            } catch (Exception e) {
            }
        }
        textoAnalyzeResource(id);
    }

    private void textoRemoveResource(Long id) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", Arrays.asList(httpServletRequest.getHeader("Authorization")));
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        String url = "/resource/" + id + "/remove";
        restTemplate().exchange(url, HttpMethod.DELETE, entity, Void.class);
    }

    private void textoAnalyzeResource(Long id) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", Arrays.asList(httpServletRequest.getHeader("Authorization")));
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        String url = "/resource/" + id + "/analyze";
        restTemplate().exchange(url, HttpMethod.GET, entity, Void.class);
    }

    @PostMapping("util/kwic")
    public KwicResponse kwic(@RequestBody KwicRequest maiaRequest) throws Exception {
        HttpHeaders headers = new HttpHeaders(getHeaders(httpServletRequest));
        headers.remove("content-length");
        TextoKwicRequest textoRequest = new TextoKwicRequest(maiaRequest);
        HttpEntity<TextoKwicRequest> entity = new HttpEntity<>(textoRequest, headers);
        UrlAndParams urlAndParams = getUrlAndPArams(httpServletRequest);
        List<Map<String, Object>> textResponse = restTemplate().exchange(urlAndParams.url, HttpMethod.POST, entity, new ParameterizedTypeReference<List<Map<String, Object>>>() {
        }, urlAndParams.params).getBody();
        KwicResponse maiaResponse = new KwicResponse(textResponse, maiaRequest.getStart(), maiaRequest.getEnd(), maiaRequest.getFilters());
        return maiaResponse;
    }

    @PostMapping("util/aic")
    public AicResponse aic(@RequestBody AicRequest maiaRequest) throws Exception {
        HttpHeaders headers = new HttpHeaders(getHeaders(httpServletRequest));
        headers.remove("content-length");
        Long semanticsFeatureId = environment.getProperty("texto.semantics-feature-id", Long.class);
        TextoAicRequest textoRequest = new TextoAicRequest(maiaRequest, semanticsFeatureId);
        HttpEntity<TextoAicRequest> entity = new HttpEntity<>(textoRequest, headers);
        UrlAndParams urlAndParams = getUrlAndPArams(httpServletRequest);
        List<Map<String, Object>> textResponse = restTemplate().exchange(urlAndParams.url, HttpMethod.POST, entity, new ParameterizedTypeReference<List<Map<String, Object>>>() {
        }, urlAndParams.params).getBody();
        AicResponse maiaResponse = new AicResponse(textResponse, maiaRequest.getStart(), maiaRequest.getEnd(), maiaRequest.getFilters());
        return maiaResponse;
    }

    @PostMapping("util/ais")
    public ResponseEntity<AisResponse> ais(@RequestBody AisRequest maiaRequest) throws Exception {
        HttpHeaders headers = new HttpHeaders(getHeaders(httpServletRequest));
        headers.remove("content-length");
        Long semanticsFeatureId = environment.getProperty("texto.semantics-feature-id", Long.class);
        TextoAisRequest textoRequest = new TextoAisRequest(maiaRequest, semanticsFeatureId);
        HttpEntity<TextoAisRequest> entity = new HttpEntity<>(textoRequest, headers);
        UrlAndParams urlAndParams = getUrlAndPArams(httpServletRequest);
        ResponseEntity<List<Map<String, Object>>> response = restTemplate().exchange(urlAndParams.url, HttpMethod.POST, entity, new ParameterizedTypeReference<List<Map<String, Object>>>() {
        }, urlAndParams.params);
        List<Map<String, Object>> textResponse = response.getBody();
        AisResponse maiaResponse = new AisResponse(textResponse, maiaRequest.getStart(), maiaRequest.getEnd(), maiaRequest.getFilters());
        if (response.getStatusCode().equals(HttpStatus.PARTIAL_CONTENT)) {
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).header("excluded", response.getHeaders().getFirst("excluded")).body(maiaResponse);
        } else {
            return ResponseEntity.ok(maiaResponse);
        }
    }

    @DeleteMapping("resource/{id}/remove")
    public void resourceRemove(@PathVariable("id") Long id) throws Exception {
        textoRemoveAnalysis(id);
        textoRemoveResource(id);
    }

    private void textoRemoveAnalysis(Long id) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", Arrays.asList(httpServletRequest.getHeader("Authorization")));
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        String url = "/resource/" + id + "/analysis";
        restTemplate().exchange(url, HttpMethod.DELETE, entity, Void.class);
    }

    @PostMapping("annotation/multiple")
    public Map<String, Object> annotationMultipleCreate(@RequestBody AnnotationMultipleRequest maiaRequest) throws Exception {
        List<Integer> errors = new ArrayList<>();
        int createds = 0;
        Long annotationId;
        for (AnnotationOffset offset : maiaRequest.getOffsets()) {
            try {
                annotationId = textoCreateAnnotation(maiaRequest, offset);
                for (AnnotationFeature feature : maiaRequest.getFeatures()) {
                    textoCreateFeature(annotationId, feature);
                }
                createds++;
            } catch (Exception e) {
                errors.add(offset.getIndex());
            }
        }
        return Map.of("success", createds, "errors", errors);
    }

    private Long textoCreateAnnotation(AnnotationMultipleRequest maiaRequest, AnnotationOffset offset) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", Arrays.asList(httpServletRequest.getHeader("Authorization")));
        headers.put("Content-Type", Arrays.asList("application/json"));
        TextoAnnotationCreateRequest textoRequest = new TextoAnnotationCreateRequest(maiaRequest, offset);
        HttpEntity<TextoAnnotationCreateRequest> entity = new HttpEntity<>(textoRequest, headers);
        String url = "/annotation/create";
        ResponseEntity<Map<String, Object>> response = restTemplate().exchange(url, HttpMethod.POST, entity, new ParameterizedTypeReference<Map<String, Object>>() {
        });
        return ((Number) response.getBody().get("id")).longValue();
    }

    private Long textoCreateFeature(Long annotationId, AnnotationFeature maiaRequest) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", Arrays.asList(httpServletRequest.getHeader("Authorization")));
        headers.put("Content-Type", Arrays.asList("application/json"));
        TextoAnnotationFeatureCreateRequest textoRequest = new TextoAnnotationFeatureCreateRequest(annotationId, maiaRequest);
        HttpEntity<TextoAnnotationFeatureCreateRequest> entity = new HttpEntity<>(textoRequest, headers);
        String url = "/annotationFeature/create";
        ResponseEntity<Map<String, Object>> response = restTemplate().exchange(url, HttpMethod.POST, entity, new ParameterizedTypeReference<Map<String, Object>>() {
        });
        return ((Number) response.getBody().get("id")).longValue();
    }

    @DeleteMapping("annotation/multiple")
    public Map<String, Object> annotationMultipleDelete(@RequestBody AnnotationMultipleRequest maiaRequest) throws Exception {
        Set<Integer> errors = new HashSet<>();
        int removeds = 0;
        List<Map<String, Object>> textoResult;
        for (AnnotationOffset offset : maiaRequest.getOffsets()) {
            try {
                textoResult = textoWordAnnotations(maiaRequest, offset);
                for (Map<String, Object> map : textoResult) {
                    if (offset.getStart() == ((Number) map.get("start")).intValue() && offset.getEnd() == ((Number) map.get("end")).intValue()) {
                        Map<Long, String> textoFeatures = ((List<Map<String, Object>>) map.get("features")).stream().collect(Collectors.toMap(m -> ((Number) m.get("feature_id")).longValue(), m -> (String) m.get("value")));
                        boolean skip = false;
                        for (AnnotationFeature feature : maiaRequest.getFeatures()) {
                            skip |= !feature.getValue().equals(textoFeatures.get(feature.getFeatureId()));
                        }
                        if (!skip) {
                            try {
                                textoRemoveAnnotation(((Number) map.get("annotation_id")).longValue());
                                removeds++;
                            } catch (Exception e) {
                                errors.add(offset.getIndex());
                            }
                        }
                    }
                }
            } catch (Exception e) {
                errors.add(offset.getIndex());
            }
        }
        return Map.of("success", removeds, "errors", errors);
    }

    private List<Map<String, Object>> textoWordAnnotations(AnnotationMultipleRequest maiaRequest, AnnotationOffset offset) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", Arrays.asList(httpServletRequest.getHeader("Authorization")));
        headers.put("Content-Type", Arrays.asList("application/json"));
        TextoWordAnnotationsRequest textoRequest = new TextoWordAnnotationsRequest(maiaRequest, offset);
        HttpEntity<TextoWordAnnotationsRequest> entity = new HttpEntity<>(textoRequest, headers);
        String url = "/util/resource/" + offset.getResourceId() + "/word-annotations";
        ResponseEntity<List<Map<String, Object>>> response = restTemplate().exchange(url, HttpMethod.POST, entity, new ParameterizedTypeReference<List<Map<String, Object>>>() {
        });
        return response.getBody();
    }

    private void textoRemoveAnnotation(Long id) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", Arrays.asList(httpServletRequest.getHeader("Authorization")));
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        String url = "/annotation/" + id + "/remove";
        restTemplate().exchange(url, HttpMethod.DELETE, entity, Void.class);
    }

    @PostMapping("util/resource/{id}/word-annotations")
    public List<Map<String, Object>> wordAnnotations(@PathVariable("id") Long id, @RequestBody WordAnnotationsRequest request) throws Exception {
        HttpHeaders headers = new HttpHeaders(getHeaders(httpServletRequest));
        headers.remove("content-length");
        HttpEntity<WordAnnotationsRequest> entity = new HttpEntity<>(request, headers);
        UrlAndParams urlAndParams = getUrlAndPArams(httpServletRequest);
        List<Map<String, Object>> textoResponse = restTemplate().exchange(urlAndParams.url, HttpMethod.POST, entity, new ParameterizedTypeReference<List<Map<String, Object>>>() {
        }, urlAndParams.params).getBody();
        final Long semanticsFeatureId = environment.getProperty("texto.semantics-feature-id", Long.class);
        for (Map<String, Object> annotation : textoResponse) {
            for (Map<String, Object> map : (List<Map<String, Object>>) annotation.get("features")) {
                resolveValueFromLexo(map, semanticsFeatureId);
            }
        }
        return textoResponse;
    }

    private void resolveValueFromLexo(Map<String, Object> feature, Long semanticsFeatureId) throws Exception {
        if (semanticsFeatureId.equals(((Number) feature.get("feature_id")).longValue())) {
            Map<String, Object> lexoResponse;
            String type = (String) feature.get("feature_type");
            String value = (String) feature.get("value");
            if ("SENSE".equals(type)) {
                lexoResponse = lexoGetLexicalSense(value);
                value = (String) lexoResponse.get("lexicalEntryLabel") + ": " + ((Map) ((List) lexoResponse.get("definition")).get(0)).get("propertyValue");
                feature.replace("value", value);
            } else if ("FORM".equals(type)) {
                lexoResponse = lexoGetForm(value);
                value = (String) ((Map) ((List) lexoResponse.get("label")).get(0)).get("propertyValue");
                feature.replace("value", value);
            } else if ("LEXICAL_ENTRY".equals(type)) {
                lexoResponse = lexoGetLexicalEntry(value);
                value = (String) lexoResponse.get("label");
                feature.replace("value", value);
            }
        }
    }

    private Map<String, Object> lexoGetLexicalSense(String id) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Accept", Arrays.asList("application/json"));
        headers.put("Authorization", Arrays.asList(httpServletRequest.getHeader("Authorization")));
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        String url = "/data/lexicalSense?id={id}&module={module}";
        Map<String, String> params = Map.of("id", id, "module", "core");
        ResponseEntity<Map<String, Object>> response = lexoRestTemplate().exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<Map<String, Object>>() {
        }, params);
        return response.getBody();
    }

    private Map<String, Object> lexoGetForm(String id) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Accept", Arrays.asList("application/json"));
        headers.put("Authorization", Arrays.asList(httpServletRequest.getHeader("Authorization")));
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        String url = "/data/form?id={id}";
        Map<String, String> params = Map.of("id", id);
        ResponseEntity<Map<String, Object>> response = lexoRestTemplate().exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<Map<String, Object>>() {
        }, params);
        return response.getBody();
    }

    private Map<String, Object> lexoGetLexicalEntry(String id) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Accept", Arrays.asList("application/json"));
        headers.put("Authorization", Arrays.asList(httpServletRequest.getHeader("Authorization")));
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        String url = "/data/lexicalEntry?id={id}&module={module}";
        Map<String, String> params = Map.of("id", id, "module", "core");
        ResponseEntity<Map<String, Object>> response = lexoRestTemplate().exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<Map<String, Object>>() {
        }, params);
        return response.getBody();
    }
}
