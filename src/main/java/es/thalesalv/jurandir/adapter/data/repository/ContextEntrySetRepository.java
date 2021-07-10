package es.thalesalv.jurandir.adapter.data.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.thalesalv.jurandir.adapter.data.entity.ContextEntrySetEntity;

@Repository
public interface ContextEntrySetRepository extends JpaRepository<ContextEntrySetEntity, UUID> {}