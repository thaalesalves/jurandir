package es.thalesalv.jurandir.adapter.data.entity;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "script_set")
public class ScriptSetEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "context_modifier")
    private String contextModifier;

    @Column(name = "input_modifier")
    private String inputModifier;

    @Column(name = "output_modifier")
    private String outputModifier;

    @Column(name = "shared_library")
    private String sharedLibrary;

    @OneToMany
    @JoinColumn(name = "scenario_id")
    private Set<ScenarioEntity> scenarios;
}
