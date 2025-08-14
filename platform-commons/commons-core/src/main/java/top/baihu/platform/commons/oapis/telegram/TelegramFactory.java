package top.baihu.platform.commons.oapis.telegram;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import top.baihu.platform.commons.core.http.HttpProxy;
import top.baihu.platform.commons.oapis.telegram.bot.TelegramBotConfig;

/**
 * @author elvea
 */
@Slf4j
public record TelegramFactory(HttpProxy proxy, OkHttpClient client, TelegramConfig config) {

    public TelegramClient getTelegramClient() {
        return this.getTelegramClient(TelegramBotConfig.builder().token(this.config().getToken()).build());
    }

    public TelegramClient getTelegramClient(TelegramBotConfig telegramBotConfig) {
        return new OkHttpTelegramClient(this.client, telegramBotConfig.getToken());
    }

}
