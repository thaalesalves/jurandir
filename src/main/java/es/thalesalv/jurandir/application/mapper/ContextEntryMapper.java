package es.thalesalv.jurandir.application.mapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import es.thalesalv.jurandir.adapter.data.entity.ContextEntryEntity;
import es.thalesalv.jurandir.domain.model.aigame.ContextEntry;

@Component
public class ContextEntryMapper implements JurandirObjectMapper<ContextEntryEntity, ContextEntry> {

    @Override
    public ContextEntry map(ContextEntryEntity input) {

        return ContextEntry.builder()
            .entry(input.getEntry())
            .id(input.getId())
            .key(input.getKey())
            .build();
    }

    @Override
    public Set<ContextEntry> map(List<ContextEntryEntity> input) {

        Set<ContextEntry> contextEntries = new HashSet<>();
        input.forEach(entity -> {
            ContextEntry entry = this.map(entity);
            contextEntries.add(entry);
        });

        return contextEntries;
    }
}
