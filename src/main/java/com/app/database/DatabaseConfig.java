package com.app.database;

import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig {

        private static final Properties properties = new Properties();

        static {

                try (
                                InputStream input = DatabaseConfig.class
                                                .getClassLoader()
                                                .getResourceAsStream("application.properties")) {

                        if (input == null) {

                                throw new RuntimeException("application.properties tidak ditemukan");
                        }

                        properties.load(input);

                } catch (Exception e) {

                        throw new RuntimeException("Gagal membaca konfigurasi database", e);
                }
        }

        public static String getUrl() {

                return properties.getProperty("db.url");
        }

        public static String getUsername() {

                return properties.getProperty("db.username");
        }

        public static String getPassword() {

                return properties.getProperty("db.password");
        }
}