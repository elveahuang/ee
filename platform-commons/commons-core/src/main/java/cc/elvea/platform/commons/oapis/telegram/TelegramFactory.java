package cc.elvea.platform.commons.oapis.telegram;

import cc.elvea.platform.commons.core.http.HttpProxy;
import cc.elvea.platform.commons.oapis.telegram.bot.TelegramBotConfig;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.meta.generics.TelegramClient;

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
