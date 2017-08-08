package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RecipeController {

	private final RecipeService recipeService;

	public RecipeController(final RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@RequestMapping("/recipe/show/{id}")
	public String showById(@PathVariable Long id, Model model) {
		model.addAttribute("recipe", recipeService.findById(id));
		return "recipe/show";
	}

	@RequestMapping("/recipe/new")
	public String newRecipe(Model model) {
		model.addAttribute("recipe", new RecipeCommand());
		return "recipe/recipeform";
	}

	@RequestMapping(name = "/recipe/", method = RequestMethod.POST)
	public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
		RecipeCommand savedRecipe = recipeService.saveRecipeCommand(command);
		return "redirect:/recipe/show/" + savedRecipe.getId();
	}

}
