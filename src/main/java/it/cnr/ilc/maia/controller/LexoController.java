package it.cnr.ilc.maia.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.cnr.ilc.maia.dto.lexo.AssociateReferralEntryRequest;
import it.cnr.ilc.maia.dto.lexo.DictionaryAssociateEntryRequest;
import it.cnr.ilc.maia.dto.lexo.DictionaryCreateAndAssociateReferralEntryRequest;
import it.cnr.ilc.maia.dto.lexo.DictionaryCreateEntryRequest;
import it.cnr.ilc.maia.dto.lexo.DictionaryEntries;
import it.cnr.ilc.maia.dto.lexo.DictionaryEntriesRequest;
import it.cnr.ilc.maia.dto.lexo.DictionaryEntriesResponse;
import it.cnr.ilc.maia.dto.lexo.DictionaryEntryCoreResponse;
import it.cnr.ilc.maia.dto.lexo.DictionaryEntryCore;
import it.cnr.ilc.maia.dto.lexo.LexiconCreateEntryRequest;
import it.cnr.ilc.maia.dto.lexo.LexicalEntry;
import it.cnr.ilc.maia.dto.lexo.LexicographicComponent;
import it.cnr.ilc.maia.dto.lexo.LexicographicComponentResponse;
import it.cnr.ilc.maia.dto.lexo.LexiconCreateAndAssociateEntryRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lexo")
public class LexoController extends ExternController {

    @GetMapping("data/dictionaryEntry")
    public DictionaryEntryCoreResponse dataDictionaryEntry(@RequestParam(required = true) String id) throws Exception {
        DictionaryEntryCore dictionaryEntry = lexoGetDictionaryEntry(id);
        return new DictionaryEntryCoreResponse(dictionaryEntry);
    }

    @PostMapping("dictionary/create/entry")
    public DictionaryEntryCoreResponse dictionaryCreateEntry(@RequestParam String author, @RequestParam(required = true) String prefix, @RequestParam(required = true) String baseIRI, @RequestBody DictionaryCreateEntryRequest request) throws Exception {
        DictionaryEntryCore dictionaryEntry = lexoCreateDictionaryEntry(author, prefix, baseIRI);
        lexoUpdateDictionaryEntry(author, dictionaryEntry.getDictionaryEntry(), "http://www.w3.org/ns/lemon/lexicog#entry", request.getLang());
        lexoUpdateDictionaryEntry(author, dictionaryEntry.getDictionaryEntry(), "http://www.w3.org/2000/01/rdf-schema#label", request.getLabel());
        dictionaryEntry = lexoGetDictionaryEntry(dictionaryEntry.getDictionaryEntry());
        return new DictionaryEntryCoreResponse(dictionaryEntry);
    }

