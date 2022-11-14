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
package com.nasumilu.webscrapper;

import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.jsoup.Jsoup;
import java.io.IOException;
import java.util.ServiceLoader;


/**
 * The application's launcher (a.k.a main)
 */
public class Launcher {

    private static final Options OPTIONS = new Options();
    private static final ServiceLoader<HtmlElementScraper> SCRAPERS = ServiceLoader.load(HtmlElementScraper.class);

    /*
     * Configure the applications cli options
     */
    static {
        OPTIONS.addOption("u", "url", true, "The url of the web page to scrap.");
        OPTIONS.addOption("s", "selector", true, "The xpath element selector.");
        OPTIONS.addOption("h", "help", false, "Prints out the help message");
    }

    /**
     * Execute the scraper with the given options
     *
     * @param args
     * @throws ParseException
     * @throws IOException
     */
    public static void main(String[] args) throws ParseException, IOException {
        var parser = new DefaultParser();
        var cli = parser.parse(OPTIONS, args);
        if (cli.hasOption("help")) {
            showHelp();
        }

        if (!cli.hasOption("selector") || !cli.hasOption("url")) {
            System.out.println("Please specify the URL and/or element selector to scrap!");
            showHelp();
        }

        scrape(cli.getOptionValue("url"), cli.getOptionValue("selector"));
    }

    /**
     * Scrap the document received from the url by searching for an appropriate HTMLElementScraper which is responsible
     * for extracting the elements content.
     *
     * @param uri
     * @param selector
     * @throws IOException
     */
    public static void scrape(String uri, String selector) throws IOException {
        var document = Jsoup.connect(uri).get();
        var element = document.selectXpath(selector).first();
        var printer = new CSVPrinter(System.out, CSVFormat.DEFAULT);
        if (null != element) {
            for (var scraper : SCRAPERS) {
                if (scraper.canScrape(element)) {
                    scraper.scrape(element, printer);
                    return;
                }
            }
        }
        printer.close(true);
    }

    private static void showHelp() {
        var formatter = new HelpFormatter();
        formatter.printHelp("scrape", OPTIONS, true);
        System.exit(0);
    }

}
