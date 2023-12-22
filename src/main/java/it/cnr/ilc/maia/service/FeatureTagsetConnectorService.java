package it.cnr.ilc.maia.service;

import it.cnr.ilc.maia.repository.FeatureRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.google.common.base.Preconditions.checkArgument;

@Service
@Slf4j
@RequiredArgsConstructor
public class FeatureTagsetConnectorService {

    @NonNull
    private final FeatureRepository featureRepository;

    public boolean existsAnyFeatureAssociatedToTagset(Long tagsetId) {
        return featureRepository.existsByTagset_Id(tagsetId);
    }
}