    private DictionaryEntryCore lexoCreateDictionaryEntry(String author, String prefix, String baseIRI) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Accept", Arrays.asList("application/json"));
        headers.put("Authorization", Arrays.asList(httpServletRequest.getHeader("Authorization")));
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        String url = "/create/dictionaryEntry?author={author}&prefix={prefix}&baseIRI={baseIRI}";
        Map<String, String> params = Map.of("author", author, "prefix", prefix, "baseIRI", baseIRI);
        ResponseEntity<String> response = restTemplate().exchange(url, HttpMethod.GET, entity, String.class, params);
        return new ObjectMapper().readValue(response.getBody(), DictionaryEntryCore.class);
    }

    public static record UpdateEntry(String relation, Object value) {

    }

    private void lexoUpdateDictionaryEntry(String author, String id, String relation, String value) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Content-Type", Arrays.asList("application/json"));
        headers.put("Authorization", Arrays.asList(httpServletRequest.getHeader("Authorization")));
        HttpEntity<UpdateEntry> entity = new HttpEntity<>(new UpdateEntry(relation, value), headers);
        String url = "/update/dictionaryEntry?author={author}&id={id}";
        Map<String, String> params = Map.of("author", author, "id", id);
        restTemplate().exchange(url, HttpMethod.POST, entity, String.class, params);
    }

    private DictionaryEntryCore lexoGetDictionaryEntry(String id) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Accept", Arrays.asList("application/json"));
        headers.put("Authorization", Arrays.asList(httpServletRequest.getHeader("Authorization")));
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        String url = "/data/dictionaryEntry?id={id}";
        Map<String, String> params = Map.of("id", id);
        ResponseEntity<String> response = restTemplate().exchange(url, HttpMethod.GET, entity, String.class, params);
        return new ObjectMapper().readValue(response.getBody(), DictionaryEntryCore.class);
    }

    @PostMapping("lexicon/create/entry")
    public LexicalEntry lexiconCreateEntry(@RequestParam String author, @RequestParam(required = true) String prefix, @RequestParam(required = true) String baseIRI, @RequestBody LexiconCreateEntryRequest request) throws Exception {
        LexicalEntry lexicalEntry = lexoCreateLexicalEntry(author, prefix, baseIRI);
        lexoUpdateLexicalEntry(author, lexicalEntry.getLexicalEntry(), "http://www.w3.org/ns/lemon/lime#entry", request.getLang());
        lexoUpdateLexicalEntry(author, lexicalEntry.getLexicalEntry(), "http://www.w3.org/2000/01/rdf-schema#label", request.getLabel());
        lexoUpdateLexicalEntry(author, lexicalEntry.getLexicalEntry(), "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", request.getType());
        lexoUpdateLinguisticRelation(author, lexicalEntry.getLexicalEntry(), "morphology", "http://www.lexinfo.net/ontology/3.0/lexinfo#partOfSpeech", request.getPos());
        return lexoGetLexicalEntry(lexicalEntry.getLexicalEntry());
    }

    private LexicalEntry lexoCreateLexicalEntry(String author, String prefix, String baseIRI) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Accept", Arrays.asList("application/json"));
        headers.put("Authorization", Arrays.asList(httpServletRequest.getHeader("Authorization")));
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        String url = "/create/lexicalEntry?author={author}&prefix={prefix}&baseIRI={baseIRI}";
        Map<String, String> params = Map.of("author", author, "prefix", prefix, "baseIRI", baseIRI);
        ResponseEntity<String> response = restTemplate().exchange(url, HttpMethod.GET, entity, String.class, params);
        return new ObjectMapper().readValue(response.getBody(), LexicalEntry.class);
    }

    private void lexoUpdateLexicalEntry(String author, String id, String relation, Object value) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Content-Type", Arrays.asList("application/json"));
        headers.put("Authorization", Arrays.asList(httpServletRequest.getHeader("Authorization")));
        HttpEntity<UpdateEntry> entity = new HttpEntity<>(new UpdateEntry(relation, value), headers);
        String url = "/update/lexicalEntry?author={author}&id={id}";
        Map<String, String> params = Map.of("author", author, "id", id);
        restTemplate().exchange(url, HttpMethod.POST, entity, String.class, params);
    }

    public static record UpdateRelation(String type, String relation, Object value) {

    }

    private void lexoUpdateLinguisticRelation(String author, String id, String type, String relation, Object value) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Content-Type", Arrays.asList("application/json"));
        headers.put("Authorization", Arrays.asList(httpServletRequest.getHeader("Authorization")));
        HttpEntity<UpdateRelation> entity = new HttpEntity<>(new UpdateRelation(type, relation, value), headers);
        String url = "/update/linguisticRelation?author={author}&id={id}";
        Map<String, String> params = Map.of("author", author, "id", id);
        restTemplate().exchange(url, HttpMethod.POST, entity, String.class, params);
    }

    private LexicalEntry lexoGetLexicalEntry(String id) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Accept", Arrays.asList("application/json"));
        headers.put("Authorization", Arrays.asList(httpServletRequest.getHeader("Authorization")));
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        String url = "/data/lexicalEntry?id={id}&module={module}";
        Map<String, String> params = Map.of("id", id, "module", "core");
        ResponseEntity<String> response = restTemplate().exchange(url, HttpMethod.GET, entity, String.class, params);
        return new ObjectMapper().readValue(response.getBody(), LexicalEntry.class);
    }

    @PostMapping("dictionary/associate/entry")
    public Date dictionaryAssociateEntry(@RequestParam String author, @RequestParam(required = true) String prefix, @RequestParam(required = true) String baseIRI, @RequestBody DictionaryAssociateEntryRequest request) throws Exception {
        LexicographicComponent lexicographicComponent = lexoCreateLexicographicComponent(author, prefix, baseIRI);
        lexoUpdateLinguisticRelation(author, lexicographicComponent.getComponent(), "lexicog", "http://www.w3.org/ns/lemon/lexicog#describes", request.getLexicalEntryId());
        lexoUpdateLexicographicComponentPosition(request.getDictionaryEntryId(), "lexicog", "http://www.w3.org/1999/02/22-rdf-syntax-ns#_n", lexicographicComponent.getComponent(), request.getPosition());
        return new Date();
    }

    private LexicographicComponent lexoCreateLexicographicComponent(String author, String prefix, String baseIRI) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Accept", Arrays.asList("application/json"));
        headers.put("Authorization", Arrays.asList(httpServletRequest.getHeader("Authorization")));
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        String url = "/create/lexicographicComponent?author={author}&prefix={prefix}&baseIRI={baseIRI}";
        Map<String, String> params = Map.of("author", author, "prefix", prefix, "baseIRI", baseIRI);
        ResponseEntity<String> response = restTemplate().exchange(url, HttpMethod.GET, entity, String.class, params);
        return new ObjectMapper().readValue(response.getBody(), LexicographicComponent.class);
    }

    public static record UpdateLexicographicComponent(String type, String relation, String component, Integer position) {

    }

    private void lexoUpdateLexicographicComponentPosition(String id, String type, String relation, String component, Integer position) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Content-Type", Arrays.asList("application/json"));
        headers.put("Authorization", Arrays.asList(httpServletRequest.getHeader("Authorization")));
        HttpEntity<UpdateLexicographicComponent> entity = new HttpEntity<>(new UpdateLexicographicComponent(type, relation, component, position), headers);
        String url = "/update/lexicographicComponentPosition?id={id}";
        Map<String, String> params = Map.of("id", id);
        restTemplate().exchange(url, HttpMethod.POST, entity, String.class, params);
    }

    @PostMapping("dictionary/createAndAssociate/entry")
    public Date dictionaryCreateAssociateEntry(@RequestParam String author, @RequestParam(required = true) String prefix, @RequestParam(required = true) String baseIRI, @RequestBody LexiconCreateAndAssociateEntryRequest request) throws Exception {
        LexicalEntry lexicalEntry = lexoCreateLexicalEntry(author, prefix, baseIRI);
        lexoUpdateLexicalEntry(author, lexicalEntry.getLexicalEntry(), "http://www.w3.org/ns/lemon/lime#entry", request.getLang());
        lexoUpdateLexicalEntry(author, lexicalEntry.getLexicalEntry(), "http://www.w3.org/2000/01/rdf-schema#label", request.getLabel());
        lexoUpdateLexicalEntry(author, lexicalEntry.getLexicalEntry(), "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", request.getType());
        lexoUpdateLinguisticRelation(author, lexicalEntry.getLexicalEntry(), "morphology", "http://www.lexinfo.net/ontology/3.0/lexinfo#partOfSpeech", request.getPos());
        LexicographicComponent lexicographicComponent = lexoCreateLexicographicComponent(author, prefix, baseIRI);
        lexoUpdateLinguisticRelation(author, lexicographicComponent.getComponent(), "lexicog", "http://www.w3.org/ns/lemon/lexicog#describes", lexicalEntry.getLexicalEntry());
        lexoUpdateLexicographicComponentPosition(request.getDictionaryEntryId(), "lexicog", "http://www.w3.org/1999/02/22-rdf-syntax-ns#_n", lexicographicComponent.getComponent(), request.getPosition());
        return new Date();
    }

    @GetMapping("data/lexicographicComponents")
    public List<LexicographicComponentResponse> dataLexicographicComponents(@RequestParam(required = true) String id) throws Exception {
        List<LexicographicComponent> components = lexoLexicographicComponents(id);
        return components.stream().map(c -> new LexicographicComponentResponse(c)).collect(Collectors.toList());
    }

    private List<LexicographicComponent> lexoLexicographicComponents(String id) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Accept", Arrays.asList("application/json"));
        headers.put("Authorization", Arrays.asList(httpServletRequest.getHeader("Authorization")));
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        String url = "/data/lexicographicComponents?id={id}";
        Map<String, String> params = Map.of("id", id);
        ResponseEntity<String> response = restTemplate().exchange(url, HttpMethod.GET, entity, String.class, params);
        return new ObjectMapper().readValue(response.getBody(), new TypeReference<List<LexicographicComponent>>() {
        });
    }

    @PostMapping("data/dictionaryEntries")
    public DictionaryEntriesResponse dataDictionaryEntries(@RequestBody DictionaryEntriesRequest request) throws Exception {
        DictionaryEntries dictionaryEntries = lexoDataDictionaryEntries(request);
        return new DictionaryEntriesResponse(dictionaryEntries);
    }

    private DictionaryEntries lexoDataDictionaryEntries(DictionaryEntriesRequest request) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Accept", Arrays.asList("application/json"));
        headers.put("Authorization", Arrays.asList(httpServletRequest.getHeader("Authorization")));
        HttpEntity<DictionaryEntriesRequest> entity = new HttpEntity<>(request, headers);
        String url = "/data/dictionaryEntries";
        Map<String, String> params = Map.of();
        ResponseEntity<String> response = restTemplate().exchange(url, HttpMethod.POST, entity, String.class, params);
        return new ObjectMapper().readValue(response.getBody(), DictionaryEntries.class);
    }

    @PostMapping("dictionary/associate/referralEntry")
    public Date dataDictionaryEntries(@RequestBody AssociateReferralEntryRequest request) throws Exception {
        lexoUpdateGenericRelation(request.getReferralEntryId(), "reference", "http://www.w3.org/2002/07/owl#sameAs", request.getFullEntryId());
        return new Date();
    }

    private void lexoUpdateGenericRelation(String id, String type, String relation, Object value) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Content-Type", Arrays.asList("application/json"));
        headers.put("Authorization", Arrays.asList(httpServletRequest.getHeader("Authorization")));
        HttpEntity<UpdateRelation> entity = new HttpEntity<>(new UpdateRelation(type, relation, value), headers);
        String url = "/update/genericRelation?id={id}";
        Map<String, String> params = Map.of("id", id);
        restTemplate().exchange(url, HttpMethod.POST, entity, String.class, params);
    }

    @PostMapping("dictionary/createAndAssociate/referralEntry")
    public Date dictionaryCreateAndAssociateReferralEntry(@RequestParam String author, @RequestParam(required = true) String prefix, @RequestParam(required = true) String baseIRI, @RequestBody DictionaryCreateAndAssociateReferralEntryRequest request) throws Exception {
        DictionaryEntryCore dictionaryFullEntry = lexoCreateDictionaryEntry(author, prefix, baseIRI);
        lexoUpdateDictionaryEntry(author, dictionaryFullEntry.getDictionaryEntry(), "http://www.w3.org/ns/lemon/lexicog#entry", request.getLang());
        lexoUpdateDictionaryEntry(author, dictionaryFullEntry.getDictionaryEntry(), "http://www.w3.org/2000/01/rdf-schema#label", request.getFullEntryLabel());
        DictionaryEntryCore dictionaryReferralEntry = lexoCreateDictionaryEntry(author, prefix, baseIRI);
        lexoUpdateDictionaryEntry(author, dictionaryReferralEntry.getDictionaryEntry(), "http://www.w3.org/ns/lemon/lexicog#entry", request.getLang());
        lexoUpdateDictionaryEntry(author, dictionaryReferralEntry.getDictionaryEntry(), "http://www.w3.org/2000/01/rdf-schema#label", request.getReferralEntryLabel());
        lexoUpdateGenericRelation(dictionaryReferralEntry.getDictionaryEntry(), "reference", "http://www.w3.org/2002/07/owl#sameAs", dictionaryFullEntry.getDictionaryEntry());
        return new Date();
    }

    @GetMapping("data/dictionaryEntryByLexicalEntry")
    public List<String> dataDictionaryByLexicalEntry(@RequestParam(required = true) String id) throws Exception {
        List<DictionaryEntryCore> dictionaryEntryCores = lexoGetDictionaryEntryByLexicalEntry(id);
        return dictionaryEntryCores.stream().map(d -> d.getLabel()).collect(Collectors.toList());
    }

    private List<DictionaryEntryCore> lexoGetDictionaryEntryByLexicalEntry(String id) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Accept", Arrays.asList("application/json"));
        headers.put("Authorization", Arrays.asList(httpServletRequest.getHeader("Authorization")));
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        String url = "/data/dictionaryEntryByLexicalEntry?id={id}";
        Map<String, String> params = Map.of("id", id);
        ResponseEntity<String> response = restTemplate().exchange(url, HttpMethod.GET, entity, String.class, params);
        return new ObjectMapper().readValue(response.getBody(), new TypeReference<List<DictionaryEntryCore>>() {
        });
    }

}
