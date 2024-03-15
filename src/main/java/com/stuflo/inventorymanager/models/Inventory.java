package com.stuflo.inventorymanager.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;
import java.util.Set;

@Entity(name = "Inventories")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Inventory extends RepresentationModel<Inventory> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "categories", joinColumns = @JoinColumn(name = "inventory_id"))
    @Column(name = "category", nullable = false)
    private Set<String> categories;

    private String type;

    @OneToMany
    @ToString.Exclude
    private Set<InventoryItem> inventoryItems;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Inventory inventory = (Inventory) o;
        return getId() != null && Objects.equals(getId(), inventory.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
