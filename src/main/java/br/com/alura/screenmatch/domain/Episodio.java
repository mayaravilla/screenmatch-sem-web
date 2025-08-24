package br.com.alura.screenmatch.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "serie")
@Table(name = "episodios")
public class Episodio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer temporada;
    private String titulo;
    private Integer numeroEpisodio;
    private Double avaliacao;
    private LocalDate dataLancamento;
    @ManyToOne
    @JoinColumn(name = "serie_id", nullable = false)
    private Serie serie;

    public Episodio (Integer temporada, DadosEpisodio dadosEpisodio){
        this.temporada = temporada;
        this.titulo = dadosEpisodio.titulo();
        this.numeroEpisodio = dadosEpisodio.numero();
        try {
            this.avaliacao = Double.parseDouble(dadosEpisodio.avaliacao());
        } catch (NumberFormatException ex){
            this.avaliacao = 0.0;
        }
        try {
            this.dataLancamento = LocalDate.parse(dadosEpisodio.dataLancamento());
        } catch (DateTimeParseException e){
            this.avaliacao = null;
        }
    }
}
