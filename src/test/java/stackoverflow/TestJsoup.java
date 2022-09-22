package stackoverflow;

import static org.junit.Assert.*;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

public class TestJsoup {

    @Test
    public void test() throws IOException {
        Document doc = Jsoup.connect("https://www.ilan.gov.tr/ilan/kategori/9/ihale-duyurulari").get();

        // System.out.println(doc.outerHtml());
//        for (Element row : doc.select("search-results-row")) {
//
//            final String title = row.select(".list-desc ng-tns-c152-3").text();
//            final String title1 = row.select(".col col-4 col-lg-4 col-border ng-star-inserted").text();
//
//            System.out.println(title);
//        }
        System.out.println(doc);
        for (Element row : doc.select("div.search-results-row")) {
            System.out.println("-------");
            for (Element col : row.select("div.col"))
                System.out.println(col.text());
        }
    }

}
