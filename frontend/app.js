const API_BASE = "https://<你的后端线上域名>/api/news";
// const API_BASE = "http://localhost:8080/api/news";

const newsListEl = document.getElementById("news-list");
const errorEl = document.getElementById("error");
const refreshBtn = document.getElementById("refresh-btn");

async function fetchNews() {
    toggleError(false);
    newsListEl.innerHTML = "<p class='loading'>加载中...</p>";

    try {
        const response = await fetch(API_BASE, { cache: "no-store" });
        if (!response.ok) {
            throw new Error(`请求失败：${response.status}`);
        }
        const data = await response.json();
        renderNews(data);
    } catch (error) {
        console.error(error);
        newsListEl.innerHTML = "";
        toggleError(true, "获取新闻失败，请稍后重试。");
    }
}

function renderNews(items) {
    if (!Array.isArray(items) || items.length === 0) {
        newsListEl.innerHTML = "<p>暂无最新资讯，稍后再来看看吧。</p>";
        return;
    }

    newsListEl.innerHTML = items.map(item => `
        <article class="news-card">
            <h2>${item.title}</h2>
            <time>${item.time}</time>
            <a href="${item.url}" target="_blank" rel="noopener noreferrer">查看原文</a>
        </article>
    `).join("");
}

function toggleError(show, message = "") {
    if (show) {
        errorEl.textContent = message;
        errorEl.classList.remove("hidden");
    } else {
        errorEl.textContent = "";
        errorEl.classList.add("hidden");
    }
}

refreshBtn.addEventListener("click", fetchNews);

window.addEventListener("DOMContentLoaded", fetchNews);
