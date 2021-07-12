package es.thalesalv.jurandir.adapter.data.repository;

import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.thalesalv.jurandir.adapter.data.entity.ContextEntryEntity;

@Repository
public interface ContextEntryRepository extends JpaRepository<ContextEntryEntity, UUID> {

    @Query(nativeQuery=true, value="SELECT key, entry FROM context_entry WHERE (key regexp ?1)")
    Set<ContextEntryEntity> findByKeyWithRegex(String regex);
}