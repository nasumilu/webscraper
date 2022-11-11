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
 * HtmlElementScraper responsible for scraping and sending the content of a well-formed html table to stdout.
 *
 * <p>A well formed HTML table <strong>SHALL</strong> have an HTMLTHeadElement and HTMLTBodyElement and
 * <strong>MUST</strong> not use any table row or column spanning. Basically, all rows in the HTMLTHeadElement and
 * HTMLTBodyElement have the same shape.</p>
 *
 * <p>Example</p>
 * <pre><code>
 *  java –jar webscrapper.jar --url  https://apps.sfcollege.edu/directory/?all --selector //table
 * </code></pre>
 */
public class HtmlTableScraper extends AbstractHtmlElementScraper {

    /**
     * Constructs the HtmlTableScraper
     */
    public HtmlTableScraper() {
        super("table");
    }

    /**
     * Used to scrap the //thead/tr/th contents.
     * @param table the table to scrap it thead content
     * @param printer printer for sending the content to stdout
     */
    private static void getHeader(Element table, CSVPrinter printer) {
        Optional.ofNullable(table.getElementsByTag("thead").first())
                .ifPresentOrElse(
                        thead -> {
                            try {
                                printer.printRecord(thead.select("tr th").stream().map(Element::text).collect(Collectors.toList()));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        },
                        () -> {
                            throw InvalidElementException.createElementByTagNameNotFound("thead");
                        }
                );
    }

    /**
     * Used to scrap the //tbody/tr/td content by rows.
     * @param table the table to scrap its tbody content
     * @param printer printer for sending the content to stdout
     */
    private static void getData(Element table, CSVPrinter printer) {
        Optional.ofNullable(table.getElementsByTag("tbody").first())
                .ifPresentOrElse(
                        tbody -> tbody.select("tr")
                                .forEach(tr -> {
                                    try {
                                        printer.printRecord(tr.select("td").stream().map(Element::text).collect(Collectors.toList()));
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                }),
                        () -> {
                            throw InvalidElementException.createElementByTagNameNotFound("tbody");
                        }
                );
    }

    @Override
    public void scrap(Element element) throws IOException {
        try (final var printer = new CSVPrinter(System.out, CSVFormat.DEFAULT)) {
            getHeader(element, printer);
            getData(element, printer);
            printer.close(true);
        }
    }
}
