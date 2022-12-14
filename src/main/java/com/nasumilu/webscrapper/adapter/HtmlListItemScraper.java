/*
 *    Copyright 2022 Michael Lucas
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.nasumilu.webscrapper.adapter;

import com.nasumilu.webscrapper.HtmlElementScraper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.jsoup.nodes.Element;

import java.io.IOException;

/**
 * HtmlElementScraper responsible for scraping the contents of a simple, well-formed (un)ordered list element.
 */
public class HtmlListItemScraper implements HtmlElementScraper {

    @Override
    public boolean canScrape(Element element) {
        var tag = element.tagName().toLowerCase();
        return tag.equals("ul") || tag.equals("ol");
    }

    @Override
    public void scrape(Element element, CSVPrinter printer) {
            element.getElementsByTag("li").forEach(item -> {
                try {
                    printer.printRecord(item.text());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
}
