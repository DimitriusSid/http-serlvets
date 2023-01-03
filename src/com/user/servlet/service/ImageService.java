package com.user.servlet.service;

import com.user.servlet.util.PropertiesUtil;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

public class ImageService {

    private static final ImageService INSTANCE = new ImageService();

    private final String basePath = PropertiesUtil.get("image.base-url");

    public void upload(String imagePath, InputStream imageContent) throws IOException {
        var imageFullPath = Path.of(basePath, imagePath);
        try (imageContent) {
            Files.createDirectories(imageFullPath.getParent());
            Files.write(imageFullPath, imageContent.readAllBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        }
    }

    private ImageService() {}

    public static ImageService getInstance() {
        return INSTANCE;
    }

    @SneakyThrows
    public Optional<InputStream> get(String imagePath) {
        var imageFullPath = Path.of(basePath, imagePath);
        return Files.exists(imageFullPath) ? Optional.of(Files.newInputStream(imageFullPath)) : Optional.empty();
    }
}
