import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class StaplesScraper {

    public static void main(String[] args) {
        String url = "https://www.staples.com/Computer-office-desks/cat_CL210795/663ea?icid=BTS:2020:STUDYSPACE:DESKS";
        List<Product> products = scrapeProducts(url);
        saveProductsToCsv(products);
    }
 public static void main(String[] args) {
        String url = "https://www.staples.com/Computer-office-desks/cat_CL210795/663ea?icid=BTS:2020:STUDYSPACE:DESKS";
        List<Product> products = scrapeProducts(url);
        saveProductsToCsv(products);
    }

    private static List<Product> scrapeProducts(String url) {
        List<Product> products = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).get();
            Elements productElements = doc.select(".card__details");
            for (Element productElement : productElements) {
                String name = productElement.select(".card__title").text();
                String price = productElement.select(".card__price").text();
                String sku = productElement.select(".card__sku").text();
                String modelNumber = productElement.select(".card__model-number").text();
                String category = productElement.select(".card__category").text();
                String description = productElement.select(".card__description").text();
                Product product = new Product(name, price, sku, modelNumber, category, description);
                products.add(product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    private static void saveProductsToCsv(List<Product> products) {
        String csvFile = "products.csv";
        try (FileWriter writer = new FileWriter(csvFile)) {
            writer.write("Product Name,Product Price,Item Number,Model Number,Product Category,Product Description\n");
            for (Product product : products) {
                writer.write(product.getName() + "," + product.getPrice() + "," + product.getSku() + "," + product.getModelNumber() + "," + product.getCategory() + "," + product.getDescription() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Product {
    private String name;
    private String price;
    private String sku;
    private String modelNumber;
    private String category;
    private String description;

    public Product(String name, String price, String sku, String modelNumber, String category, String description) {
        this.name = name;
        this.price = price;
        this.sku = sku;
        this.modelNumber = modelNumber;
        this.category = category;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getSku() {
        return sku;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }
   }

