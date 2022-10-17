package it.cnr.ilc.projectx.service;

import it.cnr.ilc.projectx.dto.*;
import it.cnr.ilc.projectx.model.Tagset;
import it.cnr.ilc.projectx.model.TagsetValue;
import it.cnr.ilc.projectx.repository.TagsetRepository;
import it.cnr.ilc.projectx.repository.TagsetValueRepository;
import it.cnr.ilc.projectx.service.event.DeleteTagsetEvent;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;

@Service
@Slf4j
@RequiredArgsConstructor
public class TagsetService {

    @NonNull
    private final TagsetRepository tagsetRepository;

    @NonNull
    private final TagsetValueRepository tagsetValueRepository;

    @NotNull
    private final ApplicationEventPublisher applicationEventPublisher;

    @NonNull
    private final FeatureTagsetConnectorService featureTagsetConnectorService;

    public List<TagsetDto> retrieveAll() {
        return mapToTagsetDto(tagsetRepository.findAll());
    }

    public TagsetDto retrieveById(Long id) {
        return mapToTagsetDto(tagsetRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new));
    }

    public Tagset retrieveEntityById(Long id) {
        return tagsetRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public CreateTagsetDto saveTagset(CreateTagsetDto tagsetDto) {
        Tagset tagset = tagsetRepository.save(mapToEntity(tagsetDto));
        return mapToCreateTagsetDto(tagset);
    }

    @Transactional
    public UpdateTagsetDto updateTagset(UpdateTagsetDto tagsetDto) {
        checkArgument(tagsetDto != null);
        checkArgument(tagsetDto.getId() != null);
        checkArgument(tagsetDto.getName() != null);

        Optional<Tagset> tobeUpdated = tagsetRepository.findById(tagsetDto.getId());

        if (tobeUpdated.isEmpty()) {
            log.error("Cannot find tagset with ID " + tagsetDto.getId());
            throw new NotFoundException("Cannot find tagset with ID " + tagsetDto.getId());
        }

        deleteAllTagsetValuesFromTagset(tobeUpdated.get());

        tagsetRepository.save(mapToEntity(tobeUpdated.get(), tagsetDto));

        Tagset tagset = tagsetRepository.findById(tagsetDto.getId()).orElseThrow(EntityNotFoundException::new);

        return mapToUpdateTagsetDto(tagset);
    }

    @Transactional
    public Boolean delete(Long tagsetId) {
        if (canBeDeleted(tagsetId)) {
            tagsetRepository.deleteById(tagsetId);

            return true;
        }

        return false;
    }

    public Boolean canBeDeleted(Long tagsetId) {
        if (featureTagsetConnectorService.existsAnyFeatureAssociatedToTagset(tagsetId)) {
            return false;
        }

        return true;
    }

    @Transactional
    private void deleteAllTagsetValuesFromTagset(Tagset tagset) {
        tagsetValueRepository.deleteAllById(tagset
                .getValues()
                .stream()
                .map(tagsetValue -> tagsetValue.getId())
                .collect(Collectors.toList()));
    }

    private Tagset mapToEntity(TagsetDto tagsetDto) {
        Tagset tagset = new Tagset();
        BeanUtils.copyProperties(tagsetDto, tagset);
        return tagset;
    }

    private Tagset mapToEntity(Tagset existingTagset, UpdateTagsetDto updateTagsetDto) {
        existingTagset.setName(updateTagsetDto.getName());
        existingTagset.setDescription(updateTagsetDto.getDescription());

        existingTagset.setValues(
                updateTagsetDto.getValues().stream().map(createTagsetValuesDto -> {
                    TagsetValue tagsetValue = new TagsetValue();
                    tagsetValue.setTagset(existingTagset);
                    tagsetValue.setName(createTagsetValuesDto.getName());
                    tagsetValue.setDescription(createTagsetValuesDto.getDescription());

                    return tagsetValue;
                }).collect(Collectors.toList()));
        return existingTagset;
    }

    private Tagset mapToEntity(CreateTagsetDto tagsetDto) {
        Tagset tagset = new Tagset();
        BeanUtils.copyProperties(tagsetDto, tagset);

        tagset.setValues(
                tagsetDto.getValues().stream().map(createTagsetValuesDto -> {
                    TagsetValue tagsetValue = new TagsetValue();
                    tagsetValue.setTagset(tagset);
                    tagsetValue.setName(createTagsetValuesDto.getName());
                    tagsetValue.setDescription(createTagsetValuesDto.getDescription());

                    return tagsetValue;
                }).collect(Collectors.toList()));
        return tagset;
    }

    private List<TagsetDto> mapToTagsetDto(List<Tagset> tagsets) {
        return tagsets.stream().map(tagset -> mapToTagsetDto(tagset)).collect(Collectors.toList());
    }

    protected TagsetDto mapToTagsetDto(Tagset tagset) {
        TagsetDto tagsetDto = new TagsetDto();
        BeanUtils.copyProperties(tagset, tagsetDto);

        tagsetDto.setValues(
                tagset.getValues().stream().map(tagsetValue -> {
                    TagsetValuesDto toBeAdded = new TagsetValuesDto();
                    toBeAdded.setId(tagsetValue.getId());
                    toBeAdded.setName(tagsetValue.getName());
                    toBeAdded.setDescription(tagsetValue.getDescription());

                    return toBeAdded;
                }).collect(Collectors.toList()));

        return tagsetDto;
    }

    private CreateTagsetDto mapToCreateTagsetDto(Tagset tagset) {
        CreateTagsetDto tagsetDto = new CreateTagsetDto();
        BeanUtils.copyProperties(tagset, tagsetDto);
        tagsetDto.setValues(
                tagset.getValues().stream().map(tagsetValue -> {
                    CreateTagsetValuesDto toBeAdded = new CreateTagsetValuesDto();
                    toBeAdded.setName(tagsetValue.getName());
                    toBeAdded.setDescription(tagsetValue.getDescription());

                    return toBeAdded;
                }).collect(Collectors.toList()));

        return tagsetDto;
    }

    private UpdateTagsetDto mapToUpdateTagsetDto(Tagset tagset) {
        UpdateTagsetDto tagsetDto = new UpdateTagsetDto();
        BeanUtils.copyProperties(tagset, tagsetDto);
        tagsetDto.setValues(
                tagset.getValues().stream().map(tagsetValue -> {
                    UpdateTagsetValuesDto toBeAdded = new UpdateTagsetValuesDto();
                    toBeAdded.setName(tagsetValue.getName());
                    toBeAdded.setDescription(tagsetValue.getDescription());

                    return toBeAdded;
                }).collect(Collectors.toList()));

        return tagsetDto;
    }
}
