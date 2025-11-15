package br.com.equipe4.app_produtos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_usuario")
@EntityListeners(AuditingEntityListener.class)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "email", length = 150, nullable = false)
    private String email;

    @Column(name = "senha", length = 100, nullable = false)
    @Min(6)
    private String senha;

    @CreatedDate
    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDate criado_em;

    @LastModifiedDate
    @Column(name = "atualizado_em", nullable = false)
    private LocalDate atualizado_em;
}
