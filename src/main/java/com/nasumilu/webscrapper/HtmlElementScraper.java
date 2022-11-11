package com.nasumilu.webscrapper;

import org.jsoup.nodes.Element;

import java.io.IOException;

/**
 * HtmlElementScraper is the base service class used to scrap the content from and Html element and send that content
 * to stdout.
 */
public interface HtmlElementScraper {

    /**
     * Indicates that this HtmlElementScraper is responsible for scraping the elements content.
     * @param element The element to check if this {@link HtmlElementScraper} can scrap
     * @return true if this {@link HtmlElementScraper} can scrap the element's content; false otherwise.
     */
    boolean canScrap(Element element);

    /**
     * Scraps the html elements content and send to stdout
     *
     * @param element The element to scrap
     * @throws IOException when an I/O issue occurs
     */
    void scrap(Element element) throws IOException;

}
