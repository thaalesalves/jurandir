package es.thalesalv.jurandir.adapter.data.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContextEntrySetRepository extends JpaRepository<ContextEntrySetRepository, UUID> {}