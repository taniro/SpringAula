package tads.eaj.aula;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AnimeController {

	private AnimeService animeService;

	@Autowired
	public void setAnimeService(AnimeService animeService) {
		this.animeService = animeService;
	}

	@RequestMapping("/")
	public String getHome(Model model, HttpServletRequest request){
		List<Anime> animeList = animeService.findAll();
		model.addAttribute("animeList", animeList);
		return "index";
	}

	@RequestMapping("/cadastrar")
	public String getPageCadastro(Model model){
		var anime = new Anime();
		model.addAttribute("anime", anime);
		return "cadastrar";
	}

	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public String addAnime(@ModelAttribute Anime anime){
		animeService.add(anime);
		return "redirect:/";
	}

	@RequestMapping("/editar/{id}")
	public ModelAndView editAnime(@PathVariable(name = "id") Long id){
		var modelAndView = new ModelAndView("editar");
		var anime = animeService.get(id);
		modelAndView.addObject("anime", anime);
		return modelAndView;
	}

	@RequestMapping("/deletar/{id}")
	public String deleteAnime(@PathVariable(name = "id") Long id){
		animeService.delete(id);
		return "redirect:/";
	}
}
