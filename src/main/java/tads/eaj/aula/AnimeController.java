package tads.eaj.aula;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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
		HttpSession session = request.getSession();
		session.setAttribute("opa", "Aula");
		request.getCookies();
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
	public String addAnime(@ModelAttribute @Valid Anime anime, Errors errors){
		if (errors.hasErrors()){
			return "cadastrar";
		}else{
			animeService.add(anime);
			return "redirect:/";
		}
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

	@RequestMapping("/setcookies")
	public String gravaCookie(HttpServletResponse response){
		Cookie c = new Cookie("nome", "marcos");
		response.addCookie(c);
		return "cookies";
	}


	@RequestMapping("/readcookies")
	public String lerCookie(@CookieValue(value = "nome", defaultValue = "taniro") String nome){
		System.out.println(nome);
		return "cookies";
	}

	@RequestMapping("/testes")
	public String testes(Model model){
		model.addAttribute("aula", "rapaz...");
		return "testes";
	}

}
