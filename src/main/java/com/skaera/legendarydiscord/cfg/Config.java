package com.skaera.legendarydiscord.cfg;


import com.skaera.legendarydiscord.Legendarydiscord;
import info.pixelmon.repack.yaml.snakeyaml.Yaml;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class Config {

    private Yaml yaml;

    public Config() {
        this.yaml = new Yaml();
    }

    public String getString(String key)  {
        try {
            FileInputStream input = new FileInputStream("config/LegendaryDiscord/config.yml");
            InputStreamReader reader = new InputStreamReader(input, StandardCharsets.UTF_8);
            BufferedReader in = new BufferedReader(reader);
            Map<String, Object> map = yaml.load(in);
            in.close();
            reader.close();
            input.close();
            return (String) map.get(key);
        } catch (IOException e) {
            return null;
        }

    }

    public boolean getBool(String key) {
        try {
            FileInputStream input = new FileInputStream("config/LegendaryDiscord/config.yml");
            InputStreamReader reader = new InputStreamReader(input, StandardCharsets.UTF_8);
            BufferedReader in = new BufferedReader(reader);
            Map<String, Object> map = yaml.load(in);
            in.close();
            reader.close();
            input.close();
            return (Boolean) map.get(key);
        } catch (IOException e) {
            return false;
        }
    }

    public static class FileSaver {
        public static void save() {
            File crt = new File("config/LegendaryDiscord/config.yml");
            if(!crt.exists()) {
                try {
                    InputStream is = Legendarydiscord.class.getResourceAsStream("/assets/legendary/config.yml");
                    FileOutputStream fs = new FileOutputStream("config/LegendaryDiscord/config.yml");
                    BufferedWriter fw = new BufferedWriter(new OutputStreamWriter(fs, StandardCharsets.UTF_8));
                    fw.write(IOUtils.toString(is, StandardCharsets.UTF_8));
                    fw.close();
                    fs.close();
                    is.close();
                } catch (IOException e) {
                }
            }
        }
    }

}
