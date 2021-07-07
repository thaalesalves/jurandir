package es.thalesalv.jurandir.adapter.data.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.thalesalv.jurandir.adapter.data.entity.ContextEntryEntity;

@Repository
public interface ContextEntryRepository extends JpaRepository<ContextEntryEntity, UUID> {

}
