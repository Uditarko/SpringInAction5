package tacos.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sun.el.stream.Stream;

import lombok.extern.slf4j.Slf4j;
import tacos.domain.models.Ingredient;
import tacos.domain.models.Taco;
import tacos.domain.models.util.IngredientType;

@Controller
@RequestMapping(path = "/design")
@Slf4j
public class DesignTacoController {

	@GetMapping
	public String showDesignForm(final Model model) {
		final List<Ingredient> ingredients = Arrays.asList(
				new Ingredient("FLTO", "Flour Tortilla", IngredientType.WRAP),
				new Ingredient("COTO", "Corn Tortilla", IngredientType.WRAP),
				new Ingredient("GRBF", "Ground Beef", IngredientType.PROTEIN),
				new Ingredient("CARN", "Carnitas", IngredientType.PROTEIN),
				new Ingredient("TMTO", "Diced Tomatoes", IngredientType.VEGGIES),
				new Ingredient("LETC", "Lettuce", IngredientType.VEGGIES),
				new Ingredient("CHED", "Cheddar", IngredientType.CHEESE),
				new Ingredient("JACK", "Monterrey Jack", IngredientType.CHEESE),
				new Ingredient("SLSA", "Salsa", IngredientType.SAUCE),
				new Ingredient("SRCR", "Sour Cream", IngredientType.SAUCE));
		Arrays.stream(IngredientType.values()).peek(j -> log.info("j = {}",j)).forEach(type -> {
			model.addAttribute(type.name().toLowerCase(), ingredients.stream().filter(in -> in.getIngredientType().equals(type))
					.peek(i -> log.info("i = {}",i)).collect(Collectors.toList()));
		});
		model.addAttribute("design", new Taco());
		return "design";
	}
	
	@PostMapping
	public String processDesign(@Valid Taco taco, Errors errors) {
		if(errors.hasErrors())
		{
			return "design";
		}
		log.info("Processing custom taco request : {}",taco);
		return "redirect:/orders/current";
	}
		
}
