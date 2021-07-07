package es.thalesalv.jurandir.adapter.data.entity;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "scenarios")
public class ScenarioEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "scripts")
    @OneToMany(targetEntity = ScriptEntity.class, mappedBy = "scenarioId", fetch = FetchType.EAGER)
    private Set<ScriptEntity> scripts;

    @Column(name = "entries")
    @OneToMany(targetEntity = ContextEntryEntity.class, mappedBy = "scenarioId", fetch = FetchType.EAGER)
    private Set<ContextEntryEntity> contextEntries;
}
