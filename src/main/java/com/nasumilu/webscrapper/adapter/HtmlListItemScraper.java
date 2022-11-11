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
 * HtmlElementScraper responsible for scraping and sending the content of a simple, well-formed (un)ordered list element.
 *
 * <p>When provided an HTMLOLListElement or HTMLUOListElement this scraper will extract the HTMLLIElement's text content
 * as a valid RFC4180 CSV string to stdout.</p>
 * <p>Example:</p>
 * <pre><code>
 *     java –jar webscrapper.jar --url  https://www.sfcollege.edu/about/accreditation/index  --selector "//ul[@class='perc-navbar-vertical']"
 * </code></pre>
 * <p>Output:</p>
 * <code><pre>
 *  About SF
 *  Office of the President
 *  Offices and Services
 *  History of the College
 *  Accreditation
 *  Programs of Study
 * </pre></code>
 */
public class HtmlListItemScraper implements HtmlElementScraper {

    @Override
    public boolean canScrap(Element element) {
        var tag = element.tagName().toLowerCase();
        return tag.equals("ul") || tag.equals("ol");
    }

    @Override
    public void scrap(Element element) throws IOException {
        try (final var printer = new CSVPrinter(System.out, CSVFormat.DEFAULT)) {
            element.getElementsByTag("li").forEach(item -> {
                try {
                    printer.printRecord(item.text());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            printer.close(true);
        }
    }
}
