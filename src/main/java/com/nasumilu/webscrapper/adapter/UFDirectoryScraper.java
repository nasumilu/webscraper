package com.nasumilu.webscrapper.adapter;

import com.nasumilu.webscrapper.HtmlElementScraper;
import org.apache.commons.csv.CSVPrinter;
import org.jsoup.nodes.Element;

public class UFDirectoryScraper implements HtmlElementScraper {
    @Override
    public boolean canScrape(Element element) {
        // should consider and an url/uri argument to avoid dispatching an incorrect scraper.
        // must match element <article> with and id of `main-content`
        return element.tagName().equalsIgnoreCase("article") && element.id().equals("main-content");
    }

    /**
     * @// TODO: 11/14/22 scrap the data here.
     */
    @Override
    public void scrape(Element element, CSVPrinter printer) {
        System.out.println(element);
    }
}
