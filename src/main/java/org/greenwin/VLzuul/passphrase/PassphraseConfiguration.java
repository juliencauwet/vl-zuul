package org.greenwin.VLzuul.passphrase;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class PassphraseConfiguration {

    @Value("${ms.passphrase.security}")
    private String passphrase;

    @Value("${ms.passphrase.key}")
    private String key;
}
