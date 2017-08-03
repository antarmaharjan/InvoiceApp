package me.ratna.invoice.Controller;

import me.ratna.invoice.Model.Product;
import me.ratna.invoice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
@Controller

public class MainController {
    @Autowired
    ProductRepository productRepository;
    @GetMapping("/")
    public String showIndex(Model model){
        String myMessage ="Welcome to the Invoice web Application";
        model.addAttribute("message",myMessage);
        return "index";
    }
    @GetMapping("/addProduct")
    public String addproduct(Model model)
    {
        model.addAttribute("newProduct", new Product());
        return "addproduct";
    }

    @PostMapping("/addProduct")
    //Validates the product
    public String postProduct(@Valid  @ModelAttribute("newProduct")Product product, BindingResult bindingResult) {
        System.out.println(bindingResult.toString());
        //if there is error, show to the product
        System.out.println(product.getDescription());
        if (bindingResult.hasErrors())
        {
            return "addproduct";
        }
        // if no error ,save to the db
        productRepository.save(product);
        //and show the result
        return "result";
    }
    @GetMapping("/showproducts")
    public @ResponseBody String showAllProducts(){
        Iterable<Product>productlist =productRepository.findAll();
        return productlist.toString();

    }

}

