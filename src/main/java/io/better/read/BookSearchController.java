package io.better.read;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Controller

@CrossOrigin(origins = "*", maxAge = 10000)
public class BookSearchController {
	private final WebClient webclient;
	private final String Cover_IMAGE_ROOT="http://covers.openLibrary.org/b/id/";
	
public BookSearchController(WebClient.Builder webclientbuilder){

	this.webclient=webclientbuilder.exchangeStrategies(ExchangeStrategies.builder()
			.codecs(c->c
					.defaultCodecs()
					.maxInMemorySize(16*1024*1024)
					
					).build()
			)
			.baseUrl("http://openlibrary.org/search.json")
			
			.build();
}

@GetMapping("/search")
public String getSearchResults(@RequestParam String query,Model model) {	
	Mono<SearchResults> m=this.webclient.get()
	.uri("?q={query}",query)
	.retrieve()
	.bodyToMono(SearchResults.class);
	SearchResults sr=m.block();
	List<SearchBooks> sb=sr.getDocs()
	.stream().limit(10)
	.map(s->{
		s.setKey(s.getKey().replace("/works/", ""));
		String coverId=s.getCover_i();
		if(StringUtils.hasText(coverId)) {
			coverId=Cover_IMAGE_ROOT+coverId+ "-M.jpg";
		}
		else {
		 	coverId="/noavailable.jpg";
		}
		s.setCover_i(coverId);
		return s;
	}).
	collect(Collectors.toList());
	model.addAttribute("m",sb);
     
	return "BookSearchResults";
}
	
}
