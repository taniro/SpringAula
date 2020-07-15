package tads.eaj.aula;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Anime {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	@NotBlank
	@Size(min = 5, max = 10, message = Mensagens.ERRO_TAMANHO_MENSAGEM)
	String titulo;
	@NotBlank
	String categoria;
	@Email
	@Size(max = 250, message = Mensagens.ERRO_TAMANHO_MENSAGEM)
	String descricao;
	Integer anoLancamento;
}
