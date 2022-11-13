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
import org.jsoup.nodes.Element;

/**
 * Base class which implements the {@link HtmlElementScraper#canScrape(Element) HtmlElementScraper.canScrape()} method.
 */
public abstract class AbstractHtmlElementScraper implements HtmlElementScraper  {

    /**
     * The name of the html element which this scraper adapter handles.
     */
    private final String tagName;

    /**
     * Constructor used by subclasses which <strong>MUST</strong> provide the name of the element it is responsible for
     * scraping.
     *
     * @param tagName The element name
     */
    protected AbstractHtmlElementScraper(String tagName) {
        this.tagName = tagName.toLowerCase();
    }

    @Override
    public boolean canScrape(Element element) {
        return element.tagName().toLowerCase().equals(this.tagName);
    }

}
