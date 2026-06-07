package br.com.alura.produto.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.net.URL;
import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;
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
public class Foto {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long fotoId;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String link;

    @Transient
    private String base64;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime creadoEn;

    @LastModifiedDate
    @Column
    private LocalDateTime actualizadoEn;

    public void actualizar(URL url) {
        this.link = url.toString();
    }

    public void actualizar(Foto foto) {
        if (foto == null)
            return;

        if (foto.fileName != null)
            this.fileName = foto.fileName;

        if (foto.link != null)
            this.link = foto.link;
    }
}
