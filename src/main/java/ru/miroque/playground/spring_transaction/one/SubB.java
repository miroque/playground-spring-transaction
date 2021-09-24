package ru.miroque.playground.spring_transaction.one;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class SubB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "key_main", foreignKey = @ForeignKey(name = "fk_subb__maina"))
    @ToString.Exclude
    private MainA main;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SubB subB = (SubB) o;
        return Objects.equals(id, subB.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
