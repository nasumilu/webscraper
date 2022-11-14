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

import com.nasumilu.webscrapper.HtmlElementScraper;
import com.nasumilu.webscrapper.adapter.HtmlDefinitionListScraper;
import com.nasumilu.webscrapper.adapter.HtmlListItemScraper;
import com.nasumilu.webscrapper.adapter.HtmlTableScraper;
import com.nasumilu.webscrapper.adapter.UFDirectoryScraper;

module webscrapper {
    requires org.jsoup;
    requires commons.csv;
    requires commons.cli;

    exports com.nasumilu.webscrapper;
    exports com.nasumilu.webscrapper.adapter;
    uses HtmlElementScraper;

    provides HtmlElementScraper with HtmlTableScraper, HtmlListItemScraper, HtmlDefinitionListScraper, UFDirectoryScraper;
}