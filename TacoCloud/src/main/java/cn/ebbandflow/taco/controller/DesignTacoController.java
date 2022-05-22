package cn.ebbandflow.taco.controller;

import cn.ebbandflow.taco.pojo.Ingredient;
import cn.ebbandflow.taco.pojo.Order;
import cn.ebbandflow.taco.pojo.Taco;
import cn.ebbandflow.taco.repository.IngredientRepository;
import cn.ebbandflow.taco.repository.TacoIngredientRepository;
import cn.ebbandflow.taco.repository.TacoRepository;
import cn.ebbandflow.taco.vo.TacoIngredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static cn.ebbandflow.taco.pojo.Ingredient.Type;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private TacoRepository tacoRepository;
    @Autowired
    private TacoIngredientRepository tacoIngredientRepository;

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(x -> ingredients.add(x));
        System.out.println(ingredients);
        Type[] types = Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
        model.addAttribute("design", new Taco());
        return "design";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @PostMapping
    public String processDesign(String name, String[] ingredients, @ModelAttribute Order order) {
        Taco design = new Taco();
        design.setName(name);
        Taco saved = tacoRepository.save(design);
        Long id = saved.getId();
        for (String i : ingredients) {
            tacoIngredientRepository.save(new TacoIngredient(id, i));
        }
        order.addDesign(saved);
        log.info("Processing design: name={},ingredients={}", name, ingredients);
        return "redirect:/orders/current";
    }
}
