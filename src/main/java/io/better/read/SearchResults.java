package io.better.read;

import java.util.List;

public class SearchResults {
	private String numFound;
	private List<SearchBooks> docs;
	public String getNumFound() {
		return numFound;
	}
	public void setNumFound(String numFound) {
		this.numFound = numFound;
	}
	public List<SearchBooks> getDocs() {
		return docs;
	}
	public void setDocs(List<SearchBooks> docs) {
		this.docs = docs;
	}
	
}
