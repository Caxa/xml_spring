package com.miit.test.controllers;

import com.miit.test.components.Clothes;
import com.miit.test.components.ClothesItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Главная Страница");
        return "home";
    }

    @GetMapping("/store")
    public String store(Model model) {
        model.addAttribute("title", "Каталог");
        return "store";
    }

    @GetMapping("/bu")
    public String bu(Model model) {
        model.addAttribute("title", "Корзина");
        return "bu";
    }

    @GetMapping("/product")
    public String getProductDetails(@RequestParam(name = "id") String productId, Model model) {
        try {
            File file = new File("src/main/resources/clothes.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Clothes.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Clothes clothes = (Clothes) jaxbUnmarshaller.unmarshal(file);

            List<ClothesItem> items = clothes.getItems();
            for (ClothesItem item : items) {
                if (item.getId().equals(productId)) {
                    model.addAttribute("title", item.getName());
                    model.addAttribute("id", item.getId());
                    model.addAttribute("price", item.getPrice());
                    model.addAttribute("category", item.getCategory());
                    model.addAttribute("image", item.getImage());
                    model.addAttribute("description", item.getDescription());
                    return "productDetails";
                }
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return "productNotFound";
    }
}
