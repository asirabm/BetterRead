package io.better.read;

import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.book.track.book.BookRepository;

//import com.book.track.DataStaxAstraProperties;

@SpringBootApplication
@RestController
@EnableConfigurationProperties(DataStaxAstraProperties.class)
//@ComponentScan(basePackages = {"com.book.track.book"})
@EnableCassandraRepositories("com.book.track.book")
public class BetterReadApp {
	@Autowired
	DataStaxAstraProperties astraProperties;
	
	@Autowired
	BookRepository bookrepo;

	public static void main(String[] args) {
		SpringApplication.run(BetterReadApp.class, args);
	}

	@RequestMapping("/user")
	public String user(@AuthenticationPrincipal OAuth2User principal) {
		System.out.println(principal);
		return principal.getAttribute("name");
	}
	@Bean
	public CqlSessionBuilderCustomizer sessionBuilderCustomizer() {
		System.out.println(astraProperties.getSecureConnectBundle());
		Path bundle = astraProperties.getSecureConnectBundle().toPath();
		System.out.println("Hello");
		return builder -> builder.withCloudSecureConnectBundle(bundle);
	}
	

}
