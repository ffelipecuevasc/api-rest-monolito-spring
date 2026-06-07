package br.com.alura.produto.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.EAGER;
import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
@Builder
@EqualsAndHashCode
@Entity
@Table
@EntityListeners(AuditingEntityListener.class)
public class Producto implements Serializable {

    @Id
    @GeneratedValue
    private UUID productoId;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String categoria;

    @Getter
    @RequiredArgsConstructor
    public enum Status {
        AVAILABLE("Disponible"),
        PENDING("Pendiente"),
        SOLD("Vendido");

        private final String descripcion;
    }

    @Enumerated(STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private BigDecimal valor;

    @Singular(value = "foto", ignoreNullCollections = true)
    @OneToMany(cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "producto_id", updatable = false, nullable = false)
    private List<Foto> fotos;

    @ElementCollection(targetClass = String.class, fetch = EAGER)
    @CollectionTable(name = "tag", joinColumns = @JoinColumn(name = "producto_id"))
    @Column(name = "tag", nullable = false)
    @Singular(value = "tag", ignoreNullCollections = true)
    private List<String> tags;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime creadoEn;

    @LastModifiedDate
    @Column
    private LocalDateTime actualizadoEn;

    public void actualizar(Producto producto) {
        if (producto == null)
            return;

        if (producto.nombre != null)
            this.nombre = producto.nombre;

        if (producto.categoria != null)
            this.categoria = producto.categoria;

        if (producto.tags != null)
            this.tags = producto.tags;

        if (producto.status != null)
            this.status = producto.status;

        if (producto.descripcion != null) {
            var descStatus = switch (this.status) {
                case AVAILABLE -> "(Disponible)";
                case PENDING -> "(Pendiente)";
                case SOLD -> "(Vendido)";
            };
            this.descripcion = producto.descripcion + " " + descStatus;
        }

        if (producto.valor != null)
            this.valor = producto.valor;

        actualizar(producto.getFotos());
    }

    private void actualizar(List<Foto> fotos) {
        if (fotos == null || fotos.isEmpty())
            return;

        for (var i = 0; i < fotos.size(); i++)
            this.fotos.get(i).actualizar(fotos.get(i));
    }
}
