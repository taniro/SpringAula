package tads.eaj.aula;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimeService {

	private AnimeRepository animeRepository;

	@Autowired
	public void setAnimeRepository(AnimeRepository animeRepository) {
		this.animeRepository = animeRepository;
	}

	public List<Anime> findAll(){
		return animeRepository.findAll();
	}

	public void add(Anime anime){
		animeRepository.save(anime);
	}

	public Anime get(Long id){
		return animeRepository.getOne(id);
	}

	public void delete(Long id){
		animeRepository.deleteById(id);
	}
}
