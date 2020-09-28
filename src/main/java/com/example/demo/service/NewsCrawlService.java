package com.example.demo.service;

import com.example.demo.entity.ClickedNews;
import com.example.demo.entity.HomeNews;
import com.example.demo.entity.News;
import com.example.demo.repository.ClickedNewsRepository;
import com.example.demo.repository.HomeNewsRepository;
import com.example.demo.repository.NewsRepository;
import lombok.extern.java.Log;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Lazy
@Log
public class NewsCrawlService {
    @Autowired
    NewsRepository newsRepository;

    @Autowired
    HomeNewsRepository homeNewsRepository;

    @Autowired
    ClickedNewsRepository clickedNewsRepository;

    private Document document;
    // url커넥팅 jsoup으로 받기
    // jsoup gradle 1.11.3 그래들에 추가해서 크롤링하게 해주기
    // 이걸 베이스로 excute()하겠
    public Document connectUrl(String url) {
        log.info("connectUrl(): " + url);
        // Mozila, Gecko, Firefox, AppleWebKit, Chrome에서도 크롤링할 수 있게 Connection 각 브라우저마다 링크 주는 게 다름
        try {
            Connection.Response homepage = Jsoup.connect(url).method(Connection.Method.GET)
                    .userAgent("Mozila/5.0 (X11; Linux x86_64; rv:10.0) Gecko/20100101 " +
                            "Firefox/10.0 AppleWebKit/537.36 (KHTML, like Gecko) " +
                            "Chrome/51.0.2704.103 Safari/537.36")
                    .execute();
            // doucument에서는 여기서 얻어온 홈페이지 정보를 주겠다
            document = homepage.parse();
        } catch (Exception e) {
            System.out.println("Error in mainCrawler");
        }

        return document;
    }
    // FindAll 은 JPA
    public List<News> newsFindAll() {
        log.info("newsFindAll()");

        return newsRepository.findAll();
    }

    public List<HomeNews> homeNewsFindAll() {
        log.info("homeNewsFindAll()");

        return homeNewsRepository.findAll();
    }
    // 도큐먼트에 접근할 수 있게
    // delete->  초기화
    public void crawlingHome() {
        log.info("crawlingHome()");

        homeNewsRepository.deleteAll();
        document = connectUrl("https://news.daum.net/");
        // Elements totla = documentl.select (여기에서 땡겨올 html쿼리추가 다음 뉴스에서 f12, 인스펙터)
        Elements total = document.select("strong.tit_thumb>a.link_txt");
        Elements image = document.select("div.item_issue>a.link_thumb>img.thumb_g");

        HomeNews homeNews = null;

        for (int i = 0; i < total.size(); i++) {
            homeNews = new HomeNews();
            homeNews.setHomeNewsNo(String.valueOf(i + 1));
            homeNews.setTitle(total.get(i).text());
            homeNews.setAddress(total.get(i).attr("href"));
            homeNews.setImage(image.get(i).attr("src"));
            homeNewsRepository.save(homeNews);
        }
    }
    // 카테고리 파악할 수 있게 크롤
    public void mainCrawler(String category) {
        log.info("mainCrawler(): " + category);

        document = connectUrl("https://news.daum.net/" + category);
        newsRepository.deleteAll();
        // 마지막에는 a관련 태그가 연결됨 - url연결시키는 것
        // 상위  strong.class 하위 div.class  넣어주면됨
        daumNews(document.select("div.item_mainnews>div.cont_thumb>strong.tit_thumb>a"), category);
        daumNews(document.select("ul.item_mainnews>li>div.cont_thumb>strong.tit_thumb>a"), category);
        daumNews(document.select("strong.tit_mainnews>a"), category);
        daumNews(document.select("ul.list_issue>li>a.link_txt"), category);
    }

    public void daumNews(Elements elements, String category) {
        log.info("daumNews(): elements - " + elements + ", category - " + category);

        News news = null;
        // delete했으니까 가져온 정보들을 여기에 다 세팅
        for (int i = 0; i < elements.size(); i++) {
            news = new News();
            // setNewsNo 계속정보들을 add해 나가게
            //  setAddress 링크를 태우고 넘어갈 수 잇게 함
            // 어떤 걸 좋아하는지 알아야 하니까 setTitle
            news.setNewsNo(String.valueOf(newsRepository.findAll().size() + 1));
            news.setAddress(elements.get(i).attr("href"));
            news.setCategory(category);
            news.setTitle(elements.get(i).text());

            newsRepository.save(news);
        }
    }

    public ClickedNews crawlingOne(String newsNo) {
        log.info("crawlingOne(): " + newsNo);
        // 번호를 가지고 정보를 가지고 오게 함
        News news = newsRepository.findByNewsNo(newsNo);

        ClickedNews clickedNews = new ClickedNews();
        // 어떤걸 클릭했는지에 대한 정보를 저장
        clickedNews.setTitle(news.getTitle());
        clickedNews.setCategory(news.getCategory());
        clickedNews.setAddress(news.getAddress());
        clickedNews.setClickedNewsNo(String.valueOf(clickedNewsRepository.findAll().size() + 1));
        // 클릭한 기사에 대한 정보들을 가지고 와라
        document = connectUrl(clickedNews.getAddress());

        clickedNews.setDate(document.select("span.num_date").text());
        clickedNews.setContents(document.select("div.article_view>section>:not(figure)").text());

        clickedNewsRepository.save(clickedNews);

        return clickedNews;
    }
}
