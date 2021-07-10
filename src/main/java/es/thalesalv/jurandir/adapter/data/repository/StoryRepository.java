package es.thalesalv.jurandir.adapter.data.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.thalesalv.jurandir.adapter.data.entity.StoryEntity;

@Repository
public interface StoryRepository extends JpaRepository<StoryEntity, UUID> {}