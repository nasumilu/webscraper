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

import com.nasumilu.webscrapper.exception.InvalidElementException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * HtmlElementScraper responsible for scraping the content of a well-formed html table.
 *
 * <p>A well formed HTML table <strong>SHALL</strong> have an HTMLTHeadElement and HTMLTBodyElement and
 * <strong>MUST</strong> not use any table row or column spanning. Basically, all rows in the HTMLTHeadElement and
 * HTMLTBodyElement have the same shape.</p>
 */
public class HtmlTableScraper extends AbstractHtmlElementScraper {

    /**
     * Constructs the HtmlTableScraper
     */
    public HtmlTableScraper() {
        super("table");
    }

    @Override
    public void scrap(Element element, CSVPrinter printer) {

        Optional.ofNullable(element.getElementsByTag("thead").first()).ifPresent(
                thead -> {
                    try {
                        // single row
                        printer.printRecord(thead.select("tr th").stream().map(Element::text)
                                .collect(Collectors.toList()));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );

        Optional.ofNullable(element.getElementsByTag("tbody").first()).ifPresent(
                tbody -> tbody.select("tr").forEach(
                        tr -> {
                            try {
                                printer.printRecord(tr.select("td")
                                        .stream()
                                        .map(Element::text)
                                        .collect(Collectors.toList())
                                );
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                )
        );
    }
}
