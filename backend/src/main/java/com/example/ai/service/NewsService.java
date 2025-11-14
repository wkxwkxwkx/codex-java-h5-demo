package com.example.ai.service;

import com.example.ai.model.NewsItem;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class NewsService {

    private static final Logger logger = LoggerFactory.getLogger(NewsService.class);
    private final CopyOnWriteArrayList<NewsItem> latestNews = new CopyOnWriteArrayList<>();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public List<NewsItem> getLatestNews() {
        return latestNews;
    }

    @PostConstruct
    public void init() {
        fetchLatest();
    }

    @Scheduled(cron = "0 0 8 * * ?")
    public void scheduledFetch() {
        fetchLatest();
    }

    public synchronized void fetchLatest() {
        logger.info("Fetching latest AI news");
        // TODO: Replace mock data with a real AI news API integration when external network access is available.
        latestNews.clear();
        latestNews.addAll(List.of(
                new NewsItem("AI breakthroughs in healthcare diagnostics", "https://example.com/ai-healthcare", formatter.format(LocalDateTime.now().minusHours(1))),
                new NewsItem("New research on Artificial Intelligence ethics", "https://example.com/ai-ethics", formatter.format(LocalDateTime.now().minusHours(2))),
                new NewsItem("Startups leverage AI for sustainable energy", "https://example.com/ai-energy", formatter.format(LocalDateTime.now().minusHours(3))),
                new NewsItem("AI accelerates drug discovery pipelines", "https://example.com/ai-drug-discovery", formatter.format(LocalDateTime.now().minusHours(4))),
                new NewsItem("Education sector adopts Artificial Intelligence tools", "https://example.com/ai-education", formatter.format(LocalDateTime.now().minusHours(5)))
        ));
        logger.info("Loaded {} AI news items", latestNews.size());
    }
}
