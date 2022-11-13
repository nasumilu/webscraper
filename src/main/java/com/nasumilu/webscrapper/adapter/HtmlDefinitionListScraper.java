package com.nasumilu.webscrapper.adapter;

import org.apache.commons.csv.CSVPrinter;
import org.jsoup.nodes.Element;

import java.io.IOException;

/**
 * HtmlElementScraper responsible for scraping the contents of a simple html definition list (&lt;dl&gt;).
 *
 * <p>The definition list's content is placed into two columns, the term and definition</p>
 */
public class HtmlDefinitionListScraper extends AbstractHtmlElementScraper {

    public HtmlDefinitionListScraper() {
        super("dl");
    }

    @Override
    public void scrap(Element element, CSVPrinter printer) {
        var dt = element.getElementsByTag("dt");
        var dd = element.getElementsByTag("dd");
        var length = Math.min(dt.size(), dd.size());

        for(var i = 0; i < length; i++) {
            try {
                printer.printRecord(dt.get(i).text(), dd.get(i).text());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
