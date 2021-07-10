package es.thalesalv.jurandir.adapter.data.entity;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "context_entry")
public class ContextEntryEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "entry_id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "key")
    private String key;

    @Column(name = "entry")
    private String entry;

    @Column(name = "is_fixed")
    private boolean isFixed;

    @ManyToMany
    @JoinTable(name = "entry_set_has_entries",
            joinColumns = @JoinColumn(name = "entry_id", insertable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "scenario_id", insertable = false, updatable = false))
    private Set<ContextEntrySetEntity> contextEntrySets;
}
