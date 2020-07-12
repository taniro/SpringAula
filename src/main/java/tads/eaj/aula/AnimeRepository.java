package tads.eaj.aula;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimeRepository extends JpaRepository<Anime, Long> {
	List<Anime> findByDescricao(String descricao);
	List<Anime> findByAnoLancamento(Integer ano);
}
