package it.cnr.ilc.maia.controller;

import it.cnr.ilc.maia.dto.texto.AicRequest;
import it.cnr.ilc.maia.dto.texto.AicResponse;
import it.cnr.ilc.maia.dto.texto.AisRequest;
import it.cnr.ilc.maia.dto.texto.AisResponse;
import it.cnr.ilc.maia.dto.texto.TextoKwicRequest;
import it.cnr.ilc.maia.dto.texto.KwicResponse;
import it.cnr.ilc.maia.dto.texto.KwicRequest;
import it.cnr.ilc.maia.dto.texto.TextoAicRequest;
import it.cnr.ilc.maia.dto.texto.TextoAisRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/texto")
public class TextoController extends ExternController {

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
        TextoAicRequest textoRequest = new TextoAicRequest(maiaRequest);
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
        TextoAisRequest textoRequest = new TextoAisRequest(maiaRequest);
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

}
