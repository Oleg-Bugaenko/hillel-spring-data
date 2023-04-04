import cart.store.entity.Cart;
import cart.store.entity.Product;
import cart.store.entity.makingTables.MakingTables;
import cart.store.repository.CartRepo;
import cart.store.repository.ProductRepo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("cart.store");

//create new table
        MakingTables makingTables = context.getBean(MakingTables.class);
        makingTables.createDatabase();

        ProductRepo productRepo = context.getBean(ProductRepo.class);
        CartRepo cartRepo = context.getBean(CartRepo.class);


//3.1 Add product
        System.out.println("Add all products ");
        addAllProduct(productRepo);

    //3.4 Get and print all product
        System.out.println("All products: ");
        List<Product> productList = productRepo.getAll();
        productList.forEach(System.out::println);

    //3.2 delete product by id
        System.out.println("\ndelete product by id = 3");
        productRepo.deleteById(3);

    //3.3 find product by id
        System.out.println("find product by id = 4");
        System.out.println(productRepo.findById(4));


//4.1 Add 4 carts
        System.out.println("Add 4 carts ");
        for (int i = 1; i <= 4; i++) cartRepo.add(new Cart());

    //4.2 delete cart
        System.out.println("Delete cart 3");
        cartRepo.deleteById(3);


    //4.3 add product to the cart
        System.out.println("\nAdd product to cart");
        Product[] products = {productRepo.findById(2),
                productRepo.findById(1),
                productRepo.findById(5)
        };
        Cart cart = new Cart();
        cartRepo.add(cart);
        cartRepo.addProductToCart(cart, products);

        cart = cartRepo.findById(5);
        System.out.println(cart);

    }

    private static void addAllProduct(ProductRepo productRepo) {
        List<Product> products = new ArrayList<>() {{
            add(new Product("Milk", 45.6));
            add(new Product("Black Bred", 22.3));
            add(new Product("Sugar", 34.0));
            add(new Product("White Bred", 24.0));
            add(new Product("Salt", 12.0));
            add(new Product("Water", 11.2));
        }};
        products.forEach(productRepo::add);
    }
}
