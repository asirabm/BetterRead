package io.better.read;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.book.track.book.Book;
import com.book.track.book.BookRepository;

@Controller
public class BookControler {
	private final String Cover_PAGE="http://covers.openLibrary.org/b/id/";
	@Autowired
	BookRepository bookrepo;
	String bookName;
	
	@RequestMapping("/book/{bookid}")
	public String getBook(@PathVariable String bookid ,Model model) {
		System.out.println(bookid);
		Optional<Book> op=bookrepo.findById(bookid);
		
		if(op.isPresent()) {
			String coverImageUrl="/images/noavailable.jpg";
			model.addAttribute("book",op.get());
			if(op.get().getCoverIds()!=null & op.get().getCoverIds().size()>0) {
				coverImageUrl=Cover_PAGE+ op.get().getCoverIds().get(0)+ "-L.jpg";
			}
			model.addAttribute("coverImage", coverImageUrl);
			return "book";
		}
		else {
			return "BookNF";
		}
		
	}


}
