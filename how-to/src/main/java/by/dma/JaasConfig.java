package by.dma;

import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;
import javax.security.auth.login.AppConfigurationEntry.LoginModuleControlFlag;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author dzmitry.marudau
 * @since 2022.08
 */
public class JaasConfig extends Configuration {

    public static void main(String[] args) {
        System.out.println("### BAD CONFIG");
        String jaasConfigParams = null;
        try {
            jaasConfigParams = "bad config";
            new JaasConfig("context", jaasConfigParams);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("### GOOD CONFIG");
        try {
            jaasConfigParams = """
org.apache.kafka.common.security.plain.PlainLoginModule required username="$ConnectionString" password="Endpoint=sb://h3-msd-eventhub-fqa.servicebus.windows.net/;SharedAccessKeyName=consumer;SharedAccessKey=7jAOQCR1M4pGldLnEgIAkFYYkgcXVAaMaDExX5rlsDc=;EntityPath=msd-consent-outbound";                    
                    """;
            new JaasConfig("context", jaasConfigParams);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private final String loginContextName;
    private final List<AppConfigurationEntry> configEntries;

    public JaasConfig(String loginContextName, String jaasConfigParams) {
        StreamTokenizer tokenizer = new StreamTokenizer(new StringReader(jaasConfigParams));
        tokenizer.slashSlashComments(true);
        tokenizer.slashStarComments(true);
        tokenizer.wordChars('-', '-');
        tokenizer.wordChars('_', '_');
        tokenizer.wordChars('$', '$');

        try {
            configEntries = new ArrayList<>();
            while (tokenizer.nextToken() != StreamTokenizer.TT_EOF) {
                configEntries.add(parseAppConfigurationEntry(tokenizer));
            }
            if (configEntries.isEmpty()) {
                throw new IllegalArgumentException("Login module not specified in JAAS config");
            } else {
                configEntries.forEach(entry -> System.out.println(
                        String.format("%s: %s", entry.getLoginModuleName(), entry.getControlFlag())));
            }

            this.loginContextName = loginContextName;

        } catch (IOException e) {
            throw new RuntimeException("Unexpected exception while parsing JAAS config");
        }
    }

    @Override
    public AppConfigurationEntry[] getAppConfigurationEntry(String name) {
        if (this.loginContextName.equals(name)) {return configEntries.toArray(new AppConfigurationEntry[0]);} else {
            return null;
        }
    }

    private LoginModuleControlFlag loginModuleControlFlag(String flag) {
        if (flag == null) {
            throw new IllegalArgumentException("Login module control flag is not available in the JAAS config");
        }

        LoginModuleControlFlag controlFlag;
        switch (flag.toUpperCase(Locale.ROOT)) {
            case "REQUIRED":
                controlFlag = LoginModuleControlFlag.REQUIRED;
                break;
            case "REQUISITE":
                controlFlag = LoginModuleControlFlag.REQUISITE;
                break;
            case "SUFFICIENT":
                controlFlag = LoginModuleControlFlag.SUFFICIENT;
                break;
            case "OPTIONAL":
                controlFlag = LoginModuleControlFlag.OPTIONAL;
                break;
            default:
                throw new IllegalArgumentException("Invalid login module control flag '" + flag + "' in JAAS config");
        }
        return controlFlag;
    }

    private AppConfigurationEntry parseAppConfigurationEntry(StreamTokenizer tokenizer) throws IOException {
        String loginModule = tokenizer.sval;
        if (tokenizer.nextToken() == StreamTokenizer.TT_EOF) {
            throw new IllegalArgumentException("Login module control flag not specified in JAAS config");
        }
        AppConfigurationEntry.LoginModuleControlFlag controlFlag = loginModuleControlFlag(tokenizer.sval);
        Map<String, String> options = new HashMap<>();
        while (tokenizer.nextToken() != StreamTokenizer.TT_EOF && tokenizer.ttype != ';') {
            String key = tokenizer.sval;
            if (tokenizer.nextToken() != '=' || tokenizer.nextToken() == StreamTokenizer.TT_EOF
                || tokenizer.sval == null) {
                throw new IllegalArgumentException("Value not specified for key '" + key + "' in JAAS config");
            }
            String value = tokenizer.sval;
            options.put(key, value);
        }
        if (tokenizer.ttype != ';') {
            throw new IllegalArgumentException("JAAS config entry not terminated by semi-colon");
        }
        return new AppConfigurationEntry(loginModule, controlFlag, options);
    }
}
