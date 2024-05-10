package it.cnr.ilc.maia.controller;

import it.cnr.ilc.maia.dto.texto.TextoKwicRequest;
import it.cnr.ilc.maia.dto.texto.KwicResponse;
import it.cnr.ilc.maia.dto.texto.KwicRequest;
import it.cnr.ilc.maia.dto.texto.TextoKwicResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
        restTemplate().exchange(urlAndParams.url, HttpMethod.POST, entity, Void.class, urlAndParams.params);
    }

    @PostMapping("util/kwic")
    public KwicResponse kwic(@RequestBody KwicRequest maiaRequest) throws Exception {
        HttpHeaders headers = new HttpHeaders(getHeaders(httpServletRequest));
        headers.remove("content-length");
        TextoKwicRequest textoRequest = new TextoKwicRequest(maiaRequest);
        HttpEntity<TextoKwicRequest> entity = new HttpEntity<>(textoRequest, headers);
        UrlAndParams urlAndParams = getUrlAndPArams(httpServletRequest);
        TextoKwicResponse textResponse = restTemplate().exchange(urlAndParams.url, HttpMethod.POST, entity, TextoKwicResponse.class, urlAndParams.params).getBody();
        KwicResponse maiaResponse = new KwicResponse(textResponse);
        return maiaResponse;
    }

}
