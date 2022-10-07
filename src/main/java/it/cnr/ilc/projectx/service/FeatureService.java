package it.cnr.ilc.projectx.service;

import it.cnr.ilc.projectx.dto.*;
import it.cnr.ilc.projectx.model.*;
import it.cnr.ilc.projectx.repository.FeatureRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;

@Service
@Slf4j
@RequiredArgsConstructor
public class FeatureService {

    @NonNull
    private final FeatureRepository featureRepository;

    @NonNull
    private final LayerService layerService;

    @NonNull
    private final TagsetService tagsetService;

    public List<FeatureDto> retrieveAllByLayerId(Long layerId) {
        return mapToFeatureDto(featureRepository.findByLayer_Id(layerId));
    }

    @Transactional
    public CreateFeatureDto saveFeature(CreateFeatureDto featureDto) {
        Layer layer = layerService.retrieveLayer(featureDto.getLayerId());
        Tagset tagset = null;
        if (featureDto.getType() == FeatureType.TAGSET) {
            tagset = tagsetService.retrieveEntityById(Objects.requireNonNull(featureDto.getTagsetId()));
        }

        Feature feature = featureRepository.save(mapToEntity(featureDto, layer, tagset));

        return mapToCreateFeatureDto(feature);
    }

/*    public TagsetDto retrieveById(Long id) {
        return mapToTagsetDto(tagsetRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new));
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

    public Boolean canBeDeleted(Long tagsetId) {
        //TODO controllare se esite una Feature che sta usando il mio tagset
        return false;
    }

    @Transactional
    public Boolean deleteTagset(Long tagsetId) {
        if (canBeDeleted(tagsetId)) {
            tagsetRepository.deleteById(tagsetId);

            return true;
        }

        return false;
    }

    private void deleteAllTagsetValuesFromTagset(Tagset tagset) {
        tagsetValueRepository.deleteAllById(tagset
                .getValues()
                .stream()
                .map(tagsetValue -> tagsetValue.getId())
                .collect(Collectors.toList()));
    }*/

    private Feature mapToEntity(FeatureDto featureDto) {
        Feature feature = new Feature();
        BeanUtils.copyProperties(featureDto, feature);
        return feature;
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

    private Feature mapToEntity(CreateFeatureDto featureDto, Layer layer, Tagset tagset) {
        Feature feature = new Feature();
        BeanUtils.copyProperties(featureDto, feature);
        feature.setLayer(layer);

        if (tagset != null) {
            feature.setTagset(tagset);
        }

        return feature;
    }

    private List<FeatureDto> mapToFeatureDto(List<Feature> features) {
        return features.stream().map(feature -> mapToFeatureDto(feature)).collect(Collectors.toList());
    }

    private FeatureDto mapToFeatureDto(Feature feature) {
        FeatureDto featureDto = new FeatureDto();
        BeanUtils.copyProperties(feature, featureDto);
        featureDto.setLayerId(feature.getLayer().getId());

        if (feature.getType() == FeatureType.TAGSET) {
            featureDto.setTagsetId(Objects.requireNonNull(feature.getTagset().getId()));
        }

        return featureDto;
    }

    private CreateFeatureDto mapToCreateFeatureDto(Feature feature) {
        CreateFeatureDto createFeatureDto = new CreateFeatureDto();
        BeanUtils.copyProperties(feature, createFeatureDto);

        return createFeatureDto;
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
