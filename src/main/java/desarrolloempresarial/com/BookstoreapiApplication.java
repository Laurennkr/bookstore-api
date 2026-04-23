package desarrolloempresarial.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
		"desarrolloempresarial.com",
		"desarrolloempresarial.com.bookstoreapi"
})
public class BookstoreapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreapiApplication.class, args);
	}

}
